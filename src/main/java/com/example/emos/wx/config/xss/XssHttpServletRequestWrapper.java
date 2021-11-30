package com.example.emos.wx.config.xss;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HtmlUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.LinkedHashMap;
import java.util.Map;

public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {
    public XssHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public String getParameter(String name) {
        String value = super.getParameter(name);
        if (!StrUtil.hasEmpty(value)) {
            value = HtmlUtil.filter(value);
        }
        return value;
    }

    @Override
    public String[] getParameterValues(String name) {
        String[] values = super.getParameterValues(name);
        if (values != null) {
            for (int i = 0; i < values.length; i++) {
                String value = values[i];
                if (!StrUtil.hasEmpty(value)) {
                    value = HtmlUtil.filter(value);
                }
                values[i] = value;
            }
        }
        return values;
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        Map<String, String[]> parameter = super.getParameterMap();
        Map<String, String[]> map = new LinkedHashMap<>();
        if (parameter != null){
            for (String key : parameter.keySet()) {
                String[] values = parameter.get(key);
                for (int i = 0; i < values.length; i++) {
                    String value = values[i];;
                    if (!StrUtil.hasEmpty(value)){
                        value = HtmlUtil.filter(value);
                    }
                    values[i]=value;
                }
                map.put(key, values);
            }
        }
        return map;
    }

    @Override
    public String getHeader(String name){
        String value = super.getHeader(name);
        if (!StrUtil.hasEmpty(name)){
            value = HtmlUtil.filter(value);
        }
        return value;
    }

}
