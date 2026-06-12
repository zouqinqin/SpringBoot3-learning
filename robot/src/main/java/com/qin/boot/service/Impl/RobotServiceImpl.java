package com.qin.boot.service.Impl;

import com.qin.boot.properties.RobotProperties;
import com.qin.boot.service.RobotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RobotServiceImpl implements RobotService {

    @Autowired
    RobotProperties properties;



    @Override
    public String getRobotMessage() {
        return "你好，"+properties.getName() +"年龄："+properties.getAge();
    }


}
