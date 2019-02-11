package com.zc.mall.api.zc.integral;

import com.zc.mall.core.common.web.BaseController;
import com.zc.mall.core.integral.model.IntegralLogModel;
import com.zc.mall.core.integral.service.IntegralLogService;
import com.zc.sys.common.exception.BusinessException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 积分日志
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
@RestController
@RequestMapping("/jf/integrallog")
public class IntegralLogController extends BaseController<IntegralLogModel> {

    @Resource
    IntegralLogService integralLogService;

    /**
     * 列表
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Object list(IntegralLogModel model) throws BusinessException {
        return integralLogService.list(model);
    }

    /**
     * 获取
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/getById", method = RequestMethod.POST)
    @ResponseBody
    public Object getById(IntegralLogModel model) throws BusinessException {
        return integralLogService.getById(model);
    }
}