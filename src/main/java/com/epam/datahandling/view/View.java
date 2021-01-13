package com.epam.datahandling.view;

public class View {
    private static final String UNABLE_WRITE = "Unable to write to file";
    private static final String IO_EXCEPTION = "Error inout/output";


    public static String getUnableWrite() {
        return UNABLE_WRITE;
    }

    public static String getIoException() {
        return IO_EXCEPTION;
    }
}
