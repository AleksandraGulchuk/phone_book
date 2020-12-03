package com.hillel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AddContactMenuAction implements MenuAction {


    @Override
    public void doAction(ContactsService contactsService) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
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
