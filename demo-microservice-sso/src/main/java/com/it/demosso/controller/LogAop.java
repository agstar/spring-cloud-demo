package com.it.demosso.controller;

import java.lang.annotation.*;

/**
 * @author RCL
 * @date 2017/6/30 18:21
 * 自定义注解 实现操作日志
 * 关于自定义注解 详情可查看百度
 * 在此随便提供一个链接 http://www.cnblogs.com/peida/archive/2013/04/24/3036689.html
 */
@Target(ElementType.METHOD)         //用于描述注解的使用范围（即：被描述的注解可以用在什么地方） 此处用于描述方法
@Retention(RetentionPolicy.RUNTIME) //表示需要在什么级别保存该注释信息，用于描述注解的生命周期（即：被描述的注解在什么范围内有效） RUNTIME:在运行时有效（即运行时保留)
@Documented                         //用于描述其它类型的annotation应该被作为被标注的程序成员的公共API，因此可以被例如javadoc此类的工具文档化。Documented是一个标记注解，没有成员
@Inherited                           //是一个标记注解 阐述了某个被标注的类型是被继承的
public @interface LogAop {
    /**
     * 操作内容 @author RCL
     * @date 2017/6/30 18:22
     */
    String content() default "";
}
