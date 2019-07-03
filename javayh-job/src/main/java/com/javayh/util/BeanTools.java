package com.javayh.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

/**
 * @author Dylan Yang
 * @Description: TODO
 * @Title: BeanTools
 * @ProjectName javayh-oauth2
 * @date 2019/7/3 11:06
 */
@Configuration
public class BeanTools implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext context) {
        applicationContext = context;
    }

    /**
     * 根据class类型获取bean
     *
     * @param classname
     * @return
     */
    public static <T> T getBean(Class<T> classname) {
        try {
            T bean = applicationContext.getBean(classname);
            return bean;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 根据class类型和名称获取bean
     *
     * @param classname
     * @param name
     * @return
     */
    public static <T> T getBean(Class<T> classname, String name) {
        try {
            T bean = applicationContext.getBean(name, classname);
            return bean;
        } catch (Exception e) {
            return null;
        }
    }

}

