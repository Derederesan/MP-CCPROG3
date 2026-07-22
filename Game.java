/**
 * A game allows the initialization of players and board. It keeps track of the current turn,
 * serves as the checker on which players should go first, and checks if a player has won. 
 */

import java.util.ArrayList;
import java.util.Collections;

public class Game {
    private int currentTurn = -1;
    private Player player1;
    private Player player2;
    private Board board;
    private boolean isGameOver;
    private Player winner;

    /**
     * Constructs a new Game that takes the input provided and assigns it
     * to the specified attributes of the class.
     */
    public Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.board = new Board();
        this.isGameOver = false;
        this.winner = null;
    }

    /**
     * Compares the chosen animal piece of each player
     * and uses it to determine which player goes first.
     *
     * @param p1Choice the chosen animal piece of player 1
     * @param p2Choice the chosen animal piece of player 2
     * @return the player that goes first
     */

    public ArrayList<Integer> shuffleRanks()
    {
    ArrayList<Integer> ranks = new ArrayList<>();

    for (int i = 1; i <= 8; i++)
    {
        ranks.add(i);
    }

    Collections.shuffle(ranks);

    return ranks;
    }
    public int firstPick(Animal p1Choice, Animal p2Choice) {
        if (p1Choice.getRank() > p2Choice.getRank()) {
            System.out.println("Player 1 moves first.");
            this.currentTurn = 1;
            return 1;
        } else if (p1Choice.getRank() < p2Choice.getRank()) {
            System.out.println("Player 2 moves first.");
            this.currentTurn = 2;
            return 2;
        } else {
            System.out.println("Tie! Players must pick again.");
            return -1;
        }
    }

    /**
     * Keeps track of which player is currently playing
     */
    public void updateTurn() {
        if (this.currentTurn == 1) {
            this.currentTurn = 2;
        } else if (this.currentTurn == 2) {
            this.currentTurn = 1;
        }
    }

    /*
     *checks if a player has won
     */
    public void checkWin()
    {
        Player currentPlayer;

        if (this.currentTurn == 1)
        {
            currentPlayer = this.player1;
        }
        else
        {
            currentPlayer = this.player2;
        }

        for (Animal animal : currentPlayer.getAnimals())
        {
            Space currentSpace = animal.getCurrentSpace();

            if (currentSpace.isEnemyDen(this.currentTurn))
            {
                this.winner = currentPlayer;
                this.isGameOver = true;
                System.out.println("Player " + this.currentTurn + " has won!");
                return;
            }
        }
    }

    /**
     * Returns the current player
     *
     * @return the current turn of the player
     */
    public int getCurrentTurn() {
        return this.currentTurn;
    }

    /**
     * Returns the winner
     *
     * @return the winner
     */
    public Player getWinner() {
        return this.winner;
    }

    public Board getBoard(){
        return this.board;
    }

}
