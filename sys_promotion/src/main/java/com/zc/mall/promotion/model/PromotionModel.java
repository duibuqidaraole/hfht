package com.zc.mall.promotion.model;

import com.zc.mall.core.common.global.BeanUtil;
import com.zc.mall.core.manage.dao.OperatorDao;
import com.zc.mall.core.manage.entity.Operator;
import com.zc.mall.core.manage.entity.OrderTask;
import com.zc.mall.core.manage.model.OperatorModel;
import com.zc.mall.core.user.dao.UserDao;
import com.zc.mall.core.user.entity.User;
import com.zc.mall.core.user.model.UserInfoModel;
import com.zc.mall.core.user.model.UserModel;
import com.zc.mall.promotion.dao.PromotionDao;
import com.zc.mall.promotion.entity.Promotion;
import com.zc.mall.promotion.service.PromotionService;
import com.zc.sys.common.exception.BusinessException;
import com.zc.sys.common.model.jpa.Page;
import com.zc.sys.common.util.date.DateUtil;
import com.zc.sys.common.util.log.LogUtil;
import com.zc.sys.common.util.validate.StringUtil;
import org.springframework.beans.BeanUtils;

import java.util.Date;

/**
 * 活动推广
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
public class PromotionModel extends Promotion {
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
    /**
     * 用户
     **/
    private User user;
    /**
     * 开始时间
     **/
    private Date startTime;
    /**
     * 结束时间
     **/
    private Date endTime;
    /**
     * 订单信息
     **/
    private OrderTask orderTask;
    /**
     * 添加管理员
     **/
    private long operatorId;
    /**
     * 商家管理员id
     **/
    private long relationId;
    /**
     * 活动状况 1表示进行中   -2已过期  -1即将开始
     **/
    private int promotionState;
    /**
     * 用户Id
     **/
    private long userId;
    /**
     * 平台添加管理员Model
     **/
    private OperatorModel operatorModel;
    /**
     * 商家关联用户Model
     **/
    private UserModel beUserModel;
    /**
     * 商家关联用户信息Model
     **/
    private UserInfoModel beUserInfoModel;
    /**
     * 真实金额
     **/
    private double realAmount;
    /**
     * vip金额
     **/
    private double vipAmount;

    public PromotionModel() {
        super();
    }

    /**
     * @param way
     * @param user
     */
    public PromotionModel(Integer way, User user) {
        this.setWay(way);
        this.setUser(user);
    }

    /**
     * 实体转换model
     */
    public static PromotionModel instance(Promotion promotion) {
        if (promotion == null || promotion.getId() <= 0) {
            return null;
        }
        PromotionModel promotionModel = new PromotionModel();
        BeanUtils.copyProperties(promotion, promotionModel);
        return promotionModel;
    }

    /**
     * model转换实体
     */
    public Promotion prototype() {
        Promotion promotion = new Promotion();
        BeanUtils.copyProperties(this, promotion);
        return promotion;
    }

    /**
     * 校验参数
     */
    public void validAdd() {
        if (StringUtil.isBlank(this.getName())) {
            throw new BusinessException("标题不能为空！");
        }
        if (this.getWay() <= 0) {
            throw new BusinessException("参数错误！");
        }
        if (StringUtil.isBlank(this.getContent())) {
            throw new BusinessException("内容不能为空！");
        }
        if (StringUtil.isBlank(this.getUrl())) {
            throw new BusinessException("活动链接不能为空！");
        }
        if (StringUtil.isBlank(this.getPic())) {
            throw new BusinessException("活动图片地址不能为空！");
        }
        if (this.getStartTime() == null) {
            throw new BusinessException("开始时间不能为空！");
        }
        if (this.getEndTime() == null) {
            throw new BusinessException("结束时间不能为空！");
        }
        if (DateUtil.msBetween(this.getEndTime(), this.getStartTime()) < 0) {
            throw new BusinessException("活动开始时间不能大于结束时间！");
        }
    }

    /**
     * 初始化添加
     */
    public void initAdd() {
        OperatorDao operatorDao = BeanUtil.getBean(OperatorDao.class);
        Operator operator = null;
        if (this.getOperatorId() > 0) {
            operator = operatorDao.find(this.getOperatorId());
            this.setOperator(operator);
        } else {
            operator = operatorDao.find(this.getRelationId());
            this.setBeUser(operator.getUser());
        }

        this.setAddTime(DateUtil.getNow());
    }


    /**
     * 校验修改参数
     *
     * @return
     */
    public Promotion validUpdate() {
        if (this.getId() <= 0) {
            throw new BusinessException("参数错误！");
        }
        if (StringUtil.isBlank(this.getName())) {
            throw new BusinessException("标题不能为空！");
        }
        if (this.getWay() <= 0) {
            throw new BusinessException("参数错误！");
        }
        if (StringUtil.isBlank(this.getContent())) {
            throw new BusinessException("内容不能为空！");
        }
        if (this.getStartTime() == null) {
            throw new BusinessException("开始时间不能为空！");
        }
        if (this.getEndTime() == null) {
            throw new BusinessException("结束时间不能为空！");
        }
        if (StringUtil.isBlank(this.getUrl())) {
            throw new BusinessException("内容不能链接！");
        }
        if (StringUtil.isBlank(this.getPic())) {
            throw new BusinessException("内容不能活动图片地址！");
        }
        PromotionDao promotionDao = BeanUtil.getBean(PromotionDao.class);
        Promotion promotion = promotionDao.find(this.getId());
        if (promotion == null) {
            throw new BusinessException("参数错误！");
        }
        return promotion;
    }

    /**
     * 初始化修改
     *
     * @param promotion
     */
    public void initUpdate(Promotion promotion) {
        OperatorDao operatorDao = BeanUtil.getBean(OperatorDao.class);
        Operator operator = null;
        if (this.getOperatorId() > 0) {
            operator = operatorDao.find(this.getOperatorId());
            this.setOperator(operator);
        } else {
            operator = operatorDao.find(this.getRelationId());
            this.setBeUser(operator.getUser());
        }
        promotion.setContent(this.getContent());
        promotion.setEndTime(this.getEndTime());
        promotion.setName(this.getName());
        promotion.setRemark(this.getRemark());
        promotion.setStartTime(this.getStartTime());
        promotion.setSummary(this.getSummary());
        promotion.setState(this.getState());
        promotion.setWay(this.getWay());
        promotion.setPic(this.getPic());
        promotion.setUrl(this.getUrl());
        promotion.setAddTime(promotion.getAddTime());
        promotion.setOperator(promotion.getOperator());

    }

    /**
     * 参数校验-活动处理
     */
    public void validParam() {
        if (this.getWay() <= 0) {
            throw new BusinessException("活动方式参数有误");
        }
        if (this.getUser() == null || this.getUser().getId() <= 0) {
            throw new BusinessException("活动参与用户有误");
        }
        UserDao userDao = BeanUtil.getBean(UserDao.class);
        User user = userDao.find(this.getUser().getId());
        if (user == null) {
            throw new BusinessException("活动参与用户有误");
        }
        this.setUser(user);
    }

    /**
     * 初始化参数
     */
    public void initParam() {

    }

    /**
     * 处理队列
     */
    public void doQueue() {
        PromotionService promotionService = BeanUtil.getBean(PromotionService.class);
        try {
            promotionService.handlePromotion(this);
        } catch (Exception e) {
            LogUtil.info("活动处理失败...." + this.getUser().getId() + "-" + this.getWay());
            LogUtil.info(e.getMessage());
        }
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
     * 设置【 用户Id】
     **/
    public void setUserId(long userId) {
        this.userId = userId;
    }

    /**
     * 获取【 用户Id】
     **/
    public long getUserId() {
        return userId;
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

    /**
     * 获取【用户】
     **/
    public User getUser() {
        return user;
    }

    /**
     * 设置【用户】
     **/
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * 获取【订单信息】
     **/
    public OrderTask getOrderTask() {
        return orderTask;
    }

    /**
     * 设置【订单信息】
     **/
    public void setOrderTask(OrderTask orderTask) {
        this.orderTask = orderTask;
    }

    /**
     * 获取【开始时间】
     **/
    public Date getStartTime() {
        return startTime;
    }


    /**
     * 设置【开始时间】
     **/
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }


    /**
     * 获取【结束时间】
     **/
    public Date getEndTime() {
        return endTime;
    }


    /**
     * 设置【结束时间】
     **/
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * 获取【添加管理员】
     **/
    public long getOperatorId() {
        return operatorId;
    }


    /**
     * 设置【添加管理员】
     **/
    public void setOperatorId(long operatorId) {
        this.operatorId = operatorId;
    }

    /**
     * 获取【商家管理员id】
     **/
    public long getRelationId() {
        return relationId;
    }

    /**
     * 设置【商家管理员id】
     **/
    public void setRelationId(long relationId) {
        this.relationId = relationId;
    }

    public OperatorModel getOperatorModel() {
        return operatorModel;
    }

    public void setOperatorModel(OperatorModel operatorModel) {
        this.operatorModel = operatorModel;
    }

    public UserModel getBeUserModel() {
        return beUserModel;
    }

    public void setBeUserModel(UserModel beUserModel) {
        this.beUserModel = beUserModel;
    }

    public int getPromotionState() {
        return promotionState;
    }

    public void setPromotionState(int promotionState) {
        this.promotionState = promotionState;
    }

    /**
     * 获取【 商家关联用户信息Model 】
     **/
    public UserInfoModel getBeUserInfoModel() {
        return beUserInfoModel;
    }

    /**
     * 设置【 商家关联用户信息Model 】
     **/
    public void setBeUserInfoModel(UserInfoModel beUserInfoModel) {
        this.beUserInfoModel = beUserInfoModel;
    }

    public double getRealAmount() {
        return realAmount;
    }

    public void setRealAmount(double realAmount) {
        this.realAmount = realAmount;
    }

    public double getVipAmount() {
        return vipAmount;
    }

    public void setVipAmount(double vipAmount) {
        this.vipAmount = vipAmount;
    }
}