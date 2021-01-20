package com.hillel.menu.actions;

import com.hillel.contacts.Contact;
import com.hillel.exception.ValidationException;
import com.hillel.service.ContactsService;
import com.hillel.menu.MenuAction;
import com.hillel.validator.ObjectValidator;
import com.hillel.validator.RulesBasedObjectValidator;
import com.hillel.validator.rules.RegExpValidatorRule;
import com.hillel.validator.rules.StartsWithValidatorRule;
import com.hillel.validator.rules.ValidatorRule;


import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class AddContactMenuAction implements MenuAction {

    private final ContactsService contactsService;
    private final BufferedReader reader;

    public AddContactMenuAction(ContactsService contactsService, BufferedReader reader) {
        this.contactsService = contactsService;
        this.reader = reader;
    }

    private boolean checkPhone(String phone) {
        List<ValidatorRule> rules = new ArrayList<>();
        rules.add(new RegExpValidatorRule(Pattern.compile("\\+\\d{12}")));
        rules.add(new StartsWithValidatorRule("+38"));
        ObjectValidator<String> validator = new RulesBasedObjectValidator(rules);
        try {
            validator.validate(phone);
            return true;
        } catch (ValidationException e) {
            System.out.println("Неверный номер " + e.getMessage());
        }
        return false;
    }

    private String getPhone() throws IOException {
        while (true) {
            System.out.println("Введите номер телефона: ");
            String phone = reader.readLine();
            boolean isPhoneChecked = checkPhone(phone);
            if (isPhoneChecked) return phone;
            System.out.println("Вы ввели некорректное значение!");
        }
    }

    @Override
    public void doAction() throws IOException {
        System.out.println("Введите имя контакта: ");
        String name = reader.readLine();
        String phone = getPhone();
        Contact contact = new Contact(name, phone);
        contactsService.add(contact);
    }

    @Override
    public String getName() {
        return "Добавить контакт";
    }

    @Override
    public boolean closeAfter() {
        return false;
    }
}
