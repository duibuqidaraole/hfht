package com.zc.mall.api.zc.common;

import com.zc.mall.core.common.model.CommonModel;
import com.zc.mall.core.common.service.CommonService;
import com.zc.mall.core.common.web.BaseController;
import com.zc.sys.common.cache.RedisCacheUtil;
import com.zc.sys.common.exception.BusinessException;
import com.zc.sys.common.form.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 公共接口
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
@RestController
@RequestMapping("/common")
public class CommonController extends BaseController<CommonModel> {

    @Resource
    CommonService commonService;
    @Resource
    private RedisCacheUtil redisCacheUtil;

    /**
     * 生成唯一请求标识
     *
     * @return
     */
    @RequestMapping(value = "/getToken", method = RequestMethod.POST)
    @ResponseBody
    public Object getToken() throws BusinessException {
        return commonService.getToken();
    }

    /**
     * 发送短信验证码
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/getMobileCode", method = RequestMethod.POST)
    @ResponseBody
    public Object getMobileCode(CommonModel model) throws BusinessException {
        return commonService.getMobileCode(model);
    }

    /**
     * 利息计算器
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/calculator", method = RequestMethod.POST)
    @ResponseBody
    public Object calculator(CommonModel model) throws BusinessException {
        return Result.success().setData(commonService.calculator(model));
    }

    /**
     * 合同下载
     *
     * @return
     */
    @RequestMapping(value = "/downloadProtocol", method = RequestMethod.POST)
    @ResponseBody
    public Object downloadProtocol(CommonModel model) throws BusinessException {
        return Result.success().setData(commonService.downloadProtocol(model));
    }

}