package view;

import javax.swing.JFrame;

public class GameView extends JFrame
{

    public GameView()
    {
        setTitle("Animal Chess");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}