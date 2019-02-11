package com.zc.mall.api.zc.goods;

import com.zc.mall.core.common.web.BaseController;
import com.zc.mall.core.user.dao.UserAddressDao;
import com.zc.mall.core.user.entity.User;
import com.zc.mall.core.user.model.UserAddressModel;
import com.zc.mall.mall.entity.GoodsSku;
import com.zc.mall.mall.entity.OrderGoods;
import com.zc.mall.mall.model.OrderInfoModel;
import com.zc.mall.mall.model.OrderLogisticsModel;
import com.zc.mall.mall.service.OrderInfoService;
import com.zc.sys.common.exception.BusinessException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 订单信息
 *
 * @author zp
 * @version 1.0.0
 * @since 2018年11月13日
 */
@RestController
@RequestMapping("/g/orderinfo")
public class OrderInfoController extends BaseController<OrderInfoModel> {

    @Resource
    OrderInfoService orderInfoService;
    @Resource
    UserAddressDao userAddressDao;

    /**
     * 列表
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Object list(OrderInfoModel model) throws BusinessException {
        return orderInfoService.list(model);
    }

    /**
     * test
     *
     * @return
     */
    @RequestMapping(value = "/test", method = RequestMethod.POST)
    @ResponseBody
    public Object test(OrderInfoModel model) throws BusinessException {
//        try {
//            return WxPayClient.unifiedorder("ow9Do5jhMQ_2Isvd8AIWYEHEExss", "test_hf_1542871329", "测试弘梵", 1);
//        } catch (Exception e) {
//            System.err.println(e.getMessage());
//            throw new BusinessException(e.getMessage());
//        }


        model.setUser(new User(6));
        List<OrderGoods> l = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            GoodsSku sku = new GoodsSku();
            sku.setId(27 + i);
            OrderGoods orderGoods = new OrderGoods();
            orderGoods.setGoodsSku(sku);
            orderGoods.setNumber(1);
            l.add(orderGoods);
        }
        model.setOrderGoodsList(l);
        model.setPayment(1);
        OrderLogisticsModel orderLogisticsModel = new OrderLogisticsModel();
        orderLogisticsModel.setLogisticsCompanyNo("kd");
        model.setOrderLogisticsModel(orderLogisticsModel);
        model.setUserAddressModel(UserAddressModel.instance(userAddressDao.find(1L)));
        return orderInfoService.add(model);
    }

    /**
     * 添加
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(OrderInfoModel model) throws BusinessException {
        return orderInfoService.add(model);
    }

    /**
     * 修改
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Object update(OrderInfoModel model) throws BusinessException {
        return orderInfoService.update(model);
    }

    /**
     * 获取
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/getById", method = RequestMethod.POST)
    @ResponseBody
    public Object getById(OrderInfoModel model) throws BusinessException {
        return orderInfoService.getById(model);
    }

    /**
     * 获取
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/getByOrderNo", method = RequestMethod.POST)
    @ResponseBody
    public Object getByOrderNo(OrderInfoModel model) throws BusinessException {
        return orderInfoService.getByOrderNo(model);
    }


    /**
     * 支付
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/pay", method = RequestMethod.POST)
    @ResponseBody
    public Object pay(OrderInfoModel model) throws BusinessException {
        return orderInfoService.pay(model);
    }

    /**
     * 发货
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/send", method = RequestMethod.POST)
    @ResponseBody
    public Object send(OrderInfoModel model) throws BusinessException {
        return orderInfoService.send(model);
    }

    /**
     * 收货
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/receive", method = RequestMethod.POST)
    @ResponseBody
    public Object receive(OrderInfoModel model) throws BusinessException {
        return orderInfoService.receive(model);
    }

    /**
     * 用户退款
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/refundUser", method = RequestMethod.POST)
    @ResponseBody
    public Object refundUser(OrderInfoModel model) throws BusinessException {
        return orderInfoService.refundUser(model);
    }

    /**
     * 退款处理
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/refundDeal", method = RequestMethod.POST)
    @ResponseBody
    public Object refundDeal(OrderInfoModel model) throws BusinessException {
        return orderInfoService.refundDeal(model);
    }

    /**
     * 退款支付
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/refundPay", method = RequestMethod.POST)
    @ResponseBody
    public Object refundPay(OrderInfoModel model) throws BusinessException {
        return orderInfoService.refundPay(model);
    }

    /**
     * 支付请求
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/payRequest", method = RequestMethod.POST)
    @ResponseBody
    public Object payRequest(OrderInfoModel model) throws BusinessException {
        return orderInfoService.payRequest(model);
    }

    /**
     * 订单关闭
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/close", method = RequestMethod.POST)
    @ResponseBody
    public Object close(OrderInfoModel model) throws BusinessException {
        return orderInfoService.close(model);
    }


}