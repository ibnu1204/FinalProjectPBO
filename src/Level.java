import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Level {
    JFrame window;
    Container con;
    JPanel titleNamePanel, startButtonPanel, levelButtonPanel, highScoreButtonPanel, 
            aboutButtonPanel, levelNamePanel, highScoreNamePanel, aboutNamePanel, easyButtonPanel, 
            mediumButtonPanel, hardButtonPanel, backButtonPanel;
    JLabel titleNameLabel, levelNameLabel,aboutNameLabel, aboutMessageLabel, highScoreNameLabel;
    Font titleFont = new Font("Times New Roman", Font.PLAIN, 90);
    Font normalFont = new Font("Times New Roman", Font.PLAIN, 28);
    JButton startButton, levelButton, aboutButton, highScoreButton, easyButton, mediumButton, hardButton, backButton;
    JTextArea mainTextArea;
	public static int getLevel;
    private static int chl;

	LevelHandler lHandler = new LevelHandler();

    //ImageIcon logo = new ImageIcon(".//res//jackfrost.jpg");

    public static void main(String[] args) {

        new Level();
    }

    public Level() {

        window = new JFrame("Run & Hide 2");
		window.setSize(800, 800);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setBackground(Color.black);
		window.setLayout(null);
		window.setLocationRelativeTo(null);
		// window.setIconImage(logo.getImage());
		con = window.getContentPane();

        // Level
		levelNamePanel = new JPanel();
		levelNamePanel.setBounds(200, 100, 400, 100);
		levelNamePanel.setBackground(Color.black);
		levelNameLabel = new JLabel("LEVEL");
		levelNameLabel.setForeground(Color.white);
		levelNameLabel.setFont(titleFont);

		// Easy
		easyButtonPanel = new JPanel();
		easyButtonPanel.setBounds(150, 300, 150, 50);
		easyButtonPanel.setBackground(Color.black);
		easyButton = new JButton("EASY");
		easyButton.setBackground(Color.black);
		easyButton.setForeground(Color.white);
		easyButton.setFont(normalFont);
		easyButton.addActionListener(lHandler);
		easyButton.setFocusPainted(false);

		// Medium
		mediumButtonPanel = new JPanel();
		mediumButtonPanel.setBounds(300, 300, 150, 50);
		mediumButtonPanel.setBackground(Color.black);
		mediumButton = new JButton("MEDIUM");
		mediumButton.setBackground(Color.black);
		mediumButton.setForeground(Color.white);
		mediumButton.setFont(normalFont);
		mediumButton.addActionListener(lHandler);
		mediumButton.setFocusPainted(false);

		// Hard
		hardButtonPanel = new JPanel();
		hardButtonPanel.setBounds(450, 300, 150, 50);
		hardButtonPanel.setBackground(Color.black);
		hardButton = new JButton("HARD");
		hardButton.setBackground(Color.black);
		hardButton.setForeground(Color.white);
		hardButton.setFont(normalFont);
		hardButton.addActionListener(lHandler);
		hardButton.setFocusPainted(false);

		// Back
		backButtonPanel = new JPanel();
		backButtonPanel.setBounds(50, 600, 150, 50);
		backButtonPanel.setBackground(Color.black);
		backButton = new JButton("BACK");
		backButton.setBackground(Color.black);
		backButton.setForeground(Color.white);
		backButton.setFont(normalFont);
		backButton.addActionListener(lHandler);
		backButton.setFocusPainted(false);

		levelNamePanel.add(levelNameLabel);
		easyButtonPanel.add(easyButton);
		mediumButtonPanel.add(mediumButton);
		hardButtonPanel.add(hardButton);
		backButtonPanel.add(backButton);

		con.add(levelNamePanel);
		con.add(easyButtonPanel);
		con.add(mediumButtonPanel);
		con.add(hardButtonPanel);
		con.add(backButtonPanel);
    }
    

	public class LevelHandler implements ActionListener {

		public void actionPerformed(ActionEvent event) {

			if(event.getActionCommand().equals("EASY")){
				GameScreen();
				chl = 1;
			}	

			else if(event.getActionCommand().equals("MEDIUM")){
				GameScreen();
				chl = 2;
			}
			else if(event.getActionCommand().equals("HARD")){
				GameScreen();
				chl = 3;
			}
		}
	}
	
    public int getLevel(int chl) {
        return chl;
    }

	
	public void GameScreen() {
		levelNamePanel.setVisible(false);
		easyButtonPanel.setVisible(false);
		mediumButtonPanel.setVisible(false);
		hardButtonPanel.setVisible(false);
		backButtonPanel.setVisible(false);
		Game game = new Game();
		window.add(game, BorderLayout.CENTER);
	}
	
	public class AnotherScreenHandler implements ActionListener {

		public void actionPerformed(ActionEvent event) {

			if(event.getActionCommand().equals("BACK")){
				window.setVisible(false);
				Welcome welcome = new Welcome();
				welcome.window.setVisible(true);
				window.dispose();
			}
		}
	}
}
