package com.hillel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RemoveContactMenuAction implements MenuAction {
    @Override
    public void doAction(ContactsService contactsService) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите номер контакта: ");
        int index = Integer.parseInt(reader.readLine());
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
