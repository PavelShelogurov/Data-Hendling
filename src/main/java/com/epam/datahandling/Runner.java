package com.epam.datahandling;

import com.epam.datahandling.lexis.Text;
import com.epam.datahandling.utils.BackupUtils;
import com.epam.datahandling.utils.Serializer;
import com.epam.datahandling.utils.TableGenerator;
import com.epam.datahandling.utils.WriterInFile;
import com.epam.datahandling.view.View;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Demo class demonstrating program capabilities of working with files, text parsing and displaying reports
 */


public class Runner {
    private static final String SRC_PATH = "src" + File.separator + "main" + File.separator + "resources" + File.separator + "original" + File.separator + "book.txt";
    private static final String DEST_PATH = "src" + File.separator + "main" + File.separator + "resources" + File.separator + "backup" + File.separator + "book.bak";
    private static final String PATH_TO_SAVE_SERIALIZER_RESULT = "src" + File.separator + "main" + File.separator + "resources" + File.separator + "backup" + File.separator + "serialize_result.bak";
    private static final Logger logger = Logger.getLogger(Runner.class.getName());
    private static final int NUMBER_COLUMN_WIDTH = 10;
    private static final int SENTENCE_COLUMN_WIDTH = 50;
    private static final int WORD_COLUMN_WIDTH = 15;
    private static final int MAX_DATA_LENGTH = 40;//максимальная длинна выводимай строки (не должна быть больше чем SENTENCE_COLUMN_WIDTH)


    public static void main(String[] args) {
        Runner runner = new Runner();
        runner.start();

    }

    private void start() {
        //backup исходного файла
        try {
            BackupUtils.backup(SRC_PATH, DEST_PATH);
        } catch (FileNotFoundException e) {
            logger.log(Level.WARNING, View.getUnableWrite());
        } catch (IOException ioException) {
            logger.log(Level.WARNING, View.getIoException());
        }

        TextProcessor textProcessor = new TextProcessor();
        TableGenerator tableGenerator = new TableGenerator(NUMBER_COLUMN_WIDTH, SENTENCE_COLUMN_WIDTH, WORD_COLUMN_WIDTH);
        Text text;
        String infoToWritingInSRCFile = new String();
        //парсинг исходного файла
        try {
            File scrFile = new File(SRC_PATH);
            text = textProcessor.parse(scrFile);
            infoToWritingInSRCFile = tableGenerator.generate(text, MAX_DATA_LENGTH);
            //сериализация резултата парсера
            Serializer.serializeResultOfParser(text, PATH_TO_SAVE_SERIALIZER_RESULT);
        } catch (FileNotFoundException e) {
            logger.log(Level.WARNING, View.getUnableWrite());
        } catch (IOException ioException) {
            logger.log(Level.WARNING, View.getIoException());
        }

        //запись получившейся таблицы в файл
        try {
            File file = new File(SRC_PATH);
            WriterInFile.writeResultToFile(file, infoToWritingInSRCFile);
        } catch (IOException e) {
            logger.log(Level.WARNING, View.getUnableWrite());
        }


    }


}
