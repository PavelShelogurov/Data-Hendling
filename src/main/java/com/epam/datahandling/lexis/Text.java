package com.epam.datahandling.lexis;

import com.epam.datahandling.utils.RegexProvider;

import java.io.Serializable;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Text to be parsed
 */
public class Text implements Serializable {
    private static final long serialVersionUID = 1L;

    private final static String SENTENCE_KEY = "regular.sentence";

    private String content;
    private Sentence[] sentences;

    public Text(String content) {
        this.content = content;
        Pattern pattern = Pattern.compile(RegexProvider.get(SENTENCE_KEY));
        Matcher matcher = pattern.matcher(content);
        sentences = new Sentence[0];
        int indexOfSentenceArray = 0;
        while (matcher.find()) {
            sentences = Arrays.copyOf(sentences, sentences.length + 1);
            sentences[indexOfSentenceArray] = new Sentence(content.substring(matcher.start(), matcher.end()));
            indexOfSentenceArray++;
        }
    }

    public String getContent() {
        return content;
    }

    public Sentence[] getSentences() {
        return sentences;
    }
}
