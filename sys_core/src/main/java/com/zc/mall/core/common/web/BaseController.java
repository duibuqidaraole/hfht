package com.zc.mall.core.common.web;

import org.springframework.web.bind.annotation.ModelAttribute;


/**
 * Controller基类，所有Controller都必须继承该类
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年7月6日
 */
public abstract class BaseController<T> {

    @ModelAttribute
    public void init(T model) {

    }


}