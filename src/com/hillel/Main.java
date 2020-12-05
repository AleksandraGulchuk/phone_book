package com.hillel;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {

//        ContactsService contactsService = new InMemoryContactsService();
        ContactsService contactsService = new InFileContactsService();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Menu menu = new Menu(contactsService, reader);
        menu.addAction(new ReadAllContactsMenuAction(contactsService), new AddContactMenuAction(contactsService, reader),
                new RemoveContactMenuAction(contactsService, reader), new ExitMenuAction(contactsService));
        menu.run();
    }
}
