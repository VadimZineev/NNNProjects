package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import static com.company.BotPerson.rand;

public class Main {

    private static long timeStamp = System.currentTimeMillis();

    public static void main(String[] args) {
        final String[] randomHello = {
                "Привет! Начинаем.",
                "Здравствуй игрок!",
                "Вот мы и встретились!"
        };
        HashMap<String, String> mapSay = new HashMap<>() {{
            put("Привет", "hello");
            put("Как дела", "how");
            put("Пока", "bye");
        }};
        HashMap<String, BotPerson> bots = new HashMap<>() {
            {
                put("Bob", new BotPerson(new HashMap<>() {{
                    put("hello", "Приветствую человек");
                    put("how", "Я нормально! Не спрашиваю у тебя, так как не знаю как ответить!");
                    put("Good bye", "Пока чувак!");
                }}, new String[]{
                        "Не знаю, что и сказать тебе, чувак!",
                        "Не знаю! Он очень мало текста в меня загрузил"
                }));
                put("Alice", new BotPerson(new HashMap<>() {{
                    put("hello", "Привет");
                    put("how", "Я отлично!");
                    put("Good bye", "Пока человек!");
                }}, new String[]{
                        "Хватит вопросов, ну не написал он ещё",
                        "Тебе пора общаться с живыми компьютерами"
                }));
            }
        };
        String[] timePhrases = {
                "Чего ждем?",
                "Так и будем молчать?",
                "Эй человек!"
        };

        Scanner file = null;
        try {
           file = new Scanner(new File("automaticPhrases.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; file.hasNext(); i++) {
            list.add(file.nextLine());
        }
        Scanner scan = new Scanner(System.in);
        System.out.println(randomHello[rand(randomHello.length)]);
        new Thread(() -> {
            while (true) {
                if (System.currentTimeMillis() - timeStamp >= 10000) {
                    System.out.println(timePhrases[rand(timePhrases.length)]);
                    timeStamp = System.currentTimeMillis();
                }
                try {
                    Thread.sleep(10000 - (System.currentTimeMillis() - timeStamp));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        while (true) {
            String say = scan.nextLine();
            timeStamp = System.currentTimeMillis();
            BotPerson bot = null;
            String botName = null;
            for (String name : bots.keySet()) {
                if (say.contains(name)) {
                    bot = bots.get(name);
                    botName = name;
                    break;
                }
            }
            String key = null;
            for (String phrase : mapSay.keySet()) {
                if (say.contains(phrase)) {
                    key = mapSay.get(phrase);
                }
            }
            if (bot != null) {
                System.out.println(botName + ": " + bot.reply(key));
            } else {
                for (Map.Entry<String, BotPerson> e : bots.entrySet()) {
                    System.out.println(e.getKey() + ": " + e.getValue().reply(key));
                }
            }
            if (say.equals("Пока")) {
                System.out.println("Пока, человек!");
                break;
            }
        }
    }
}

