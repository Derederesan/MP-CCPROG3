public class Space{

    public static final String LAND = "Land";  
    public static final String ANIMAL_DEN = "Animal Den"; 
    public static final String TRAP  ="Trap"; 
    public static final String RIVER = "River"; 

    private String type; 
    private Animal animal; 

    public Space(String type)
    {
        this.type=type; 
        this.animal=null; 
    }

    public boolean isAnimalDen()
    {
        return this.type.equals(ANIMAL_DEN); 
    }

    public boolean isLand()
    {
        return this.type.equals(LAND); 
    }

    public boolean isTrap()
    {
        return this.type.equals(TRAP); 
    }

    public boolean isRiver()
    {
        return this.type.equals(RIVER); 
    }

    public Animal getAnimal()
    {
        return this.animal; 
    }

    public void setAnimal(Animal animal)
    {
        this.animal= animal; 
    }
}