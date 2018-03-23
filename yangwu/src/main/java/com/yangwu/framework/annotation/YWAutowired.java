package com.yangwu.framework.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target({FIELD,ElementType.CONSTRUCTOR,ElementType.METHOD})
public @interface YWAutowired {

}
