package com.practice.boot.service;

import com.practice.boot.properties.RobotProperties;
import org.springframework.beans.factory.annotation.Autowired;

public class  RobotService {

    @Autowired
    RobotProperties properties;

    public String getRobotMessage() {
        return "你好，"+properties.getName() +"年龄："+properties.getAge();
    }
}
