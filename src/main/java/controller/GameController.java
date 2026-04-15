package controller;

import user.UserHandler;

public class GameController {
    public boolean gameIsOn;
    private final UserHandler userHandler;

    public GameController(){
    gameIsOn = true;
    userHandler = new UserHandler();

    }

    public void startNewGame(){
        selectionTopic();
        guessing();
    }

    private void selectionTopic(){
        userHandler.print("Выберете тему загаданного слова");
        userHandler.print();
        userHandler.scanInt();
        //тема со словами из папки resources и где-то локально (коллекция или массив) храним полученные слова
    }

    private void guessing(){
        //не знаю сколько уровней будет, рандомное число
        while(level <= 20){
            userHandler.print("что-то печатаем");
            //объекты для взаимодействия с пользователем
            level++;
        }

        //
    }




}
