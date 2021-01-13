package com.epam.datahandling.utils;

import java.util.ResourceBundle;

public final class RegexProvider {
    private final static String REGULAR_FILE = "regular_expressions";

    public static String get(String key) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(REGULAR_FILE);
        return resourceBundle.getString(key);
    }

    private RegexProvider(){

    }

}

