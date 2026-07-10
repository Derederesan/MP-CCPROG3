public class BigCat extends Animal
{
    /**
    *Constructs a new BigCat that takes the input and assigns it to its attributes 
    */
    public BigCat(String name, int rank, Space position)
    {
        this.name = name; 
        this.rank = rank; 
        this.position = position; 
    }
    /**
     * Checks if a bigcat can jump from land to river 
     * @param position the space that the bigcat intends to jump to 
     * @return true if there is no mouse in the river 
     */
    public boolean canMove(Space position)
    {
        //check if there is no mouse along the way 
        if(position.getAnimal().getRank() != 1 )
        {
            return true; 
        }
    }

}
