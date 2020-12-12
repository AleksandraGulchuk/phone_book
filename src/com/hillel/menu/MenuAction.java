package com.hillel.menu;

import java.io.IOException;

public interface MenuAction {

    void doAction() throws IOException;

    String getName();

    boolean closeAfter();
}
