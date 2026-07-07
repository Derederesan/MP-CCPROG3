public class BigCat extends Animal
{
    /**
     * Checks if a bigcat can jump from land to river 
     * @param target the space that the bigcat intends to jump to 
     * @return true if there is no mouse in the river 
     */
    public boolean jumpRiver(Space target)
    {
        //check if there is no mouse along the way 
        if(target.getAnimal().getRank() != 1 )
        {
            return true; 
        }
    }

}