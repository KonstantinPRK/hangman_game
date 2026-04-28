package model;

import utils.*;
import java.util.Random;

public class Game {
    private final GameSession currentSession;
    private final WordConstructor word;
    private final Terminal terminal;

    public Game(Terminal terminal, WordConstructor word, GameSession session){
        this.word = word;
        this.terminal = terminal;
        this.currentSession = session;
    }


    public GameSession getSession(){
        return currentSession;
    }


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


    private void selectWord(){
        currentSession.setUserTopic(selectionTopic());
        currentSession.setUserWord(selectionWord());
        word.setNewWord(currentSession.getUserWord());
    }

    private String selectionTopic(){
        terminal.print("Выберете тему загаданного слова: ");
        terminal.printTopicsList();
        terminal.print("");

        int topicNumber = terminal.getUserInt();
        return terminal.getTopicName(topicNumber); //от 1 до 24
    }

    private String selectionWord(){
        Random random = new Random();
        String[] words = terminal.getTopicWords(currentSession.getUserTopic());

        int randomWordIndex = random.nextInt(0, words.length);
        terminal.print("Определено слово. Угадайте его!");

        return words[randomWordIndex];
    }


    private void playRound(){
        while(currentSession.getLevel() < 10 && !word.isFull()){
            displayUserInterface();
            checkAndUpdate();
            terminal.drawHangman(currentSession.getLevel());
        }
    }

    private void checkAndUpdate(){
        boolean isLetterCorrect = word.applyLetter(terminal.getUserChar());
        if (isLetterCorrect) {
            terminal.print("Верная буква!");
        } else {
            currentSession.incrementMistakes();
            terminal.print("Нет такой буквы");
        }
    }

    private void displayUserInterface(){
        terminal.print("Выбранная тема: " + currentSession.getUserTopic());
        terminal.drawHangman(currentSession.getLevel());
        terminal.print(word.getCurrentStateWord());
        terminal.print("Введите русскоязычный символ: ");
    }


    private void finishRound(){
        if (word.isFull()) {
            terminal.print("Победа! Загаданное слово: " + currentSession.getUserWord());
        } else {
            terminal.print("Поражение. Слово было: " + currentSession.getUserWord());
        }
    }


    private void offerNewGame() {
        terminal.print("Хотите начать заново ? (y / n)");
        boolean isEnd = terminal.getUserChar() != 'y';

        if(isEnd){
            currentSession.gameOFF();
            terminal.print("Игра завершена");
        }
    }

}
