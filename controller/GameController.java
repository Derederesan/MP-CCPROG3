package controller;

import java.awt.event.*;
import model.Game;
import model.Animal;
import view.GameView;

/**
 * THIS controls the interaction between the GameModel and the GameView.
 * it receives the player's keyboard input & updates game accordingly.
 */

public class GameController implements KeyListener
{
    /* stores the game model */
    private final Game model;

    /* stores the game view */
    private final GameView view;

    /* keeps track of the currently selected animal */
    private Animal selectedAnimal = null;

    /**
     * it creates a controller and connects the model with the view
     *
     * @param model the game model
     * @param view the game view
     */
    public GameController(Game model, GameView view)
    {
        this.model = model;
        this.view = view;

        this.view.addKeyListener(this);
        this.view.setFocusable(true);
    }

    /**
     * this handles the player's keyboard input and moves the selected animal
     * based on the direction entered.
     *
     * @param a the key event from the user
     */
    @Override
    public void keyTyped(KeyEvent a)
    {
        if (selectedAnimal == null)
        {
            view.updateStatus("Select an animal first.");
            return;
        }

        switch (a.getKeyChar())
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
                return;
        }

        model.updateTurn();
        model.checkWin();

        view.updateStatus("Player " + model.getCurrentTurn() + "'s turn");
        view.refreshBoard();

        selectedAnimal = null;
    }

    /**
     * required by the KeyListener interface.
     */
    
    @Override
    public void keyPressed(KeyEvent a)
    {
        // intentionally left empty
    }

    /**
     * required by the KeyListener interface.
     */
    
    @Override
    public void keyReleased(KeyEvent a)
    {
        // intentionally left empty
    }
}
