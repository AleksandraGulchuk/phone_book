package com.hillel.menu.actions;

import com.hillel.contacts.Contact;
import com.hillel.menu.MenuAction;
import com.hillel.service.ContactsService;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class SearchByNameMenuAction implements MenuAction {
    private final ContactsService contactsService;
    private final BufferedReader reader;

    public SearchByNameMenuAction(ContactsService contactsService, BufferedReader reader) {
        this.contactsService = contactsService;
        this.reader = reader;
    }

    @Override
    public void doAction() throws IOException {
        System.out.println("Введите начало имени: ");
        List<Contact> foundContacts = contactsService.searchByName(reader.readLine());
        if (foundContacts.isEmpty()) {
            System.out.println("Контакты не найдены.");
        } else {
            System.out.println("Найдены контакты:");
            foundContacts.forEach(contact -> System.out.println(contact));
        }
    }

    @Override
    public String getName() {
        return "Поиск по началу имени";
    }

    @Override
    public boolean closeAfter() {
        return false;
    }
}
