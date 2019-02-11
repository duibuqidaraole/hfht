package com.zc.mall.api.zc.sys;

import com.zc.mall.core.common.web.BaseController;
import com.zc.mall.core.sys.model.TemplateModel;
import com.zc.mall.core.sys.service.TemplateService;
import com.zc.sys.common.exception.BusinessException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 模版配置
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
@RestController
@RequestMapping("/s/template")
public class TemplateController extends BaseController<TemplateModel> {

    @Resource
    TemplateService templateService;

    /**
     * 列表
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Object list(TemplateModel model) throws BusinessException {
        return templateService.list(model);
    }

    /**
     * 添加
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(TemplateModel model) throws BusinessException {
        return templateService.add(model);
    }

    /**
     * 修改
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Object update(TemplateModel model) throws BusinessException {
        return templateService.update(model);
    }

    /**
     * 获取
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/getById", method = RequestMethod.POST)
    @ResponseBody
    public Object getById(TemplateModel model) throws BusinessException {
        return templateService.getById(model);
    }
}