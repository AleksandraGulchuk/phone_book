package com.hillel.contacts;

import lombok.Value;

@Value
public class Contact {

    String name;
    Type type;
    String phone;

    @Override
    public String toString() {
        return name + "[" + type.getValue() + ":" + phone + "]";
    }
}
