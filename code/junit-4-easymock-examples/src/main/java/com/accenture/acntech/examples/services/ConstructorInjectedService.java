package com.accenture.acntech.examples.services;

import com.accenture.acntech.examples.utils.StringUtil;

public class ConstructorInjectedService {

    private StringUtil stringUtil;

    public ConstructorInjectedService(StringUtil stringUtil) {
        this.stringUtil = stringUtil;
    }

    public String createCamelCaseFromSentence(String sentence) {
        if (sentence == null) {
            throw new IllegalArgumentException("Input is null");
        }

        String[] words = stringUtil.split(sentence, "\\s+");
        if (words == null) {
            return null;
        }

        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            word = stringUtil.capitalize(word);
            words[i] = word;
        }
        return stringUtil.join(words, "");
    }
}
