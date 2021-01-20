package com.hillel.service;

import com.hillel.contacts.Contact;

import java.io.IOException;
import java.util.List;

public interface ContactsService {

    List<Contact> getAll() throws IOException;

    void remove(int index) throws IOException;

    void add(Contact contact) throws IOException;

    List<Contact> searchByName(String nameStartsWith) throws IOException;

    List<Contact> searchByPhone(String phonePart) throws IOException;

}
