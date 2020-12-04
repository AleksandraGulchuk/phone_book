package com.hillel;

import java.io.IOException;

public class ReadAllContactsMenuAction implements MenuAction {


    @Override
    public void doAction(ContactsService contactsService) throws IOException {
        System.out.println("Ваш список контактов:");
        System.out.println(contactsService.getAll());
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
