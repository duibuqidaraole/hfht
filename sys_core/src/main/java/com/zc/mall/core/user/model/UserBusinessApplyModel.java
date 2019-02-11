package com.zc.mall.core.user.model;

import com.zc.mall.core.user.entity.UserBusinessApply;
import com.zc.sys.common.exception.BusinessException;
import com.zc.sys.common.model.jpa.Page;
import com.zc.sys.common.util.date.DateUtil;
import com.zc.sys.common.util.validate.StringUtil;
import org.springframework.beans.BeanUtils;

/**
 * 商家申请信息
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
public class UserBusinessApplyModel extends UserBusinessApply {
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
     * 实体转换model
     */
    public static UserBusinessApplyModel instance(UserBusinessApply userBusinessApply) {
        UserBusinessApplyModel userBusinessApplyModel = new UserBusinessApplyModel();
        BeanUtils.copyProperties(userBusinessApply, userBusinessApplyModel);
        return userBusinessApplyModel;
    }

    /**
     * model转换实体
     */
    public UserBusinessApply prototype() {
        UserBusinessApply userBusinessApply = new UserBusinessApply();
        BeanUtils.copyProperties(this, userBusinessApply);
        return userBusinessApply;
    }

    /**
     * 添加参数检查
     *
     * @param menu
     */
    public void addInitCheckParams(UserBusinessApplyModel model) {

        if (StringUtil.isBlank(model.getCompanyName())) {
            throw new BusinessException("公司名称不能为空");
        }
        if (StringUtil.isBlank(model.getMobile())) {
            throw new BusinessException("电话不能为空");
        }
        if (StringUtil.isBlank(model.getEmail())) {
            throw new BusinessException("邮箱不能为空");
        }
        if (StringUtil.isBlank(model.getUserName())) {
            throw new BusinessException("联系人不能为空");
        }
        if (StringUtil.isBlank(model.getContent())) {
            throw new BusinessException("内容说明不能为空");
        }
        this.setAddTime(DateUtil.getNow());
    }

    /**
     * 设置修改基本参数
     *
     * @param menu
     */
    public void setUpdateParams(UserBusinessApplyModel model) {
        //this.setDoTime(DateUtil.getNow());
        this.setCompanyName(model.getCompanyName());
        this.setUserName(model.getUserName());
        this.setMobile(model.getMobile());
        this.setState(model.getState());
        this.setEmail(model.getEmail());
        this.setRemark(model.getRemark());
        this.setContent(model.getContent());
    }

    /**
     * 处理申请审核成功的
     *
     * @param model
     */
    public UserBusinessApply delSuccss(UserBusinessApply userBusinessApply) {
        if (this.getState() == 1) {
            userBusinessApply.setDoTime(DateUtil.getNow());
            userBusinessApply.setRemark(this.getRemark());
            userBusinessApply.setState(this.getState());
        }
        return userBusinessApply;


    }

    /**
     * 处理申请审核失败的
     *
     * @param model
     */
    public void delFail(UserBusinessApplyModel model) {
        if (model.getState() == 2)
            this.setDoTime(DateUtil.getNow());

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
}
