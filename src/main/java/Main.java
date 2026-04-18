import service.*;

public class Main {
    public static void main(String[] args){
            GameService gameService = GameService.getInstance();

            //представим что в будущем его можно будет вызвать много раз для разных пользователей, пока что все в однопоточном режиме
            gameService.startNewGameSession();
    }

}
