package com.hillel.menu.actions;

import com.hillel.contacts.Contact;
import com.hillel.service.ContactsService;
import com.hillel.menu.MenuAction;

import java.io.IOException;
import java.util.List;

public class ReadAllContactsMenuAction implements MenuAction {

    private final ContactsService contactsService;

    public ReadAllContactsMenuAction(ContactsService contactsService) {
        this.contactsService = contactsService;
    }

    @Override
    public void doAction() {
        System.out.println("Ваш список контактов:");
        List<Contact> contacts = contactsService.getAll();
        if (contacts.isEmpty()) {
            System.out.println("Список пуст!");
        } else contacts.forEach(contact -> System.out.println(contact));
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
