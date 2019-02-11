package com.zc.mall.api.zc.xc;

import com.zc.mall.core.common.web.BaseController;
import com.zc.mall.core.xc.model.ArticleModel;
import com.zc.mall.core.xc.service.ArticleService;
import com.zc.sys.common.exception.BusinessException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 文章
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月09日
 */
@RestController
@RequestMapping("/xc/article")
public class ArticleController extends BaseController<ArticleModel> {

    @Resource
    private ArticleService articleService;

    /**
     * 列表
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Object list(ArticleModel model) throws BusinessException {
        return articleService.list(model);
    }

    /**
     * 添加
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(ArticleModel model) throws BusinessException {
        return articleService.add(model);
    }

    /**
     * 添加商家团队成员
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/addTeam", method = RequestMethod.POST)
    @ResponseBody
    public Object addTeam(ArticleModel model) throws BusinessException {
        return articleService.addTeam(model);
    }

    /**
     * 修改
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Object update(ArticleModel model) throws BusinessException {
        return articleService.update(model);
    }

    /**
     * 修改商家团队成员
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/updateTeam", method = RequestMethod.POST)
    @ResponseBody
    public Object updateTeam(ArticleModel model) throws BusinessException {
        return articleService.updateTeam(model);
    }

    /**
     * 获取
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/getById", method = RequestMethod.POST)
    @ResponseBody
    public Object getById(ArticleModel model) throws BusinessException {
        return articleService.getById(model);
    }

    /**
     * 通过Id删除单个
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/deleteById", method = RequestMethod.POST)
    @ResponseBody
    public Object deleteById(ArticleModel model) throws BusinessException {
        return articleService.deleteById(model);
    }

    /**
     * 通过siteNid查询
     *
     * @siteNid
     */
    @RequestMapping(value = "/findBySiteNid", method = RequestMethod.POST)
    @ResponseBody
    public Object findBySiteNid(ArticleModel Model) throws Exception {
        return articleService.findByModel(Model);
    }
}