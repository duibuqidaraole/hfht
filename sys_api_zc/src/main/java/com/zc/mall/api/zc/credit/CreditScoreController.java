package com.zc.mall.api.zc.credit;

import com.zc.mall.core.common.web.BaseController;
import com.zc.mall.core.credit.model.CreditScoreModel;
import com.zc.mall.core.credit.service.CreditScoreService;
import com.zc.sys.common.exception.BusinessException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 信用分账户
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
@RestController
@RequestMapping("/cs/creditScore")
public class CreditScoreController extends BaseController<CreditScoreModel> {

    @Resource
    CreditScoreService creditScoreService;

    /**
     * 列表
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Object list(CreditScoreModel model) throws BusinessException {
        return creditScoreService.list(model);
    }

    /**
     * 添加
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(CreditScoreModel model) throws BusinessException {
        return creditScoreService.add(model);
    }

    /**
     * 修改
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Object update(CreditScoreModel model) throws BusinessException {
        return creditScoreService.update(model);
    }

    /**
     * 获取
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/getById", method = RequestMethod.POST)
    @ResponseBody
    public Object getById(CreditScoreModel model) throws BusinessException {
        return creditScoreService.getById(model);
    }
}