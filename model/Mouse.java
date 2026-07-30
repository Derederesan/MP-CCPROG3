package model;

/**
    *  A Mouse is a subclass of the superclass animal. It checks wheter a mouse can enter a  river,
    * and whether or not it can capture a specific target.
    */

public class Mouse extends Animal
{
   /**
   * Constructs a new Mouse that takes the provided input
   * and assigns it to the attributes of the class
   */
   public Mouse(Space position)
   {
       super("Mouse", 1, position, 0, 0, 0);
   }

    /**
   * Constructs a new Mouse that takes the provided input
   * and assigns it to the attributes of the class
   */
     public Mouse(String name, int rank, Space position, int ownerId, int col, int row)
    {
       super(name, rank, position, ownerId, col, row);
    }

   /**
   * Checks if Mouse can move to a specific position
   * @param position the space where mouse intends to move
   */
    public boolean canMove(Space position)
    {
       //if river can cross
       if (position.isRiver())
       {
          return true;
       }
       //if not river, check if it can cross
        return super.canMove(position);
    }

    
     /**
     * Checks if the attacker can capture the intended target 
     * @param target the animal that the current player is targeting 
     * @return true if and only if the current target is either an elephant or another mouse 
     */
    @Override
    public boolean canCapture(Animal target)
    {
        // A mouse on the river may not capture an elephant or another mouse on land[cite: 1]
        if (this.getCurrentSpace().isRiver() && target.getCurrentSpace().isLand())
        {
            return false;
        }

        // A mouse on land may not capture a mouse on the river[cite: 1]
        if (this.getCurrentSpace().isLand() && target.getCurrentSpace().isRiver())
        {
            return false;
        }

        // Mouse can capture an elephant (rank 8) or another mouse (rank 1)[cite: 1]
        if(target.getRank() == 8 || target.getRank() == 1)
        {
                return true;
        }
        
        // Default: Mouse cannot capture any other animal ranks
        return false;
    }
}
