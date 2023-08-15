package com.opensource.easyddd.infrastructure.config.apilog.log;

import lombok.Data;
import lombok.ToString;

/**
 * 包名：com.maxhub.link.code.log
 * 文件名：SystemLog.class
 * 版权：Copyright by CVTE 公司
 * 描述：
 * 修改人：陈旭(chenxu9741@cvte.com)
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 *
 * @author 陈旭
 * @date 2022-11-11 19:41
 **/

@Data
@ToString
public class SysLog {


    private static final long serialVersionUID=1L;

    /**
     * id
     */
    private Integer sysLogId;

    /**
     * 系统用户ID
     */
    private String userId;

    /**
     * 用户姓名
     */
    private String userName;

    /**
     * 用户IP
     */
    private String userIp;

    /**
     * 所属系统： MGR-运营平台, MCH-商户中心
     */
    private String sysType;

    /**
     * 方法名
     */
    private String methodName;

    /**
     * 方法描述
     */
    private String methodRemark;

    /**
     * 请求地址
     */
    private String reqUrl;

    /**
     * 请求头参数
     */
    private Object optReqHeader;

    /**
     * 操作请求参数
     */
    private Object optReqParam;

    /**
     * 操作响应结果
     */
    private Object optResInfo;

    /**
     * 创建时间
     */
    private String createdAt;


    /**
     * 响应耗时
     */
    private Long timeConsuming;


}
