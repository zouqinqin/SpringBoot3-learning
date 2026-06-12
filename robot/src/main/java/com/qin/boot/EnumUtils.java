package com.qin.boot;

import com.qin.boot.service.BaseEnum;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EnumUtils {

    // code → name（返前端时用）
    public static <E extends Enum<E> & BaseEnum> String getNameByCode(
            Class<E> enumClass, String code) {
        for (E e : enumClass.getEnumConstants()) {
            if (e.getCode().equals(code)) {
                return e.getName();
            }
        }
        return null;
    }

    // name → code（前端传name查询时用）
    public static <E extends Enum<E> & BaseEnum> String getCodeByName(
            Class<E> enumClass, String name) {
        for (E e : enumClass.getEnumConstants()) {
            if (e.getName().equals(name)) {
                return e.getCode();
            }
        }
        return null;
    }

    // 获取所有枚举（给前端下拉选项用）
    public static <E extends Enum<E> & BaseEnum> List<Map<String, String>> toList(Class<E> enumClass) {
        return Arrays.stream(enumClass.getEnumConstants())
                .map(e -> {
                    Map<String, String> map = new LinkedHashMap<>();
                    map.put("code", e.getCode());
                    map.put("name", e.getName());
                    return map;
                })
                .collect(Collectors.toList());
    }
}
