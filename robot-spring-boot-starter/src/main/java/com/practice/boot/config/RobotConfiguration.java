package com.practice.boot.config;

import com.practice.boot.controller.HelloRobotController;
import com.practice.boot.properties.RobotProperties;
import com.practice.boot.service.RobotService;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import({HelloRobotController.class, RobotService.class, RobotProperties.class})
@Configuration
public class RobotConfiguration {

}
