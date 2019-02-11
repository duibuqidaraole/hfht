package com.zc.mall.core.common.web.interceptor;

import com.zc.sys.common.exception.BusinessException;
import com.zc.sys.common.exception.NoRollBackException;
import com.zc.sys.common.form.Result;
import com.zc.sys.common.util.WebUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 异常中心控制器
 */
@Controller
public class ExceptionCenterController implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object obj, Exception ex) {
        if (ex instanceof BusinessException || ex instanceof NoRollBackException) {
            WebUtils.responseJson(response, Result.error(ex.getMessage()));
        }
        return null;
    }

}
