package model;
/**
 * A River is a child class that inherits the methods and attributes of Space.
 */
public class River extends Space{

    /**
     *  Constructs a new River that takes the input provided
     * and assigns it to the specified attributes of the class.
     */
    public River(int ownerId)
    {
        super("River", ownerId);
    }
}
