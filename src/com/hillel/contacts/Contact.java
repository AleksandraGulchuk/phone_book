package com.hillel.contacts;

import java.util.Arrays;

public class Contact {

    private final String name;
    private final String phone;

    public Contact(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return "Имя: " + name + ", телефон: " + phone;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(new String[]{name, phone});
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || obj.getClass() != Contact.class) return false;
        Contact anotherContact = (Contact) obj;
        return name.equals(anotherContact.name) && phone.equals(anotherContact.phone);
    }
}
