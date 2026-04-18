package service;

import model.*;
import utils.*;

import java.util.HashMap;
import java.util.Map;

public class GameService {
    private Map<Integer, Game> sessions;
    private Output output;
    private Input input;

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
        output = new Output();
        input = new Input(new DictionaryLoader());
    }

    public void startNewGameSession(){
        Game newGame = new Game(output, input, new WordConstructor(), new GameSession());
        int sessionID = newGame.getSession().getSessionID();
        sessions.put(sessionID, newGame);
        newGame.gameON();
    }
}
