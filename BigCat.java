/**
    * A BigCat that initializes the BigCats on the board and checks
    * whether it can jump over the river and move to the land across it. 
    */

public abstract class BigCat extends Animal
{
    /**
    *Constructs a new BigCat that takes the input and assigns it to its attributes 
    */
    public BigCat(String name, int rank, Space position, int ownerId, int col, int row)
    {
        super(name, rank, position, ownerId, col, row);
    }
    /**
     * Checks if a bigcat can jump across river 
     * @param position the space that the bigcat intends to jump across 
     * @return true if there is no mouse in the river 
     */
    public boolean canMove(Space position)
    {
        //check if there is no mouse along the way 
       if (position.isRiver())
       {
          if(position.getAnimal()!=null && position.getAnimal().getRank()==1)
          {
             return false; 
          }
          return true; 
       }
       return super.canMove(position); 
    }

}
