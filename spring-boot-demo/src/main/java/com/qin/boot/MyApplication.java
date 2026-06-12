package com.qin.boot;

import com.practice.boot.annotation.EnableRobot;
import com.practice.boot.config.RobotConfiguration;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

//@Import(RobotConfiguration.class)
@SpringBootApplication
//@EnableRobot
public class MyApplication {

    /**
     * 启动扫描的 包     com.qin.boot
     *
     * starter 扫描的包 com.practice.boot
     *
     *
     * @param args
     */

    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class, args);
    }

}
