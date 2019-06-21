package com.it.demouser.util;

import org.springframework.context.ApplicationContext;

/**
 * spring配置上下文，获取bean
 *
 * @author rcl
 * @date 2018/6/9 21:50
 */
public class SpringContextUtils {

    private static ApplicationContext applicationContext;

    public static void setApplicationContext(ApplicationContext applicationContext) {
        SpringContextUtils.applicationContext = applicationContext;
    }

    public static Object getBean(String beanId) {
        return applicationContext.getBean(beanId);
    }

}
