import service.*;

/**
 * Служит точкой входа в консольную игру «Виселица».
 */
public class Main {
    /**
     * Получает единственный экземпляр игрового сервиса и запускает новую игровую сессию.
     *
     * @param args аргументы командной строки; приложением не используются
     */
    public static void main(String[] args){
            GameService gameService = GameService.getInstance();

            gameService.startNewGameSession();
    }

}
