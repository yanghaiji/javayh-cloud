package com.javayh.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * @author Dylan Yang
 * @Description: MyLocaleResolver
 * @Title: MyLocaleResolver
 * @ProjectName javayh-oauth2
 * @date 2019/6/18 16:07
 */
public class MyLocaleResolver implements LocaleResolver {

    @Override
    public Locale resolveLocale(HttpServletRequest httpServletRequest) {
        String l = httpServletRequest.getParameter("l");
        Locale locale = Locale.getDefault();//默认语言
        if(!StringUtils.isEmpty(l)){
            String[] split =l.split("_");
            locale = new Locale(split[0],split[1]);//split[0]语言，
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale) {

    }

}

