package com.hillel.menu.actions;

import com.hillel.contacts.ContactsList;
import com.hillel.menu.MenuAction;
import com.hillel.service.ContactsService;

import java.io.BufferedReader;
import java.io.IOException;

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
        ContactsList foundContacts = contactsService.searchByName(reader.readLine());
        if (foundContacts == null) {
            System.out.println("Контакт не найден");
        } else {
            System.out.println("Найдены контакты:\n" + foundContacts);
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
