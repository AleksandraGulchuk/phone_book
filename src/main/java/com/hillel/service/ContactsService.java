package com.hillel.service;

import com.hillel.contacts.Contact;

import java.io.IOException;
import java.util.List;

public interface ContactsService {

    List<Contact> getAll();

    void remove(int index);

    void add(Contact contact);

    List<Contact> searchByName(String nameStartsWith);

    List<Contact> searchByPhone(String phonePart);

}
