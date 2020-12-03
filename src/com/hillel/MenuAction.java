package com.hillel;

import java.io.IOException;

public interface MenuAction {

    public void doAction(ContactsService contactsService) throws IOException;

    public String getName();

    public boolean closeAfter();
}
