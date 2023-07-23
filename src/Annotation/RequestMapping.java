package Annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.ElementType;

@Target(value ={ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestMapping {

	String value() default "index";
	
}
