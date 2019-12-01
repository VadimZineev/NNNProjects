package com.company;

import java.util.HashMap;

public class BotPerson {

    static int rand(int bound) {
        return (int) (Math.random() * bound);
    }

    HashMap<String, String> replies;
    String[] randomPhrases;

    public BotPerson(HashMap<String, String> replies, String[] randomPhrases) {
        this.replies = replies;
        this.randomPhrases = randomPhrases;
    }

    public String reply(String key) {
        if (key != null) {
            String answer = replies.get(key);
            if (answer != null) {
                return answer;
            }
        }
        return randomPhrases[rand(randomPhrases.length)];

    }
}
