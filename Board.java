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
    *  Initializes the board, adding the spaces  
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
                    int ownerId = (i == 0) ? 2 : 1;
                    this.board[i][j] = new Space(Space.ANIMAL_DEN, ownerId);
                }
                // Traps around dens
                else if(((i == 0 || i == 8) && (j == 2 || j == 4)) || ((i == 1 || i == 7) && j == 3))
                {
                    int ownerId = (i <= 1) ? 2 : 1;
                    this.board[i][j] = new Space(Space.TRAP, ownerId);
                }
                // River blocks
                else if((i >= 3 && i <= 5) && (j == 1 || j == 2 || j == 4 || j == 5))
                {
                    this.board[i][j] = new Space(Space.RIVER, 0);
                }
                // Regular Land
                else
                {
                    this.board[i][j] = new Space(Space.LAND, 0);
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
            System.out.println("Move blocked: Out of bounds!");
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
    *@param animal the animal that will move 
    *@param target the space the animal is moving to 
    *@param newRow the row the animal is moving to 
    *@param newCol the column the animal is moving to 
    */
    private void performMove(Animal animal, Space target, int newRow, int newCol) 
    {
        // Make the animal's old space null
        animal.getCurrentSpace().setAnimal(null);
        
        // Put animal in new space
        target.setAnimal(animal);
        
        // Update the animal's position
        animal.updatePosition(target, newRow, newCol);
    }
   /**  
    * Calculates the coordinates the animal intends to move to, checks if move is valis 
    * and moves the animal 
    * @param animal the animal that is to be moved 
    * @param direction the direction the animal intends to move 
    */
    public void moveAnimal(Animal animal, char direction)
    {
        //calculate coordinates 
        int r = animal.getRow();
        int c= animal.getCol(); 
        
    	if(animal instanceof BigCat)
    	{
           while( 
    	}
    	else
    	{
        	if(direction == 'U')
                {
                    r--;
                }
                else if(direction == 'D')
                {
                    r++;
                }
                else if(direction == 'L')
                {
                    c--;
                }
                else if(direction == 'R')
                {
                    c++;
                }
        
                Space target = getSpace(r, c); 
        
                if (isValidMove(animal,target))
                {
                    performMove(animal, target, r,c); 
                }
    	}
         
        
    }
   
}
