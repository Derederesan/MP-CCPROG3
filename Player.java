package model;

/**
    *  A Player controls the movement of the animal on the board, as well as sets the color of animals. 
*/
import java.util.ArrayList;

public class Player 
{
    private int playerNum; 
    private String color; 
    private char move; 
    private ArrayList<Animal> animals; 

    /**
    *  Constructs a new Player that takes the input provided 
    * and assigns it to the specified attributes of the class.
    */
    public Player(int playerNum)
    {
        this.playerNum = playerNum;
        this.animals = new ArrayList<Animal>();
    }

    /**
    * Sets the color of the player's animal 
    * @param color the chosen color 
    */
    
    public void setColor(String color)
    {
        this.color = color; 
    }

    /**
    * Takes the current movement the player intends to execute 
    * @param movement the movement to be set 
    */
    
    public void setMove(char movement)
    {
        this.move = movement; 
    }

    /**
    * Gets the player's number 
    * @return the player's number 
    */
    
    public int getPlayerNum()
    {
        return this.playerNum; 
    }

    /**
    *  Gets the ArrayList of the player's animals 
    * @return the ArrayList of the player's animals 
    */
    
    public ArrayList<Animal> getAnimals()
    {
        return this.animals; 
    }
}
