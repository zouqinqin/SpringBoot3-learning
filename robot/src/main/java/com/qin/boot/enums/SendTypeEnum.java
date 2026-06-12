package com.qin.boot.enums;

import com.qin.boot.service.BaseEnum;

public enum SendTypeEnum implements BaseEnum {
    BILL_TYPE("billEmail","账单邮件"),
    DUE_BLL_TYPE("dueBillEmail","催逾期邮件");

    private final String code;
    private final String name;

    SendTypeEnum( String code,String name) {
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
