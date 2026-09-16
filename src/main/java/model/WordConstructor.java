package model;

import java.util.*;

/**
 * Хранит состояние разгадываемого слова и открывает позиции угаданных букв.
 */
public class WordConstructor {
    /** Количество ещё не открытых символов. */
    private int balanceOfsymbols;
    /** Очереди неоткрытых позиций для каждой буквы слова. */
    private Map<Character, Deque<Integer>> charIndexes;
    /** Текущее отображаемое состояние слова. */
    private char[] currentStateWord;


    /**
     * Подготавливает объект к разгадыванию нового слова.
     *
     * @param userWord новое загаданное слово
     */
    public void setNewWord(String userWord) {
        initializeWordSpace(userWord);
        saveCharIndexes(userWord);
    }


    /**
     * Сбрасывает счётчик, таблицу позиций и отображаемое состояние слова.
     *
     * @param userWord новое загаданное слово
     */
    private void initializeWordSpace(String userWord){
        balanceOfsymbols = userWord.length();
        charIndexes = new HashMap<>();
        currentStateWord = new char[userWord.length()];
        Arrays.fill(currentStateWord, '_');
    }


    /**
     * Сохраняет все позиции каждой буквы загаданного слова.
     *
     * @param userWord загаданное слово
     */
    private void saveCharIndexes(String userWord){
        int index = 0;
        for(char letter : userWord.toCharArray()){
            Character charKey = letter;
            charIndexes.computeIfAbsent(charKey, k -> new ArrayDeque<>(4)).add(index);
            index++;
        }
    }


    /**
     * Открывает все ещё скрытые позиции указанной буквы.
     *
     * @param letter предложенная пользователем буква
     * @return {@code true}, если буква присутствовала среди неоткрытых позиций
     */
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


    /**
     * Проверяет, полностью ли разгадано слово.
     *
     * @return {@code true}, если неоткрытых символов не осталось
     */
    public boolean isFull(){
        return balanceOfsymbols == 0;
    }


    /**
     * Возвращает текущее отображаемое состояние слова.
     *
     * @return строка с открытыми буквами и символами подчёркивания на скрытых позициях
     */
    public String getCurrentStateWord() {
        return new String(currentStateWord);
    }
}
