package model;

import utils.*;
import java.util.Random;

public class Game {
    private final GameSession currentSession;
    private final WordConstructor wordConstructor;
    private Output output;
    private Input input;

    public Game(Output output, Input input, WordConstructor word, GameSession session){
        this.wordConstructor = word;
        this.output = output;
        this.input = input;
        this.currentSession = session;
    }

    public GameSession getSession(){
        return currentSession;
    }

    public void gameON(){
        try{
            while(currentSession.gameIsOn()){
                startNewGame();
            }
        } catch (Exception e){
            output.somethingWrong();
        }
    }

    public void startNewGame(){
        wordSelection();
        wordGuessing();
    }

    private void wordSelection(){
        currentSession.setUserTopic(selectionTopic());
        currentSession.setUserWord(selectionWord());
        wordConstructor.setWord(currentSession.getUserWord());
    }

    private String selectionTopic(){
        output.print(
                "Выберете тему загаданного слова: ",
                input.getTopicsList()
        );

        return input.getTopicFromUser(); //от 1 до 24
    }

    private String selectionWord(){
        Random random = new Random();
        String[] words = input.getTopicsList();

        int randomWordIndex = random.nextInt(0, words.length);
        output.print("Определено слово. Угадайте его!");

        return words[randomWordIndex];
    }

    private void wordGuessing(){
        while(currentSession.getLevel() <= 10){
            output.print(
                    "Выбранная тема: " + currentSession.getUserTopic(),
                    output.drawHangman(currentSession.getLevel()),
                    wordConstructor.getCurrentStateWord()
            );

            if(userRespondIsTrue()) currentSession.levelUp();
        }

        currentSession.gameOFF();
    }

    private boolean userRespondIsTrue(){
        while(true){
            char letter = input.getUserRespond();
            boolean respondIsTrue = wordConstructor.checkRespond(letter);
            if(respondIsTrue)return true;
        }
    }

}
