package model;

import utils.*;
import java.util.Random;

/**
 * Управляет игровым циклом «Виселицы»: выбирает тему и слово, обрабатывает попытки
 * пользователя и завершает раунд или игровую сессию.
 */
public class Game {
    /** Состояние текущей игровой сессии. */
    private final GameSession currentSession;
    /** Объект, управляющий открытием букв загаданного слова. */
    private final WordConstructor word;
    /** Терминал для взаимодействия с пользователем. */
    private final Terminal terminal;

    /**
     * Создаёт игру с заданными зависимостями и состоянием сессии.
     *
     * @param terminal терминал для ввода и вывода
     * @param word объект для управления состоянием слова
     * @param session состояние игровой сессии
     */
    public Game(Terminal terminal, WordConstructor word, GameSession session){
        this.word = word;
        this.terminal = terminal;
        this.currentSession = session;
    }


    /**
     * Возвращает состояние игровой сессии.
     *
     * @return текущая игровая сессия
     */
    public GameSession getSession(){
        return currentSession;
    }


    /**
     * Запускает игровой цикл и проводит раунды, пока пользователь не завершит сессию.
     * При непредвиденной ошибке выводит общее сообщение пользователю.
     */
    public void startNewGame(){
        try{
            while(currentSession.gameIsOn()){
                selectWord();
                currentSession.resetLevel();
                playRound();
                finishRound();
                offerNewGame();
            }
        } catch (Exception e){
            terminal.print("Что-то пошло не так");
        }
    }


    /**
     * Выбирает тему и случайное слово, затем подготавливает его к разгадыванию.
     */
    private void selectWord(){
        currentSession.setUserTopic(selectionTopic());
        currentSession.setUserWord(selectionWord());
        word.setNewWord(currentSession.getUserWord());
    }


    /**
     * Запрашивает у пользователя тему загаданного слова.
     *
     * @return название выбранной темы
     */
    private String selectionTopic(){
        terminal.print("Выберете тему загаданного слова: ");
        terminal.printTopicsList();
        terminal.print("");

        int topicNumber = terminal.getUserInt();
        return terminal.getTopicName(topicNumber);
    }


    /**
     * Случайно выбирает слово из текущей темы.
     *
     * @return выбранное слово
     */
    private String selectionWord(){
        Random random = new Random();
        String[] words = terminal.getTopicWords(currentSession.getUserTopic());

        int randomWordIndex = random.nextInt(0, words.length);
        terminal.print("Определено слово. Угадайте его!");

        return words[randomWordIndex];
    }


    /**
     * Проводит один раунд до победы либо достижения предельного уровня ошибок.
     */
    private void playRound(){
        while(currentSession.getLevel() < 10 && !word.isFull()){
            displayUserInterface();
            checkAndUpdate();
            terminal.drawHangman(currentSession.getLevel());
        }
    }


    /**
     * Проверяет введённую букву и обновляет состояние слова или счётчик ошибок.
     */
    private void checkAndUpdate(){
        boolean isLetterCorrect = word.applyLetter(terminal.getUserChar());
        if (isLetterCorrect) {
            terminal.print("Верная буква!");
        } else {
            currentSession.incrementMistakes();
            terminal.print("Нет такой буквы");
        }
    }


    /**
     * Выводит тему, текущее состояние виселицы и разгадываемого слова.
     */
    private void displayUserInterface(){
        terminal.print("Выбранная тема: " + currentSession.getUserTopic());
        terminal.drawHangman(currentSession.getLevel());
        terminal.print(word.getCurrentStateWord());
        terminal.print("Введите русскоязычный символ: ");
    }


    /**
     * Выводит результат завершённого раунда и загаданное слово.
     */
    private void finishRound(){
        if (word.isFull()) {
            terminal.print("Победа! Загаданное слово: " + currentSession.getUserWord());
        } else {
            terminal.print("Поражение. Слово было: " + currentSession.getUserWord());
        }
    }


    /**
     * Предлагает начать новый раунд и завершает сессию при отказе пользователя.
     */
    private void offerNewGame() {
        terminal.print("Хотите начать заново ? (y / n)");
        boolean isEnd = terminal.getUserChar() != 'y';

        if(isEnd){
            currentSession.gameOFF();
            terminal.print("Игра завершена");
        }
    }

}
