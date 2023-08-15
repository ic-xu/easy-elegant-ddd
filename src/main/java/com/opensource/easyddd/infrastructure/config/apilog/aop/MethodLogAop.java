package com.opensource.easyddd.infrastructure.config.apilog.aop;



import com.fasterxml.jackson.databind.ObjectMapper;

import com.opensource.easyddd.infrastructure.auth.UserBaseInfo;
import com.opensource.easyddd.infrastructure.config.apilog.log.RequestKitBean;
import com.opensource.easyddd.infrastructure.config.apilog.log.SysLog;
import com.opensource.easyddd.infrastructure.utils.UserInfoUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.tomcat.util.threads.TaskThreadFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Objects;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 方法级日志切面组件
 *
 * @author terrfly
 * @modify pangxiaoyu
 * @site https://www.jeequan.com
 * @date 2021-06-07 07:15
 */
@Component
@Aspect
public class MethodLogAop {

    private static final Logger logger = LoggerFactory.getLogger(MethodLogAop.class);


    @Resource
    private RequestKitBean requestKitBean;


    //新人菜鸟实现
   private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");


    /**
     * 异步处理线程池
     */
    private final static ExecutorService executorService = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(100), new TaskThreadFactory("request-log",false,11));

    /**
     * 切点
     */
    @Pointcut("@annotation(MethodLog)")
    public void methodCachePointcut() {}

    /**
     * 切面
     *
     * @param point
     * @return
     * @throws Throwable
     */
    @Around("methodCachePointcut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {

        final SysLog sysLog = new SysLog();
        //处理切面任务 发生异常将向外抛出 不记录日志
        long startTime = System.currentTimeMillis();
        Object result = point.proceed();
        long timeConsuming = System.currentTimeMillis() - startTime;
        try {
            // 基础日志信息
            sysLog.setTimeConsuming(timeConsuming);
            setBaseLogInfo(point, sysLog);
            sysLog.setOptResInfo(result);
            executorService.execute(() -> {
                try {
                    String jsonString = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(sysLog);
                    logger.info("{} >>> \n  {} ", sysLog.getMethodRemark(), jsonString);
                } catch (Exception e) {
                    e.printStackTrace();
                    logger.info("{} >>> \n  {} ", sysLog.getMethodRemark(), sysLog);
                }

            });
        } catch (Exception e) {
            logger.error("methodLogError", e);
        }

        return result;
    }

    /**
     * @author: pangxiaoyu
     * @date: 2021/6/7 14:04
     * @describe: 记录异常操作请求信息
     */
    @AfterThrowing(pointcut = "methodCachePointcut()", throwing = "e")
    public void doException(JoinPoint joinPoint, Throwable e) throws Exception {
        e.printStackTrace();
        final SysLog sysLog = new SysLog();
        // 基础日志信息
        setBaseLogInfo(joinPoint, sysLog);
        sysLog.setOptResInfo(e instanceof Exception ? e.getMessage() : "请求异常");
        executorService.execute(() -> {
            try {
                String jsonString = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(sysLog);
                logger.error("错误请求信息 {} >>> \n  {} ", sysLog.getMethodRemark(), jsonString);
            } catch (Exception ex) {
                ex.printStackTrace();
                logger.error("错误请求信息 {} >>> \n  {} ", sysLog.getMethodRemark(), sysLog);
            }
        });
    }

    /**
     * 获取方法中的中文备注
     *
     * @param joinPoint
     * @return
     * @throws Exception
     */
    public static String getAnnotationRemark(JoinPoint joinPoint) throws Exception {

        Signature sig = joinPoint.getSignature();
        Method m = joinPoint.getTarget().getClass().getMethod(joinPoint.getSignature().getName(), ((MethodSignature) sig).getParameterTypes());

        MethodLog methodCache = m.getAnnotation(MethodLog.class);
        if (methodCache != null) {
            return methodCache.remark();
        }
        return "";
    }

    /**
     * @author: pangxiaoyu
     * @date: 2021/6/7 14:12
     * @describe: 日志基本信息 公共方法
     */
    private void setBaseLogInfo(JoinPoint joinPoint, SysLog sysLog) throws Exception {
        // 使用point.getArgs()可获取request，仅限于spring MVC参数包含request，改为通过contextHolder获取。
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        try {
            UserBaseInfo userInfo = UserInfoUtils.getUserBaseInfo(request);

            sysLog.setUserId(userInfo.getUserId());

            sysLog.setUserName(userInfo.getDisplayName());
        } catch (Exception ignore) {
        }
        //请求参数
        sysLog.setOptReqParam(requestKitBean.getReqParamJson());
        //请求头参数
        sysLog.setOptReqHeader(requestKitBean.getRequestHeader());
        //注解备注
        sysLog.setMethodRemark(getAnnotationRemark(joinPoint));
        //包名 方法名
        String methodName = joinPoint.getSignature().getName();
        String packageName = joinPoint.getThis().getClass().getName();
        if (packageName.indexOf("$$EnhancerByCGLIB$$") > -1 || packageName.indexOf("$$EnhancerBySpringCGLIB$$") > -1) { // 如果是CGLIB动态生成的类
            packageName = packageName.substring(0, packageName.indexOf("$$"));
        }
        sysLog.setMethodName(packageName + "." + methodName);
        sysLog.setReqUrl(request.getRequestURL().toString());
        sysLog.setUserIp(requestKitBean.getClientIp());
        sysLog.setCreatedAt(dateFormat.format(System.currentTimeMillis()));

    }

}
