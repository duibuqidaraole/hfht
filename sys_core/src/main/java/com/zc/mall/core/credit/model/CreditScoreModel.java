package com.zc.mall.core.credit.model;

import com.zc.mall.core.common.global.Global;
import com.zc.mall.core.credit.entity.CreditScore;
import com.zc.mall.core.manage.model.OperatorModel;
import com.zc.mall.core.sys.constant.ConfigParamConstant;
import com.zc.mall.core.user.entity.User;
import com.zc.mall.core.user.model.UserModel;
import com.zc.sys.common.model.jpa.Page;
import com.zc.sys.common.util.calculate.BigDecimalUtil;
import org.springframework.beans.BeanUtils;

/**
 * 信用分账户
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
public class CreditScoreModel extends CreditScore {
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
     * 操作管理员
     **/
    private OperatorModel operatorModel;
    /**
     * 备注
     **/
    private String remark;

    public CreditScoreModel() {

    }

    public CreditScoreModel(User user, double balanceScore) {
        this.setUser(user);
        this.setBalanceScore(balanceScore);
    }

    /**
     * 实体转换model
     */
    public static CreditScoreModel instance(CreditScore creditScore) {
        if (creditScore == null || creditScore.getId() <= 0) {
            return null;
        }
        CreditScoreModel creditScoreModel = new CreditScoreModel();
        BeanUtils.copyProperties(creditScore, creditScoreModel);
        return creditScoreModel;
    }

    /**
     * model转换实体
     */
    public CreditScore prototype() {
        CreditScore creditScore = new CreditScore();
        BeanUtils.copyProperties(this, creditScore);
        return creditScore;
    }

    /**
     * 初始化注册
     *
     * @param model
     */
    public void initReg(UserModel model) {
        double doubleZero = BigDecimalUtil.round(0);
        this.setBalanceScore(Global.getdouble(ConfigParamConstant.SYS_PARAM_INIT_CREDIT_SCORE));
        this.setSysScore(doubleZero);
        this.setZmxyScore(-1d);
        this.setVersion(0);
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
     * 获取【操作管理员】
     **/
    public OperatorModel getOperatorModel() {
        return operatorModel;
    }

    /**
     * 设置【操作管理员】
     **/
    public void setOperatorModel(OperatorModel operatorModel) {
        this.operatorModel = operatorModel;
    }

    /**
     * 获取【备注】
     **/
    public String getRemark() {
        return remark;
    }

    /**
     * 设置【备注】
     **/
    public void setRemark(String remark) {
        this.remark = remark;
    }

}