package com.zc.mall.promotion.model;

import com.zc.mall.core.common.constant.BaseConstant;
import com.zc.mall.core.common.constant.BaseConstant.OrderNid;
import com.zc.mall.core.common.executer.Executer;
import com.zc.mall.core.common.global.BeanUtil;
import com.zc.mall.core.manage.dao.OperatorDao;
import com.zc.mall.core.manage.entity.OrderTask;
import com.zc.mall.core.manage.model.OperatorModel;
import com.zc.mall.core.user.dao.UserDao;
import com.zc.mall.core.user.entity.User;
import com.zc.mall.core.user.model.UserInfoModel;
import com.zc.mall.core.user.model.UserModel;
import com.zc.mall.promotion.dao.BonusCouponsDao;
import com.zc.mall.promotion.entity.BonusCoupons;
import com.zc.mall.promotion.entity.BonusCouponsRecord;
import com.zc.mall.promotion.entity.PromotionPrizeRecord;
import com.zc.mall.promotion.executer.BonusCouponsExecuter;
import com.zc.mall.promotion.service.BonusCouponsRecordService;
import com.zc.sys.common.exception.BusinessException;
import com.zc.sys.common.model.jpa.Page;
import com.zc.sys.common.util.date.DateUtil;
import com.zc.sys.common.util.validate.StringUtil;
import org.springframework.beans.BeanUtils;

import java.util.Date;

/**
 * 红包发放记录
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年12月20日
 */
public class BonusCouponsRecordModel extends BonusCouponsRecord {
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
     * BonusCouponsModel
     **/
    private BonusCouponsModel bonusCouponsModel;
    /**
     * 平台添加管理员Model
     **/
    private OperatorModel operatorModel;
    /**
     * UserModel
     **/
    private UserModel userModel;
    /**
     * 商家关联用户Model
     **/
    private UserModel beUserModel;
    /**
     * 商家关联用户信息Model
     **/
    private UserInfoModel beUserInfoModel;
    /**
     * 用户id
     **/
    private long userId;
    /**
     * 商家管理员id
     **/
    private long relationId;
    /**
     * 添加管理员
     **/
    private long operatorId;
    /**
     * 使用限制最小投资额度
     **/
    private double useSingleInvestAmount;
    /**
     * 开始时间
     **/
    private String addTimeStartTime;
    /**
     * 结束时间
     **/
    private String addTimeEndTime;
    /**
     * 推广奖励记录
     **/
    private PromotionPrizeRecord prizeRecord;
    /**
     * 券编号
     **/
    private String prizeNo;
    /**
     * 红包类型
     **/
    private int type;
    /**
     * 订单信息
     **/
    private OrderTask orderTask;
    /**
     * 查询用 多个红包id
     **/
    private String bounsIds;
    /**
     * 最低金额
     **/
    private Double lessAmount;


    public BonusCouponsRecordModel() {
        super();
    }

    /**
     * 使用红包封装
     *
     * @param id      红包id
     * @param useType 使用类型
     * @param useId   关联id
     * @param remark  备注
     */
    public BonusCouponsRecordModel(long id, int useType, String useId, String remark) {
        this.setId(id);
        this.setUseType(useType);
        this.setUseId(useId);
        this.setRemark(remark);
    }

    /**
     * 使用红包封装
     *
     * @param useType 使用类型
     * @param useId   关联id
     * @param state   状态
     */
    public BonusCouponsRecordModel(int useType, String useId, int state) {
        this.setUseType(useType);
        this.setUseId(useId);
        this.setState(state);
    }

    /**
     * 使用红包
     *
     * @param id      红包id
     * @param useType 使用类型
     * @param useId   关联id
     * @param remark  备注
     */
    public static void useBonusCoupons(long id, int useType, String useId, String remark) {
        BonusCouponsRecordModel model = new BonusCouponsRecordModel(id, useType, useId, remark);
        BonusCouponsRecordService bonusCouponsRecordService = BeanUtil.getBean(BonusCouponsRecordService.class);
        bonusCouponsRecordService.doUse(model);
    }

