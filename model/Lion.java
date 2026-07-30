package model;

/**
* A lion is the subclass of the abstract superclass BigCat 
*/
public class Lion extends BigCat
{
    /**
    *  Constructs a new Space that takes the input provided 
    * and assigns it to the specified attributes of the class.
    */
    public Lion(Space position)
    {
        super("Lion", 7, position, 0, 0, 0);
    }
     /**
    *  Constructs a new Space that takes the input provided 
    * and assigns it to the specified attributes of the class.
    */
    public Lion(String name, int rank, Space position, int ownerId, int col, int row)
    {
       super(name, rank, position, ownerId, col, row);
    }

}
