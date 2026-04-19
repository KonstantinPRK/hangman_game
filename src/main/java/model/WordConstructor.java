package model;

import java.util.*;

public class WordConstructor {
    private Map<Character, Deque<Integer>> charIndexes;
    private char[] currentStateWord;


    public void setNewWord(String userWord) {
        initializeWordSpace(userWord);
        saveCharIndexes(userWord);
    }

    private void initializeWordSpace(String userWord){
        charIndexes = new HashMap<>();
        currentStateWord = new char[userWord.length()];
        Arrays.fill(currentStateWord, '_');
    }

    private void saveCharIndexes(String userWord){
        int index = 0;
        for(char letter : userWord.toCharArray()){
            Character charKey = letter;
            charIndexes.computeIfAbsent(charKey, k -> new ArrayDeque<>(4)).add(index);
            index++;
        }
    }


    public void update(char letter) {
        while(charIndexes.get(letter).peek() != null){
            int charIndex = charIndexes.get(letter).pop();
            currentStateWord[charIndex] = letter;
        }
    }


    public String getCurrentStateWord() {
        return new String(currentStateWord);
    }


    public boolean isLetterExist(char letter) {
        return charIndexes.containsKey(letter);
    }


    public String drawHangman(int level) {
        return switch (level) {
            case 1 -> """
                +---+
                |   |
                    |
                    |
                    |
                    |
                    |
                =========
                """;
            case 2 -> """
                +---+
                |   |
                O   |
                    |
                    |
                    |
                    |
                =========
                """;
            case 3 -> """
                +---+
                |   |
                O   |
                |   |
                    |
                    |
                    |
                =========
                """;
            case 4 -> """
                +---+
                |   |
                O   |
               /|   |
                    |
                    |
                    |
                =========
                """;
            case 5 -> """
                +---+
                |   |
                O   |
               /|\\  |
                    |
                    |
                    |
                =========
                """;
            case 6 -> """
                +---+
                |   |
                O   |
               /|\\  |
               /    |
                    |
                    |
                =========
                """;
            case 7 -> """
                +---+
                |   |
                O   |
               /|\\  |
               / \\  |
                    |
                    |
                =========
                """;
            case 8 -> """
                +---+
                |   |
                O   |
               /|\\  |
               / \\  |
               /    |
                    |
                =========
                """;
            case 9 -> """
                +---+
                |   |
                O   |
               /|\\  |
               / \\  |
               / \\  |
                    |
                =========
                """;
            case 10 -> """
                +---+
                |   |
                O   |
               /|\\  |
               / \\  |
               / \\  |
                XXX
                =========
                ВЫ ПРОИГРАЛИ!
                """;
            default -> "Error";
        };
    }


}
