import java.util.Scanner;
import java.util.ArrayList;

public class Driver
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        // Initialize the players
        Player player1 = new Player(1);
        Player player2 = new Player(2);

        // Initialize the game
        Game game = new Game(player1, player2);

        // Initialize the board
        Board board = new Board(player1, player2);

        // Covered and shuffled set (ONLY if shuffleRanks() exists)
        System.out.println("Covered and shuffled set feature not yet implemented.");

        System.out.println("=======================================");
        System.out.println("||                                   ||");
        System.out.println("||            Animal Chess           ||");
        System.out.println("||                                   ||");
        System.out.println("=======================================");

        System.out.println("-----------------------------------------");
        System.out.println("*                                       *");
        System.out.println("       Game initialized successfully!    ");
        System.out.println("*                                       *");
        System.out.println("-----------------------------------------");

        // First pick
        System.out.println("                                         ");
        System.out.println("       Testing First Turn Selection    ");
        System.out.println("        |                              ");
        System.out.println("        V                              ");


        if (!player1.getAnimals().isEmpty() && !player2.getAnimals().isEmpty())
        {
            Animal p1FirstAnimal = player1.getAnimals().get(0);
            Animal p2FirstAnimal = player2.getAnimals().get(0);

            game.firstPick(p1FirstAnimal, p2FirstAnimal);
        }
        else
        {
            System.out.println(" Setup temporary pieces in your Player class to test firstPick!");
        }
        System.out.println("                                         ");
        System.out.println("-----------------------------------------");


        // Turn rotation
        System.out.println("\n           Testing Turn Rotation         ");
        System.out.println("\nStarting Turn: Player " + game.getCurrentTurn());

        game.updateTurn();

        System.out.println("Next Turn: Player " + game.getCurrentTurn());
        System.out.println("\n-----------------------------------------");


        // Win check
        System.out.println("\n    Current Status | Win Conditions    ");
        game.checkWin();

        if (game.getWinner() != null)
        {
            System.out.println("Winner: Player " + game.getWinner().getPlayerNum());
        }
        else
        {
            System.out.println("No winner yet.");
        }
        System.out.println("\n-----------------------------------------");


        // Gameplay loop
        System.out.println("\n    Now Ready for Inputs (e.g. 1DU, 2DR)    ");
        System.out.println("\n-----------------------------------------");


        while (true)
        {
            System.out.print("Enter Input (or EXIT): ");



            String input = scanner.nextLine().trim().toUpperCase();
            if (input.equals("EXIT"))
                break;

            if (input.length() != 3)
                continue;

            int pNum = Character.getNumericValue(input.charAt(0));
            char piece = input.charAt(1);
            char dir = input.charAt(2);

            String name = "";

            switch (piece)
            {
                case 'M': name = "Mouse"; break;
                case 'C': name = "Cat"; break;
                case 'W': name = "Wolf"; break;
                case 'D': name = "Dog"; break;
                case 'P': name = "Leopard"; break;
                case 'L': name = "Lion"; break;
                case 'T': name = "Tiger"; break;
                case 'E': name = "Elephant"; break;
            }

            Animal chosen = null;

            ArrayList<Animal> list = (pNum == 1)
                    ? player1.getAnimals()
                    : player2.getAnimals();

            for (Animal a : list)
            {
                if (a.getName().equalsIgnoreCase(name))
                {
                    chosen = a;
                    break;
                }
            }

            if (chosen != null)
            {
                board.moveAnimal(chosen, dir);
                System.out.println("Moved " + name + ".");
            }
            else
            {
                System.out.println("Piece not found.");

            }
        }

        scanner.close();
    }
}
