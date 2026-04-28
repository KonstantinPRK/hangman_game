package model;

public class GameSession {
    private static int newID = 0;
    private final int sessionID;
    private boolean gameIsOn;
    private int level;
    private String currentTopic, currentWord;

    public GameSession(){
        this.sessionID = ++newID;
        this.gameIsOn = true;
        this.level = 1;
    }

    public void resetLevel() {
        this.level = 1;
    }



    public int getSessionID(){
        return sessionID;
    }


    public boolean gameIsOn(){
        return gameIsOn;
    }

    public void gameOFF(){
        gameIsOn = false;
    }


    public int getLevel(){
        return level;
    }

    public void incrementMistakes(){
        ++level;
    }


    public void setUserTopic(String selectedTopic) {
        this.currentTopic = selectedTopic;
    }

    public String getUserTopic(){
        return currentTopic;
    }


    public void setUserWord(String selectedWord){
        this.currentWord = selectedWord;
    }

    public String getUserWord(){
        return currentWord;
    }

}
