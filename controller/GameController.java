import java.awt.event. *;
import javax.swing.*;

package controller;

public class GameController implements KeyListener{
    private Game model; 
    private GameView view; 

    public GameController(Game model, GameView view)
    {
      this.model = model; 
      this.view = view; 

      this.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent a)
    {
      switch(a.getKeyChar())
      {
        case 'L':
        case 'l':
          model.getBoard().
          break;
        case 'R':
        case 'r':
          break;
        case 'U':
        case 'u':
          break;
        case 'D':
        case 'd':
          break; 
        default:
          System.out.println("Invalid input!"); 
      }
      model.updateTurn();
      model.checkWin();
      
    }

    @Override
    public void keyPressed(KeyEvent a)
    {
      //intentionally empty, must be present
    }

    @Override
    public void keyReleased(KeyEvent a)
    {
      //intentionally empty, must be present
    }

}
