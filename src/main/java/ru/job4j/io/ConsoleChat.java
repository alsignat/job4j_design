package ru.job4j.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        List<String> chat = new ArrayList<>();
        List<String> phrases = readPhrases();
        Random generator = new Random();
        boolean isRunning = true;
        boolean isActive = true;

        do {
            String userMessage = scanner.nextLine();
            chat.add("user: " + userMessage);
            switch (userMessage) {
                case OUT:
                    isRunning = false;
                    break;
                case STOP:
                    isActive = false;
                    break;
                case CONTINUE:
                    isActive = true;
                default:
                    if (isActive) {
                        String botMessage = phrases.get(generator.nextInt(0, phrases.size()));
                        System.out.println(botMessage);
                        chat.add("bot: " + botMessage);
                    }
            }

        } while (isRunning);
        saveLog(chat);
    }

    private List<String> readPhrases() {
        List<String> phrases = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(botAnswers, StandardCharsets.UTF_8))) {
            reader.lines().map(s -> s + System.lineSeparator()).forEach(phrases::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return phrases;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(path, StandardCharsets.UTF_8, true))) {
            log.forEach(pw::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("./data/chat.txt", "./data/bot.txt");
        cc.run();
    }
}
