package com.hillel.service;

import com.hillel.contacts.Contact;
import com.hillel.contacts.Type;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class InFileContactsService implements ContactsService {

    private final File contactsFile = new File("contactsFile.txt");


    @Override
    public List<Contact> getAll() {
        List<Contact> contactsList = new ArrayList<>();
        createFile();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(contactsFile))) {
            String currentLine;
            while ((currentLine = bufferedReader.readLine()) != null) {
                Contact contact = getContact(currentLine);
                if (contact != null) contactsList.add(contact);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contactsList;
    }

    @Override
    public void remove(int index) {
        List<Contact> contactsList = getAll();
        if (isIndex(index, contactsList)) {
            Contact removedContact = contactsList.remove(index - 1);
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(contactsFile));) {
                if (removedContact != null) {
                    System.out.println(removedContact + " удален из телефонной книги.");
                }
                for (Contact contact : contactsList) {
                    bufferedWriter.write(contact + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void add(Contact contact) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(contactsFile, true))) {
            List<Contact> contactsList = getAll();
            if (!isContactExist(contact)) {
                bufferedWriter.write(contact + "\n");
                System.out.println(contact + " добавлен в телефонную книгу.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Contact> searchByName(String nameStartsWith) {
        List<Contact> contacts = getAll();
        return contacts.stream()
                .filter(contact -> contact.getName().startsWith(nameStartsWith))
                .collect(Collectors.toList());
    }

    @Override
    public List<Contact> searchByPhone(String phonePart) {
        List<Contact> contacts = getAll();
        return contacts.stream()
                .filter(contact -> contact.getPhone().contains(phonePart))
                .collect(Collectors.toList());
    }

    private void createFile() {
        try {
            contactsFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Contact getContact(String currentLine) {
        Pattern pattern = Pattern.compile("(.+)(?:\\[\\w+:)(.+)(?:])");
        Matcher matcher = pattern.matcher(currentLine);
        if (matcher.find()) {
            String name = matcher.group(1);
            String phone = matcher.group(2);
            return new Contact(name, getType(phone), phone);
        } else return null;
    }

    private Type getType(String phone) {
        if (phone.matches("\\+38\\d{10}")) return Type.PHONE;
        if (phone.matches("\\w+@\\w+\\.\\w+")) return Type.EMAIL;
        return null;
    }

    private boolean isIndex(int index, List<Contact> contactsList) {
        if (index > contactsList.size() || index <= 0) {
            System.out.println("Контакта с таким порядковым номером не существует!");
            return false;
        }
        return true;
    }

    private boolean isContactExist(Contact contact) {
        List<Contact> contactsList = getAll();
        if (contactsList.contains(contact)) {
            System.out.println(contact + " уже существует в телефонной книге!");
            return true;
        }
        return false;
    }


}