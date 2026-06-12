package com.qin.boot.controller;

import com.qin.boot.EnumUtils;
import com.qin.boot.enums.OrderStatusEnum;
import com.qin.boot.enums.SendTypeEnum;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dict")
public class DictController {

    // 前端调用：GET /dict/orderStatus
    @GetMapping("/{enumName}")
    public List<Map<String, String>> getDict(@PathVariable String enumName) {
        switch (enumName) {
            case "orderStatus":
                return EnumUtils.toList(OrderStatusEnum.class);
            case "sentType":
                return EnumUtils.toList((SendTypeEnum.class));
            default:
                throw new IllegalArgumentException("未知字典类型");
        }
    }



}