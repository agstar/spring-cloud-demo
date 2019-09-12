package com.it.demosso.controller;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author RCL
 * @date 2017/6/30 18:10
 * @description 操作日志类 使用aop拦截自定义注解
 */
@Aspect
@Component
public class OperateLogAop {

    @Around(value = "@annotation(logAop)")
    public Object interceptorLog(ProceedingJoinPoint pj, LogAop logAop) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        ServletWebRequest servletWebRequest=new ServletWebRequest(request);
        HttpServletResponse response=servletWebRequest.getResponse();
        System.out.println(response);
        HttpServletResponse response2 = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        System.out.println(response2);
        //ServletWebRequest servletWebRequest = new ServletWebRequest(request);
        //String userName = servletWebRequest.getRemoteUser();
        //String what = servletWebRequest.getUserPrincipal().getName();
        //System.out.println(what + "<--这是什么用户名?是用户登录账号：" + userName);
        //要想取出user对象，需要在session中为期赋值
        // System.out.println("用户名：" + user.getUsernamezh());
        //System.out.println("操作内容：" + logAop.content());
        // System.out.println(pj.getSignature().getName());

        //pj.getTarget();//获取到代理对象
        //pj.getArgs(); //获取到目标方法的参数列表
        //pj.getSignature();//获取到目标方法，其中包含一些信息
        //System.out.println("Aop start");
        Object result = null;
        try {
            //原本方法操作
            result = pj.proceed(pj.getArgs());
        } catch (Exception e) {
            // 异常处理记录日志..log.error(e);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        //System.out.println(methodRemark + "---------------");
        //System.out.println("Aop end");
        return result;
    }

    // 获取方法的中文备注____用于记录用户的操作日志描述
    public static String getMthodRemark(ProceedingJoinPoint joinPoint)
            throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();//拦截的类型
        System.out.println("-------" + targetName + "====是什么");
        String methodName = joinPoint.getSignature().getName();
        System.out.println("====调用" + methodName + "方法-开始！");
        Object[] arguments = joinPoint.getArgs();   //获得参数列表
        System.out.println("打印出方法调用时传入的参数，可以在这里通过添加参数的类型，进行一些简易逻辑处理和判断");
        if (arguments.length <= 0) {
            System.out.println("=== " + methodName + " 方法没有参数");
        } else {
            for (int i = 0; i < arguments.length; i++) {
                Object obj = arguments[i];
                if (obj instanceof Integer) {
                    System.out.println("操作参数Integer类型");
                }
                System.out.println("==== 参数   " + (i + 1) + " : " + arguments[i]);
            }
        }
        //System.out.println("注解参数貌似不需要");
        Class targetClass = Class.forName(targetName);
        Method[] method = targetClass.getMethods();
        String methode = "";
        for (Method m : method) {
            if (m.getName().equals(methodName)) {
                Class[] tmpCs = m.getParameterTypes();
                if (tmpCs.length == arguments.length) {
                    LogAop methodCache = m.getAnnotation(LogAop.class);
                    methode = methodCache.content();
                    break;
                }
            }
        }
        return methode;
    }


}
