package com.zc.mall.promotion.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.zc.mall.core.common.constant.BaseConstant;
import com.zc.mall.core.common.executer.Executer;
import com.zc.mall.core.common.global.BeanUtil;
import com.zc.mall.core.manage.entity.OrderTask;
import com.zc.mall.core.user.dao.UserDao;
import com.zc.mall.core.user.entity.User;
import com.zc.mall.core.user.model.UserModel;
import com.zc.mall.promotion.dao.UserVipCouponsDao;
import com.zc.mall.promotion.dao.VipCouponsDao;
import com.zc.mall.promotion.entity.UserVipCoupons;
import com.zc.mall.promotion.entity.VipCoupons;
import com.zc.mall.promotion.executer.VipCouponsExecuter;
import com.zc.mall.promotion.service.UserVipCouponsService;
import com.zc.sys.common.exception.BusinessException;
import com.zc.sys.common.model.jpa.Page;
import com.zc.sys.common.model.jpa.PageDataList;
import com.zc.sys.common.util.date.DateUtil;
import com.zc.sys.common.util.validate.StringUtil;
import org.springframework.beans.BeanUtils;

import java.util.Date;

/**
 * 用户vip会员卡
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年11月15日
 */
public class UserVipCouponsModel extends UserVipCoupons {
    /**
     * 序列号
     **/
    private static final long serialVersionUID = 1L;

    /**
     * 当前页码
     **/
    private int pageNo;
    /**
     * 每页数据条数
     **/
    private int pageSize = Page.ROWS;
    /**
     * 条件查询
     **/
    private String searchName;

    private OrderTask orderTask;

    private String prizeNo;

    /**
     * 添加管理员
     **/
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private UserModel userModel;
    /**
     * 添加管理员
     **/
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private VipCouponsModel vipCouponsModel;

    public UserVipCouponsModel(String prizeNo, User user) {
        this.setPrizeNo(prizeNo);
        this.setUser(user);
    }

    public UserVipCouponsModel() {
        super();
    }

    /**
     * 实体转换model
     */
    public static UserVipCouponsModel instance(UserVipCoupons userVipCoupons) {
        if (userVipCoupons == null || userVipCoupons.getId() <= 0) {
            return null;
        }
        UserVipCouponsModel userVipCouponsModel = new UserVipCouponsModel();
        BeanUtils.copyProperties(userVipCoupons, userVipCouponsModel);
        return userVipCouponsModel;
    }

    /**
     * model转换实体
     */
    public UserVipCoupons prototype() {
        UserVipCoupons userVipCoupons = new UserVipCoupons();
        BeanUtils.copyProperties(this, userVipCoupons);
        return userVipCoupons;
    }

    /**
     * 校验发放请求
     */
    public void checkGiveOutRequest() {
        if (this.getUser() == null || this.getUser().getId() <= 0) {
            throw new BusinessException("参数错误");
        }
        if (this.getVipCoupons() == null || this.getVipCoupons().getId() <= 0) {
            throw new BusinessException("参数错误");
        }
        VipCouponsDao vipCouponsDao = BeanUtil.getBean(VipCouponsDao.class);
        VipCoupons vipCoupons = null;
        if (this.getVipCoupons() != null && this.getVipCoupons().getId() > 0) {
            vipCoupons = vipCouponsDao.find(this.getVipCoupons().getId());
            if (vipCoupons == null) {
                throw new BusinessException("奖励券不存在");
            }
        } else if (this.getVipCoupons() != null && StringUtil.isNotBlank(this.getVipCoupons().getPrizeNo())) {
            vipCoupons = vipCouponsDao.findByPrizeNo(this.getVipCoupons().getPrizeNo());
            if (vipCoupons == null) {
                throw new BusinessException("奖励券不存在");
            }
        } else {
            throw new BusinessException("奖励券参数有误");
        }

        UserDao userDao = BeanUtil.getBean(UserDao.class);
        User user = userDao.find(this.getUser().getId());
        if (user == null) {
            throw new BusinessException("发放用户不存在");
        }
        checkInUsed();
        this.setVipCoupons(vipCoupons);
    }

    /**
     * 检验vip是否已经发放过
     */
    private void checkInUsed() {
        UserVipCouponsDao userVipCouponsDao = BeanUtil.getBean(UserVipCouponsDao.class);
        PageDataList<UserVipCoupons> list = userVipCouponsDao.checkInUsed(this);
        if (list != null && list.getList().size() > 0) {
            //该用户已拥有此vip
            this.setState(BaseConstant.BUSINESS_STATE_NO);
        }
    }

