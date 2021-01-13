package com.epam.datahandling.utils;

import java.io.*;

public final class BackupUtils {

    /**
     * Backup file. Create backup file if it is not exists. Override existing backup.
     *
     * @param src  Path to the source file
     * @param dest Path to backup file
     * @throws IOException If source file is not exist
     */
    private static final String SEPARATOR_FROM_OTHER_SYSTEMS = "/";

    public static void backup(String src, String dest) throws IOException {
        FileInputStream srcFile = null;
        FileOutputStream destFile = null;

        //замена разделителя в пути до файла на нужный
        dest = dest.replace(SEPARATOR_FROM_OTHER_SYSTEMS, File.separator);

        //если путь указан до несуществующий папки, то нужно создать эту папку
        createFilePath(dest);

        srcFile = new FileInputStream(src);
        destFile = new FileOutputStream(dest);
        int byteAvailable = srcFile.available();
        byte[] byteFromSrcFile = new byte[byteAvailable];
        srcFile.read(byteFromSrcFile);
        srcFile.close();

        destFile.write(byteFromSrcFile);
        destFile.close();
    }

    private BackupUtils() {

    }

    private static void createFilePath(String dest) {
        String[] filePathPlusFileName = dest.split(File.separator + File.separator);
        StringBuilder destFilePath = new StringBuilder();
        for (int i = 0; i < filePathPlusFileName.length - 1; i++) {
            destFilePath.append(filePathPlusFileName[i]);
            destFilePath.append(File.separator);
        }
        File filePath = new File(new String(destFilePath));
        filePath.mkdirs();
    }

}
