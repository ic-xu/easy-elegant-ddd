package com.opensource.easyddd.infrastructure.utils;

import com.opensource.easyddd.infrastructure.auth.UserBaseInfo;
import com.opensource.easyddd.infrastructure.constant.HeaderConstant;
import com.opensource.easyddd.infrastructure.filter.ParameterRequestWrapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Objects;

/**
 * 包名：com.maxhub.os.infrastructure.utils
 * 文件名：UserInfoUtils.class
 * 版权：Copyright by CVTE 公司
 * 描述：
 * 修改人：陈旭(chenxu9741@cvte.com)
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 *
 * @author 陈旭
 * @group [][] xx
 * @date 2022-12-22 19:29
 **/
public class UserInfoUtils {

    public static UserBaseInfo getUserBaseInfo(HttpServletRequest request) {
        UserBaseInfo userBaseInfo = new UserBaseInfo();
        userBaseInfo.setUserId(request.getHeader("x-user-id"));
        userBaseInfo.setDisplayName(request.getHeader("x-user-display-name"));
        userBaseInfo.setAvatar(request.getHeader("x-user-avatar"));
        return userBaseInfo;
    }


    public static UserBaseInfo getUserBaseInfo() {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        UserBaseInfo userBaseInfo = new UserBaseInfo();
        userBaseInfo.setUserId(request.getHeader("x-user-id"));
        userBaseInfo.setDisplayName(request.getHeader("x-user-display-name"));
        userBaseInfo.setAvatar(request.getHeader("x-user-avatar"));
        return userBaseInfo;
    }


    public static String getUserToken() {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        return request.getHeader(HeaderConstant.USER_TOKEN);
    }



    public static String getAuthorization(){
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        return request.getHeader(HeaderConstant.AUTHORIZATION);
    }



    public static ParameterRequestWrapper wrapperUserInfo2Request(ParameterRequestWrapper request, String userToken, UserBaseInfo userBaseInfo) {
        request.addHeader("x-user-id", userBaseInfo.getUserId());
        request.addHeader(HeaderConstant.AUTHORIZATION, userToken);
        String bearerToken = request.getHeader(HeaderConstant.AUTHORIZATION);
        if (!ObjectUtils.isEmpty(bearerToken)) {
            String[] tokenArr = bearerToken.split(" ");
            if (tokenArr.length > 1) {
                request.addHeader(HeaderConstant.USER_TOKEN, tokenArr[1]);
            }
        }
        request.addHeader("x-user-avatar", userBaseInfo.getAvatar());
        request.addHeader("x-user-display-name", userBaseInfo.getDisplayName());
        return request;
    }

}
