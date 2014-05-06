package com.accenture.acntech.examples.services;

import com.accenture.acntech.examples.utils.StringUtil;

public class PowerMockWithEasyMockService {

    public String createCamelCaseFromSentence(String sentence) {
        if (sentence == null) {
            throw new IllegalArgumentException("Input is null");
        }

        String[] words = StringUtil.split(sentence, "\\s+");
        if (words == null) {
            return null;
        }

        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            word = StringUtil.capitalize(word);
            words[i] = word;
        }
        return StringUtil.join(words, "");
    }
}
