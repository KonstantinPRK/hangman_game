package model;

import utils.*;

import java.util.Set;

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
            //игра идет по кругу пока текущему пользователю не надоест
            while(currentSession.gameIsOn()){
                startNewGame();
            }

        } catch (Exception e){
            //внеплановое завершение игры
            output.somethingWrong();
        }
    }

    public void startNewGame(){

        guessing();
    }

    private void userSelection(){
        Set<String> selectedTopic = selectionTopic();
        String selectedWord = selectionWord(selectedTopic);

        currentSession.setUserWord(selectedWord);
        wordConstructor.setWord(currentSession.getUserWord());
    }

    private Set<String> selectionTopic(){
        output.print("Выберете тему загаданного слова");
        output.print(input.getTopicsList());
        return input.getUserTopic();
    }

    private String selectionWord(Set<String> selectedTopic){
        //рандомайзер
        return "случайно выбранное слово";
    }

    private void guessing(){
        //не знаю сколько уровней будет, рандомное число
        while(currentSession.getLevel() <= 20){
            output.print("что-то печатаем");

            //рисует текущего человечка
            output.showHangmanLevel(11);

            //какая-то логика

            currentSession.levelUp();
        }
    }


}
