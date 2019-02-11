package com.zc.mall.promotion.model;

import com.zc.mall.core.common.constant.BaseConstant;
import com.zc.mall.core.manage.model.OperatorModel;
import com.zc.mall.core.user.model.UserModel;
import com.zc.mall.promotion.entity.PromotionPrizeConfig;
import com.zc.mall.promotion.entity.PromotionPrizeRecord;
import com.zc.sys.common.model.jpa.Page;
import com.zc.sys.common.util.date.DateUtil;
import org.springframework.beans.BeanUtils;

/**
 * 活动推广奖励记录
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
public class PromotionPrizeRecordModel extends PromotionPrizeRecord {
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
     * 商家管理员id
     **/
    private long relationId;
    /**
     * 活动Model
     **/
    private PromotionModel promtionModel;
    /**
     * 商家关联用户Model
     **/
    private UserModel beUserModel;
    /**
     * 活动配置Model
     **/
    private PromotionPrizeConfigModel promtionPrizeConfigModel;
    /**
     * 用户Model实体
     */
    private UserModel userModel;
    /**
     * 管理员Model实体
     **/
    private OperatorModel operatorModel;
    /**
     * 活动model
     **/
    private PromotionModel promotionModel;

    /**
     * 实体转换model
     */
    public static PromotionPrizeRecordModel instance(PromotionPrizeRecord promotionPrizeRecord) {
        if (promotionPrizeRecord == null || promotionPrizeRecord.getId() <= 0) {
            return null;
        }
        PromotionPrizeRecordModel promotionPrizeRecordModel = new PromotionPrizeRecordModel();
        BeanUtils.copyProperties(promotionPrizeRecord, promotionPrizeRecordModel);
        return promotionPrizeRecordModel;
    }

    /**
     * model转换实体
     */
    public PromotionPrizeRecord prototype() {
        PromotionPrizeRecord promotionPrizeRecord = new PromotionPrizeRecord();
        BeanUtils.copyProperties(this, promotionPrizeRecord);
        return promotionPrizeRecord;
    }

    /**
     * 初始化添加
     *
     * @param model
     * @param promotionPrizeConfig
     * @return
     */
    public void initAdd(PromotionModel model, PromotionPrizeConfig promotionPrizeConfig) {
        this.setAddTime(DateUtil.getNow());
        this.setPromotion(promotionPrizeConfig.getPromotion());
        this.setPromotionPrizeConfig(promotionPrizeConfig);
        this.setState(BaseConstant.BUSINESS_STATE_YES);
        this.setUser(model.getUser());
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
     * 获取【活动Model】
     **/
    public PromotionModel getPromtionModel() {
        return promtionModel;
    }


    /**
     * 设置【活动Model】
     **/
    public void setPromtionModel(PromotionModel promtionModel) {
        this.promtionModel = promtionModel;
    }

    /**
     * 获取【活动配置Model】
     **/
    public PromotionPrizeConfigModel getPromtionPrizeConfigModel() {
        return promtionPrizeConfigModel;
    }

    /**
     * 设置【活动配置Model】
     **/
    public void setPromtionPrizeConfigModel(PromotionPrizeConfigModel promtionPrizeConfigModel) {
        this.promtionPrizeConfigModel = promtionPrizeConfigModel;
    }

    /**
     * 获取【用户Model实体】
     **/
    public UserModel getUserModel() {
        return userModel;
    }

    /**
     * 设置【用户Model实体】
     **/
    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
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
     * 获取【管理员Model实体】
     **/
    public OperatorModel getOperatorModel() {
        return operatorModel;
    }

    /**
     * 设置【管理员Model实体】
     **/
    public void setOperatorModel(OperatorModel operatorModel) {
        this.operatorModel = operatorModel;
    }

    public UserModel getBeUserModel() {
        return beUserModel;
    }

    public void setBeUserModel(UserModel beUserModel) {
        this.beUserModel = beUserModel;
    }

    public PromotionModel getPromotionModel() {
        return promotionModel;
    }

    public void setPromotionModel(PromotionModel promotionModel) {
        this.promotionModel = promotionModel;
    }
}