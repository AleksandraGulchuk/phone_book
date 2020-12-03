package com.hillel;

public interface ContactsService {

    public ContactsList getAll();

    public void remove(int index);

    public void add(Contact contact);
}
