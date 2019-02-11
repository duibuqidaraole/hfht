package com.zc.mall.core.account.dao.impl;

import com.zc.mall.core.account.dao.AccountDao;
import com.zc.mall.core.account.entity.Account;
import com.zc.mall.core.account.model.AccountModel;
import com.zc.mall.core.common.global.BeanUtil;
import com.zc.mall.core.user.dao.UserRelationDao;
import com.zc.mall.core.user.entity.UserRelation;
import com.zc.mall.core.user.model.UserModel;
import com.zc.sys.common.dao.jpa.BaseDaoImpl;
import com.zc.sys.common.exception.VersionControlException;
import com.zc.sys.common.model.jpa.OrderFilter.OrderType;
import com.zc.sys.common.model.jpa.PageDataList;
import com.zc.sys.common.model.jpa.QueryParam;
import com.zc.sys.common.model.jpa.SearchFilter;
import com.zc.sys.common.util.validate.StringUtil;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 资金账户
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
@Repository
public class AccountDaoImpl extends BaseDaoImpl<Account> implements AccountDao {

    @Override
    public PageDataList<Account> list(AccountModel model) {
        QueryParam param = QueryParam.getInstance();
        if (StringUtil.isNotBlank(model.getMobileOrRealName())) {
            SearchFilter orFilter1 = new SearchFilter("user.mobile", model.getMobileOrRealName());
            SearchFilter orFilter2 = new SearchFilter("user.realName", model.getMobileOrRealName());
            param.addOrFilter(orFilter1, orFilter2);
        }
        if (model.getUserId() > 0) {
            UserRelationDao userRelationDao = BeanUtil.getBean(UserRelationDao.class);
            QueryParam params = QueryParam.getInstance();
            params.addParam("beUser.id", model.getUserId());
            params.addParam("type", 2);
            List<UserRelation> list = userRelationDao.findByCriteria(params);
            String userIds = model.getUserId() + ",";
            if (list.size() > 0) {
                for (UserRelation u : list) {
                    userIds += u.getUser().getId() + ",";
                }
            }
            if (!StringUtil.isBlank(userIds)) {
                param.addOrFilter("user.id", userIds.split(","));
            }

        }
        param.addOrder(OrderType.DESC, "sort");
        param.addPage(model.getPageNo(), model.getPageSize());
        return super.findPageList(param);
    }

    /**
     * 根据userId查询
     *
     * @param userId
     * @return
     */
    @Override
    public Account findByUser(long userId) {
        return this.findObjByProperty("user.id", userId);
    }

    /**
     * 更新账户资金
     *
     * @param id
     * @param total
     * @param balance
     * @param freezeAmount
     * @param version
     * @return
     * @throws VersionControlException
     */
    @Override
    @Transactional
    public void updateAccount(long id, double total, double balance, double freezeAmount, int version) throws VersionControlException {
        if (total == 0 && balance == 0 && freezeAmount == 0) {
            return;
        }
        StringBuffer jpql = new StringBuffer();
        jpql.append(" update Account acct SET acct.total=acct.total+:total,acct.balance=acct.balance+:balance,acct.freezeAmount=acct.freezeAmount+:freezeAmount,acct.version=acct.version+1 ");
        jpql.append(" WHERE acct.id=:id ");
        jpql.append(" AND round(acct.total+:total)>=0");
        jpql.append(" AND round(acct.balance+:balance)>=0");
        jpql.append(" AND round(acct.freezeAmount+:freezeAmount)>=0");
        jpql.append(" AND acct.version=:version");
        int count = updateByJpql(jpql.toString(), new String[]{"total", "balance", "freezeAmount", "id", "version"},
                new Object[]{total, balance, freezeAmount, id, version});
        if (count != 1) {
            throw new VersionControlException("用户资金更新有误");
        }
        Account account = findByIdUseJpql(id);
        em.refresh(account);
    }

    /**
     * jpql查询实体
     *
     * @param id
     * @return
     */
    @Override
    public Account findByIdUseJpql(long id) {
        StringBuffer jpql = new StringBuffer();
        jpql.append(" from Account acct where acct.id = :id ");
        return this.findByJpql(jpql.toString(), new String[]{"id"}, new Object[]{id});
    }

    /**
     * 添加用户资金信息
     *
     * @param beUserModel
     */
    @Override
    public void setUserAccount(UserModel beUserModel) {
        Account account = findByUser(beUserModel.getId());
        beUserModel.setAccountModel(AccountModel.instance(account));
    }

}