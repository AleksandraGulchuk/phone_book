package com.hillel;

import java.io.BufferedReader;
import java.io.IOException;

public class AddContactMenuAction implements MenuAction {

    private final ContactsService contactsService;
    private BufferedReader reader;

    public AddContactMenuAction(ContactsService contactsService, BufferedReader reader) {
        this.contactsService = contactsService;
        this.reader = reader;
    }

    @Override
    public void doAction() throws IOException {
        System.out.println("Введите имя контакта: ");
        String name = reader.readLine();
        System.out.println("Введите номер телефона: ");
        String phone = reader.readLine();
        Contact contact = new Contact(name, phone);
        contactsService.add(contact);
    }

    @Override
    public String getName() {
        return "Добавить контакт";
    }

    @Override
    public boolean closeAfter() {
        return false;
    }
}
