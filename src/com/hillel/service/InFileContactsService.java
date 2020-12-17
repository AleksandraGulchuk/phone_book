package com.hillel.service;

import com.hillel.contacts.Contact;
import com.hillel.contacts.ContactsList;

import java.io.*;

public class InFileContactsService implements ContactsService {

    private final String FILE_NAME = "contactsList.txt";

    @Override
    public ContactsList getAll() throws IOException {
        ContactsList contactsList = new ContactsList();
        String currentLine;
        String[] currentLineArray;
        BufferedReader bufferedReader = new BufferedReader(new FileReader(FILE_NAME));
        while ((currentLine = bufferedReader.readLine()) != null) {
            currentLineArray = currentLine.split("-");
            contactsList.add(new Contact(currentLineArray[0], currentLineArray[1]));
        }
        bufferedReader.close();
        return contactsList;
    }

    @Override
    public void remove(int index) throws IOException {
        ContactsList contactsList = getAll();
        Contact removedContact = contactsList.remove(index - 1);
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(FILE_NAME));
        if (removedContact != null) {
            System.out.println(removedContact + " удален из телефонной книги.");
        }
        for (int i = 0; i < contactsList.size(); i++) {
            bufferedWriter.write(contactsList.get(i).getName() + "-" + contactsList.get(i).getPhone() + "\n");
        }
        bufferedWriter.close();
    }

    @Override
    public void add(Contact contact) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(FILE_NAME, true));
        ContactsList contactsList = getAll();
        if (contactsList.contains(contact)) {
            System.out.println(contact + " уже существует в телефонной книге!");
        } else {
            bufferedWriter.write(contact.getName() + "-" + contact.getPhone() + "\n");
            System.out.println(contact + " добавлен в телефонную книгу.");
        }
        bufferedWriter.close();
    }

    @Override
    public ContactsList searchByName(String nameStartsWith) throws IOException {
        ContactsList contacts = getAll();
        if (contacts.size() == 0) {
            return null;
        }
        ContactsList foundContacts = new ContactsList();
        for (int i = 0; i < contacts.size(); i++) {
            String name = contacts.get(i).getName();
            if (name.startsWith(nameStartsWith)) {
                foundContacts.add(contacts.get(i));
            }
        }
        if (foundContacts.size() == 0) {
            return null;
        } else return foundContacts;
    }

    @Override
    public ContactsList searchByPhone(String phonePart) throws IOException {
        ContactsList contacts = getAll();
        if (contacts.size() == 0) {
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
}