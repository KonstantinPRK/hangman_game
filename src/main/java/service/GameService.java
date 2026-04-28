package service;

import model.*;
import utils.*;
import java.util.*;

public class GameService {
    //пока что sessions никак не используется
    private Map<Integer, Game> sessions;
    private Terminal terminal;
    private DictionaryLoader dictionaryLoader;

    private static GameService gameService;
    public static GameService getInstance() {
        if (gameService == null) {
            gameService = new GameService();
        }
        return gameService;
    }

    private GameService(){
        initialize();
    }


    private void initialize(){
        sessions = new HashMap<>();
        dictionaryLoader = new DictionaryLoader();
        terminal = new Terminal(System.out, new Scanner(System.in), dictionaryLoader);
    }


    public void startNewGameSession(){
        Game newGame = new Game(terminal, new WordConstructor(), new GameSession());
        int sessionID = newGame.getSession().getSessionID();
        sessions.put(sessionID, newGame);
        newGame.startNewGame();
    }
}
