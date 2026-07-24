package view;

import java.awt.*;
import javax.swing.*;
import model.Animal;
import model.Game;
import model.Space;

/**
 * THIS displays the graphical user interface of the Animal Chess game.
 * serves as the View in the MVC architecture.
 * it is responsible only for displaying the GUI components
 */

public class GameView extends JFrame
{

    /* panel that will contain the game board */
    private JPanel boardPanel;

    /* displays the current game status */
    private JLabel statusLabel;
    private JPanel bottomPanel;
    private JLabel player1Label;
    private JLabel player2Label;
    private JLabel instructionLabel;

    /* stores references to every button on the game board */
    private JButton[][] boardButtons;

    /**
     * the basic GUI components
     * creates the main game window and initializes
     */

    public GameView()
    {

        setTitle("Animal Chess");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        statusLabel = new JLabel("Animal Chess", SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 28));
        statusLabel.setForeground(new Color(70, 40, 20));
        add(statusLabel, BorderLayout.NORTH);

        boardPanel = new JPanel(new GridLayout(9, 7, 4, 4));
        boardPanel.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));
        boardButtons = new JButton[9][7];

        for (int row = 0; row < 9; row++)
        {
            for (int col = 0; col < 7; col++)
            {
                boardButtons[row][col] = new JButton();
                boardButtons[row][col].setFont(new Font("Dialog", Font.BOLD, 15));
                boardButtons[row][col].setFocusPainted(false);
                boardButtons[row][col].setHorizontalAlignment(SwingConstants.CENTER);
                boardButtons[row][col].setVerticalAlignment(SwingConstants.CENTER);
                boardButtons[row][col].setBorder(BorderFactory.createLineBorder(new Color(210, 210, 210)));
                boardButtons[row][col].setBackground(Color.WHITE);
                boardButtons[row][col].setOpaque(true);

                if ((row + col) % 2 == 0)
                {
                    boardButtons[row][col].setBackground(new Color(245, 240, 220)); // beige
                }
                else
                {
                    boardButtons[row][col].setBackground(new Color(220, 235, 210)); // light green
                }

                boardPanel.add(boardButtons[row][col]);
            }
        }

        add(boardPanel, BorderLayout.CENTER);

        bottomPanel = new JPanel(new GridLayout(1,3));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        player1Label = new JLabel("PLAYER 1", SwingConstants.CENTER);
        player1Label.setFont(new Font("Arial", Font.BOLD, 18));
        player1Label.setForeground(new Color(40,90,220));

        instructionLabel = new JLabel("Select an animal, then enter L / R / U / D", SwingConstants.CENTER);
        instructionLabel.setFont(new Font("Arial", Font.BOLD, 16));

        player2Label = new JLabel("PLAYER 2", SwingConstants.CENTER);
        player2Label.setFont(new Font("Arial", Font.BOLD, 18));
        player2Label.setForeground(new Color(220,70,70));

        bottomPanel.add(player1Label);
        bottomPanel.add(instructionLabel);
        bottomPanel.add(player2Label);

        add(bottomPanel, BorderLayout.SOUTH);
        setVisible(true);
    }

    /**
     * returns the button at the specified board position.
     *
     * @param row row index
     * @param col column index
     * @return the button at the given position
     */
    public JButton getBoardButton(int row, int col)
    {
        return boardButtons[row][col];
    }

    /* returns all board buttons */
    public JButton[][] getBoardButtons()
    {
        return boardButtons;
    }

    /* updates the status label */
    public void updateStatus(String text)
    {
        statusLabel.setText(text);

        if (text.contains("Player 1"))
        {
            player1Label.setText("PLAYER 1 - Your Turn");
            player2Label.setText("PLAYER 2 - Waiting...");
        }
        else if (text.contains("Player 2"))
        {
            player1Label.setText("PLAYER 1 - Waiting...");
            player2Label.setText("PLAYER 2 - Your Turn");
        }
    }

    /**
     * refreshes the game board and updates button labels based on the game model state.
     *
     * @param model the current game model
     */
    public void refreshBoard(Game model)
    {
        for (int r = 0; r < 9; r++)
        {
            for (int c = 0; c < 7; c++)
            {
                Space space = model.getBoard().getSpace(r, c);
                if (space != null)
                {
                    Animal animal = space.getAnimal();
                    if (animal == null)
                    {
                        boardButtons[r][c].setText("");
                    }
                    else
                    {
                        String piece = animal.getName();
                        switch (animal.getName())
                        {
                            case "Tiger":
                                piece = "🐯 Tiger";
                                break;
                            case "Lion":
                                piece = "🦁 Lion";
                                break;
                            case "Elephant":
                                piece = "🐘 Elephant";
                                break;
                            case "Mouse":
                                piece = "🐭 Mouse";
                                break;
                            case "Cat":
                                piece = "🐱 Cat";
                                break;
                            case "Dog":
                                piece = "🐶 Dog";
                                break;
                            case "Wolf":
                                piece = "🐺 Wolf";
                                break;
                            case "Leopard":
                                piece = "🐆 Leopard";
                                break;
                        }

                        boardButtons[r][c].setText(piece);

                        if (animal.getOwnerId() == 1)
                        {
                            boardButtons[r][c].setForeground(new Color(30, 90, 210)); // blue
                        }
                        else
                        {
                            boardButtons[r][c].setForeground(new Color(210, 60, 60)); // red
                        }
                    }
                }
            }
        }

        boardPanel.revalidate();
        boardPanel.repaint();
    }

    /* returns the status label */
    public JLabel getStatusLabel()
    {
        return statusLabel;
    }

    /* returns the game board panel */
    public JPanel getBoardPanel()
    {
        return boardPanel;
    }

    /**
     * displays a message dialog to the user
     *
     * @param message the message to display
     */
    public void showMessage(String message)
    {
        JOptionPane.showMessageDialog(this, message);
    }
}
