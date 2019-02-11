package com.zc.mall.api.zc.sys;

import com.zc.mall.core.common.web.BaseController;
import com.zc.mall.core.sys.model.ConfigModel;
import com.zc.mall.core.sys.service.ConfigService;
import com.zc.sys.common.exception.BusinessException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 系统参数配置
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
@RestController
@RequestMapping("/s/config")
public class ConfigController extends BaseController<ConfigModel> {

    @Resource
    ConfigService configService;

    /**
     * 列表
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Object list(ConfigModel model) throws BusinessException {
        return configService.list(model);
    }

    /**
     * 添加
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(ConfigModel model) throws BusinessException {
        return configService.add(model);
    }

    /**
     * 修改
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Object update(ConfigModel model) throws BusinessException {
        return configService.update(model);
    }

    /**
     * 获取
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/getById", method = RequestMethod.POST)
    @ResponseBody
    public Object getById(ConfigModel model) throws BusinessException {
        return configService.getById(model);
    }

    /**
     * 通过Nid获取
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/getConfigByNid", method = RequestMethod.POST)
    @ResponseBody
    public Object getConfigByNid(ConfigModel model) throws BusinessException {
        return configService.getConfigByNid(model);
    }
}