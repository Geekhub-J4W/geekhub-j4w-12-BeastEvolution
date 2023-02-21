package com.web.util;

public enum ResponseStatus {
    SUCCESS("ok"),
    FAIL("fail");

    private final String toStringMassage;

    ResponseStatus(String toStringMassage) {
        this.toStringMassage = toStringMassage;
    }


    @Override
    public String toString() {
        return toStringMassage;
    }
}
