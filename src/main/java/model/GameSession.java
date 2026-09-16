package model;

/**
 * Хранит состояние отдельной игровой сессии: её идентификатор, выбранную тему,
 * загаданное слово и текущий уровень ошибок.
 */
public class GameSession {
    /** Счётчик созданных игровых сессий. */
    private static int newID = 0;
    /** Уникальный идентификатор текущей сессии. */
    private final int sessionID;

    /** Признак продолжения игровой сессии. */
    private boolean gameIsOn;
    /** Текущий уровень ошибок игрока. */
    private int level;
    /** Выбранная тема и загаданное слово текущего раунда. */
    private String currentTopic, currentWord;

    /**
     * Создаёт активную игровую сессию с новым идентификатором и начальным уровнем.
     */
    public GameSession(){
        this.sessionID = ++newID;
        this.gameIsOn = true;
        this.level = 1;
    }


    /**
     * Возвращает уровень ошибок к начальному значению.
     */
    public void resetLevel() {
        this.level = 1;
    }


    /**
     * Возвращает уникальный идентификатор сессии.
     *
     * @return идентификатор сессии
     */
    public int getSessionID(){
        return sessionID;
    }


    /**
     * Проверяет, продолжается ли игровая сессия.
     *
     * @return {@code true}, если сессия активна
     */
    public boolean gameIsOn(){
        return gameIsOn;
    }


    /**
     * Завершает игровую сессию.
     */
    public void gameOFF(){
        gameIsOn = false;
    }


    /**
     * Возвращает текущий уровень ошибок.
     *
     * @return текущий уровень
     */
    public int getLevel(){
        return level;
    }


    /**
     * Увеличивает уровень ошибок на единицу.
     */
    public void incrementMistakes(){
        ++level;
    }


    /**
     * Сохраняет выбранную пользователем тему.
     *
     * @param selectedTopic название выбранной темы
     */
    public void setUserTopic(String selectedTopic) {
        this.currentTopic = selectedTopic;
    }


    /**
     * Возвращает выбранную пользователем тему.
     *
     * @return название текущей темы
     */
    public String getUserTopic(){
        return currentTopic;
    }


    /**
     * Сохраняет слово, выбранное для текущего раунда.
     *
     * @param selectedWord загаданное слово
     */
    public void setUserWord(String selectedWord){
        this.currentWord = selectedWord;
    }


    /**
     * Возвращает слово, загаданное в текущем раунде.
     *
     * @return загаданное слово
     */
    public String getUserWord(){
        return currentWord;
    }

}
