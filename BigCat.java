public class BigCat extends Animal
{
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
