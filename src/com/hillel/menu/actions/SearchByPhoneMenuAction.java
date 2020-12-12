package com.hillel.menu.actions;

import com.hillel.contacts.ContactsList;
import com.hillel.menu.MenuAction;
import com.hillel.service.ContactsService;

import java.io.BufferedReader;
import java.io.IOException;

public class SearchByPhoneMenuAction implements MenuAction {

    private final ContactsService contactsService;
    private final BufferedReader reader;

    public SearchByPhoneMenuAction(ContactsService contactsService, BufferedReader reader) {
        this.contactsService = contactsService;
        this.reader = reader;
    }

    private ContactsList searchContacts(String phonePart) throws IOException {
        ContactsList contacts = contactsService.getAll();
        if (contacts.size() == 0) {
            System.out.println("Список пуст!");
            return null;
        }
        ContactsList foundContacts = new ContactsList();
        for (int i = 0; i < contacts.size(); i++) {
            String phone = contacts.get(i).getPhone();
            if (phone.contains(phonePart)) {
                foundContacts.add(contacts.get(i));
            }
        }
        if (foundContacts.size() == 0) {
            return null;
        } else return foundContacts;
    }

    @Override
    public void doAction() throws IOException {
        System.out.println("Введите часть номера телефона: ");
        ContactsList foundContacts = searchContacts(reader.readLine());
        if (foundContacts == null) {
            System.out.println("Контакт не найден");
        } else {
            System.out.println("Найдены контакты:\n" + foundContacts);
        }
    }

    @Override
    public String getName() {
        return "Поиск по части номера";
    }

    @Override
    public boolean closeAfter() {
        return false;
    }
}
