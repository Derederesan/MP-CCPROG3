package view;

import java.awt.*;
import javax.swing.*;

/**
 * THIS displays the graphical user interface of the Animal Chess game.
 * it is responsible only for displaying the GUI components
 * serves as the View in the MVC architecture.
 */

public class GameView extends JFrame
{

    /* panel that will contain the game board */
    private JPanel boardPanel;

    /* displays the current game status */
    private JLabel statusLabel;

    /* stores references to every button on the game board */
    private JButton[][] boardButtons;

    /**
     * creates the main game window and initializes
     * the basic GUI components.
     */

    public GameView()
    {

        setTitle("Animal Chess");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        statusLabel = new JLabel("Animal Chess", SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(statusLabel, BorderLayout.NORTH);

        boardPanel = new JPanel(new GridLayout(9, 7));
        boardButtons = new JButton[9][7];

        for (int row = 0; row < 9; row++)
        {
            for (int col = 0; col < 7; col++)
            {
                boardButtons[row][col] = new JButton();
                boardPanel.add(boardButtons[row][col]);
            }
        }

        add(boardPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    public JButton getBoardButton(int row, int col)
    {
        return boardButtons[row][col];
    }

    public JButton[][] getBoardButtons()
    {
        return boardButtons;
    }

    public void updateStatus(String text)
    {
        statusLabel.setText(text);
    }

    public void refreshBoard()
    {
        boardPanel.revalidate();
        boardPanel.repaint();
    }
}
