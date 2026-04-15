import game.Game;

public class Main {
    public static void main(String[] args){
        Game game = new Game();

        try{
            while(game.gameIsOn){
                game.startNewGame();
            }

        } catch (Exception e){
         //внеплановое завершение игры
        }
    }
}
