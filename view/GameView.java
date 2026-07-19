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

        // temporary board placeholders
        // these buttons will later be replaced w/ actual board tiles and animal pieces
        for (int i = 0; i < 63; i++)
        {
            boardPanel.add(new JButton());
        }

        add(boardPanel, BorderLayout.CENTER);

        setVisible(true);
    }
}
