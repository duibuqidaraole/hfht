
package com.zc.mall.promotion.model;

import com.zc.mall.core.common.constant.BaseConstant;
import com.zc.mall.core.common.global.BeanUtil;
import com.zc.mall.core.manage.dao.OperatorDao;
import com.zc.mall.core.manage.model.OperatorModel;
import com.zc.mall.core.user.dao.UserDao;
import com.zc.mall.core.user.model.UserInfoModel;
import com.zc.mall.core.user.model.UserModel;
import com.zc.mall.promotion.dao.BonusCouponsDao;
import com.zc.mall.promotion.entity.BonusCoupons;
import com.zc.sys.common.exception.BusinessException;
import com.zc.sys.common.model.jpa.Page;
import com.zc.sys.common.util.date.DateUtil;
import com.zc.sys.common.util.validate.StringUtil;
import org.springframework.beans.BeanUtils;

/**
 * 红包
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年12月20日
 */
public class BonusCouponsModel extends BonusCoupons {
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
     * 用户id
     **/
    private long userId;
    /**
     * 平台添加管理员id
     **/
    private long operatorId;
    /**
     * 商家添加管理员id
     **/
    private long relationId;
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
     * 实体转换model
     */
    public static BonusCouponsModel instance(BonusCoupons bonusCoupons) {
        if (bonusCoupons == null || bonusCoupons.getId() <= 0) {
            return null;
        }
        BonusCouponsModel bonusCouponsModel = new BonusCouponsModel();
        BeanUtils.copyProperties(bonusCoupons, bonusCouponsModel);
        return bonusCouponsModel;

    }

    /**
     * model转换实体
     */
    public BonusCoupons prototype() {
        BonusCoupons bonusCoupons = new BonusCoupons();
        BeanUtils.copyProperties(this, bonusCoupons);
        return bonusCoupons;
    }

    /**
     * 初始化添加参数
     */
    public void init() {
        OperatorDao operatorDao = BeanUtil.getBean(OperatorDao.class);
        UserDao userDao = BeanUtil.getBean(UserDao.class);
        if (this.getOperatorId() > 0 && this.getRelationId() > 0) {
            this.setOperator(operatorDao.find(this.getOperatorId()));
            this.setBeUser(userDao.find(this.getRelationId()));
        } else {
            this.setOperator(operatorDao.find(this.getOperatorId()));
        }
        this.setAddTime(DateUtil.getNow());
        this.setGrantTotalInvestAmount(0);
        this.setCouponsNo(StringUtil.getSerialNumber());
        if (this.getAmountType() == 0) {
            this.setAmountType(BaseConstant.AMOUNT_TYPE_MONEY);
        }

    }

    /**
     * 初始化添加参数
     */
    public void setParamsUpdate(BonusCoupons bonusCoupons) {
        OperatorDao operatorDao = BeanUtil.getBean(OperatorDao.class);
        UserDao userDao = BeanUtil.getBean(UserDao.class);
        if (this.getOperatorId() > 0 && this.getRelationId() > 0) {
            bonusCoupons.setOperator(operatorDao.find(this.getOperatorId()));
            bonusCoupons.setBeUser(userDao.find(this.getRelationId()));
        } else {
            bonusCoupons.setOperator(operatorDao.find(this.getOperatorId()));
        }
        bonusCoupons.setCouponsNo(bonusCoupons.getCouponsNo());
        bonusCoupons.setName(this.getName());
        bonusCoupons.setAmount(this.getAmount());
        bonusCoupons.setName(this.getName());
        bonusCoupons.setGrantSingleInvestAmount(this.getGrantSingleInvestAmount());
        bonusCoupons.setType(this.getType());
        bonusCoupons.setState(this.getState());
        bonusCoupons.setValidityType(this.getValidityType());
        bonusCoupons.setValidityValue(this.getValidityValue());
        bonusCoupons.setAddTime(bonusCoupons.getAddTime());
        bonusCoupons.setStartTime(this.getStartTime());
        bonusCoupons.setEndTime(this.getEndTime());
        bonusCoupons.setQuota(this.getQuota());
        bonusCoupons.setUseQuota(this.getUseQuota());
        bonusCoupons.setRemark(this.getRemark());
        bonusCoupons.setGrantTotalInvestAmount(this.getGrantTotalInvestAmount());
        bonusCoupons.setUseSingleInvestAmount(this.getUseSingleInvestAmount());
        bonusCoupons.setSummary(this.getSummary());
        this.setCouponsNo(StringUtil.getSerialNumber());
    }

    /**
     * 添加红包参数校验
     */
    public void validParam() {
//		if (StringUtil.isBlank(this.getCouponsNo())) {
//			throw new BusinessException("卷编号不能为空！");
//		}
        if (StringUtil.isBlank(this.getName())) {
            throw new BusinessException("卷名称不能为空！");
        }
        if (StringUtil.isBlank(this.getSummary())) {
            throw new BusinessException("简介不能为空！");
        }
        if (this.getAmount() <= 0) {
            throw new BusinessException("卷面金额不能为空或小于零！");
        }
        if (this.getType() <= 0) {
            throw new BusinessException("类型不能为空！");
        }
        if (!StringUtil.isNumber(String.valueOf(this.getState()))) {
            throw new BusinessException("状态不能为空！");
        }
        if (this.getValidityType() <= 0) {
            throw new BusinessException("您还未选择有效期类型！");
        }
        if (StringUtil.isBlank(this.getValidityValue())) {
            throw new BusinessException("有效期值不能为空！");
        }
        if (StringUtil.isBlank(this.getQuota()) || this.getQuota() < 0) {
            throw new BusinessException("配额不能为空！");
        }
        if (DateUtil.msBetween(this.getEndTime(), this.getStartTime()) < 0) {
            throw new BusinessException("发放开始时间不能大于发放结束时间！");
        }
    }

    /**
     * 根据券No查询
     *
     * @param couponsNo
     * @return
     */
    public static BonusCoupons getByCouponsNo(String couponsNo) {
        BonusCouponsDao bonusCouponsDao = BeanUtil.getBean(BonusCouponsDao.class);
        return bonusCouponsDao.getByCouponsNo(couponsNo);
    }


    public UserModel getBeUserModel() {
        return beUserModel;
    }

    public void setBeUserModel(UserModel beUserModel) {
        this.beUserModel = beUserModel;
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
     * 设置【商家添加管理员id】
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

    public UserInfoModel getBeUserInfoModel() {
        return beUserInfoModel;
    }

    public void setBeUserInfoModel(UserInfoModel beUserInfoModel) {
        this.beUserInfoModel = beUserInfoModel;
    }

}