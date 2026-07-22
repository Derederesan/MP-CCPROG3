import java.awt.event. *;
import javax.swing.*;

package controller;

public class GameController implements KeyListener{
    private Game model; 
    private GameView view; 
    private Animal selectedAnimal = null;
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
          model.getBoard().moveAnimal(selectedAnimal, 'L');
          break;
        case 'R':
        case 'r':
           model.getBoard().moveAnimal(selectedAnimal, 'R');
          break;
        case 'U':
        case 'u':
           model.getBoard().moveAnimal(selectedAnimal, 'U');
          break;
        case 'D':
        case 'd':
           model.getBoard().moveAnimal(selectedAnimal, 'D');
          break; 
        default:
          System.out.println("Invalid input!"); 
      }
      model.updateTurn();
      model.checkWin();
      selectedAnimal = null;

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
