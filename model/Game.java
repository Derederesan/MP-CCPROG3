package model;


/**
 * A Land is a child class that inherits the methods and attributes of Space.
 */
public class Land extends Space
{
    /**
     *  Constructs a new Land that takes the input provided
     * and assigns it to the specified attributes of the class.
     */
    public Land(int ownerId)
    {
        super("Land", ownerId);
    }
}
