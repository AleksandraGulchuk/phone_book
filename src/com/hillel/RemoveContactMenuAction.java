package com.hillel;

import java.io.BufferedReader;
import java.io.IOException;

public class RemoveContactMenuAction implements MenuAction {

    private final ContactsService contactsService;
    private BufferedReader reader;

    public RemoveContactMenuAction(ContactsService contactsService, BufferedReader reader) {
        this.contactsService = contactsService;
        this.reader = reader;
    }

    @Override
    public void doAction() throws IOException {
        int index = 0;
        while (index == 0) {
            System.out.println("Введите порядковый номер контакта: ");
            try {
                index = Integer.parseInt(reader.readLine());
            } catch (NumberFormatException exception) {
                System.out.println("Вы ввели некорректное значение!");
                index = 0;
            }
        }
        contactsService.remove(index);
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
