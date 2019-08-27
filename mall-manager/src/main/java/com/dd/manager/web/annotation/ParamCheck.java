package com.dd.manager.web.annotation;

import java.lang.annotation.*;

/**
 * 默认做空验证。对于严格验证，需标记format正则格式
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Documented
public @interface ParamCheck {
	/**
	 * 正则格式
	 * @return
	 */
	String [] format() default "";
	/**
	 * 为空校验，默认不为空
	 * @return
	 */
	boolean notNull() default true;
}
