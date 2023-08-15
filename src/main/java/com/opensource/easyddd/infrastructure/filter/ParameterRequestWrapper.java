package com.opensource.easyddd.infrastructure.filter;


import jakarta.servlet.ReadListener;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;

import java.io.*;
import java.util.*;

/**
 * request.parameter
 *
 * @author SanLi
 * Created by 2689170096@qq.com/SanLi on 2018/1/28
 */
@Slf4j
public class ParameterRequestWrapper extends HttpServletRequestWrapper {

    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    /**
     * 存放参数
     */
    private Map<String, String[]> params = new HashMap<>();

    /**
     * 存放请求头
     */
    private Map<String, String> headers = new HashMap<>();


    /**
     * Constructs a request object wrapping the given request.
     *
     * @param request request
     * @throws IllegalArgumentException if the request is null
     */
    public ParameterRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        IOUtils.copy(request.getInputStream(),byteArrayOutputStream);
        //将参数表，赋予给当前的Map以便于持有request中的参数
        this.params.putAll(request.getParameterMap());
        Enumeration<String> headerkeys = request.getHeaderNames();
        while (headerkeys.hasMoreElements()){
            String headKey = headerkeys.nextElement();
            this.headers.put(headKey,request.getHeader(headKey));
        }
    }


    /**
     * 在获取所有的参数名,必须重写此方法，否则对象中参数值映射不上
     *
     * @return
     */
    @Override
    public Enumeration<String> getParameterNames() {
        return new Vector<>(params.keySet()).elements();
    }


    /**
     * 重写getParameter方法
     *
     * @param name 参数名
     * @return 返回参数值
     */
    @Override
    public String getParameter(String name) {
        String[] values = params.get(name);
        if (values == null || values.length == 0) {
            return null;
        }
        return values[0];
    }

    @Override
    public String[] getParameterValues(String name) {
        String[] values = params.get(name);
        if (values == null || values.length == 0) {
            return null;
        }
        return values;
    }


    /**
     * add a header with given name and value
     *
     * @param name
     * @param value
     */
    public void addHeader(String name, String value) {
        headers.put(name, value);
    }
    @Override
    public String getHeader(String name) {
        String headerValue = super.getHeader(name);
        if (headers.containsKey(name)) {
            headerValue = headers.get(name);
        }
        return headerValue;
    }
    /**
     * get the Header names
     */
    @Override
    public Enumeration<String> getHeaderNames() {
        List<String> names = Collections.list(super.getHeaderNames());
        names.addAll(headers.keySet());
        return Collections.enumeration(names);
    }
    @Override
    public Enumeration<String> getHeaders(String name) {
        List<String> values = Collections.list(super.getHeaders(name));
        if (headers.containsKey(name)) {
            values = Arrays.asList(headers.get(name));
        }
        return Collections.enumeration(values);
    }


    /**
     * 增加多个参数
     *
     * @param otherParams 增加的多个参数
     */
    public void addAllParameters(Map<String, Object> otherParams) {
        for (Map.Entry<String, Object> entry : otherParams.entrySet()) {
            addParameter(entry.getKey(), entry.getValue());
        }
    }

    /**
     * 增加参数
     *
     * @param name  参数名
     * @param value 参数值
     */
    public void addParameter(String name, Object value) {
        if (value != null) {
            if (value instanceof String[]) {
                params.put(name, (String[]) value);
            } else if (value instanceof String) {
                params.put(name, new String[]{(String) value});
            } else {
                params.put(name, new String[]{String.valueOf(value)});
            }
        }
    }


    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    @Override
    public ServletInputStream getInputStream() {
        final ByteArrayInputStream bais = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        return new ServletInputStream() {

            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {

            }

            @Override
            public int read() {
                return bais.read();
            }
        };
    }
}