    /**
     * 撤回使用红包
     *
     * @param userId  用户
     * @param useType 使用类型
     * @param useId   使用关联id
     */
    public static void cancelUseBonusCoupons(long userId, int useType, String useId) {
        BonusCouponsRecordModel model = new BonusCouponsRecordModel(userId, useType, useId,
                BaseConstant.BUSINESS_STATE_YES);
        BonusCouponsRecordService bonusCouponsRecordService = BeanUtil.getBean(BonusCouponsRecordService.class);
        bonusCouponsRecordService.doCancelUse(model);
    }

    public BonusCouponsRecordModel(long bonusCouponsRecordId, long userId) {
        this.setId(bonusCouponsRecordId);
        this.setUser(new User(userId));
    }

    public BonusCouponsRecordModel(String prizeNo, User user) {
        this.setPrizeNo(prizeNo);
        this.setUser(user);
        this.setUserId(user.getId());
    }

    /**
     * 撤回使用红包
     *
     * @param userId  用户
     * @param useType 使用类型
     * @param useId   使用关联id
     */
    public BonusCouponsRecordModel(long userId, int useType, String useId, int state) {
        this.setUser(new User(userId));
        this.setUseType(useType);
        this.setUseId(useId);
        this.setState(state);
    }

    /**
     * 实体转换model
     */
    public static BonusCouponsRecordModel instance(BonusCouponsRecord bonusCouponsRecord) {
        if (bonusCouponsRecord == null || bonusCouponsRecord.getId() <= 0) {
            return null;
        }
        BonusCouponsRecordModel bonusCouponsRecordModel = new BonusCouponsRecordModel();
        BeanUtils.copyProperties(bonusCouponsRecord, bonusCouponsRecordModel);
        return bonusCouponsRecordModel;
    }

    /**
     * model转换实体
     */
    public BonusCouponsRecord prototype() {
        BonusCouponsRecord bonusCouponsRecord = new BonusCouponsRecord();
        BeanUtils.copyProperties(this, bonusCouponsRecord);
        return bonusCouponsRecord;
    }

    /**
     * 发放奖励请求
     *
     * @param prizeRecord
     */
    public static void giveOutRequest(PromotionPrizeRecordModel prizeRecord) {
        BonusCouponsRecordService bonusCouponsRecordService = BeanUtil.getBean(BonusCouponsRecordService.class);
        BonusCouponsRecordModel model = new BonusCouponsRecordModel(prizeRecord.getPromotionPrizeConfig().getPrizeNo(),
                prizeRecord.getUser());
        if (prizeRecord.getPromotionModel() != null) {
            model.setVipAmount(prizeRecord.getPromotionModel().getVipAmount());
            model.setRealAmount(prizeRecord.getPromotionModel().getRealAmount());
        }
        bonusCouponsRecordService.giveOutRequest(model);
    }


    /**
     * 校验发放请求
     */
    public void checkGiveOutRequest() {
        if (this.getUserId() <= 0) {
            throw new BusinessException("参数错误");
        }
        if (StringUtil.isBlank(this.getPrizeNo())) {
            throw new BusinessException("参数错误");
        }
        BonusCouponsDao bonusCouponsDao = BeanUtil.getBean(BonusCouponsDao.class);
        BonusCoupons bonusCoupons = bonusCouponsDao.getByCouponsNo(this.prizeNo);
        if (bonusCoupons == null) {
            throw new BusinessException("奖励券不存在");
        }
        if (DateUtil.daysBetween(bonusCoupons.getEndTime(), DateUtil.getNow()) > 0) {
            throw new BusinessException("优惠券已过期");
        }
        if (bonusCoupons.getQuota() - bonusCoupons.getUseQuota() <= 0) {
            throw new BusinessException("红包库存不足");
        }
        this.setBonusCoupons(bonusCoupons);
    }

