package utils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class DictionaryLoader {
    private final Map<Integer, String> topicIndexes = new HashMap<>();
    private final Map<String, String[]> topicRepository = new HashMap<>();
    private String[] topics;

    public DictionaryLoader() {
        loadAllDictionaries();
        initializeTopicsList();
    }


    public String[] getTopicsList() {
        return topics;
    }


    public String getTopicName(int topicNumber) {
        return topicIndexes.get(topicNumber);
    }


    public String[] getWords(String topic) {
        return topicRepository.get(topic);
    }


    private void initializeTopicsList() {
        String[] result = new String[topicIndexes.size()];

        for (int i = 1; i <= topicIndexes.size(); i++) {
            result[i - 1] = topicIndexes.get(i);
        }

        this.topics = result;
    }

    private void loadAllDictionaries() {
        List<String> fileNames = readTopicFileNames();

        for (String fileName : fileNames) {
            // 1.Animals.txt
            String topic = fileName.replace(".txt", "");
            int topicIndex = Integer.parseInt(topic.split("\\.")[0]);
            topicIndexes.put(topicIndex, topic);

            String[] words = loadWordsFromClasspath(fileName);
            topicRepository.put(topic, words);
        }
    }

    private List<String> readTopicFileNames() {
        List<String> fileNames = new ArrayList<>();
        InputStream indexStream = getClass().getResourceAsStream("/dictionary/topics.txt");

        if (indexStream == null) {
            throw new RuntimeException("Файл не найден");
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(indexStream, StandardCharsets.UTF_8))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String trimmed = line.trim();
                if (!trimmed.isEmpty()) {
                    fileNames.add(trimmed);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Ошибка чтения topics.txt", e);
        }

        return fileNames;
    }

    private String[] loadWordsFromClasspath(String fileName) {
        String resourcePath = "/dictionary/" + fileName;

        InputStream is = openResourceStream(resourcePath);
        String content = readAllLinesFromResource(is);
        List<String> wordList = parseWordsFromContent(content);

        return wordList.toArray(new String[0]);
    }

    private InputStream openResourceStream(String resourcePath) {
        InputStream is = getClass().getResourceAsStream(resourcePath);

        if (is == null) {
            throw new RuntimeException("Файл не найден: " + resourcePath);
        }

        return is;
    }

    private String readAllLinesFromResource(InputStream is) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
            StringBuilder content = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                content.append(line);
            }

            return content.toString();

        } catch (IOException e) {
            throw new RuntimeException("Ошибка чтения ресурса", e);
        }
    }

    private List<String> parseWordsFromContent(String content) {
        List<String> wordList = new ArrayList<>();
        String[] rawWords = content.toLowerCase().split(",");

        for (String w : rawWords) {
            String trimmed = w.trim();
            if (!trimmed.isEmpty()) {
                wordList.add(trimmed);
            }
        }

        return wordList;
    }
}