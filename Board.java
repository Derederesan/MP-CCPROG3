public class Board 
{
    private Space[][] board; 
    public static final int ROWS = 9; 
    public static final int COLS = 7; 

    public Board()
    {
        this.board = new Space[ROWS][COLS];
        initializeBoard();
    }

    public void initializeBoard()
    {
        for(int i = 0; i < ROWS; i++)
        {
            for(int j = 0; j < COLS; j++)
            {
                // Animal Dens
                if((i == 0 && j == 3) || (i == 8 && j == 3))
                {
                    this.board[i][j] = new Space(Space.ANIMAL_DEN);
                }
                // Traps around dens
                else if(((i == 0 || i == 8) && (j == 2 || j == 4)) || ((i == 1 || i == 7) && j == 3))
                {
                    this.board[i][j] = new Space(Space.TRAP);
                }
                // River blocks
                else if((i >= 3 && i <= 5) && (j == 1 || j == 2 || j == 4 || j == 5))
                {
                    this.board[i][j] = new Space(Space.RIVER);
                }
                // Regular Land
                else
                {
                    this.board[i][j] = new Space(Space.LAND);
                }
            }
        }
    }

    public Space getSpace(int row, int col)
    {
        if(row >= 0 && row < ROWS && col >= 0 && col < COLS)
        {
            return this.board[row][col];
        }
        return null;
    }

    public boolean isValidMove(Animal animal, Space target)
    {
        if(target == null)
        {
            return false;
        }
        // Basic requirement rule: program logic check structure matching ruleset
        return true;
    }
}