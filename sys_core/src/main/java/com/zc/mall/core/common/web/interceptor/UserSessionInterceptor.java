package com.zc.mall.core.common.web.interceptor;

import com.zc.mall.core.common.constant.BaseConstant;
import com.zc.mall.core.common.global.BeanUtil;
import com.zc.mall.core.common.web.annotation.UserSessionAnnotation;
import com.zc.mall.core.user.dao.UserDao;
import com.zc.mall.core.user.entity.User;
import com.zc.sys.common.cache.RedisCacheUtil;
import com.zc.sys.common.exception.BusinessException;
import com.zc.sys.common.util.validate.StringUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;

/**
 * 用户session拦截器
 *
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月9日
 */
@Component
public class UserSessionInterceptor extends HandlerInterceptorAdapter {
    /**
     * 请求处理之前执行的方法
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            UserSessionAnnotation sessionAnnotation = findAnnotation((HandlerMethod) handler, UserSessionAnnotation.class);
            //没有声明需要权限,或者声明不验证权限
            if (sessionAnnotation == null) {
                return true;
            }

            String sessionKey = request.getHeader("sessionKey");
            if (StringUtil.isBlank(sessionKey)) {
                sessionKey = request.getParameter("sessionKey");
            }
            if (StringUtil.isBlank(sessionKey)) {
                throw new BusinessException("您还未登录");
            }

            Long sessionValue = RedisCacheUtil.instance().getCache(sessionKey, Long.class);
            if (sessionValue == null || sessionValue.longValue() <= 0) {
                throw new BusinessException("您还未登录");
            }
            UserDao userDao = BeanUtil.getBean(UserDao.class);
            User user = userDao.find(sessionValue);
            if (user == null) {
                throw new BusinessException("您还未登录");
            }
            if (user.getUserIdentify().getState() != BaseConstant.INFO_STATE_YES) {
                throw new BusinessException("用户状态异常");
            }
            return true;
        } else {
            return true;
        }
    }

    /**
     * 在请求处理之后执行该方法
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    /**
     * 请求处理完成所执行的方法
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    /**
     * 处理异步请求之后的方法
     */
    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

    }

    private <T extends Annotation> T findAnnotation(HandlerMethod handler, Class<T> annotationType) {
        T annotation = handler.getBeanType().getAnnotation(annotationType);
        if (annotation != null) return annotation;
        return handler.getMethodAnnotation(annotationType);
    }
}
