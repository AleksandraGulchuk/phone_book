package com.hillel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RemoveContactMenuAction implements MenuAction {
    @Override
    public void doAction(ContactsService contactsService) throws IOException {
        int index = 0;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
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
