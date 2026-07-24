import model.Game;
import view.Game;
import controller.GameController

public class MVCGame
{
    public static void main(String[] args)
    {
        GameView view = new GameView(); 
        Game model = new Game(); 
        GameController controller = new GameController(model,view);
    }
}
