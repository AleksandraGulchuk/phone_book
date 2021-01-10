package com.hillel.service;

import com.hillel.contacts.Contact;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InFileContactsService implements ContactsService {

    private final String FILE_NAME = "contactsList.txt";

    @Override
    public List<Contact> getAll() throws IOException {
        List<Contact> contactsList = new ArrayList<>();
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
        List<Contact> contactsList = getAll();
        if (isIndex(index, contactsList)) {
            Contact removedContact = contactsList.remove(index - 1);
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(FILE_NAME));
            if (removedContact != null) {
                System.out.println("Контакт " + removedContact + " удален из телефонной книги.");
            }
            for (Contact contact : contactsList) {
                bufferedWriter.write(contact.getName() + "-" + contact.getPhone() + "\n");
            }
            bufferedWriter.close();
        }
    }

    @Override
    public void add(Contact contact) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(FILE_NAME, true));
        List<Contact> contactsList = getAll();
        if (contactsList.contains(contact)) {
            System.out.println(contact + " уже существует в телефонной книге!");
        } else {
            bufferedWriter.write(contact.getName() + "-" + contact.getPhone() + "\n");
            System.out.println(contact + " добавлен в телефонную книгу.");
        }
        bufferedWriter.close();
    }

    @Override
    public List<Contact> searchByName(String nameStartsWith) throws IOException {
        List<Contact> contacts = getAll();
        return contacts.stream()
                .filter(contact -> contact.getName().startsWith(nameStartsWith))
                .collect(Collectors.toList());
    }


    @Override
    public List<Contact> searchByPhone(String phonePart) throws IOException {
        List<Contact> contacts = getAll();
        return contacts.stream()
                .filter(contact -> contact.getPhone().contains(phonePart))
                .collect(Collectors.toList());
    }

    private boolean isIndex(int index, List<Contact> contactsList) {
        if (index > contactsList.size() || index <= 0) {
            System.out.println("Контакта с таким порядковым номером не существует!");
            return false;
        }
        return true;
    }
}