package com.hillel.contacts;

import java.util.Arrays;

public class ContactsList {
    private Contact[] contacts = new Contact[8];
    private int size = 0;

    public void add(Contact contact) {
        if (size == contacts.length) {
            contacts = Arrays.copyOf(contacts, (int) Math.ceil(size * 1.5));
        }
        contacts[size] = contact;
        size++;
    }

    public Contact get(int index) {
        if (isIndex(index)) {
            return contacts[index];
        }
        return null;
    }

    public Contact remove(int index) {
        if (isIndex(index)) {
            Contact contact = contacts[index];
            Contact[] temp = new Contact[contacts.length - 1];
            for (int i = 0; i < size; i++) {
                temp[i] = contacts[i < index ? i : i + 1];
            }
            contacts = temp;
            size--;
            return contact;
        }
        return null;
    }

    public void set(int index, Contact contact) {
        if (isIndex(index)) {
            contacts[index] = contact;
        }
    }

    public int size() {
        return size;
    }

    public boolean contains(Contact contact) {
        for (int i = 0; i < size; i++) {
            if (contact.equals(contacts[i])) {
                return true;
            }
        }
        return false;
    }

    private boolean isIndex(int index) {
        if (index >= size || index < 0) {
            System.out.println("Контакта с таким порядковым номером не существует!");
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        for (int i = 0; i < size; i++) {
            string.append(i + 1).append(". ").append(contacts[i]).append("\n");
        }
        return string.toString();
    }

}
