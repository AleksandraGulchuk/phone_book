package com.hillel.service;

import com.hillel.contacts.Contact;
import com.hillel.contacts.ContactsList;

public class InMemoryContactsService implements ContactsService {

    private final ContactsList contactsList = new ContactsList();

    @Override
    public ContactsList getAll() {
        return contactsList;
    }

    @Override
    public void remove(int index) {
        Contact removedContact = contactsList.remove(index - 1);
        if (removedContact != null) {
            System.out.println(removedContact + " удален из телефонной книги.");
        }
    }

    @Override
    public void add(Contact contact) {
        if (contactsList.contains(contact)) {
            System.out.println(contact + " уже существует в телефонной книге!");
        } else {
            contactsList.add(contact);
            System.out.println(contact + " добавлен в телефонную книгу.");
        }
    }
}
