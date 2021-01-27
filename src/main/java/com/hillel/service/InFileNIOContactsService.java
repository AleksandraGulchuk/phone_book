package com.hillel.service;

import com.hillel.contacts.Contact;
import com.hillel.contacts.Type;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class InFileNIOContactsService implements ContactsService {

    private final Path path = Paths.get("C:/Alex/Hillel/4/phone_book/src/main/resources/contactsFile.txt");

    @Override
    public List<Contact> getAll() {
        List<Contact> contactsList = new ArrayList<>();
        createFileIfAbsent();
        try {
            contactsList = Files.readAllLines(path).stream()
                    .map(this::getContactFromLine)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return contactsList;
    }


    @Override
    public void remove(int index) {
        List<Contact> contacts = getAll();
        if (isIndex(index, contacts)) {
            Contact removedContact = contacts.remove(index - 1);
            if(contacts.isEmpty()){
                System.out.println(removedContact + " удален из телефонной книги.");
                try {
                    Files.delete(path);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else for (Contact contact : contacts) {
                try {
                    Files.write(path, (contact.toString() + "\n").getBytes(StandardCharsets.UTF_8));
                    System.out.println(removedContact + " удален из телефонной книги.");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public void remove1(int index) {
        List<Contact> contacts = getAll();
        if (isIndex(index, contacts)) {
            Contact removedContact = contacts.remove(index - 1);
            for (Contact contact : contacts) {
                try {
                    Files.write(path, (contact.toString() + "\n").getBytes(StandardCharsets.UTF_8));
                    if (removedContact != null) {
                        System.out.println(removedContact + " удален из телефонной книги.");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        try {
            Files.write(path, Files.readAllLines(path).remove(index).getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void add(Contact contact) {
        createFileIfAbsent();
        try {
            if (!isContactExist(contact)) {
                Files.write(path, (contact.toString() + "\n")
                        .getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
                System.out.println(contact + " добавлен в телефонную книгу.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Contact> searchByName(String nameStartsWith) {
        List<Contact> contacts = new ArrayList<>();
        try {
            contacts = Files.readAllLines(path).stream()
                    .map(this::getContactFromLine)
                    .filter(Objects::nonNull)
                    .filter(contact -> contact.getName().startsWith(nameStartsWith))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contacts;
    }

    @Override
    public List<Contact> searchByPhone(String phonePart) {
        List<Contact> contacts = new ArrayList<>();
        try {
            contacts = Files.readAllLines(path).stream()
                    .map(this::getContactFromLine)
                    .filter(Objects::nonNull)
                    .filter(contact -> contact.getPhone().contains(phonePart))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contacts;
    }


    private void createFileIfAbsent() {
        if (!Files.exists(path)) {
            try {
                Files.createFile(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private Contact getContactFromLine(String currentLine) {
        Pattern pattern = Pattern.compile("(.+)(?:\\[\\w+:)(.+)(?:])");
        Matcher matcher = pattern.matcher(currentLine);
        if (matcher.find()) {
            String name = matcher.group(1);
            String phone = matcher.group(2);
            return new Contact(name, extractType(phone), phone);
        } else return null;
    }

    private Type extractType(String phone) {
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
