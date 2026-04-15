import controller.GameController;

public class Main {
    public static void main(String[] args){
        GameController gameController = new GameController();

        try{
            while(gameController.gameIsOn){
                gameController.startNewGame();
            }

        } catch (Exception e){
         //внеплановое завершение игры
        }
    }
}
