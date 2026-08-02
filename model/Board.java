package model;

/**
 * A Board allows the initialization of the board, adding the spaces and the animals accordingly.
 * Aside from this, the Board can also check if an animal's movement is valid before updating its
 *position on the board. It also has a getter for the space on the board.
 */

import java.util.*;

public class Board {
    private Space[][] board;
    public static final int ROWS = 7;
    public static final int COLS = 9;

    /**
     * Constructs a new Board that takes the input provided
     * and assigns it to the specified attributes of the class.
     */

    public Board() {
        this.board = new Space[ROWS][COLS];
        initializeBoard();
        initializeAnimals();
    }

    /**
     * Initializes the board, adding the spaces
     */

    public void initializeBoard() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                // Animal Dens
                if (i == 3 && j == 0)
                {
                    this.board[i][j] = new AnimalDen(1);
                } else if (i == 3 && j == 8)
                {
                    this.board[i][j] = new AnimalDen(2);
                }
                // Traps around dens
                else if (((i == 2 || i == 4) && (j == 0 || j == 8)) || ((i == 3) && (j == 1 || j == 7))) {
                    int ownerId = (j < 4) ? 1 : 2;
                    this.board[i][j] = new Trap(ownerId);
                }
                // River blocks
                else if ((j >= 3 && j <= 5) && (i == 1 || i == 2 || i == 4 || i == 5)) {
                    this.board[i][j] = new River(0);
                }
                // Regular Land
                else {
                    this.board[i][j] = new Land(0);
                }
            }
        }
    }

    /*
     *Initializes the animals onto the board
     */
    public void initializeAnimals() {
        //initialize player one's animals(left side of board)
        addAnimal("Tiger", Animal.TIGER, 0, 0, 1);
        addAnimal("Elephant", Animal.ELEPHANT, 0, 2, 1);
        addAnimal("Cat", Animal.CAT, 1, 1, 1);
        addAnimal("Wolf", Animal.WOLF, 2, 2, 1);
        addAnimal("Leopard", Animal.LEOPARD, 4, 2, 1);
        addAnimal("Dog", Animal.DOG, 5, 1, 1);
        addAnimal("Lion", Animal.LION, 6, 0, 1);
        addAnimal("Mouse", Animal.MOUSE, 6, 2, 1);

        //initialize player two's animals(right side of board)
        addAnimal("Mouse", Animal.MOUSE, 0, 6, 2);
        addAnimal("Lion", Animal.LION, 0, 8, 2);
        addAnimal("Dog", Animal.DOG, 1, 7, 2);
        addAnimal("Leopard", Animal.LEOPARD, 2, 6, 2);
        addAnimal("Wolf", Animal.WOLF, 4, 6, 2);
        addAnimal("Cat", Animal.CAT, 5, 7, 2);
        addAnimal("Elephant", Animal.ELEPHANT, 6, 6, 2);
        addAnimal("Tiger", Animal.TIGER, 6, 8, 2);

    }

    /**
     * used to add an animal to the board
     */
    private void addAnimal(String name, int rank, int row, int col, int ownerId) {
        Space space = getSpace(row, col);
        Animal animal;
        //switch statement to initialize polymorphism(mouse, tiger and lion class);
        switch(rank)
        {
            case Animal.MOUSE:
                animal = new Mouse(space, ownerId, col, row);
                break;

            case Animal.LION:
                animal = new Lion(space, ownerId, col, row);
                break;

            case Animal.TIGER:
                animal = new Tiger(space, ownerId, col, row);
                break;

            default:
                animal = new Animal(name, rank, space, ownerId, col, row);
                break;
        }
        space.setAnimal(animal);
    }

    /**
     * Retrieves the space according to the parameters
     *
     * @param row the specified row
     * @param col the specifies col
     * @return corresponding board position or null
     */

    public Space getSpace(int row, int col) {
        if (row >= 0 && row < ROWS && col >= 0 && col < COLS) {
            return this.board[row][col];
        }
        return null;
    }

    /**
     * Checks if the animal can move to its target position
     *
     * @param animal the animal to move
     * @param target the intended position
     * @return true if and only if the position is
     */

    public boolean isValidMove(Animal animal, Space target) {
        if (target == null)
        {
            return false;
        }

        if (!animal.canMove(target))
        {
            return false;
        }

        //if animal is currently on a trap
        if (animal.getCurrentSpace().isTrap() && animal.getSkipsTurn()==true)
        {
            return false;
        }

        if(target.getAnimal()!=null && target.getAnimal().getRank()>animal.getRank())
        {
            return false;
        }
        return true;
    }

    /**
     * Moves an animal from is previous space to its new space
     *
     * @param animal the animal that will move
     * @param target the space the animal is moving to
     * @param newRow the row the animal is moving to
     * @param newCol the column the animal is moving to
     */
    private void performMove(Animal animal, Space target, int newRow, int newCol) {
        // Make the animal's old space null
        animal.getCurrentSpace().setAnimal(null);

        // Put animal in new space
        target.setAnimal(animal);

        // Update the animal's position
        animal.updatePosition(target, newRow, newCol);
    }

    public String possibleMove(Animal animal)
    {
        int curRow = animal.getRow();
        int curCol = animal.getCol();
        String Up = "";
        String Down = "";
        String Left = "";
        String Right = "";
        if(curRow > 0 && this.isValidMove(animal,this.board[curRow-1][curCol]))
            Up = "U";
        if(curRow<ROWS -1 && this.isValidMove(animal,this.board[curRow+1][curCol]))
            Down =" D";
        if(curCol >0 && this.isValidMove(animal,this.board[curRow][curCol-1]))
            Left = " L";
        if(curCol<COLS-1 && this.isValidMove(animal,this.board[curRow][curCol+1]))
            Right = " R";
        if(animal.getSkipsTurn()==true)
            return "Skips Turn!";
        if(Up.equals("")&& Down.equals("")&&Left.equals("")&&Right.equals(""))
            return "No Possible Moves.";
        return "Possible Moves: " + Up + Down + Left + Right;
    }
    /**
     * Calculates the coordinates the animal intends to move to, checks if move is valis
     * and moves the animal. Also checks if animal can capture another animal
     *
     * @param animal    the animal that is to be moved
     * @param direction the direction the animal intends to move
     */
    public String moveAnimal(Animal animal, char direction) {
        int curCol = animal.getCol();
        int curRow = animal.getRow();
        int r = animal.getRow();
        int c = animal.getCol();

        //move one step first
        if (direction == 'U') r--;
        else if (direction == 'D') r++;
        else if (direction == 'L') c--;
        else if (direction == 'R') c++;

        Space target = getSpace(r, c);

        if ((animal instanceof Tiger || animal instanceof Lion)
                && target != null
                && target.isRiver()) {

            // Jump logic - check for mouse blocking in intermediate river tiles
            while (target != null && target.isRiver()) {
                if (target.getAnimal() != null && target.getAnimal().getRank() == Animal.MOUSE) {
                    return "Invalid move!"; // Blocked by mouse in river
                }

                if (direction == 'U') r--;
                else if (direction == 'D') r++;
                else if (direction == 'L') c--;
                else if (direction == 'R') c++;

                target = getSpace(r, c);
            }

            if (target == null) {
                return "Invalid move!";
            }
        }

        if (isValidMove(animal, target)) {
            if (target.getAnimal() != null){
                Animal victim = target.getAnimal();

                if (animal.canCapture(victim)) {
                    String victimName = victim.getName();

                    target.setAnimal(null);
                    performMove(animal, target, r, c);

                    return animal.getName() + " captured " + victimName + "!";
                } else {
                    return "Unable to capture " + victim.getName() + "!";
                }
            } else {

                Space oldSpace = animal.getCurrentSpace();
                performMove(animal, target, r, c);
                if(oldSpace.isTrap())
                {
                    this.board[curRow][curCol]= new Land(0);
                }
                if(target.isTrap())
                {
                    animal.setSkipTurn(true);
                    return animal.getName() + " is Trapped!";
                }
                else
                    return animal.getName() + " moved.";
            }

        }
        else if(target == null)
        {
            return "Out of bounds!";
        }
        else if (animal.getCurrentSpace().isTrap() && animal.getSkipsTurn()==true)
        {
            return animal.getName() + " is still Trapped!";
        }
        else if(target.isTrap() && (target.getOwnerId() == animal.getOwnerId()))
        {
            return animal.getName() + " cannot move to own trap!";
        }
        else if(target.isAnimalDen() && (target.getOwnerId() == animal.getOwnerId()))
        {
            return animal.getName() + " cannot move to own den!";
        }
        else if(target.isRiver())
        {
            return animal.getName() + " cannot move into river!";
        }
        System.out.println(animal.getSkipsTurn());
        return "Invalid move!";
    }
}
