package com.hillel;

import com.hillel.menu.*;
import com.hillel.menu.actions.*;
import com.hillel.service.ContactsService;
import com.hillel.service.InFileNIOContactsService;

import java.io.*;

public class Main {

    public static void main(String[] args) {

        ContactsService contactsService = new InFileNIOContactsService();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        MenuAction[] actions = new MenuAction[]{
                new ReadAllContactsMenuAction(contactsService),
                new AddContactMenuAction(contactsService, reader),
                new RemoveContactMenuAction(contactsService, reader),
                new SearchByPhoneMenuAction(contactsService, reader),
                new SearchByNameMenuAction(contactsService, reader),
                new ExitMenuAction()
        };

        Menu menu = new Menu(contactsService, reader, actions);
        try {
            menu.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
