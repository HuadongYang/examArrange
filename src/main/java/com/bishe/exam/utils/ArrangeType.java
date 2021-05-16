package com.bishe.exam.utils;

public enum ArrangeType {
    SNO,
    NAME;

    public static ArrangeType getByStr(String str) {
        for (ArrangeType type : values()) {
            if (type.toString().equals(str)) {
                return type;
            }
        }
        return SNO;
    }
}
