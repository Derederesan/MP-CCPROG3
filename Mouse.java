/**
    *  A Mouse is a subclass of the superclass animal. It checks wheter a mouse can enter a  river, 
    * and whether or not it can capture a specific target. 
    */

public class Mouse extends Animal
{
   /**
   *
   */
    public Mouse(Space position)
    {
        super("Mouse", 1, position);
    }

    public boolean canMove()
    {
        return true;
    }

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
        return target.getRank() == 8 || target.getRank() == 1;
    }
}
