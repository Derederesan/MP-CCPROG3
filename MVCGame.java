import model.Game;
import model.Player;
import view.GameView;
import controller.GameController;

public class MVCGame
{
    public static void main(String[] args)
    {
        /* initializes players */
        Player player1 = new Player(1);
        Player player2 = new Player(2);

        /* initializes game model */
        Game model = new Game(player1, player2);

        /* initialize the game view */
        GameView view = new GameView(); 

        /* connects the model and view through the controller */
        GameController controller = new GameController(model,view);
    }
}
