package com.hillel.service;

import com.hillel.contacts.Contact;
import com.hillel.contacts.ContactsList;

import java.io.IOException;

public interface ContactsService {

    ContactsList getAll() throws IOException;

    void remove(int index) throws IOException;

    void add(Contact contact) throws IOException;

    ContactsList searchByName(String nameStartsWith) throws IOException;

    ContactsList searchByPhone(String phonePart) throws IOException;

}
