import java.util.ArrayList;

public class Player 
{
    private int playerNum; 
    private String name; 
    private String color; 
    private char movement; 
    private ArrayList<Animal> animals; 

    public Player(int playerNum, String name)
    {
        this.playerNum = playerNum;
        this.name = name;
        this.animals = new ArrayList<Animal>();
    }

    public void setColor(String color)
    {
        this.color = color; 
    }

    public void setMove(char movement)
    {
        this.movement = movement; 
    }

    public int getPlayerNum()
    {
        return this.playerNum; 
    }

    public ArrayList<Animal> getAnimals()
    {
        return this.animals; 
    }
}