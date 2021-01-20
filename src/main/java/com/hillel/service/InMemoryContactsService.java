package com.hillel.service;

import com.hillel.contacts.Contact;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryContactsService implements ContactsService {

    private final List<Contact> contactsList = new ArrayList<>();

    @Override
    public List<Contact> getAll() {
        return contactsList;
    }

    @Override
    public void remove(int index) {
        if (isIndex(index)) {
            Contact removedContact = contactsList.remove(index - 1);
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

    @Override
    public List<Contact> searchByName(String nameStartsWith) {
        List<Contact> contacts = getAll();
        if (contacts.isEmpty()) {
            return contacts;
        }
        return contacts.stream()
                .filter(contact -> contact.getName().startsWith(nameStartsWith))
                .collect(Collectors.toList());
    }

    @Override
    public List<Contact> searchByPhone(String phonePart) {
        List<Contact> contacts = getAll();
        if (contacts.isEmpty()) {
            return contacts;
        }
        return contacts.stream()
                .filter(contact -> contact.getPhone().contains(phonePart))
                .collect(Collectors.toList());
    }

    private boolean isIndex(int index) {
        if (index > contactsList.size() || index <= 0) {
            System.out.println("Контакта с таким порядковым номером не существует!");
            return false;
        }
        return true;
    }
}
