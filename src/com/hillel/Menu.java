package com.hillel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Menu {
    private final ContactsService contactsService;
    private MenuAction[] actions;

    public Menu(ContactsService contactsService) {
        this.contactsService = contactsService;
    }

    public void addAction(MenuAction... actions) {
        this.actions = actions;
    }

    public void run() throws IOException {
        int choice = 0;
        while (choice != actions.length) {
            printActionsName();
            choice = getChoice();
            actions[choice - 1].doAction(contactsService);
        }
    }

    private void printActionsName() {
        for (int i = 0; i < actions.length; i++) {
            System.out.println((i + 1) + " - " + actions[i].getName());
        }
    }

    private int getChoice() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
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