    /**
     * 初始化发放请求
     */
    public void initGiveOutRequest() {
        BonusCouponsDao bonusCouponsDao = BeanUtil.getBean(BonusCouponsDao.class);
        BonusCoupons bonusCoupons = bonusCouponsDao.getByCouponsNo(this.prizeNo);
        Date nowTime = DateUtil.getNow();
        Date endTime = getEndTime(bonusCoupons.getValidityType(), DateUtil.getNow(), bonusCoupons.getValidityValue());
        // 活动已到期
        if (DateUtil.msBetween(endTime, nowTime) <= 0) {
            throw new BusinessException("活动奖励不在期限内");
        }
        OperatorDao operatorDao = BeanUtil.getBean(OperatorDao.class);
        UserDao userDao = BeanUtil.getBean(UserDao.class);
        if (this.getOperatorId() > 0 && this.getRelationId() > 0) {
            this.setOperator(operatorDao.find(this.getOperatorId()));
            this.setBeUser(userDao.find(this.getRelationId()));
        } else {
            if (this.getBonusCoupons().getBeUser() != null) {
                this.setBeUser(this.getBonusCoupons().getBeUser());
            }
            this.setOperator(operatorDao.find(this.getOperatorId()));
        }
        User user = userDao.find(this.getUserId());
        this.setUser(user);
        this.setBonusCoupons(bonusCoupons);
        this.setAddTime(nowTime);
        this.setStartTime(nowTime);
        this.setEndTime(endTime);
        this.setState(BaseConstant.BUSINESS_STATE_WAIT);// 处理中
        if (this.getVipAmount() > 0) {
            //this.setVipAmount();
        }
    }

    /**
     * 初始化发放
     *
     * @param bonusCouponsRecord
     */
    public void initGiveOut(BonusCouponsRecord bonusCouponsRecord) {
        BonusCouponsDao bonusCouponsDao = BeanUtil.getBean(BonusCouponsDao.class);
        int bonusCouponsBalance = bonusCouponsDao.findBonusCouponsBalance(bonusCouponsRecord.getBonusCoupons().getId());
        if (bonusCouponsBalance <= 0) {
            throw new BusinessException("红包库存不足");
        }
        BonusCoupons bonusCoupons = bonusCouponsDao.find(bonusCouponsRecord.getBonusCoupons().getId());
        // 现金红包发放
        if (bonusCoupons.getType() == BaseConstant.BUSINESS_STATE_WAIT) {
            bonusCouponsRecord.setState(BaseConstant.BUSINESS_STATE_YES);// 已使用
            //添加日志
            Executer tenderDealBonusCouponsAmount = BeanUtil.getBean(BonusCouponsExecuter.class);
            tenderDealBonusCouponsAmount.execute(BonusCouponsModel.instance(bonusCoupons), bonusCouponsRecord.getRealAmount() == 0 ? bonusCoupons.getAmount() : bonusCouponsRecord.getRealAmount(),
                    bonusCouponsRecord.getUser());
        } else {
            bonusCouponsRecord.setState(BaseConstant.BUSINESS_STATE_NO);// 未使用
        }
        bonusCoupons.setUseQuota(bonusCoupons.getUseQuota() + 1);
        if (this.getRealAmount() == 0) {
            bonusCouponsRecord.setRealAmount(bonusCoupons.getAmount());
        } else {
            bonusCouponsRecord.setRealAmount(this.getRealAmount());
            bonusCouponsRecord.setVipAmount(this.getVipAmount());
        }

    }

    /**
     * 获取奖励结束时间
     *
     * @param validityType
     * @param startTime
     * @param value
     * @return
     */
    private Date getEndTime(int validityType, Date startTime, String value) {
        Date endTime = null;
        switch (validityType) {
            case BaseConstant.VALIDITY_TYPE_DAY:// 天数
                endTime = DateUtil.rollDay(startTime, Integer.parseInt(value));
                break;
            case BaseConstant.VALIDITY_TYPE_TIME:// 时间
                endTime = DateUtil.valueOf(value);
                break;
            default:
                throw new BusinessException("参数错误");
        }
        return endTime;
    }

    /**
     * 使用初始化
     *
     * @param bonusCouponsRecord
     */
    public void initUse(BonusCouponsRecord bonusCouponsRecord) {
        Date nowTime = DateUtil.getNow();
        bonusCouponsRecord.setState(BaseConstant.BUSINESS_STATE_YES);
        bonusCouponsRecord.setUseTime(nowTime);
        bonusCouponsRecord.setUseType(this.getUseType());
        bonusCouponsRecord.setUseId(this.getUseId());
        bonusCouponsRecord.setRemark(this.getRemark());
    }

    /**
     * 初始化撤回信息
     *
     * @param bonusCouponsRecord
     */
    public void initCancelUse(BonusCouponsRecord bonusCouponsRecord) {
        bonusCouponsRecord.setState(BaseConstant.BUSINESS_STATE_NO);
        //int betweenDay = DateUtil.daysBetween(DateUtil.getNow(), bonusCouponsRecord.getUseTime());
        //bonusCouponsRecord.setEndTime(DateUtil.rollDay(bonusCouponsRecord.getEndTime(), betweenDay + 1));// 有效时间延后
        bonusCouponsRecord.setUseTime(null);
        bonusCouponsRecord.setUseType(0);
        bonusCouponsRecord.setUseId(null);
    }

