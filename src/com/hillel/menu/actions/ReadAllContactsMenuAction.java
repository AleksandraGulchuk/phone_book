package com.hillel.menu.actions;

import com.hillel.contacts.ContactsList;
import com.hillel.service.ContactsService;
import com.hillel.menu.MenuAction;

import java.io.IOException;

public class ReadAllContactsMenuAction implements MenuAction {

    private final ContactsService contactsService;

    public ReadAllContactsMenuAction(ContactsService contactsService) {
        this.contactsService = contactsService;
    }

    @Override
    public void doAction() throws IOException {
        System.out.println("Ваш список контактов:");
        ContactsList contacts = contactsService.getAll();
        if (contacts.size() == 0) {
            System.out.println("Список пуст!");
        } else System.out.println(contactsService.getAll());
    }

    @Override
    public String getName() {
        return "Показать список";
    }

    @Override
    public boolean closeAfter() {
        return false;
    }
}
