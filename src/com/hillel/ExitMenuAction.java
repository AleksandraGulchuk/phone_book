package com.hillel;

import java.io.IOException;

public class ExitMenuAction implements MenuAction {
    @Override
    public void doAction(ContactsService contactsService) throws IOException {
        System.out.println("Спасибо! Хорошего дня!");
    }

    @Override
    public String getName() {
        return "Выход";
    }

    @Override
    public boolean closeAfter() {
        return false;
    }
}
