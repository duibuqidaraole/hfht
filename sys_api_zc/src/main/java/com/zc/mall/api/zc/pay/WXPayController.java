package com.zc.mall.api.zc.pay;


import com.alibaba.druid.util.IOUtils;
import com.alibaba.fastjson.JSONObject;
import com.zc.mall.core.user.dao.UserDao;
import com.zc.mall.core.user.entity.User;
import com.zc.mall.core.user.model.UserModel;
import com.zc.mall.core.user.service.UserService;
import com.zc.sys.common.exception.BusinessException;
import com.zc.sys.common.util.validate.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 微信支付
 *
 * @author zp
 */

@RestController
@RequestMapping("/pay/wx")
public class WXPayController {
    private static Logger log = LoggerFactory.getLogger(WXPayController.class);
    @Resource
    private UserService userService;
    @Resource
    private UserDao userDao;

    /**
     * 统一下单
     *
     * @return
     */
    @RequestMapping(value = "/unifiedorderNotify")
    @ResponseBody
    public void unifiedorderNotify(HttpServletRequest request, HttpServletResponse response) {
        log.info("微信支付-统一下单异步通知-------start---------");
        try {
            String callParam = IOUtils.read(request.getInputStream());
            log.info("微信支付-统一下单回调参数---------------" + callParam);
            // 支付处理

        } catch (Exception e) {
            log.info(e.getMessage());
        } finally {
            log.info("微信支付-统一下单异步通知-------end---------");
        }
    }


    /**
     * 微信登陆
     *
     * @return
     */
    @RequestMapping(value = "/wxLogin")
    @ResponseBody
    public void wxLogin(String logInfo) {
        log.info("微信登陆-统一下单异步通知-------start---------");
        JSONObject jsonObject = (JSONObject) JSONObject.parse(logInfo);
        JSONObject xml = jsonObject.getJSONObject("xml");
        String openId = xml.getString("FromUserName");
        //商户名
        String toUserId = xml.getString("ToUserName");
        User user = userDao.findByOpenId(openId);
        if (user == null) {
            String eventKey = xml.getString("EventKey");
            String inviteId = "";
            if (StringUtil.isNotBlank(eventKey)) {
                inviteId = eventKey.split(":").length > 1 ? eventKey.split(":")[1] : "";
            }
            if (StringUtil.isBlank(openId)) {
                throw new BusinessException("登陆异常");
            }
            UserModel userModel = new UserModel();
            userModel.setOpenId(openId);
            User inviteUser = null;
            if (StringUtil.isNotBlank(inviteId)) {
                inviteUser = userDao.find(new Long(inviteId));
            }
            if (inviteUser == null) {
                inviteUser = userDao.find(1L);
            }
            userModel.setInviteUser(inviteUser);
            userService.loginByOpenId(userModel);
            log.info("微信登陆-统一下单异步通知-------注册end---------");
        } else {
            log.info("微信登陆-统一下单异步通知-------登录end---------");
        }
    }


    /**
     * 支付反馈
     *
     * @return
     */
    @RequestMapping(value = "/wxPayFeedback")
    @ResponseBody
    public static void wxPayFeedback(HttpServletRequest request, HttpServletResponse response) {
        try {
            log.info("微信支付反馈-统一下单异步通知----------------");
            String params = IOUtils.read(request.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
