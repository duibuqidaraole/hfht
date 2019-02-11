package com.zc.mall.core.common.web.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月9日
 */
@Component
public class ParamSafeInterceptor extends HandlerInterceptorAdapter {
    /**
     * 请求处理之前执行的方法
     */
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
		/*System.err.println("=====================preHandle.........");
		Enumeration<?> em = request.getParameterNames();
		 while (em.hasMoreElements()) {
		    String name = (String) em.nextElement();
		    String value = request.getParameter(name);
		    System.err.println(name+"============"+value);
		}*/
        return true;
    }

    /**
     * 在请求处理之后执行该方法
     */
    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
//		System.err.println("=====================postHandle.........");
    }

    /**
     * 请求处理完成所执行的方法
     */
    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
//		 System.err.println("=====================afterCompletion.........");
    }

    /**
     * 处理异步请求之后的方法
     */
    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request,
                                               HttpServletResponse response, Object handler) throws Exception {
//		 System.err.println("=====================afterConcurrentHandlingStarted.........");
    }
}
