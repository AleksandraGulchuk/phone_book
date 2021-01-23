package com.hillel.menu.actions;

import com.hillel.contacts.Contact;
import com.hillel.contacts.Type;
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

    private Type getType(String phone){
        if (phone.matches("\\+38\\d{10}")) return Type.PHONE;
        if (phone.matches("\\w+@\\w+\\.\\w+")) return Type.EMAIL;
        return null;
    }

    @Override
    public void doAction() throws IOException {
        System.out.println("Введите имя контакта: ");
        String name = reader.readLine();
        System.out.println("Введите номер телефона или адрес электронной почты: ");
        String phone = reader.readLine();
        Type type = getType(phone);
        if(type == null) {
            System.out.println("Вы ввели некорректное значение!");
        } else {
            contactsService.add(new Contact(name, type, phone));
        }
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
