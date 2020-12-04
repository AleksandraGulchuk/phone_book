package com.hillel;

public class ExitMenuAction implements MenuAction {

    @Override
    public void doAction(ContactsService contactsService) {
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
