package com.epam.datahandling.lexis;

import com.epam.datahandling.utils.RegexProvider;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Sentence is a sequence of characters separated by ".", "!", "?" and new line characters
 */
public class Sentence implements Serializable {
    private static final long serialVersionUID = 1L;

    private final static String WORD_KEY = "regular.word";


    private String content;
    private Word[] words;

    public Sentence(String content) {
        this.content = content;
        Pattern pattern = Pattern.compile(RegexProvider.get(WORD_KEY));
        Matcher matcher = pattern.matcher(content);
        words = new Word[0];
        int indexOfWordsArray = 0;
        while (matcher.find()) {
            words = Arrays.copyOf(words, words.length + 1);
            words[indexOfWordsArray] = new Word(content.substring(matcher.start(), matcher.end()));
            indexOfWordsArray++;
        }
    }

    public String getContent() {
        return content;
    }

    public Word[] getWords() {
        return words;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sentence sentence = (Sentence) o;
        return Objects.equals(content, sentence.content) &&
                Arrays.equals(words, sentence.words);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(content);
        result = 31 * result + Arrays.hashCode(words);
        return result;
    }
}
