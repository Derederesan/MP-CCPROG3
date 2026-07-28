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

    // first pick of both player 1 and player 2 
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

    public void handleInitialPick(Animal clickedAnimal)
    {
        if(p1Pick == null)
        {
            if(clickedAnimal.getOwnerId()==1)
            {
                p1Pick = clickedAnimal; 
                view.updateStatus("Player 1 picked " + p1Pick.getName()+". Player 2, pick an animal.");
            }
            else
            {
                view.updateStatus("Invalid! Pick your own animal!(blue)");
            }
        }
        else if(p2Pick == null) 
        {
            if(clickedAnimal.getOwnerId()==2)
            {
                p2Pick = clickedAnimal; 
                view.updateStatus("Player 2 picked " + p2Pick.getName());
                model.firstPick(p1Pick,p2Pick); 
                if(model.getCurrentTurn()==1)
                    view.updateStatus("Player 1 moves first."); 
                else if(model.getCurrentTurn()==2)
                    view.updateStatus("Player 2 moves first.");
                else if(model.getCurrentTurn()==-1)
                    view.updateStatus("Tie! Players must pick again.");
            }
            else
            {
                view.updateStatus("Invalid! Pick your own animal!(red)");
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
                        selectedAnimal = clickedAnimal;
                        Animal clickedAnimal = space.getAnimal();
                

                        if (clickedAnimal != null)
                        {
                            if(model.getCurrentTurn()==-1)
                            {
                                    handleInitialPick(clickedAnimal); 
                            }
                            else if (model.getCurrentTurn() != -1 && clickedAnimal.getOwnerId() != model.getCurrentTurn())
                            {
                                view.updateStatus("That's not your animal!");
                                selectedAnimal = null;
                            }
                            else if(model.getCurrentTurn()!=-1)
                            {
                                // removes the old highlight first
                                for (int row = 0; row < 9; row++)
                                {
                                    for (int col = 0; col < 7; col++)
                                        {
                                            buttons[row][col].setBorder(
                                                BorderFactory.createLineBorder(new Color(210, 210, 210))
                                            );
                                        }
                                }

selectedAnimal = clickedAnimal;

// highlights the selected animal
buttons[r][c].setBorder(
    BorderFactory.createLineBorder(new Color(255, 170, 0), 3)
);

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
        if(model.getCurrentTurn()==-1)
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
        switch (a.getKeyChar())
        {
            
            case 'L':
            case 'l':
                
                view.getBoardButton(r,c).setText("");
                model.getBoard().moveAnimal(selectedAnimal, 'L');
                break;

            case 'R':
            case 'r':
                
                view.getBoardButton(r,c).setText("");
                model.getBoard().moveAnimal(selectedAnimal, 'R');
       
                break;

            case 'U':
            case 'u':
                
                view.getBoardButton(r,c).setText("");
                model.getBoard().moveAnimal(selectedAnimal, 'U');
        
                break;

            case 'D':
            case 'd':
                
                view.getBoardButton(r,c).setText("");
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
