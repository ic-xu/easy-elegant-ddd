package com.opensource.easyddd.infrastructure.filter;



import com.alibaba.fastjson.JSON;
import com.opensource.easyddd.infrastructure.auth.UserBaseInfo;
import com.opensource.easyddd.infrastructure.constant.HeaderConstant;
import com.opensource.easyddd.infrastructure.dto.BaseResponse;
import com.opensource.easyddd.infrastructure.utils.UserInfoUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StreamUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;



/**
 * @author chenxu
 */
@Component
@Slf4j
public class AuthenticatorFilter extends OncePerRequestFilter {
    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();
//    @Resource
//    private UserInfoManager userInfoManager;

    private static final String JSON_CONTENT_TYPE = "application/json";


    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,  HttpServletResponse response,  FilterChain filterChain) throws ServletException, IOException {
        ParameterRequestWrapper requestWrapper = new ParameterRequestWrapper(httpServletRequest);
        String method = httpServletRequest.getMethod();
        String host = httpServletRequest.getRemoteHost();
        String headers = getRequestHeaders(httpServletRequest);
        String parameterMap = "";
        String requestBody = "";
        if (httpServletRequest.getParameterMap() != null && !httpServletRequest.getParameterMap().isEmpty()) {
            parameterMap = JSON.toJSONString(httpServletRequest.getParameterMap());
        }
        String contentType = httpServletRequest.getContentType();
        if (("POST".equalsIgnoreCase(httpServletRequest.getMethod()) || "PUT".equalsIgnoreCase(httpServletRequest.getMethod())) && contentType != null && contentType.contains(JSON_CONTENT_TYPE)) {
            requestBody = getRequestBody(httpServletRequest);
        }
        String requestURI = httpServletRequest.getRequestURI();
        String requestLog = String.format("request: [traceId:%s, host:%s, method:%s, uri:%s, headers:%s, parameterMap:%s, request body:%s]", httpServletRequest.getHeader("x-apm-traceid"), host, method, requestURI, headers, parameterMap, requestBody);
        log.info("请求参数{}", requestLog);

        //判断是否需要进行token验证，/open/** 的路径不需要校验，auth/** 的路径需要校验
        if (check(requestURI) && !excludePath(requestURI)) {
            String authorization = httpServletRequest.getHeader(HeaderConstant.AUTHORIZATION);
            if (null == authorization) {
                response.setCharacterEncoding(httpServletRequest.getCharacterEncoding());
                response.setContentType("application/json; charset=utf-8");
                PrintWriter writer = response.getWriter();
                response.setStatus(401);
                writer = writer.append(JSON.toJSONString(BaseResponse.error(4011000, "This request is unauthorized.")));
                writer.flush();
                writer.close();
            } else {
                /*1.获取加密串,进行解密*/
                UserBaseInfo userBaseInfo = null;
                try {
                    userBaseInfo = auth(authorization);
                    /*2.解密出加密串，解析出userId 和userName 两个参数，并存入 request 的参数中 */
                    ParameterRequestWrapper request = UserInfoUtils.wrapperUserInfo2Request(requestWrapper, authorization, userBaseInfo);
                    filterChain.doFilter(request, response);
                } catch (Exception e) {
                    log.error("请求认证失败 \n {}", e.getMessage());
                    response.setCharacterEncoding(httpServletRequest.getCharacterEncoding());
                    response.setContentType("application/json; charset=utf-8");
                    PrintWriter writer = response.getWriter();
                    response.setStatus(401);
                    writer = writer.append(JSON.toJSONString(BaseResponse.error(4011000, "This request is unauthorized.")));
                    writer.flush();
                    writer.close();
                }
            }
        } else {
            filterChain.doFilter(requestWrapper, response);
        }

    }

    /**
     * 获取request中的所有的headers参数
     *
     * @param request
     * @return
     */
    private String getRequestHeaders(HttpServletRequest request) {
        Map<String, String> headersMap = new HashMap<>();
        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            headersMap.put(key, value);
        }
        return headersMap.toString();
    }

    private String getRequestBody(HttpServletRequest request) {
        byte[] bodyBytes;
        try {
            bodyBytes = StreamUtils.copyToByteArray(request.getInputStream());
            return new String(bodyBytes, request.getCharacterEncoding());
        } catch (IOException e) {
            try {
                throw new RuntimeException("request body parse error");
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }

    }

    private UserBaseInfo auth(String userToken) {
//        return userInfoManager.getUserBaseInfoBy(userToken);

        return null;
    }







    private boolean excludePath(String requestUri) {
       String[] noAuthPath = new  String[]{"/api/open/**"};
        for (String url : noAuthPath) {
            boolean match = PATH_MATCHER.match(url, requestUri);
            if (match) {
                return true;
            }
        }
        return false;
    }




    public boolean check(String requestUri) {
        String[] authPaths = new String[]{"/api/auth/**"};
        for (String url : authPaths) {
            boolean match = PATH_MATCHER.match(url, requestUri);
            if (match) {
                return true;
            }
        }
        return false;
    }






}
