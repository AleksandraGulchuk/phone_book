package com.hillel;

import java.io.IOException;

public interface ContactsService {

    ContactsList getAll() throws IOException;

    void remove(int index) throws IOException;

    void add(Contact contact) throws IOException;
}
