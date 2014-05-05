package com.accenture.examples.services;

import com.accenture.examples.utils.StringUtil;

public class SetterInjectedService {

    private StringUtil stringUtil;

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

    public void setStringUtil(StringUtil stringUtil) {
        this.stringUtil = stringUtil;
    }
}
