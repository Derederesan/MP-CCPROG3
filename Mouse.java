public class Mouse extends Animal 
{
    public Mouse(Space position) 
    {
        super("Mouse", 1, position);
    }

    /**
     * Checks if the mouse can enter a space, allowing river access.
     * @return true since mice can swim
     */
    public boolean canEnterRiver() 
    {
        // TODO: check if anything blocks entry? 
        // // if target is occupied logic? edit later nalang
        return true;
    }

    /**
     * Checks if the mouse can capture a target piece based on terrain rules.
     * @param target the opponent piece to capture
     * @return true if capture is valid
     */
    public boolean canCapture(Animal target) 
    {
        // A mouse on the river may not capture an elephant or another mouse on land[cite: 1]
        if (this.getCurrentSpace().isRiver() && target.getCurrentSpace().isLand()) 
        {
            // // wait up matching to game logic canCapture or separate?
            return false;
        }
        
        // Mouse can capture elephant (rank 8) or another mouse (rank 1)[cite: 1]
        // // verify if tracking the traps are needed to structure directly or separate logic block
        return target.getRank() == 8 || target.getRank() == 1;
    }
}
