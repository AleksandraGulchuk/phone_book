package com.hillel;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

//        ContactsService contactsService = new InMemoryContactsService();
        ContactsService contactsService = new InFileContactsService();

        Menu menu = new Menu(contactsService);
        menu.addAction(new ReadAllContactsMenuAction(), new AddContactMenuAction(),
                new RemoveContactMenuAction(), new ExitMenuAction());
        menu.run();

    }
}
