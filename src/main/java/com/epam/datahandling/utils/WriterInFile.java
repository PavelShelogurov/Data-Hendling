package com.epam.datahandling.utils;

import java.io.*;

public final class WriterInFile {

    private WriterInFile(){}

    public static void writeResultToFile(File file, String content) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
        writer.newLine();
        writer.write(content);
        writer.flush();
        writer.close();

    }
}