    /**
     * 队列
     */
    public void doQueue() {
        BonusCouponsRecordService bonusCouponsRecordService = BeanUtil.getBean(BonusCouponsRecordService.class);
        String type = this.getOrderTask().getType();
        if (OrderNid.ORDER_NID_USER_PROMOTION_GIVE_OUT_BONUS_COUPONS.getNid().equals(type)) {
            bonusCouponsRecordService.giveOut(this);
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
     * 获取【bonusCouponsModel】
     **/
    public BonusCouponsModel getBonusCouponsModel() {
        return bonusCouponsModel;
    }

    /**
     * 设置【bonusCouponsModel】
     **/
    public void setBonusCouponsModel(BonusCouponsModel bonusCouponsModel) {
        this.bonusCouponsModel = bonusCouponsModel;
    }

    /**
     * 获取【operatorModel】
     **/
    public OperatorModel getOperatorModel() {
        return operatorModel;
    }

    /**
     * 设置【operatorModel】
     **/
    public void setOperatorModel(OperatorModel operatorModel) {
        this.operatorModel = operatorModel;
    }

    /**
     * 获取【userModel】
     **/
    public UserModel getUserModel() {
        return userModel;
    }

    /**
     * 设置【userModel】
     **/
    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    /**
     * 获取【用户id】
     **/
    public long getUserId() {
        return userId;
    }

    /**
     * 设置【用户id】
     **/
    public void setUserId(long userId) {
        this.userId = userId;
    }

    /**
     * 获取【推广奖励记录】
     **/
    public PromotionPrizeRecord getPrizeRecord() {
        return prizeRecord;
    }

    /**
     * 设置【推广奖励记录】
     **/
    public void setPrizeRecord(PromotionPrizeRecord prizeRecord) {
        this.prizeRecord = prizeRecord;
    }

    /**
     * 获取【券编号】
     **/
    public String getPrizeNo() {
        return prizeNo;
    }

    /**
     * 设置【券编号】
     **/
    public void setPrizeNo(String prizeNo) {
        this.prizeNo = prizeNo;
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
    public String getAddTimeStartTime() {
        return addTimeStartTime;
    }

    /**
     * 设置【开始时间】
     **/
    public void setAddTimeStartTime(String addTimeStartTime) {
        this.addTimeStartTime = addTimeStartTime;
    }

    /**
     * 获取【结束时间】
     **/
    public String getAddTimeEndTime() {
        return addTimeEndTime;
    }

    /**
     * 设置【结束时间】
     **/
    public void setAddTimeEndTime(String addTimeEndTime) {
        this.addTimeEndTime = addTimeEndTime;
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

    /**
     * 获取【红包类型】
     **/
    public int getType() {
        return type;
    }

    /**
     * 设置【红包类型】
     **/
    public void setType(int type) {
        this.type = type;
    }

    /**
     * 获取【平台添加管理员】
     **/
    public long getOperatorId() {
        return operatorId;
    }

    /**
     * 设置【平台添加管理员】
     **/
    public void setOperatorId(long operatorId) {
        this.operatorId = operatorId;
    }

    public UserModel getBeUserModel() {
        return beUserModel;
    }

    public void setBeUserModel(UserModel beUserModel) {
        this.beUserModel = beUserModel;
    }

    public UserInfoModel getBeUserInfoModel() {
        return beUserInfoModel;
    }

    public void setBeUserInfoModel(UserInfoModel beUserInfoModel) {
        this.beUserInfoModel = beUserInfoModel;
    }

    public double getUseSingleInvestAmount() {
        return useSingleInvestAmount;
    }

    public void setUseSingleInvestAmount(double useSingleInvestAmount) {
        this.useSingleInvestAmount = useSingleInvestAmount;
    }

    public String getBounsIds() {
        return bounsIds;
    }

    public void setBounsIds(String bounsIds) {
        this.bounsIds = bounsIds;
    }

    public Double getLessAmount() {
        return lessAmount;
    }

    public void setLessAmount(Double lessAmount) {
        this.lessAmount = lessAmount;
    }
}