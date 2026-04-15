package game;

import user.Handler;

public class Game {
    public boolean gameIsOn;
    private int level;
    private final Handler handler;


    public Game(){
    handler = new Handler();
    gameIsOn = true;
    }

    public void startNewGame(){
        level = 0;
        selectionTopic();
        guessing();
        //запуск вспомогательных методов, логика игры
    }

    private void selectionTopic(){
        //тема со словами из папки resources и где-то локально (коллекция или массив) храним полученные слова
    }

    private void guessing(){
        //не знаю сколько уровней будет, рандомное число
        while(level < 20){
            handler.print("что-то печатаем");
            //объекты для взаимодействия с пользователем
            level++;
        }
    }


}
