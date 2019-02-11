package com.zc.mall.api.zc.manage;

import com.zc.mall.core.common.web.BaseController;
import com.zc.mall.core.manage.model.NoticeMessageModel;
import com.zc.mall.core.manage.service.NoticeMessageService;
import com.zc.sys.common.exception.BusinessException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 通知消息
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月15日
 */
@RestController
@RequestMapping("/m/noticeMessage")
public class NoticeMessageController extends BaseController<NoticeMessageModel> {

    @Resource
    NoticeMessageService noticeMessageService;

    /**
     * 列表
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Object list(NoticeMessageModel model) throws BusinessException {
        return noticeMessageService.list(model);
    }

    /**
     * 添加
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(NoticeMessageModel model) throws BusinessException {
        return noticeMessageService.add(model);
    }

    /**
     * 修改
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Object update(NoticeMessageModel model) throws BusinessException {
        return noticeMessageService.update(model);
    }

    /**
     * 获取
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/getById", method = RequestMethod.POST)
    @ResponseBody
    public Object getById(NoticeMessageModel model) throws BusinessException {
        return noticeMessageService.getById(model);
    }

    /**
     * 通过用户id和状态等信息查询统计
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/getByUserIdAndState", method = RequestMethod.POST)
    @ResponseBody
    public Object getByUserIdAndState(NoticeMessageModel model) throws BusinessException {
        return noticeMessageService.getByUserIdAndState(model);
    }

}