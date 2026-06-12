package com.practice.boot.controller;

import com.practice.boot.service.RobotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloRobotController {

    @Autowired
    RobotService robotService;

    @RequestMapping("/robot/hello")
    public String hello() {
        return robotService.getRobotMessage();
    }


}
