public class Driver
{
    public static void main(String[] args)
    {
        // Initialize the players for the match
        Player player1 = new Player(1);
        Player player2 = new Player(2);

        // Instantiate the main game controller
        Game game = new Game(player1, player2);
        System.out.println("Game initialized successfully!");

        // simple test check for first pick using the players' animals
        System.out.println("\n--- Testing First Turn Selection ---");
        if (!player1.getAnimals().isEmpty() && !player2.getAnimals().isEmpty()) 
        {
            Animal p1FirstAnimal = player1.getAnimals().get(0);
            Animal p2FirstAnimal = player2.getAnimals().get(0);
            game.firstPick(p1FirstAnimal, p2FirstAnimal);
        } else {
            System.out.println("Setup temporary pieces in your Player class to test firstPick!");
        }

        // basic turn rotation verification
        System.out.println("\n--- Testing Turn Rotation ---");
        System.out.println("Starting Turn: Player " + game.getCurrentTurn());
        game.updateTurn();
        System.out.println("Next Turn: Player " + game.getCurrentTurn());

        // run a quick win condition check
        System.out.println("\n--- Checking Win Conditions ---");
        game.checkWin();
        System.out.println("Is the game currently over? " + (game.getWinner() != null));
    }
}
