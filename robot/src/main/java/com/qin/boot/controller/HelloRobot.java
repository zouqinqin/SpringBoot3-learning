package com.qin.boot.controller;

import com.qin.boot.service.RobotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloRobot {

    @Autowired
    RobotService robotService;

    @RequestMapping("/robot/hello")
    public String hello() {
        return robotService.getRobotMessage();
    }


}
