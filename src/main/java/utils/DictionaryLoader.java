package utils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * Загружает тематические словари из ресурсов приложения и предоставляет доступ
 * к названиям тем и содержащимся в них словам.
 */
public class DictionaryLoader {
    /** Соответствие порядковых номеров названиям тем. */
    private final Map<Integer, String> topicIndexes = new HashMap<>();
    /** Соответствие названий тем массивам слов. */
    private final Map<String, String[]> topicRepository = new HashMap<>();

    /** Упорядоченный список доступных тем. */
    private String[] topics;

    /**
     * Создаёт загрузчик, загружает все словари и формирует список тем.
     */
    public DictionaryLoader() {
        loadAllDictionaries();
        initializeTopicsList();
    }


    /**
     * Возвращает упорядоченный список доступных тем.
     *
     * @return массив названий тем
     */
    public String[] getTopicsList() {
        return topics;
    }


    /**
     * Возвращает название темы по её порядковому номеру.
     *
     * @param topicNumber порядковый номер темы
     * @return название темы или {@code null}, если номер отсутствует
     */
    public String getTopicName(int topicNumber) {
        return topicIndexes.get(topicNumber);
    }


    /**
     * Возвращает слова указанной темы.
     *
     * @param topic название темы
     * @return массив слов или {@code null}, если тема отсутствует
     */
    public String[] getWords(String topic) {
        return topicRepository.get(topic);
    }


    /**
     * Формирует упорядоченный массив тем на основе их числовых индексов.
     */
    private void initializeTopicsList() {
        String[] result = new String[topicIndexes.size()];

        for (int i = 1; i <= topicIndexes.size(); i++) {
            result[i - 1] = topicIndexes.get(i);
        }

        this.topics = result;
    }


    /**
     * Загружает все словари, перечисленные в индексном файле тем.
     */
    private void loadAllDictionaries() {
        List<String> fileNames = readTopicFileNames();

        for (String fileName : fileNames) {
            String topic = fileName.replace(".txt", "");
            int topicIndex = Integer.parseInt(topic.split("\\.")[0]);
            topicIndexes.put(topicIndex, topic);

            String[] words = loadWordsFromClasspath(fileName);
            topicRepository.put(topic, words);
        }
    }


    /**
     * Читает имена файлов тематических словарей из ресурса {@code topics.txt}.
     *
     * @return список непустых имён файлов
     * @throws RuntimeException если индексный файл отсутствует или не может быть прочитан
     */
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


    /**
     * Загружает и разбирает слова из указанного ресурса словаря.
     *
     * @param fileName имя файла словаря
     * @return массив слов из файла
     * @throws RuntimeException если ресурс отсутствует или не может быть прочитан
     */
    private String[] loadWordsFromClasspath(String fileName) {
        String resourcePath = "/dictionary/" + fileName;

        InputStream is = openResourceStream(resourcePath);
        String content = readAllLinesFromResource(is);
        List<String> wordList = parseWordsFromContent(content);

        return wordList.toArray(new String[0]);
    }


    /**
     * Открывает поток чтения для ресурса словаря.
     *
     * @param resourcePath путь к ресурсу в classpath
     * @return открытый поток ресурса
     * @throws RuntimeException если ресурс не найден
     */
    private InputStream openResourceStream(String resourcePath) {
        InputStream is = getClass().getResourceAsStream(resourcePath);

        if (is == null) {
            throw new RuntimeException("Файл не найден: " + resourcePath);
        }

        return is;
    }


    /**
     * Читает ресурс целиком, объединяя его строки без разделителей.
     *
     * @param is поток читаемого ресурса
     * @return полное текстовое содержимое ресурса
     * @throws RuntimeException если при чтении произошла ошибка
     */
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


    /**
     * Разбирает разделённую запятыми строку слов, нормализуя регистр и пробелы.
     *
     * @param content исходное содержимое словаря
     * @return список непустых слов в нижнем регистре
     */
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
