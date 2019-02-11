package com.zc.mall.api.zc.promotion;

import com.zc.mall.core.common.web.BaseController;
import com.zc.mall.promotion.model.BonusCouponsRecordModel;
import com.zc.mall.promotion.service.BonusCouponsRecordService;
import com.zc.sys.common.exception.BusinessException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 红包发放记录
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年12月20日
 */
@RestController
@RequestMapping("/pt/bonuscouponsrecord")
public class BonusCouponsRecordController extends BaseController<BonusCouponsRecordModel> {

    @Resource
    BonusCouponsRecordService bonusCouponsRecordService;

    /**
     * 列表
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Object list(BonusCouponsRecordModel model) throws BusinessException {
        return bonusCouponsRecordService.list(model);
    }

    /**
     * 添加
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(BonusCouponsRecordModel model) throws BusinessException {
        return bonusCouponsRecordService.add(model);
    }

    /**
     * 修改
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Object update(BonusCouponsRecordModel model) throws BusinessException {
        return bonusCouponsRecordService.update(model);
    }

    /**
     * 获取
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/getById", method = RequestMethod.POST)
    @ResponseBody
    public Object getById(BonusCouponsRecordModel model) throws BusinessException {
        return bonusCouponsRecordService.getById(model);
    }

    /**
     * 查询使用的红包
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/getByUse", method = RequestMethod.POST)
    @ResponseBody
    public Object getByUse(BonusCouponsRecordModel model) throws BusinessException {
        return bonusCouponsRecordService.getByUse(model);
    }

    /**
     * 奖励发放请求
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/giveOutRequest", method = RequestMethod.POST)
    @ResponseBody
    public Object giveOutRequest(BonusCouponsRecordModel model) throws BusinessException {
        return bonusCouponsRecordService.giveOutRequest(model);
    }

    /**
     * 发放新人奖励
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/giveOutNewcomerReward", method = RequestMethod.POST)
    @ResponseBody
    public Object giveOutNewcomerReward(BonusCouponsRecordModel model) throws BusinessException {
        return bonusCouponsRecordService.giveOutNewcomerReward(model);
    }

    /**
     * 新人奖励检查
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/checkUserNewcomer", method = RequestMethod.POST)
    @ResponseBody
    public Object checkUserNewcomer(BonusCouponsRecordModel model) throws BusinessException {
        return bonusCouponsRecordService.checkUserNewcomer(model);
    }


}