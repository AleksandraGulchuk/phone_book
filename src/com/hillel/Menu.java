package com.hillel;

import java.io.BufferedReader;
import java.io.IOException;

public class Menu {

    private MenuAction[] actions;
    private final ContactsService contactsService;
    private BufferedReader reader;

    public Menu(ContactsService contactsService, BufferedReader reader) {
        this.contactsService = contactsService;
        this.reader = reader;
    }

    public void addAction(MenuAction... actions) {
        this.actions = actions;
    }

    public void run() throws IOException {
        int choice;
        boolean isCloseAfter = false;
        while (!isCloseAfter) {
            printActionsName();
            choice = getChoice();
            actions[choice - 1].doAction();
            isCloseAfter = actions[choice - 1].closeAfter();
        }
    }

    private void printActionsName() {
        for (int i = 0; i < actions.length; i++) {
            System.out.println((i + 1) + " - " + actions[i].getName());
        }
    }

    private int getChoice() throws IOException {
        int choice = 0;
        while (choice <= 0 || choice > actions.length) {
            System.out.println("Сделайте свой выбор:");
            try {
                choice = Integer.parseInt(reader.readLine());
            } catch (NumberFormatException exception) {
                System.out.println("Вы ввели некорректное значение!");
            }
        }
        return choice;
    }

}
