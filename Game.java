/**
 * 
 */
public class Game 
{
    private int currentTurn = -1; 
    private Player player1; 
    private Player player2; 
    private Board board; 

    /**
     * Compares the chosen animal piece of each player 
     * and uses it to determine which player goes first. 
     * @param 1 chosen animal piece of player 1 
     * @param 2 chosen animal piece of player 2
     * @return the player that goes first 
     */
    public int firstPick(Animal p1Choice, Animal p2Choice)
    {
      if(p1Choice.getRank()>p2Choice.getRank())
      {
        System.out.println("Player 1 moves first.");
        this.currentTurn=1; 
        return 1; 
      }  
      else if(p1Choice.getRank()<p2Choice.getRank())
      {
        System.out.println("Player 2 moves first."); 
        this.currentTurn=2; 
        return 2;
      }
      else
      {
        System.out.println("Tie! Players must pick again.");
        return -1; 
      }
    } 

    /**
     * Keeps track of which player is currently playing 
     */
    public void updateTurn ()
    {
        if(this.currentTurn==1)
        {
            this.currentTurn=2;
        }
        else if(this.currentTurn==2)
        {
            this.currentTurn=1; 
        }
    }

    public boolean canCapture(Animal attacker, Animal target)
    {
        //check if attacker is an elephant and target is a mouse
        if(attacker.getRank()==8 && target.getRank()==1)
        {
            System.out.println("Invalid capture. Elephant cannot capture a mouse");
            return false;
        }
        //check if attacker is a mouse and is on the river
        else if(attacker.getRank()==1 && attacker.getCurrentSpace().isRiver())
        {
             //check if target is elephant
                 if(target.getRank()==8)
                 {
                    return false; 
                 }
                 //check if target is a mouse
                 else if(target.getRank()==1)
                 {
                    //check if mouse is on land 
                    if(target.getCurrentSpace().isLand())
                    {
                        return false; 
                    }
                    else if(target.getCurrentSpace().isRiver())
                    {
                        return true; 
                    }
                 }        
        }
         //if mouse is on land
            else
            {
                //check if target is elephant
                if(target.getRank()==8)
                {
                    return true; 
                }
                //check if target is mouse and is on river
                else if(target.getRank()==1)
                {
                    if(target.getCurrentSpace().isRiver())
                    {
                        return false;
                    }
                    else if(target.getCurrentSpace().isLand())
                    {
                        return true; 
                    }
                    
                }    
            }

        //if attacker is none of the above, check if opponent's piece is on one of player's traps 
        if(target.getCurrentSpace().isTrap())
        {
            return true; 
        }
        //if not, check if attacker is equal or higher rank than target 
        return attacker.getRank()>=target.getRank(); 
    }

    return false; 
}
