package com.hillel;

import java.io.*;

public class InFileContactsService implements ContactsService {

    String fileName = "contactsList.txt";

    @Override
    public ContactsList getAll() throws IOException {
        ContactsList contactsList = new ContactsList();
        String currentLine;
        String[] currentLineArray;
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        while ((currentLine = br.readLine()) != null) {
            currentLineArray = currentLine.split("-");
            contactsList.add(new Contact(currentLineArray[0], currentLineArray[1]));
        }
        br.close();
        return contactsList;
    }

    @Override
    public void remove(int index) throws IOException {
        ContactsList contactsList = getAll();
        Contact removedContact = contactsList.remove(index - 1);
        BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
        if (removedContact != null) {
            System.out.println(removedContact + " удален из телефонной книги.");
        }
        for (int i = 0; i < contactsList.size(); i++) {
            bw.write(contactsList.get(i).getName() + "-" + contactsList.get(i).getPhone() + "\n");
        }
        bw.close();
    }

    @Override
    public void add(Contact contact) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, true));
        ContactsList contactsList = getAll();
        if (contactsList.contains(contact)) {
            System.out.println(contact + " уже существует в телефонной книге!");
        } else {
            bw.write(contact.getName() + "-" + contact.getPhone() + "\n");
            System.out.println(contact + " добавлен в телефонную книгу.");
        }
        bw.close();
    }
}