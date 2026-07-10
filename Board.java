/**
* A Board allows the initialization of the board, adding the spaces and the animals accordingly.
* Aside from this, the Board can also check if an animal's movement is valid before updating its 
*position on the board. It also has a getter for the space on the board. 
*/

import java.util.*; 

public class Board 
{
    private Space[][] board; 
    public static final int ROWS = 9; 
    public static final int COLS = 7; 

    /**
    *  Constructs a new Board that takes the input provided 
    * and assigns it to the specified attributes of the class.
    */

    public Board()
    {
        this.board = new Space[ROWS][COLS];
        initializeBoard();
    }

    /**
    *  Initializes the board, adding the spaces and initial spaces of the animals 
    */

    public void initializeBoard()
    {
        for(int i = 0; i < ROWS; i++)
        {
            for(int j = 0; j < COLS; j++)
            {
                // Animal Dens
                if((i == 0 && j == 3) || (i == 8 && j == 3))
                {
                    this.board[i][j] = new Space(Space.ANIMAL_DEN);
                }
                // Traps around dens
                else if(((i == 0 || i == 8) && (j == 2 || j == 4)) || ((i == 1 || i == 7) && j == 3))
                {
                    this.board[i][j] = new Space(Space.TRAP);
                }
                // River blocks
                else if((i >= 3 && i <= 5) && (j == 1 || j == 2 || j == 4 || j == 5))
                {
                    this.board[i][j] = new Space(Space.RIVER);
                }
                // Regular Land
                else
                {
                    this.board[i][j] = new Space(Space.LAND);
                }
            }
        }
    }

    /**
    * Retrieves the space according to the parameters 
    * @param row the specified row 
    * @param col the specifies col 
    * @return corresponding board position or null 
    */

    public Space getSpace(int row, int col)
    {
        if(row >= 0 && row < ROWS && col >= 0 && col < COLS)
        {
            return this.board[row][col];
        }
        return null;
    }

    /**
    *  Checks if the animal can move to its target position 
    * @param animal the animal to move 
    * @param target the intended position 
    * @return true if and only if the position is 
    */

    public boolean isValidMove(Animal animal, Space target)
    {
        if(target == null)
        {
            return false;
        }
        if(!animal.canMove(target))
        {
            return false; 
        }
        // Basic requirement rule: program logic check structure matching ruleset
        return true;
    }
    /**  
    *Moves an animal from is previous space to its new space 
    * @param animal the animal that is to be moved 
    * @target the new space the animal intends to occupy 
    */
    public void moveAnimal(Animal animal, Space target)
    {
        //make the animals' old space null 
        animal.getCurrentSpace().setAnimal(null); 
        //put animal in new space
        target.setAnimal(animal); 
        //update animal's position
        animal.setPosition(target); 
    }
}
