package model;

/**
    * The subclass of the abstract superclass BigCat 
    */
    
public class Tiger extends BigCat
{
    /**
    * Constructs the a new Tiger that takes the provided input and assigns it 
    * to the specified attributes of the class 
    */

    public Tiger(Space position)
    {
        super("Tiger", 6, position, 0, 0, 0);
    }

    /**
    * Constructs the a new Tiger that takes the provided input and assigns it 
    * to the specified attributes of the class 
    */
    public Tiger (String name, int rank, Space position, int ownerId, int col, int row)
    {
        super(name, rank, position, ownerId, col, row);
    }

}
