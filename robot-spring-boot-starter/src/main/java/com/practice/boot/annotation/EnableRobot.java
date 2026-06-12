package com.practice.boot.annotation;

import com.practice.boot.config.RobotConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.DelegatingWebMvcConfiguration;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(RobotConfiguration.class)
public @interface EnableRobot {
}
