package hide_run;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Main {
    public static JPanel mainPanel = new JPanel(new BorderLayout());
    public static JFrame frame = new JFrame("Lari dan Sembunyi");
    public static Game game = new Game();

    public static CardLayout card = new CardLayout();
    public static JPanel cardPanel = new JPanel(card);

    public static void main(String[] args) {
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        frame.add(game, BorderLayout.CENTER);
    }
}
