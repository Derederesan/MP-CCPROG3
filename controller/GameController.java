package controller;

import model.Game;
import model.Space;
import model.Animal;
import view.GameView;
import java.awt.Color;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.BorderFactory;

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

    /* first pick of both player 1 and player 2 */
    private Animal p1Pick = null;
    private Animal p2Pick = null;

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

        for (int r = 0; r < 7; r++)
        {
            for (int c = 0; c < 9; c++)
            {
                buttons[r][c].addActionListener(this);
            }
        }
    }

    /**
     * handles the first animal pick of both players.
     *
     * @param clickedAnimal the animal selected by the player
     */
    public void handleInitialPick(Animal clickedAnimal)
    {
        if (p1Pick == null)
        {
            if (clickedAnimal.getOwnerId() == 1)
            {
                p1Pick = clickedAnimal;
                view.updateStatus("Player 1 picked " + p1Pick.getName()
                        + ". Player 2, pick an animal.");
            }
            else
            {
                view.updateStatus("Invalid! Pick your own animal! (blue)");
            }
        }
        else if (p2Pick == null)
        {
            if (clickedAnimal.getOwnerId() == 2)
            {
                p2Pick = clickedAnimal;
                view.updateStatus("Player 2 picked " + p2Pick.getName());

                model.firstPick(p1Pick, p2Pick);

                if (model.getCurrentTurn() == 1)
                {
                    view.updateStatus("Player 2 picked " + p2Pick.getName() + ". Player 1 moves first.");
                    view.highlightTurn(1);
                }
                else if (model.getCurrentTurn() == 2)
                {
                    view.updateStatus("Player 2 picked " + p2Pick.getName() +". Player 2 moves first.");
                    view.highlightTurn(2);
                }
                else if (model.getCurrentTurn() == -1)
                {
                    view.updateStatus("Tie! Players must pick again.");

                    // reset both picks so they can choose again
                    p1Pick = null;
                    p2Pick = null;
                }
            }
            else
            {
                view.updateStatus("Invalid! Pick your own animal! (red)");
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

        for (int r = 0; r < 7; r++)
        {
            for (int c = 0; c < 9; c++)
            {
                if (e.getSource() == buttons[r][c])
                {
                    Space space = model.getBoard().getSpace(r, c);

                    if (space != null)
                    {
                        Animal clickedAnimal = space.getAnimal();

                        if (clickedAnimal != null)
                        {
                            if (model.getCurrentTurn() == -1)
                            {
                                handleInitialPick(clickedAnimal);
                            }
                            else if (clickedAnimal.getOwnerId() != model.getCurrentTurn())
                            {
                                view.updateStatus("That's not your animal!");
                                selectedAnimal = null;
                            }
                            else
                            {
                                // removes the old highlight first
                                for (int row = 0; row < 7; row++)
                                {
                                    for (int col = 0; col < 9; col++)
                                    {
                                        buttons[row][col].setBorder(
                                                BorderFactory.createLineBorder(
                                                        new Color(210, 210, 210)
                                                )
                                        );
                                    }
                                }

                                selectedAnimal = clickedAnimal;

                                // highlights the selected animal
                                buttons[r][c].setBorder(
                                        BorderFactory.createLineBorder(
                                                new Color(255, 170, 0), 3
                                        )
                                );

                                view.updateStatus(
                                        "Selected: " + selectedAnimal.getName()
                                                + ". Use U/D/L/R to move. \n" +
                                        model.getBoard().possibleMove(selectedAnimal)
                                );
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
        if (model.getCurrentTurn() == -1)
        {
            view.updateStatus("Please complete animal selection first!");
            return;
        }

        if (selectedAnimal == null)
        {
            view.updateStatus("Select an animal first.");
            return;
        }

        int r = selectedAnimal.getRow();
        int c = selectedAnimal.getCol();

        String moveMessage;
        String Moves; 

        switch (a.getKeyChar())
        {
            case 'L':
            case 'l':
                view.getBoardButton(r, c).setText("");
                moveMessage = model.getBoard().moveAnimal(selectedAnimal, 'L');
                Moves = model.getBoard().possibleMove(selectedAnimal);
                break;

            case 'R':
            case 'r':
                view.getBoardButton(r, c).setText("");
                moveMessage = model.getBoard().moveAnimal(selectedAnimal, 'R');
                Moves = model.getBoard().possibleMove(selectedAnimal);
                break;

            case 'U':
            case 'u':
                view.getBoardButton(r, c).setText("");
                moveMessage = model.getBoard().moveAnimal(selectedAnimal, 'U');
                Moves = model.getBoard().possibleMove(selectedAnimal);
                break;

            case 'D':
            case 'd':
                view.getBoardButton(r, c).setText("");
                moveMessage = model.getBoard().moveAnimal(selectedAnimal, 'D');
                Moves = model.getBoard().possibleMove(selectedAnimal);
                break;

            default:
                view.updateStatus("Invalid input! Use U, D, L, or R.");
                return;
        }

        // if the move did not happen, the player's turn stays the same
        if (moveMessage.equals("Invalid move!")
                || moveMessage.equals("Out of bounds!")
                 || moveMessage.contains("Unable to capture") 
                || moveMessage.contains("cannot move"))
        {
            view.updateStatus(moveMessage + " Try again. " + Moves);
            view.refreshBoard(model);
            view.requestFocusInWindow();
            selectedAnimal = null;
            return;
        }
        else if( moveMessage.contains("is still Trapped!"))
        {
            view.updateStatus(moveMessage + " " + Moves);
            view.refreshBoard(model);
            view.requestFocusInWindow();
            selectedAnimal.setSkipTurn(false);
            selectedAnimal = null;
            return;
        }

model.checkWin();

        if (model.getWinner() != null)
        {
            view.showMessage(
                    "Player " + model.getWinner().getPlayerNum() + " wins!"
            );
        }
        else
        {
             // changes turn only after a successful move
            model.updateTurn();
            view.updateStatus(
                    moveMessage + " Player "
                            + model.getCurrentTurn() + "'s turn."
            );

            view.highlightTurn(model.getCurrentTurn());
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
