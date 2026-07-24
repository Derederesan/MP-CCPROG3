package model;

/**
* A Space allows the initialization of a space on the board, keeping track of whether 
* it has an animal on it and who owns it. It can create any of the four spaces: land, animal den, trap or river. 
* It also has a method to check if the specified space is either a river, a trap, a land or an animal den. 
* It also has setter and getter methods for its animal and owner Id. 
*/
public class Space{

    public static final String LAND = "Land";  
    public static final String ANIMAL_DEN = "Animal Den"; 
    public static final String TRAP  ="Trap"; 
    public static final String RIVER = "River"; 

    private String type; 
    private Animal animal; 
    private int ownerId; 

    /**
    *  Constructs a new Space that takes the input provided 
    * and assigns it to the specified attributes of the class.
    */
    public Space(String type, int ownerId)
    {
        this.type=type; 
        this.animal=null; 
        this.ownerId = ownerId; 
    }
    
    /**
    * Checks if the space is an animal den 
    * @return true if and only if the space is an animal den 
    */
    
    public boolean isAnimalDen()
    {
        return this.type.equals(ANIMAL_DEN); 
    }

    /**
    * Checks if the space is a land
    * @return true if and only if the space is a land  
    */
    
    public boolean isLand()
    {
        return this.type.equals(LAND); 
    }

    /**
    * Checks if the space is a trap 
    * @return true if and only if the space is a trap
    */
    
    public boolean isTrap()
    {
        return this.type.equals(TRAP); 
    }

    /**
    * Checks if the space is a river
    * @return true if and only if the space is a river  
    */
    
    public boolean isRiver()
    {
        return this.type.equals(RIVER); 
    }

    /**
    * Checks if the space is an enemy's den 
    * @param playerId takes the Id of the player to compare against the owner Id of the animal den 
    * @return true if and only if the space is an enemy's den 
    */
    
    public boolean isEnemyDen(int playerId)
    {
        return this.isAnimalDen() && (this.ownerId != playerId);  
    }

    /**
    * retrieves the animal residing on the space 
    * @return the animal 
    */
    
    public Animal getAnimal()
    {
        return this.animal; 
    }
    /**
    * Sets the animal within the space  
    * @param animal the animal to be put in the space 
    */
    
    public void setAnimal(Animal animal)
    {
        this.animal= animal; 
    }
    /**
    * retrieves the id of the current space's owner 
    * @return the id of the space's owner 
    */
    
    public int getOwnerId()
    {
        return this.ownerId; 
    }
}
