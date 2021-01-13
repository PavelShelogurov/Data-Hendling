package com.epam.datahandling.utils;

import com.epam.datahandling.lexis.Sentence;
import com.epam.datahandling.lexis.Text;
import com.epam.datahandling.lexis.Word;

/**
 * Utility class creating table by input data
 */
public class TableGenerator {
    private static final String SYMBOL_EQUALS = "=";
    private static final String SYMBOL_DASH = "-";
    private static final String SYMBOL_LINE_BREAK = "\n";
    private static final String SYMBOL_SPACE = "\u0020";
    private static final String NUMBER = "№";
    private static final String SENTENCE = "Sentence";
    private static final String WORDS_COUNT = "Word's Count";
    private static final String VERTICAL_SlASH = "|";

    private int numberColumnWidth;
    private int sentenceColumnWidth;
    private int wordColumnWidth;

    public TableGenerator(int numberColumnWidth, int sentenceColumnWidth, int wordColumnWidth) {
        this.numberColumnWidth = numberColumnWidth;
        this.sentenceColumnWidth = sentenceColumnWidth;
        this.wordColumnWidth = wordColumnWidth;
    }

    public String generate(Text text, int maxDataLength) {
        Sentence[] sortedArrayOfSentences = sortSentenceArrayByCountOfWordsIn(text);

        StringBuilder generatedString = new StringBuilder();
        generatedString.append(generateSeparatedLine(SYMBOL_EQUALS));//добавляем верхнюю 'разделительную' строку
        generatedString.append(generateInfoLine(NUMBER, SENTENCE, WORDS_COUNT));//добавляем верхнюю строку с текстом названия колонок
        generatedString.append(generateSeparatedLine(SYMBOL_EQUALS));//добавляем разделительную линию после строки с названием колонок
        //цикл генерирует строки с информацией
        for (int i = 0; i < sortedArrayOfSentences.length; i++) {
            generatedString.append(generateInfoLine(String.valueOf(i + 1), limitNumberOfWordsDisplayed(sortedArrayOfSentences[i].getWords(), maxDataLength), String.valueOf(sortedArrayOfSentences[i].getWords().length)));
            generatedString.append(generateSeparatedLine(SYMBOL_DASH));
        }

        generatedString.trimToSize();
        return new String(generatedString);

    }

    //метод который генерирует разделительную линию (во всю ширину строки)
    private String generateSeparatedLine(String typeOfSeparator) {
        StringBuilder separatorLine = new StringBuilder();
        separatorLine.append(VERTICAL_SlASH);
        for (int i = 0; i < (numberColumnWidth + sentenceColumnWidth + wordColumnWidth); i++) {
            separatorLine.append(typeOfSeparator);
        }
        separatorLine.append(VERTICAL_SlASH);
        separatorLine.append(SYMBOL_LINE_BREAK);
        return new String(separatorLine);
    }

    //метод который генерирует строку с текстом
    private String generateInfoLine(String numberColumnText, String sentenceColumnText, String wordColumnText) {
        StringBuilder infoLine = new StringBuilder();
        //первый столбец с номером
        infoLine.append(VERTICAL_SlASH);
        infoLine.append(centeringSymbolsInTheMiddleOfColumn(numberColumnText, numberColumnWidth, numberColumnText.length()));
        infoLine.append(VERTICAL_SlASH);
        //второй столбец с предложением
        infoLine.append(centeringSymbolsInTheMiddleOfColumn(sentenceColumnText, sentenceColumnWidth, sentenceColumnText.length()));
        //третий столбец с количеством строк
        infoLine.append(VERTICAL_SlASH);
        infoLine.append(centeringSymbolsInTheMiddleOfColumn(wordColumnText, wordColumnWidth, wordColumnText.length()));
        infoLine.append(VERTICAL_SlASH);
        //перенос строки
        infoLine.append(SYMBOL_LINE_BREAK);
        return new String(infoLine);
    }


    //метод который центрирует текст поседине колонки
    private String centeringSymbolsInTheMiddleOfColumn(String printingText, int cellSize, int maxDataLength) {
        int countOfSymbolSpaceBeforeAndAfterTex = (int) ((cellSize - maxDataLength) / 2);//количество пробелов между текстом и бокоыми разделителями колонки
        StringBuilder returnedString = new StringBuilder();
        for (int i = 0; i < countOfSymbolSpaceBeforeAndAfterTex; i++) {
            returnedString.append(SYMBOL_SPACE);
        }
        returnedString.append(printingText);
        for (int i = 0; i < countOfSymbolSpaceBeforeAndAfterTex; i++) {
            returnedString.append(SYMBOL_SPACE);
        }
        return new String(returnedString);
    }

    //метод для сортировки массива предложений по возрастанию длинны предложения
    private Sentence[] sortSentenceArrayByCountOfWordsIn(Text text) {
        Sentence[] arrayForSort = text.getSentences().clone();
        for (int i = arrayForSort.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arrayForSort[j].getWords().length > arrayForSort[j + 1].getWords().length) {
                    Sentence temp = arrayForSort[j];
                    arrayForSort[j] = arrayForSort[j + 1];
                    arrayForSort[j + 1] = temp;
                }
            }
        }
        return arrayForSort;
    }

    //метод который ограничивает строку до нужного размера (int maxDataLength)
    private String limitNumberOfWordsDisplayed(Word[] wordsOfSentence, int maxDataLength) {
        StringBuilder resultLine = new StringBuilder();
        for (int i = 0; i < wordsOfSentence.length; i++) {
            if ((resultLine.length() + wordsOfSentence[i].getContent().length()) < maxDataLength) {
                resultLine.append(wordsOfSentence[i].getContent());
                resultLine.append(SYMBOL_SPACE);
            } else {
                return new String(resultLine);
            }
        }
        return new String(resultLine);
    }

}
