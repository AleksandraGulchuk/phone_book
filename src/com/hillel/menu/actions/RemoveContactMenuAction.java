package com.hillel.menu.actions;

import com.hillel.service.ContactsService;
import com.hillel.menu.MenuAction;

import java.io.BufferedReader;
import java.io.IOException;

public class RemoveContactMenuAction implements MenuAction {

    private final ContactsService contactsService;
    private final BufferedReader reader;

    public RemoveContactMenuAction(ContactsService contactsService, BufferedReader reader) {
        this.contactsService = contactsService;
        this.reader = reader;
    }

    @Override
    public void doAction() throws IOException {
        System.out.println("Введите порядковый номер контакта: ");
        try {
            int index = Integer.parseInt(reader.readLine());
            contactsService.remove(index);
        } catch (NumberFormatException exception) {
            System.out.println("Вы ввели некорректное значение!");
        }
    }

    @Override
    public String getName() {
        return "Удалить контакт";
    }

    @Override
    public boolean closeAfter() {
        return false;
    }
}
