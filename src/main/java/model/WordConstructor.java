package model;

import java.util.*;

public class WordConstructor {
    private int balanceOfsymbols;
    private Map<Character, Deque<Integer>> charIndexes;
    private char[] currentStateWord;


    public void setNewWord(String userWord) {
        initializeWordSpace(userWord);
        saveCharIndexes(userWord);
    }

    private void initializeWordSpace(String userWord){
        balanceOfsymbols = userWord.length();
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


    public boolean applyLetter(char letter) {
        Deque<Integer> positions = charIndexes.get(letter);
        if (charIndexes.get(letter) == null || positions.isEmpty()) {
            return false;
        }

        while (!positions.isEmpty()) {
            int index = positions.pop();
            currentStateWord[index] = letter;
            balanceOfsymbols--;
        }
        return true;
    }


    public boolean isFull(){
        return balanceOfsymbols == 0;
    }


    public String getCurrentStateWord() {
        return new String(currentStateWord);
    }
}
