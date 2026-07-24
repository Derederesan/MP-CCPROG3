package controller;

import java.awt.event.*;
import javax.swing.JButton;
import model.Game;
import model.Animal;
import model.Space;
import view.GameView;

/**
 * THIS controls the interaction between the GameModel and the GameView.
 * it receives the player's keyboard input & updates game accordingly.
 */

public class GameController implements KeyListener, ActionListener
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

        JButton[][] buttons = this.view.getBoardButtons();
        for (int r = 0; r < 9; r++)
        {
            for (int c = 0; c < 7; c++)
            {
                buttons[r][c].addActionListener(this);
            }
        }
    }

    /**
     * handles piece selection when a board button is clicked.
     *
     * @param e the action event from the board button
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        JButton[][] buttons = view.getBoardButtons();

        for (int r = 0; r < 9; r++)
        {
            for (int c = 0; c < 7; c++)
            {
                if (e.getSource() == buttons[r][c])
                {
                    Space space = model.getBoard().getSpace(r, c);
                    if (space != null)
                    {
                        Animal clickedAnimal = space.getAnimal();

                        if (clickedAnimal != null)
                        {
                            if (model.getCurrentTurn() != -1 && clickedAnimal.getOwnerId() != model.getCurrentTurn())
                            {
                                view.updateStatus("That's not your animal!");
                                selectedAnimal = null;
                            }
                            else
                            {
                                selectedAnimal = clickedAnimal;
                                view.updateStatus("Selected: " + selectedAnimal.getName() + ". Use U/D/L/R to move.");
                            }
                        }
                        else
                        {
                            view.updateStatus("No animal on this space.");
                            selectedAnimal = null;
                        }
                    }

                    view.requestFocusInWindow();
                    return;
                }
            }
        }
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
                view.updateStatus("Invalid input! Use U, D, L, or R.");
                return;
        }

        model.updateTurn();
        model.checkWin();

        if (model.getWinner() != null)
        {
            view.showMessage("Player " + model.getWinner().getPlayerNum() + " wins!");
        }
        else
        {
            view.updateStatus("Player " + model.getCurrentTurn() + "'s turn");
        }

        view.refreshBoard(model);
        view.requestFocusInWindow();
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
