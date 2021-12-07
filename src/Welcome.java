import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Welcome {

	JFrame window;
	Container con;
	JPanel mainPanel = new JPanel(new BorderLayout()), titleNamePanel, startButtonPanel, levelButtonPanel,
			highScoreButtonPanel, levelNamePanel, highScoreNamePanel, easyButtonPanel, mediumButtonPanel,
			hardButtonPanel, backButtonPanel;
	JLabel titleNameLabel, levelNameLabel, highScoreNameLabel;
	Font titleFont = new Font("Times New Roman", Font.PLAIN, 90);
	Font normalFont = new Font("Times New Roman", Font.PLAIN, 28);
	JButton startButton, levelButton, highScoreButton, easyButton, mediumButton, hardButton, backButton;
	JTextArea mainTextArea;

	StartScreenHandler tsHandler = new StartScreenHandler();
	AnotherScreenHandler aHandler = new AnotherScreenHandler();

	// ImageIcon logo = new ImageIcon(".//res//jackfrost.jpg");

	public static void main(String[] args) {

		new Welcome();
	}

	public Welcome() {

		window = new JFrame("Run & Hide");
		window.setSize(800, 800);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setBackground(Color.black);
		window.setLayout(null);
		window.setLocationRelativeTo(null);
		// window.setIconImage(logo.getImage());
		con = window.getContentPane();

		// Title
		titleNamePanel = new JPanel();
		titleNamePanel.setBounds(100, 100, 600, 100);
		titleNamePanel.setBackground(Color.black);
		titleNameLabel = new JLabel("RUN & HIDE");
		titleNameLabel.setForeground(Color.white);
		titleNameLabel.setFont(titleFont);

		// Start
		startButtonPanel = new JPanel();
		startButtonPanel.setBounds(300, 300, 200, 50);
		startButtonPanel.setBackground(Color.black);

		startButton = new JButton("START");
		startButton.setBackground(Color.black);
		startButton.setForeground(Color.white);
		startButton.setFont(normalFont);
		startButton.addActionListener(tsHandler);
		startButton.setFocusPainted(false);

		// Level
		levelButtonPanel = new JPanel();
		levelButtonPanel.setBounds(300, 350, 200, 50);
		levelButtonPanel.setBackground(Color.black);

		levelButton = new JButton("LEVEL");
		levelButton.setBackground(Color.black);
		levelButton.setForeground(Color.white);
		levelButton.setFont(normalFont);
		levelButton.addActionListener(aHandler);
		levelButton.setFocusPainted(false);

		// Highscore
		highScoreButtonPanel = new JPanel();
		highScoreButtonPanel.setBounds(300, 400, 200, 50);
		highScoreButtonPanel.setBackground(Color.black);

		highScoreButton = new JButton("HIGHSCORE");
		highScoreButton.setBackground(Color.black);
		highScoreButton.setForeground(Color.white);
		highScoreButton.setFont(normalFont);
		highScoreButton.addActionListener(aHandler);
		highScoreButton.setFocusPainted(false);

		titleNamePanel.add(titleNameLabel);
		startButtonPanel.add(startButton);
		levelButtonPanel.add(levelButton);
		highScoreButtonPanel.add(highScoreButton);

		con.add(titleNamePanel);
		con.add(startButtonPanel);
		con.add(levelButtonPanel);
		con.add(highScoreButtonPanel);

		window.setVisible(true);
	}

	public class StartScreenHandler implements ActionListener {

		public void actionPerformed(ActionEvent event) {

			GameScreen();

		}
	}

	public void GameScreen() {
		titleNamePanel.setVisible(false);
		startButtonPanel.setVisible(false);
		levelButtonPanel.setVisible(false);
		highScoreButtonPanel.setVisible(false);

		Game game = new Game();

		window.add(game, BorderLayout.CENTER);
	}

	public class AnotherScreenHandler implements ActionListener {

		public void actionPerformed(ActionEvent event) {

			if(event.getActionCommand().equals("LEVEL")){
				window.setVisible(false);
				Level level = new Level();
				level.window.setVisible(true);
				window.dispose();
			}
			else if(event.getActionCommand().equals("HIGHSCORE")){
				window.setVisible(false);
				HighScore highScore = new HighScore();
				highScore.window.setVisible(true);
				window.dispose();
			}

		}
	}
}