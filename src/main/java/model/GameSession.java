package model;

public class GameSession {
    private static int newID = 0;
    private int sessionID;
    private boolean gameIsOn;
    private int level;
    private String currentWord;

    public GameSession(){
        this.sessionID = ++newID;
        gameIsOn = true;
    }

    public int getSessionID(){
        return sessionID;
    }

    public boolean gameIsOn(){
        return gameIsOn;
    }

    public int getLevel(){
        return level;
    }

    public int levelUp(){
        return ++level;
    }

    public void setUserWord(String word){
        this.currentWord = word;
    }

    public String getUserWord(){
        return currentWord;
    }
}
