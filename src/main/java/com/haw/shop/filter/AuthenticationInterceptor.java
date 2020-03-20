package com.haw.shop.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.haw.shop.model.UserInfo;
import com.haw.shop.service.UserService;
import com.haw.shop.token.LoginToken;
import com.haw.shop.token.PassToken;
import com.haw.shop.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * Created by aiwei on 2020-3-13.
 */
public class AuthenticationInterceptor implements HandlerInterceptor {
    @Autowired
    private UserService userService;

    @Override
    /**
     * 1.从 http 请求头中取出 token，
     2.判断是否映射到方法
     3.检查是否有passtoken注释，有则跳过认证
     4.检查有没有需要用户登录的注解，有则需要取出并验证
     5.认证通过则可以访问，不通过会报相关错误信息
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        //检查是否有passtoken注释，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }
        //检查有没有需要用户权限的注解
        if (method.isAnnotationPresent(LoginToken.class)) {
            LoginToken loginToken = method.getAnnotation(LoginToken.class);
            if (loginToken.required()) {
                // 从cookie中取出token
                Cookie cookie = Utils.getCookieByName(request, "token");

                String url = request.getRequestURL().toString();
                String param = request.getQueryString();
                String redirectURL = "";
                if (param != null) {
                    redirectURL = url + "?" + param;
                } else {
                    redirectURL = url;
                }
                // 执行认证
                if (cookie == null) {
                    response.sendRedirect("/login?redirectURL=" + redirectURL);
                    throw new RuntimeException("无登录令牌，请重新登录");
                }
                String token = cookie.getValue();
                // 获取 token 中的 user id
                String userId = null;
                try {
                    userId = JWT.decode(token).getAudience().get(0);
                } catch (JWTDecodeException j) {
                    response.sendRedirect("/login?redirectURL=" + redirectURL);
                    throw new RuntimeException("解码登录令牌异常");
                }
                Integer id = Integer.valueOf(userId);
                UserInfo userInfo = userService.getUser(id);
                if (userInfo == null) {
                    response.sendRedirect("/login?redirectURL=" + redirectURL);
                    throw new RuntimeException("非法登录令牌：无该用户");
                }
                // 验证 token 的签名
                JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("screct")).build();
                try {
                    jwtVerifier.verify(token);
                } catch (JWTVerificationException e) {
                    response.sendRedirect("/login?redirectURL=" + redirectURL);
                    throw new RuntimeException("非法登录令牌：密匙错误");
                }
                return true;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