    /**
     * 初始化发放请求
     */
    public void initGiveOutRequest() {
        VipCouponsDao vipCouponsDao = BeanUtil.getBean(VipCouponsDao.class);
        VipCoupons vipCoupons = vipCouponsDao.find(this.getVipCoupons().getId());
        Date nowTime = DateUtil.getNow();
        Date endTime = null;
        if (StringUtil.isNotBlank(vipCoupons.getValidityValue())) {
            endTime = calculateEndTime(vipCoupons.getValidityType(), DateUtil.getNow(), vipCoupons.getValidityValue());
            // 活动已到期
            if (DateUtil.msBetween(endTime, nowTime) <= 0) {
                throw new BusinessException("活动奖励不在期限内");
            }
            this.setEndTime(endTime);

        }
        UserDao userDao = BeanUtil.getBean(UserDao.class);
        User user = userDao.find(this.getUser().getId());
        this.setUser(user);
        this.setVipCoupons(vipCoupons);
        this.setAddTime(nowTime);
        this.setBeginTime(nowTime);
        // 处理中
        if (this.getState() != BaseConstant.BUSINESS_STATE_NO) {
            this.setState(BaseConstant.BUSINESS_STATE_WAIT);
        }
    }

    /**
     * 计算结束时间
     *
     * @param validityType
     * @param startTime
     * @param value
     * @return
     */
    private Date calculateEndTime(int validityType, Date startTime, String value) {
        Date endTime = null;
        switch (validityType) {
            // 天数
            case BaseConstant.VALIDITY_TYPE_DAY:
                endTime = DateUtil.rollDay(startTime, Integer.parseInt(value));
                break;
            // 时间
            case BaseConstant.VALIDITY_TYPE_TIME:
                endTime = DateUtil.valueOf(value);
                break;
            default:
                throw new BusinessException("参数错误");
        }
        return endTime;
    }

    /**
     * 初始化发放
     *
     * @param userVipCoupons
     */
    public void initGiveOut(UserVipCoupons userVipCoupons) {
        VipCouponsDao vipCouponsDao = BeanUtil.getBean(VipCouponsDao.class);
        VipCoupons vipCoupons = vipCouponsDao.find(userVipCoupons.getVipCoupons().getId());
        // vip发放
        if (this.getState() != BaseConstant.BUSINESS_STATE_NO) {
            userVipCoupons.setState(BaseConstant.BUSINESS_STATE_YES);// 已使用
            vipCoupons.setUseQuota(vipCoupons.getUseQuota() + 1);
        } else {
            userVipCoupons.setState(this.getState());
        }
        Executer tenderDealVipCouponsAmount = BeanUtil.getBean(VipCouponsExecuter.class);
        VipCouponsModel vipCouponsModel = VipCouponsModel.instance(vipCoupons);
        vipCouponsModel.setState(userVipCoupons.getState());
        tenderDealVipCouponsAmount.execute(vipCouponsModel, vipCoupons.getPrince(),
                userVipCoupons.getUser());
        userVipCoupons.setState(vipCouponsModel.getState());
        vipCoupons.setUseQuota(vipCouponsModel.getUseQuota());

    }


    /**
     * 获取【当前页码】
     **/
    public int getPageNo() {
        return pageNo;
    }

    /**
     * 设置【当前页码】
     **/
    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    /**
     * 获取【每页数据条数】
     **/
    public int getPageSize() {
        return pageSize;
    }

    /**
     * 设置【每页数据条数】
     **/
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * 获取【条件查询】
     **/
    public String getSearchName() {
        return searchName;
    }

    /**
     * 设置【条件查询】
     **/
    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    public VipCouponsModel getVipCouponsModel() {
        return vipCouponsModel;
    }

    public void setVipCouponsModel(VipCouponsModel vipCouponsModel) {
        this.vipCouponsModel = vipCouponsModel;
    }

    public OrderTask getOrderTask() {
        return orderTask;
    }

    public void setOrderTask(OrderTask orderTask) {
        this.orderTask = orderTask;
    }

    public void doQueue() {
        UserVipCouponsService userVipCouponsService = BeanUtil.getBean(UserVipCouponsService.class);
        String type = this.getOrderTask().getType();
        if (BaseConstant.OrderNid.ORDER_NID_USER_PROMOTION_GIVE_OUT_USER_VIP_COUPONS.getNid().equals(type)) {
            userVipCouponsService.giveOut(this);
        }
    }

    public String getPrizeNo() {
        return prizeNo;
    }

    public void setPrizeNo(String prizeNo) {
        this.prizeNo = prizeNo;
    }
}