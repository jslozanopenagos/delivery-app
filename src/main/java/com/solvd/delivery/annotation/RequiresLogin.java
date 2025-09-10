package com.solvd.delivery.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RequiresLogin {
    String[] roles() default {};
}
