package com.zc.mall.api.zc.account;

import com.zc.mall.core.account.model.AccountModel;
import com.zc.mall.core.account.service.AccountService;
import com.zc.mall.core.common.web.BaseController;
import com.zc.sys.common.exception.BusinessException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 资金账户
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
@RestController
@RequestMapping("/acc/account")
public class AccountController extends BaseController<AccountModel> {

    @Resource
    AccountService accountService;

    /**
     * 列表
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Object list(AccountModel model) throws BusinessException {
        return accountService.list(model);
    }

    /**
     * 获取
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/getById", method = RequestMethod.POST)
    @ResponseBody
    public Object getById(AccountModel model) throws BusinessException {
        return accountService.getById(model);
    }

    /**
     * 获取
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/findByUserId", method = RequestMethod.POST)
    @ResponseBody
    public Object findByUserId(AccountModel model) throws BusinessException {
        return accountService.findByUser(model.getUserId());
    }
}