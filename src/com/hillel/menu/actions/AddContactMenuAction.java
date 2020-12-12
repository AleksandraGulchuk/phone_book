package com.hillel.menu.actions;

import com.hillel.contacts.Contact;
import com.hillel.service.ContactsService;
import com.hillel.menu.MenuAction;

import java.io.BufferedReader;
import java.io.IOException;

public class AddContactMenuAction implements MenuAction {

    private final ContactsService contactsService;
    private final BufferedReader reader;

    public AddContactMenuAction(ContactsService contactsService, BufferedReader reader) {
        this.contactsService = contactsService;
        this.reader = reader;
    }

    private boolean checkPhone(String phone) {
        return phone.matches("\\+38\\d{10}");
    }

    private String getPhone() throws IOException {
        while (true) {
            System.out.println("Введите номер телефона: ");
            String phone = reader.readLine();
            boolean isPhoneChecked = checkPhone(phone);
            if (isPhoneChecked) return phone;
            System.out.println("Вы ввели некорректное значение!");
        }
    }

    @Override
    public void doAction() throws IOException {
        System.out.println("Введите имя контакта: ");
        String name = reader.readLine();
        String phone = getPhone();
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
