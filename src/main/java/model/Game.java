package model;

import utils.*;
import java.util.Random;

public class Game {
    private final GameSession currentSession;
    private final WordConstructor wordConstructor;
    private final Output output;
    private final Input input;

    public Game(Output output, Input input, WordConstructor word, GameSession session){
        this.wordConstructor = word;
        this.output = output;
        this.input = input;
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
            output.somethingWrong();
        }
    }


    private void selectWord(){
        currentSession.setUserTopic(selectionTopic());
        currentSession.setUserWord(selectionWord());
        wordConstructor.setWord(currentSession.getUserWord());
    }


    private String selectionTopic(){
        output.print(
                "Выберете тему загаданного слова: ",
                input.getTopicsList()
        );

        int topicNumber = input.getUserInt();
        return input.getTopicName(topicNumber); //от 1 до 24
    }


    private String selectionWord(){
        Random random = new Random();
        String[] words = input.getTopicWords();

        int randomWordIndex = random.nextInt(0, words.length);
        output.print("Определено слово. Угадайте его!");

        return words[randomWordIndex];
    }


    private void guessWord(){
        while(currentSession.getLevel() <= 10){
            output.print(
                    "Выбранная тема: " + currentSession.getUserTopic(),
                    wordConstructor.drawHangman(currentSession.getLevel()),
                    wordConstructor.getCurrentStateWord()
            );

            if(userRespondIsTrue()) currentSession.levelUp();
        }
    }


    private boolean userRespondIsTrue(){
        while(true){
            char letter = input.getUserChar();
            boolean respondIsTrue = wordConstructor.checkRespond(letter);
            if(respondIsTrue) return true;
        }
    }


    private void endOfGame(){
        output.print("Хотите продолжить игру ? (y / n)");
        boolean isEnd = input.getUserChar() == 'y';
        if(isEnd){
            currentSession.gameOFF();
            output.print("Игра завершена");
        }
    }

}
