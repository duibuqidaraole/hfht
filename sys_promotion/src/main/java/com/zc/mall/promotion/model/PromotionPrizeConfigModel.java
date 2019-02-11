package com.zc.mall.promotion.model;

import com.zc.mall.core.common.global.BeanUtil;
import com.zc.mall.core.manage.dao.OperatorDao;
import com.zc.mall.core.manage.entity.Operator;
import com.zc.mall.core.manage.model.OperatorModel;
import com.zc.mall.core.user.model.UserInfoModel;
import com.zc.mall.core.user.model.UserModel;
import com.zc.mall.promotion.constant.BasePromotionConstant;
import com.zc.mall.promotion.dao.PromotionDao;
import com.zc.mall.promotion.dao.PromotionPrizeConfigDao;
import com.zc.mall.promotion.entity.Promotion;
import com.zc.mall.promotion.entity.PromotionPrizeConfig;
import com.zc.sys.common.exception.BusinessException;
import com.zc.sys.common.model.jpa.Page;
import com.zc.sys.common.util.date.DateUtil;
import com.zc.sys.common.util.validate.StringUtil;
import org.springframework.beans.BeanUtils;

/**
 * 活动推广奖励配置
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
public class PromotionPrizeConfigModel extends PromotionPrizeConfig {
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
     * 活动Model
     **/
    private PromotionModel promtionModel;
    /**
     * 平台添加管理员Model
     **/
    private OperatorModel operatorModel;
    /**
     * 活动id
     **/
    private long promotionId;
    /**
     * 添加管理员id
     **/
    private long operatorId;
    /**
     * 商家管理员id
     **/
    private long relationId;
    /**
     * 商家关联用户Model
     **/
    private UserModel beUserModel;
    /**
     * 商家关联用户信息Model
     **/
    private UserInfoModel beUserInfoModel;

    /**
     * 实体转换model
     */
    public static PromotionPrizeConfigModel instance(PromotionPrizeConfig promotionPrizeConfig) {
        if (promotionPrizeConfig == null || promotionPrizeConfig.getId() <= 0) {
            return null;
        }
        PromotionPrizeConfigModel promotionPrizeConfigModel = new PromotionPrizeConfigModel();
        BeanUtils.copyProperties(promotionPrizeConfig, promotionPrizeConfigModel);
        return promotionPrizeConfigModel;
    }

    /**
     * model转换实体
     */
    public PromotionPrizeConfig prototype() {
        PromotionPrizeConfig promotionPrizeConfig = new PromotionPrizeConfig();
        BeanUtils.copyProperties(this, promotionPrizeConfig);
        return promotionPrizeConfig;
    }

    /**
     * 校验添加参数
     */
    public void validAdd() {
        if (this.getPromotionId() <= 0) {
            throw new BusinessException("参数错误");
        }
        if (this.getType() <= 0) {
            throw new BusinessException("参数错误");
        }
        if (this.getState() == 0) {
            throw new BusinessException("参数错误");
        }
        if (StringUtil.isBlank(this.getPrizeNo())) {
            throw new BusinessException("参数错误");
        }
        if (!isExistPrizeConfig(this.getType(), this.getPrizeNo())) {
            throw new BusinessException("奖励券不存在");
        }
    }

    /**
     * 判断券No是否存在
     *
     * @param type
     * @param prizeNo
     * @return
     */
    public boolean isExistPrizeConfig(int type, String prizeNo) {
        Object obj = null;
        switch (type) {
            case BasePromotionConstant.PROMOTIONPRIZE_TYPE_BONUS_COUPONS:// 红包
                obj = BonusCouponsModel.getByCouponsNo(prizeNo);
                break;
            case BasePromotionConstant.PROMOTIONPRIZE_TYPE_VIP:// vip红包
                obj = VipCouponsModel.getByprizeNo(prizeNo);
                break;
            default:
                break;
        }
        return obj != null;
    }

    /**
     * 初始化添加
     */
    public void initAdd() {
        PromotionDao promotionDao = BeanUtil.getBean(PromotionDao.class);
        Promotion promotion = promotionDao.find(this.getPromotionId());
        if (promotion == null) {
            throw new BusinessException("参数错误");
        }
        OperatorDao operatorDao = BeanUtil.getBean(OperatorDao.class);
        Operator operator = null;
        if (this.getOperatorId() > 0) {
            operator = operatorDao.find(this.getOperatorId());
            this.setOperator(operator);
        } else {
            operator = operatorDao.find(this.getRelationId());
            this.setBeUser(operator.getUser());
        }
        this.setPromotion(promotion);
        this.setAddTime(DateUtil.getNow());
    }

    /**
     * 校验修改参数
     *
     * @return
     */
    public PromotionPrizeConfig validUpdate() {
        if (this.getId() <= 0) {
            throw new BusinessException("参数错误");
        }
        if (this.getType() <= 0) {
            throw new BusinessException("参数错误");
        }
        if (this.getState() == 0) {
            throw new BusinessException("参数错误");
        }
        if (StringUtil.isBlank(this.getPrizeNo())) {
            throw new BusinessException("参数错误");
        }
        if (this.getId() <= 0) {
            throw new BusinessException("参数错误");
        }
        PromotionPrizeConfigDao promotionPrizeConfigDao = BeanUtil.getBean(PromotionPrizeConfigDao.class);
        PromotionPrizeConfig promotionPrizeConfig = promotionPrizeConfigDao.find(this.getId());
        if (promotionPrizeConfig == null) {
            throw new BusinessException("参数错误");
        }
        return promotionPrizeConfig;
    }

    /**
     * 初始化修改
     */
    public PromotionPrizeConfig updateParams() {
        PromotionPrizeConfigDao promotionPrizeConfigDao = BeanUtil.getBean(PromotionPrizeConfigDao.class);
        PromotionPrizeConfig promotionPrizeConfig = promotionPrizeConfigDao.find(this.getId());
        promotionPrizeConfig.setType(this.getType());
        promotionPrizeConfig.setPrizeNo(this.getPrizeNo());
        promotionPrizeConfig.setRemark(this.getRemark());
        promotionPrizeConfig.setState(this.getState());
        promotionPrizeConfig.setOperator(promotionPrizeConfig.getOperator());
        promotionPrizeConfig.setPromotion(promotionPrizeConfig.getPromotion());
        if (promotionPrizeConfig.getOperator() != null && promotionPrizeConfig.getOperator().getUser() != null) {
            promotionPrizeConfig.setBeUser(promotionPrizeConfig.getOperator().getUser());
        }
        return promotionPrizeConfig;
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
     * 获取【活动id】
     **/
    public long getPromotionId() {
        return promotionId;
    }


    /**
     * 设置【活动id】
     **/
    public void setPromotionId(long promotionId) {
        this.promotionId = promotionId;
    }

    /**
     * 获取【添加管理员id】
     **/
    public long getOperatorId() {
        return operatorId;
    }


    /**
     * 设置【添加管理员id】
     **/
    public void setOperatorId(long operatorId) {
        this.operatorId = operatorId;
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


    public UserModel getBeUserModel() {
        return beUserModel;
    }

    public void setBeUserModel(UserModel beUserModel) {
        this.beUserModel = beUserModel;
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
}