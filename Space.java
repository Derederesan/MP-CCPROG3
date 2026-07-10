public class Space{

    public static final String LAND = "Land";  
    public static final String ANIMAL_DEN = "Animal Den"; 
    public static final String TRAP  ="Trap"; 
    public static final String RIVER = "River"; 

    private String type; 
    private Animal animal; 
    private int ownerId; 

    public Space(String type, int ownerId)
    {
        this.type=type; 
        this.animal=null; 
        this.ownerId = ownerId; 
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

    public boolean isEnemyDen(int playerId)
    {
        return this.isAnimalDen() && (this.ownerId != playerId);  
    }

    public Animal getAnimal()
    {
        return this.animal; 
    }

    public void setAnimal(Animal animal)
    {
        this.animal= animal; 
    }

    public int getOwnerId()
    {
        return this.ownerId; 
    }
}
