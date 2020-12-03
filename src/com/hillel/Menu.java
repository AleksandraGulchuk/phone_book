package com.hillel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Menu {
    private MenuAction[] actions;
    private ContactsService contactsService;

    public Menu(MenuAction[] actions, ContactsService contactsService) {
        this.actions = actions;
        this.contactsService = contactsService;
    }

    public void doAction(int choice) throws IOException {
        actions[choice - 1].doAction(contactsService);
    }

    public void run() {
        for (int i = 0; i < actions.length; i++) {
            System.out.println((i + 1) + " - " + actions[i].getName());
        }
    }

    public int getChoice() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int choice = 0;
        while (choice <= 0 || choice > actions.length) {
            System.out.println("Сделайте свой выбор:");
            try {
                choice = Integer.parseInt(reader.readLine());
            } catch (NumberFormatException exception) {
                System.out.println("Вы ввели нечисловое значение!");
                choice = 0;
            }
        }
        return choice;
    }
}
