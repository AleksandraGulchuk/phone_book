package com.hillel.contacts;

public enum Type {
    PHONE ("phone"),
    EMAIL("email");
    private final String value;

    Type(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
