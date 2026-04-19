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
                guessWord();
                endOfGame();
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
        terminal.getTopicsList();

        int topicNumber = terminal.getUserInt();
        return terminal.getTopicName(topicNumber); //от 1 до 24
    }


    private String selectionWord(){
        Random random = new Random();
        String[] words = terminal.getTopicWords();

        int randomWordIndex = random.nextInt(0, words.length);
        terminal.print("Определено слово. Угадайте его!");

        return words[randomWordIndex];
    }


    private void guessWord(){
        while(currentSession.getLevel() <= 10){
            displayUserInterface();
            checkUserRespondAndUpdate();
        }
    }

    private void displayUserInterface(){
        terminal.print("Выбранная тема: " + currentSession.getUserTopic());
        terminal.print(word.drawHangman(currentSession.getLevel()));
        terminal.print(word.getCurrentStateWord());
        terminal.print("Введите символ: ");
    }

    private void checkUserRespondAndUpdate(){
        char letter = terminal.getUserChar();
        if(word.isLetterExist(letter)) {
            word.update(letter);
            terminal.print("Выбрана верная буква");
        } else {
            currentSession.deathIsCloser();
            terminal.print("Неправильный ответ");
        }
    }


    private void endOfGame(){
        terminal.print("Хотите начать заново ? (y / n)");
        boolean isEnd = terminal.getUserChar() == 'y';
        if(isEnd){
            currentSession.gameOFF();
            terminal.print("Игра завершена");
        }
    }

}
