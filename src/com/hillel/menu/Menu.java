package com.hillel.menu;

import com.hillel.service.ContactsService;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;

public class Menu {

    private MenuAction[] actions;
    private final ContactsService contactsService;
    private final BufferedReader reader;

    public Menu(ContactsService contactsService, BufferedReader reader, MenuAction[] actions) {
        this.contactsService = contactsService;
        this.reader = reader;
        this.actions = actions;
    }

    public void addAction(MenuAction action) {
        MenuAction[] tmpActions = Arrays.copyOf(actions, actions.length + 1);
        tmpActions[tmpActions.length - 1] = action;
        this.actions = tmpActions;
    }

    public void run() throws IOException {
        boolean isCloseAfter = false;
        int choice;
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
        while (true) {
            System.out.println("Сделайте свой выбор:");
            String choiceString = reader.readLine();
            if (validateMenuIndex(choiceString)) {
                return Integer.parseInt(choiceString);
            }
        }
    }

    private boolean validateMenuIndex(String choiceString) {
        int choice = -1;
        try {
            choice = Integer.parseInt(choiceString);
        } catch (NumberFormatException exception) {
            System.out.println("Вы ввели некорректное значение!");
        }
        return choice > 0 && choice <= actions.length;
    }

}
