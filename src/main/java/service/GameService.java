package service;

import model.*;
import utils.*;
import java.util.*;

/**
 * Создаёт игровые сессии и хранит запущенные игры.
 * Реализован как одиночка, общий для всего приложения.
 */
public class GameService {
    /** Единственный экземпляр игрового сервиса. */
    private static GameService gameService;

    /** Игры, зарегистрированные по идентификаторам сессий. */
    private Map<Integer, Game> sessions;
    /** Общий терминал для игровых сессий. */
    private Terminal terminal;
    /** Загрузчик словарей, используемый терминалом. */
    private DictionaryLoader dictionaryLoader;

    /**
     * Возвращает единственный экземпляр игрового сервиса, создавая его при первом обращении.
     *
     * @return общий экземпляр {@code GameService}
     */
    public static GameService getInstance() {
        if (gameService == null) {
            gameService = new GameService();
        }
        return gameService;
    }


    /**
     * Создаёт сервис и инициализирует его зависимости.
     */
    private GameService(){
        initialize();
    }


    /**
     * Подготавливает хранилище сессий, словари и терминал.
     */
    private void initialize(){
        sessions = new HashMap<>();
        dictionaryLoader = new DictionaryLoader();
        terminal = new Terminal(System.out, new Scanner(System.in), dictionaryLoader);
    }


    /**
     * Создаёт, регистрирует и запускает новую игровую сессию.
     */
    public void startNewGameSession(){
        Game newGame = new Game(terminal, new WordConstructor(), new GameSession());
        int sessionID = newGame.getSession().getSessionID();
        sessions.put(sessionID, newGame);
        newGame.startNewGame();
    }
}
