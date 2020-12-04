package com.hillel;

import java.io.IOException;

public interface MenuAction {

    void doAction(ContactsService contactsService) throws IOException;

    String getName();

    boolean closeAfter();
}
