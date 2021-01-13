package com.epam.datahandling.utils;

import com.epam.datahandling.lexis.Text;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public final class Serializer {

    public static void serializeResultOfParser(Text text, String pathToSave) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(pathToSave);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(text);
    }

    private Serializer(){}

}