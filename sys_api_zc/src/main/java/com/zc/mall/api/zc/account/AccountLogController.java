package com.zc.mall.api.zc.account;

import com.zc.mall.core.account.model.AccountLogModel;
import com.zc.mall.core.account.service.AccountLogService;
import com.zc.mall.core.common.web.BaseController;
import com.zc.sys.common.exception.BusinessException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 资金日志
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
@RestController
@RequestMapping("/acc/accountlog")
public class AccountLogController extends BaseController<AccountLogModel> {

    @Resource
    AccountLogService accountLogService;

    /**
     * 列表
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Object list(AccountLogModel model) throws BusinessException {
        return accountLogService.list(model);
    }

    /**
     * 添加
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(AccountLogModel model) throws BusinessException {
        return accountLogService.add(model);
    }

    /**
     * 修改
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Object update(AccountLogModel model) throws BusinessException {
        return accountLogService.update(model);
    }

    /**
     * 获取
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/getById", method = RequestMethod.POST)
    @ResponseBody
    public Object getById(AccountLogModel model) throws BusinessException {
        return accountLogService.getById(model);
    }
}