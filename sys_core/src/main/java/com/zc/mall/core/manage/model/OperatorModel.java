package com.zc.mall.core.manage.model;

import com.zc.mall.core.common.global.BeanUtil;
import com.zc.mall.core.manage.dao.OperatorDao;
import com.zc.mall.core.manage.dao.RoleDao;
import com.zc.mall.core.manage.entity.Operator;
import com.zc.mall.core.manage.entity.Role;
import com.zc.mall.core.user.dao.UserDao;
import com.zc.mall.core.user.entity.User;
import com.zc.mall.core.user.model.UserModel;
import com.zc.sys.common.exception.BusinessException;
import com.zc.sys.common.model.jpa.Page;
import com.zc.sys.common.util.date.DateUtil;
import com.zc.sys.common.util.encrypt.MD5;
import com.zc.sys.common.util.validate.StringUtil;
import org.springframework.beans.BeanUtils;

/**
 * 管理员
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
public class OperatorModel extends Operator {
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
     * 角色Model
     **/
    private UserModel userModel;
    /**
     * 用户关联Model
     **/
    private RoleModel roleModel;
    /**
     * 用户类型
     **/
    private int type;
    /**
     * 用户Id
     **/
    private long userId;
    /**
     * 管理员Id
     **/
    private long operatorId;
    /**
     * 角色Id
     **/
    private long roleId;
    /**
     * 法人姓名
     **/
    private String LegalName;

    /**
     * 实体转换model
     */
    public static OperatorModel instance(Operator operator) {
        if (operator == null || operator.getId() <= 0) {
            return null;
        }
        OperatorModel operatorModel = new OperatorModel();
        BeanUtils.copyProperties(operator, operatorModel);
        return operatorModel;
    }

    /**
     * model转换实体
     */
    public Operator prototype() {
        Operator operator = new Operator();
        BeanUtils.copyProperties(this, operator);
        return operator;
    }

    /**
     * 登录校验参数
     */
    public void checkLogin() {
        if (StringUtil.isBlank(this.getName())) {
            throw new BusinessException("用户名不能为空.");
        }
        if (StringUtil.isBlank(this.getPwd())) {
            throw new BusinessException("密码不能为空.");
        }
    }

    /**
     * 校验交易密码
     *
     * @param id
     * @param payPwd
     * @return
     */
    public static boolean checkPayPwd(long id, String payPwd) {
        return BeanUtil.getBean(OperatorDao.class).checkPayPwd(id, payPwd);
    }

    /**
     * 参数校验
     */
    public void validParam() {
        if (StringUtil.isBlank(this.getName())) {
            throw new BusinessException("用户名不能为空！");
        }
        if (StringUtil.isBlank(this.getUserName())) {
            throw new BusinessException("姓名不能为空！");
        }
        // if (StringUtil.isBlank(this.getPayPwd())) {
        // throw new BusinessException("交易密码不能为空！");
        // }
        // if (StringUtil.isBlank(this.getPwd())) {
        // throw new BusinessException("密码不能为空！");
        // }
        if (StringUtil.isBlank(this.getRole())) {
            throw new BusinessException("角色不能为空！");
        }
        if (StringUtil.isBlank(this.getMobile())) {
            throw new BusinessException("电话不能为空！");
        }
    }

    /**
     * 设置修改基本参数
     *
     * @param operator
     */
    public void initParamadd(Operator operator) {
        if (this.getOperatorId() > 0) {
            UserDao userDao = BeanUtil.getBean(UserDao.class);
            User user = userDao.find(this.getUserId());
            operator.setPid(Integer.parseInt(String.valueOf(this
                    .getOperatorId())));
            operator.setUser(user);
        }
        operator.setAddTime(DateUtil.getNow());
        operator.setPwd(MD5.toMD5(this.getPwd()));
        operator.setPayPwd(MD5.toMD5(this.getPayPwd()));
    }

    /**
     * 设置修改基本参数
     */
    public Operator initParamAddRelation() {
        UserDao userDao = BeanUtil.getBean(UserDao.class);
        User user = userDao.find(this.getUserId());
        OperatorDao operatorDao = BeanUtil.getBean(OperatorDao.class);
        Operator operatorEntiy = operatorDao.findObjByProperty("user.id", this.getUserId());
        if (operatorEntiy != null) {
            throw new BusinessException("您已经生成过后台账号了！直接登录吧? 若忘记密码请联系平台管理员");
        }
        Operator operator = this.prototype();
        operator.setUser(user);
        operator.setPwd(MD5.toMD5("123456"));
        operator.setAddTime(DateUtil.getNow());
        operator.setState(this.getState());
        operator.setMobile(user.getMobile());
        RoleDao roleDao = BeanUtil.getBean(RoleDao.class);
        Role role = roleDao.find(Long.parseLong("6"));
        operator.setRole(role);
        operator.setPayPwd(MD5.toMD5("123456"));
        operator.setPid(0);
        operator.setUserName(user.getUserName());
        operator.setName(this.getLegalName());
        operator.setAddManager(this.getAddManager());
        operator.setAddTime(DateUtil.getNow());
        return operator;
    }

    /**
     * 设置修改基本参数
     *
     * @param menu
     */
    public void setUpdateParam(Operator operator) {
        if (this.getOperatorId() > 0) {
            operator.setPid(Integer.parseInt(String.valueOf(this
                    .getOperatorId())));
        }
        operator.setUser(operator.getUser());
        operator.setName(this.getName());
        operator.setMobile(this.getMobile());
        operator.setRole(this.getRole());
        operator.setEmail(this.getEmail());
        operator.setUserName(this.getUserName());
        operator.setState(this.getState());
        operator.setRemark(this.getRemark());
        operator.setUpdateManager(this.getUpdateManager());
        operator.setUpdateTime(DateUtil.getNow());
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
     * 获取【roleModel】
     **/
    public RoleModel getRoleModel() {
        return roleModel;
    }

    /**
     * 设置【roleModel】
     **/
    public void setRoleModel(RoleModel roleModel) {
        this.roleModel = roleModel;
    }

    /**
     * 获取【用户类型】
     **/
    public int getType() {
        return type;
    }

    /**
     * 设置【用户类型】
     **/
    public void setType(int type) {
        this.type = type;
    }

    /**
     * 获取【用户Id】
     **/
    public long getUserId() {
        return userId;
    }

    /**
     * 设置【用户Id】
     **/
    public void setUserId(long userId) {
        this.userId = userId;
    }

    /**
     * 获取【legalName】
     **/
    public String getLegalName() {
        return LegalName;
    }

    /**
     * 设置【legalName】
     **/
    public void setLegalName(String legalName) {
        LegalName = legalName;
    }

    /**
     * 获取【角色Model】
     **/
    public UserModel getUserModel() {
        return userModel;
    }

    /**
     * 设置【角色Model】
     **/
    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    public long getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(long operatorId) {
        this.operatorId = operatorId;
    }

}