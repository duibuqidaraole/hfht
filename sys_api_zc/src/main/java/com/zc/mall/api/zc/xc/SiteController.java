package com.zc.mall.api.zc.xc;

import com.zc.mall.core.common.web.BaseController;
import com.zc.mall.core.xc.model.SiteModel;
import com.zc.mall.core.xc.service.SiteService;
import com.zc.sys.common.exception.BusinessException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 栏目
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
@RestController
@RequestMapping("/xc/site")
public class SiteController extends BaseController<SiteModel> {

    @Resource
    SiteService siteService;

    /**
     * 列表
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Object list(SiteModel model) throws BusinessException {
        return siteService.list(model);
    }

    /**
     * 列表
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/listNoPage", method = RequestMethod.POST)
    @ResponseBody
    public Object listNoPage(SiteModel model) throws BusinessException {
        return siteService.listNoPage(model);
    }

    /**
     * 添加
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(SiteModel model) throws BusinessException {
        return siteService.add(model);
    }

    /**
     * 修改
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Object update(SiteModel model) throws BusinessException {
        return siteService.update(model);
    }

    /**
     * 获取
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/getById", method = RequestMethod.POST)
    @ResponseBody
    public Object getById(SiteModel model) throws BusinessException {
        return siteService.getById(model);
    }

    /**
     * 根据nid获取所有子栏目及其文章
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/getAllByNid", method = RequestMethod.POST)
    @ResponseBody
    public Object getAllByNid(SiteModel model) throws BusinessException {
        return siteService.getAllByNid(model);
    }

}