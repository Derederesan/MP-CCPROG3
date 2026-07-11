/**
* An Animal allows the initialization of an animal as well as the rules of its movement. It 
* has getter methods for its name, rank and currentspace. It can also check if it can move 
* to a different space on the board. 
*/
public class Animal
{
    public static final int ELEPHANT = 8; 
    public static final int LION = 7; 
    public static final int TIGER = 6; 
    public static final int LEOPARD = 5; 
    public static final int DOG = 4; 
    public static final int WOLF = 3; 
    public static final int CAT = 2; 
    public static final int MOUSE = 1; 

    private String name; 
    private final int RANK; 
    private Space currentSpace; 
    private int ownerId; 
    private int col; 
    private int row; 

    /**
    *  Constructs a new Animal that takes the input provided 
    * and assigns it to the specified attributes of the class.
    */

    public Animal(String name, int rank, Space position, int ownerId, int col, int row)
    {
        this.name= name; 
        this.RANK = rank; 
        this.currentSpace = position;  
        this.ownerId = ownerId; 
        this.col =col; 
        this.row =row; 
    }

    /**
    * Retrieves the name of the animal 
    * @return the name of the animal 
    */

    public String getName()
    {
        return this.name; 
    }

    /**
    * Retrieves the rank of the animal 
    * @return the rank of the animal 
    */
    
    public int getRank()
    {
        return this.RANK; 
    }

    /**
    * Retrieves the current position of the animal 
    * @return the current position of the animal 
    */
    public Space getCurrentSpace()
    {
        return this.currentSpace;
    }
    /**
    *Retrieves the current row the animal is on 
    *@return the animal's current row 
    */
    public int getRow()
    {
        return this.row; 
    }
    /**
    *Retrieves the current column the animal is on 
    *@return the animal's current column 
    */
    public int getCol()
    {
        return this.col;
    }
    
    /**
    *  Checks if the animal can move to a certain position on the board  
    * @return true if and only if it is not moving to a river and is not 
    * moving to its own den 
    */

    public boolean canMove(Space position)
    {
        //if position is a river, not allowed 
        if(position.isRiver())
        {
            return false; 
        }
        //if the position is the player's own den, not allowed 
        if(position.isAnimalDen() && (position.getOwnerId() == this.ownerId))
        {
            return false; 
        }
        return true; 
    }
    /**
    * Sets the new position of the animal 
    * @param row the animal's new row 
    * @param col the animal's new column 
    */
    public void setPosition(int row, int col)
    {
        this.row = row;
        this.col =col; 
    }

    /**
    *Sets the new space of the animal 
    * @param newSpace the animal's new Space 
    */
    public void setSpace(newSpace)
    
    /**
     * Checks if the attacker can capture the intended target 
     * @param attacker the animal that the current player has chosen 
     * @param target the animal that the current player is targeting 
     * @return true if and only if the current target is either in a trap or attacker
     * is equal/higher rank than the target
     */
    public boolean canCapture(Animal attacker, Animal target)
    {
        //if attacker is currently on a trap 
        if(target.getCurrentSpace().isTrap())
        {
            return true; 
        }
        //if not, check if attacker is equal or higher rank than target 
        return attacker.getRank()>=target.getRank(); 
    }

    return false; 
    }
}
