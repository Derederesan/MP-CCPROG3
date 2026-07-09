public class Mouse extends Animal
{
    public Mouse(Space position)
    {
        super("Mouse", 1, position);
    }

    public boolean canEnterRiver()
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

        // An opponent piece on a trap can be captured regardless of rank[cite: 1]
        if (target.getCurrentSpace().isTrap())
        {
            return true;
        }

        // Mouse can capture an elephant (rank 8) or another mouse (rank 1)[cite: 1]
        return target.getRank() == 8 || target.getRank() == 1;
    }
}
