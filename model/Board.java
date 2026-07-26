package model;

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
        initializeAnimals();
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
    /*
    *Initializes the animals onto the board 
    */
    public void initializeAnimals()
    {
        //initialize player one's animals(left side of board)
        addAnimal("Tiger", Animal.TIGER, 0,0,1); 
        addAnimal("Elephant", Animal.ELEPHANT, 0,2,1); 
        addAnimal("Cat", Animal.CAT, 1,1,1); 
        addAnimal("Wolf", Animal.WOLF, 2,2,1); 
        addAnimal("Leopard", Animal.LEOPARD, 4,2,1); 
        addAnimal("Dog", Animal.DOG, 5,1,1); 
        addAnimal("Lion", Animal.LION, 6,0,1); 
        addAnimal("Mouse", Animal.MOUSE, 2,0,1);

        //initialize player two's animals(right side of board)
        addAnimal("Mouse", Animal.MOUSE, 0,6,2); 
        addAnimal("Lion", Animal.LION, 8,6,2);
        addAnimal("Dog", Animal.DOG, 5,6,2);
        addAnimal("Leopard", Animal.LEOPARD, 2,6,2);
        addAnimal("Wolf", Animal.WOLF, 4,6,2);
        addAnimal("Cat", Animal.CAT, 7,5,2);
        addAnimal("Elephant", Animal.ELEPHANT, 6,6,2);
        addAnimal("Tiger", Animal.TIGER, 8,0,2);
    }
    /**
    *used to add an animal to the board 
    */
    private void addAnimal(String name, int rank, int row, int col, int ownerId)
    {
        Space space = getSpace(row, col); 
        Animal animal = new Animal(name, rank, space, ownerId, col, row); 
        space.setAnimal(animal); 
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
    * and moves the animal. Also checks if animal can capture another animal
    * @param animal the animal that is to be moved 
    * @param direction the direction the animal intends to move 
    */
    public void moveAnimal(Animal animal, char direction) 
    {
        int r = animal.getRow();
        int c = animal.getCol();
    
        if (animal instanceof BigCat) {
            // Jump logic
            while (getSpace(r, c) != null && getSpace(r, c).isRiver()) {
                if (direction == 'U') r--;
                else if (direction == 'D') r++;
                else if (direction == 'L') c--;
                else if (direction == 'R') c++;
            }
        } else {
            // Standard movement
            if (direction == 'U') r--;
            else if (direction == 'D') r++;
            else if (direction == 'L') c--;
            else if (direction == 'R') c++;
        }
    
        Space target = getSpace(r, c);
    
        if (isValidMove(animal, target)) {
            // Consolidated Capture and Move Logic
            if (target.getAnimal() != null) {
                Animal victim = target.getAnimal();
                if (animal.canCapture(victim)) {
                    target.setAnimal(null);
                    performMove(animal, target, r, c);
                } else {
                    System.out.println("Unable to capture animal!");
                }
            } else {
                performMove(animal, target, r, c);
            }
        }
    }
   
}
