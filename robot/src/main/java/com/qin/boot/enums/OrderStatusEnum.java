package com.qin.boot.enums;

import com.qin.boot.service.BaseEnum;

public enum OrderStatusEnum implements BaseEnum {
    PENDING("01","待审批"),
    APPROVED("02","已审核"),
    REJECTED("03","已拒绝");

    private final String code;
    private final String name;

    OrderStatusEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }


    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getName() {
        return name;
    }
}
