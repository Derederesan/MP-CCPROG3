public class MVCGame
{
    public static void main(String[] args)
    {
        GameView view = new GameView(); 
        Game model = new Game(); 
        GameController = new GameController(model,view);
    }
}
