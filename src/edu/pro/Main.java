package edu.pro;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static String cleanText(String url) throws IOException {

        String content = new String(Files.readAllBytes(Paths.get(url)));
        content = content.replaceAll("[^A-Za-z ]", " ").toLowerCase(Locale.ROOT);
        return content;
    }

    public static void main(String[] args) throws IOException {
        LocalDateTime start = LocalDateTime.now();


        String content = cleanText("src/edu/pro/txt/harry.txt");


        String[] words = content.split("\\s+");


        Map<String, Integer> frequencyMap = new HashMap<>();
        for (String word : words) {
            frequencyMap.put(word, frequencyMap.getOrDefault(word, 0) + 1);
        }


        List<Map.Entry<String, Integer>> sortedWords = frequencyMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Collections.reverseOrder()))
                .collect(Collectors.toList());


        sortedWords.stream().limit(30).forEach(entry ->
                System.out.println(entry.getKey() + " " + entry.getValue())
        );

        LocalDateTime finish = LocalDateTime.now();
        System.out.println("------");
        System.out.println(ChronoUnit.MILLIS.between(start, finish));
    }
}
