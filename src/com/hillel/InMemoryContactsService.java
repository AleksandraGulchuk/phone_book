package com.hillel;

public class InMemoryContactsService implements ContactsService {
    private ContactsList contactsList;

    public InMemoryContactsService(ContactsList contactsList) {
        this.contactsList = contactsList;
    }

    @Override
    public ContactsList getAll() {
        return contactsList;
    }

    @Override
    public void remove(int index) {
        Contact removedContact = contactsList.remove(index - 1);
        if (removedContact != null) {
            System.out.println(removedContact + " удален из телефонной книги.");
        }
    }

    @Override
    public void add(Contact contact) {
        if (!contactsList.contains(contact)) {
            contactsList.add(contact);
            System.out.println(contact + " добавлен в телефонную книгу.");
        } else System.out.println(contact + " уже существует в телефонной книге!");
    }
}
