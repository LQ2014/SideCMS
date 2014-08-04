package com.side.util.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.side.consts.AnnotationConst;

/**
 * 标识一个方法是否能处理请求
 * @author 刘青
 * @date 2014-07-24
 */
@Target (ElementType.METHOD)
@Retention (RetentionPolicy.RUNTIME)
@Documented
public @interface Deal {
	String path();
	String type() default AnnotationConst.DEFAULT_DEAL_TYPE;
	String forward();
	String error() default "";
}
