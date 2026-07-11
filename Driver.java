import java.util.ArrayList;
public class Driver
{
    public static void main(String[] args)
    {
        // Initialize the players
        Player player1 = new Player(1);
        Player player2 = new Player(2);

        // Create the game
        Game game = new Game(player1, player2);
        
        // Covered and shuffled set
        ArrayList<Integer> shuffled = game.shuffleRanks();
        System.out.println("Covered and shuffled set: " + shuffled);
        
        System.out.println("=== Animal Chess ===");
        System.out.println("Game initialized successfully!");

        // Test first player selection
        System.out.println("\n--- Testing First Turn Selection ---");

        if (!player1.getAnimals().isEmpty() && !player2.getAnimals().isEmpty())
        {
            Animal p1FirstAnimal = player1.getAnimals().get(0);
            Animal p2FirstAnimal = player2.getAnimals().get(0);

            game.firstPick(p1FirstAnimal, p2FirstAnimal);
        }
        else
        {
            System.out.println("No animals initialized yet.");
        }

        // Test turn rotation
        System.out.println("\n--- Testing Turn Rotation ---");

        System.out.println("Starting Turn: Player " + game.getCurrentTurn());

        game.updateTurn();

        System.out.println("Next Turn: Player " + game.getCurrentTurn());

        // Test win condition
        System.out.println("\n--- Checking Win Conditions ---");

        game.checkWin();

        if (game.getWinner() != null)
        {
            System.out.println("Winner: Player " + game.getWinner().getPlayerNum());
        }
        else
        {
            System.out.println("No winner yet.");
        }
    }
}
