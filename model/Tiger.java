package model;

/**
 * A Trap is a child class that inherits the methods and attributes of Space.
 */

public class Trap extends Space{

    /**
     *  Constructs a new Trap that takes the input provided
     * and assigns it to the specified attributes of the class.
     */
    public Trap(int ownerId)
    {
        super("Trap", ownerId);
    }
}
