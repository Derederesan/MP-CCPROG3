public class Animal
{
    public static final int ELEPHANT = 8; 
    public static final int LION = 7; 
    public static final int TIGER = 6; 
    public static final int LEOPARD = 5; 
    public static final int DOG = 4; 
    public static final int WOLF = 3; 
    public static final int CAT = 2; 
    public static final int MOUSE = 1; 

    private String name; 
    private final int RANK; 
    private Space currentSpace; 

    public Animal(String name, int rank, Space position)
    {
        this.name= name; 
        this.RANK = rank; 
        this.currentSpace = position;  
    }

    public String getName()
    {
        return this.name; 
    }

    public int getRank()
    {
        return this.RANK; 
    }

    public Space getCurrentSpace()
    {
        return this.currentSpace;
    }

    public boolean canMove(Space position)
    {
    }
}
