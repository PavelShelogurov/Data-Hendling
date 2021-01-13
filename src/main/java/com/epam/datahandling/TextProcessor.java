package com.epam.datahandling;

import com.epam.datahandling.lexis.Text;

import java.io.*;

public class TextProcessor {
    private final static String REGULAR_NEW_STRING = "regular.newString";

    public Text parse(File src) throws FileNotFoundException, IOException {
        StringBuilder content = new StringBuilder();

        FileReader fileReader = new FileReader(src);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        while (bufferedReader.ready()) {
            //собираем весь текст в переменную в том виде как она была в документе
            content = content.append(bufferedReader.readLine());
        }

        return new Text(new String(content));
    }

}
