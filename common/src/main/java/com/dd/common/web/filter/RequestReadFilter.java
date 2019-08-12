package com.dd.common.web.filter;

import com.alibaba.fastjson.JSON;
import com.dd.common.web.ResettableRequestWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

public class RequestReadFilter implements Filter {

    private final Logger logger = LoggerFactory.getLogger(RequestReadFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // 对request进行包装，使得请求体可重复读
        request = wrap(request);

        // 格式化请求参数
        String parameter = formatParameter(request.getParameterMap());

        // 当请求体内容格式为JSON时获取请求体
        String body = null;
        if ("application/json".equals(request.getContentType())) {
            body = getJsonBody(request.getInputStream());
        }

        StringBuilder infoStringBuilder = new StringBuilder();
        infoStringBuilder.append("Request Info Start:\n")
                .append("Path: ").append(request.getServletPath()).append("\n")
                .append("Method: ").append(request.getMethod()).append("\n")
                .append("ContentType: ").append(request.getContentType()).append("\n")
                .append("Parameter: ").append(parameter);
        if ("application/json".equals(request.getContentType())) {
            infoStringBuilder.append("\nBody: ").append(body);
        }

        logger.info(infoStringBuilder.toString());

        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }

    private ResettableRequestWrapper wrap(HttpServletRequest request) {
        ResettableRequestWrapper requestWrapper = null;
        try {
            requestWrapper = new ResettableRequestWrapper(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return requestWrapper;
    }

    private String formatParameter(Map<String, String[]> parameterMap) {
        String parameter = null;
        if (parameterMap != null && parameterMap.size() > 0) {
            parameter = JSON.toJSONString(parameterMap);
        }
        return parameter;
    }

    private String getJsonBody(InputStream in) throws IOException {
        StringBuilder bodyStringBuilder = new StringBuilder();
        String line;
        BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
        while ((line = reader.readLine()) != null) {
            bodyStringBuilder.append(line);
        }
        return bodyStringBuilder.length() == 0 ? null : bodyStringBuilder.toString();
    }
}
