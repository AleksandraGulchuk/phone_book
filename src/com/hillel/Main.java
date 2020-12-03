package com.hillel;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        ContactsList contactsList = new ContactsList();
        ContactsService contactsService = new InMemoryContactsService(contactsList);
        MenuAction[] actions = {
                new ReadAllContactsMenuAction(),
                new AddContactMenuAction(),
                new RemoveContactMenuAction(),
                new ExitMenuAction()
        };
        Menu menu = new Menu(actions, contactsService);

        int choice = 0;
        while (choice != 4) {
            menu.run();
            choice = menu.getChoice();
            menu.doAction(choice);
        }

    }
}
