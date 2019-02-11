package com.zc.mall.api.zc.sys;

import com.zc.mall.core.common.web.BaseController;
import com.zc.mall.core.sys.model.DictModel;
import com.zc.mall.core.sys.service.DictService;
import com.zc.sys.common.exception.BusinessException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 数据字典
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
@RestController
@RequestMapping("/s/dict")
public class DictController extends BaseController<DictModel> {

    @Resource
    DictService dictService;

    /**
     * 列表
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Object list(DictModel model) throws BusinessException {
        return dictService.list(model);
    }

    /**
     * 添加
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(DictModel model) throws BusinessException {
        return dictService.add(model);
    }

    /**
     * 修改
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Object update(DictModel model) throws BusinessException {
        return dictService.update(model);
    }

    /**
     * 获取
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/getById", method = RequestMethod.POST)
    @ResponseBody
    public Object getById(DictModel model) throws BusinessException {
        return dictService.getById(model);
    }

    /**
     * nid查询字典
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/getByNid", method = RequestMethod.POST)
    @ResponseBody
    public Object getByNid(DictModel model) throws BusinessException {
        return dictService.getByNid(model);
    }

    /**
     * 刷新緩存
     *
     * @return
     */
    @RequestMapping(value = "/refreshDictCache", method = RequestMethod.POST)
    @ResponseBody
    public Object refreshDictCache() throws BusinessException {
        return dictService.refreshDictCache();
    }

}