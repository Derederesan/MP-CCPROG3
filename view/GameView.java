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
        setSize(1000, 760);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        getContentPane().setBackground(new Color(244, 238, 220));


        /* STATUS LABEL */

        statusLabel = new JLabel("Animal Chess", SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 28));
        statusLabel.setForeground(new Color(75, 48, 28));
        statusLabel.setOpaque(true);
        statusLabel.setBackground(new Color(244, 238, 220));
        statusLabel.setBorder(
                BorderFactory.createEmptyBorder(12, 10, 12, 10)
        );

        add(statusLabel, BorderLayout.NORTH);


        /* GAME BOARD */

        boardPanel = new JPanel(new GridLayout(7, 9, 2, 2));
        boardPanel.setBackground(new Color(105, 75, 40));

        boardButtons = new JButton[7][9];

        for (int row = 0; row < 7; row++)
        {
            for (int col = 0; col < 9; col++)
            {
                boardButtons[row][col] = new JButton();

                boardButtons[row][col].setFont(
                        new Font("Dialog", Font.BOLD, 15)
                );

                boardButtons[row][col].setFocusPainted(false);
                boardButtons[row][col].setHorizontalAlignment(
                        SwingConstants.CENTER
                );
                boardButtons[row][col].setVerticalAlignment(
                        SwingConstants.CENTER
                );

                boardButtons[row][col].setBorder(
                        BorderFactory.createLineBorder(
                                new Color(145, 125, 85)
                        )
                );

                boardButtons[row][col].setOpaque(true);

                if ((row + col) % 2 == 0)
                {
                    boardButtons[row][col].setBackground(
                            new Color(238, 229, 194)
                    );
                }
                else
                {
                    boardButtons[row][col].setBackground(
                            new Color(195, 200, 148)
                    );
                }

                boardPanel.add(boardButtons[row][col]);
            }
        }


        /* ROW LABELS */

        JPanel rowLabelPanel =
                new JPanel(new GridLayout(7, 1, 2, 2));

        rowLabelPanel.setBackground(new Color(78, 48, 21));

        for (int row = 1; row <= 7; row++)
        {
            JLabel rowLabel = new JLabel(
                    String.valueOf(row),
                    SwingConstants.CENTER
            );

            rowLabel.setFont(
                    new Font("Arial", Font.BOLD, 18)
            );

            rowLabel.setForeground(
                    new Color(250, 244, 225)
            );

            rowLabel.setOpaque(true);

            rowLabel.setBackground(
                    new Color(78, 48, 21)
            );

            rowLabel.setPreferredSize(
                    new Dimension(42, 0)
            );

            rowLabelPanel.add(rowLabel);
        }


        /* COLUMN LABELS */

        JPanel columnLabelPanel =
                new JPanel(new GridLayout(1, 9, 2, 2));

        columnLabelPanel.setBackground(
                new Color(78, 48, 21)
        );

        for (int col = 0; col < 9; col++)
        {
            JLabel columnLabel = new JLabel(
                    String.valueOf((char) ('A' + col)),
                    SwingConstants.CENTER
            );

            columnLabel.setFont(
                    new Font("Arial", Font.BOLD, 18)
            );

            columnLabel.setForeground(
                    new Color(250, 244, 225)
            );

            columnLabel.setOpaque(true);

            columnLabel.setBackground(
                    new Color(78, 48, 21)
            );

            columnLabel.setPreferredSize(
                    new Dimension(0, 35)
            );

            columnLabelPanel.add(columnLabel);
        }


        /* CORNER BETWEEN ROW AND COLUMN LABELS */

        JPanel cornerPanel = new JPanel();

        cornerPanel.setBackground(
                new Color(78, 48, 21)
        );

        cornerPanel.setPreferredSize(
                new Dimension(42, 35)
        );


        /* BOARD + COORDINATES */

        JPanel boardWithCoordinates =
                new JPanel(new BorderLayout(2, 2));

        boardWithCoordinates.setBackground(
                new Color(78, 48, 21)
        );

        boardWithCoordinates.add(
                rowLabelPanel,
                BorderLayout.WEST
        );

        boardWithCoordinates.add(
                boardPanel,
                BorderLayout.CENTER
        );


        JPanel bottomCoordinates =
                new JPanel(new BorderLayout(2, 0));

        bottomCoordinates.setBackground(
                new Color(78, 48, 21)
        );

        bottomCoordinates.add(
                cornerPanel,
                BorderLayout.WEST
        );

        bottomCoordinates.add(
                columnLabelPanel,
                BorderLayout.CENTER
        );

        boardWithCoordinates.add(
                bottomCoordinates,
                BorderLayout.SOUTH
        );


        /* BOARD CONTAINER */

        JPanel boardContainer =
                new JPanel(new BorderLayout());

        boardContainer.setBackground(
                new Color(244, 238, 220)
        );

        boardContainer.setBorder(
                BorderFactory.createEmptyBorder(
                        10, 18, 10, 18
                )
        );

        boardContainer.add(
                boardWithCoordinates,
                BorderLayout.CENTER
        );

        add(
                boardContainer,
                BorderLayout.CENTER
        );


        /* BOTTOM PLAYER AREA */

        bottomPanel = new JPanel(
                new GridLayout(1, 3)
        );

        bottomPanel.setBackground(
                new Color(78, 48, 21)
        );

        bottomPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        12, 15, 12, 15
                )
        );


        /* PLAYER 1 */

        player1Label = new JLabel(
                "PLAYER 1",
                SwingConstants.CENTER
        );

        player1Label.setFont(
                new Font("Arial", Font.BOLD, 20)
        );

        player1Label.setForeground(
                new Color(70, 135, 210)
        );

        player1Label.setOpaque(true);

        player1Label.setBackground(
                new Color(78, 48, 21)
        );


        /* INSTRUCTIONS */

        instructionLabel = new JLabel(
                "Select an animal, then enter L / R / U / D",
                SwingConstants.CENTER
        );

        instructionLabel.setFont(
                new Font("Arial", Font.BOLD, 15)
        );

        instructionLabel.setForeground(
                new Color(250, 244, 225)
        );

        instructionLabel.setOpaque(true);

        instructionLabel.setBackground(
                new Color(78, 48, 21)
        );


        /* PLAYER 2 */

        player2Label = new JLabel(
                "PLAYER 2",
                SwingConstants.CENTER
        );

        player2Label.setFont(
                new Font("Arial", Font.BOLD, 20)
        );

        player2Label.setForeground(
                new Color(220, 95, 70)
        );

        player2Label.setOpaque(true);

        player2Label.setBackground(
                new Color(78, 48, 21)
        );


        /*
         * VERTICAL DIVIDER LINES
         */

        player1Label.setBorder(
                BorderFactory.createMatteBorder(
                        0, 0, 0, 1,
                        new Color(160, 125, 75)
                )
        );

        instructionLabel.setBorder(
                BorderFactory.createMatteBorder(
                        0, 0, 0, 1,
                        new Color(160, 125, 75)
                )
        );


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
    public void updateStatus(String message)
    {
        statusLabel.setText(message);
    }


    /* highlights the player whose turn it is */
    public void highlightTurn(int player)
    {
        if (player == 1)
        {
            player1Label.setText("PLAYER 1 - Your Turn");

            player1Label.setForeground(
                    new Color(90, 165, 235)
            );

            player1Label.setFont(
                    new Font("Arial", Font.BOLD, 22)
            );


            player2Label.setText("PLAYER 2 - Waiting...");

            player2Label.setForeground(
                    new Color(205, 115, 95)
            );

            player2Label.setFont(
                    new Font("Arial", Font.BOLD, 18)
            );
        }

        else if (player == 2)
        {
            player2Label.setText("PLAYER 2 - Your Turn");

            player2Label.setForeground(
                    new Color(235, 105, 80)
            );

            player2Label.setFont(
                    new Font("Arial", Font.BOLD, 22)
            );


            player1Label.setText("PLAYER 1 - Waiting...");

            player1Label.setForeground(
                    new Color(100, 145, 195)
            );

            player1Label.setFont(
                    new Font("Arial", Font.BOLD, 18)
            );
        }

        player1Label.repaint();
        player2Label.repaint();
    }


    /**
     * refreshes the game board and updates button labels
     * based on the game model state.
     *
     * @param model the current game model
     */
    public void refreshBoard(Game model)
    {
        if (model.getCurrentTurn() == -1)
        {
            updateStatus("Player 1, pick an animal.");
        }

        for (int r = 0; r < 7; r++)
        {
            for (int c = 0; c < 9; c++)
            {
                /* removes selection highlight after refresh */
                boardButtons[r][c].setBorder(
                        BorderFactory.createLineBorder(
                                new Color(145, 125, 85)
                        )
                );


                /* resets board square color */
                if ((r + c) % 2 == 0)
                {
                    boardButtons[r][c].setBackground(
                            new Color(238, 229, 194)
                    );
                }
                else
                {
                    boardButtons[r][c].setBackground(
                            new Color(195, 200, 148)
                    );
                }


                Space space =
                        model.getBoard().getSpace(r, c);

                if (space != null)
                {
                    Animal animal =
                            space.getAnimal();

                    if (animal == null)
                    {
                        boardButtons[r][c].setText("");

                        boardButtons[r][c].setForeground(
                                new Color(45, 38, 28)
                        );

                        if (space.isAnimalDen())
                        {
                            boardButtons[r][c].setText(
                                    "🏘️ Den"
                            );
                        }

                        else if (space.isTrap())
                        {
                            boardButtons[r][c].setText(
                                    "🕸️ Trap"
                            );
                        }

                        else if (space.isRiver())
                        {
                            boardButtons[r][c].setText(
                                    "🌊 River"
                            );
                        }
                    }

                    else
                    {
                        String piece =
                                animal.getName();

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
                            boardButtons[r][c].setForeground(
                                    new Color(35, 90, 175)
                            );
                        }
                        else
                        {
                            boardButtons[r][c].setForeground(
                                    new Color(195, 65, 55)
                            );
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
        JOptionPane.showMessageDialog(
                this,
                message
        );
    }
}
