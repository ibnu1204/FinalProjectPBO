import java.awt.*;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Welcome {

	JFrame window;
	Container con;
	JPanel mainPanel = new JPanel(new BorderLayout()), titleNamePanel, startButtonPanel, levelButtonPanel, highScoreButtonPanel, aboutButtonPanel;
	JLabel titleNameLabel;
	Font titleFont = new Font("Times New Roman", Font.PLAIN, 90);
	Font normalFont = new Font("Times New Roman", Font.PLAIN, 28);
	JButton startButton, levelButton, highScoreButton, aboutButton;

	StartScreenHandler tsHandler = new StartScreenHandler();
	AnotherScreenHandler aHandler = new AnotherScreenHandler();


	public static void main(String[] args) {

		new Welcome();
	}

	public Welcome() {

		window = new JFrame("Run & Hide");
		window.setSize(800, 800);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setBackground(Color.blue);
		window.setLayout(new BorderLayout());
		window.setLocationRelativeTo(null);
		con = window.getContentPane();

		// Main Panel
		mainPanel.setBackground(Color.black);

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

		// About
		aboutButtonPanel = new JPanel();
		aboutButtonPanel.setBounds(300, 450, 200, 50);
		aboutButtonPanel.setBackground(Color.black);

		aboutButton = new JButton("ABOUT");
		aboutButton.setBackground(Color.black);
		aboutButton.setForeground(Color.white);
		aboutButton.setFont(normalFont);
		aboutButton.addActionListener(aHandler);
		aboutButton.setFocusPainted(false);

		titleNamePanel.add(titleNameLabel);
		startButtonPanel.add(startButton);
		levelButtonPanel.add(levelButton);
		highScoreButtonPanel.add(highScoreButton);
		aboutButtonPanel.add(aboutButton);

		
		con.add(titleNamePanel);
		con.add(startButtonPanel);
		con.add(levelButtonPanel);
		con.add(highScoreButtonPanel);
		con.add(aboutButtonPanel);
		con.add(mainPanel);

		window.setVisible(true);
	}

	public class StartScreenHandler implements ActionListener {

		public void actionPerformed(ActionEvent event) {
			GameScreen();
		}
	}

	public void GameScreen() {
		mainPanel.setVisible(false);
		titleNamePanel.setVisible(false);
		startButtonPanel.setVisible(false);
		levelButtonPanel.setVisible(false);
		highScoreButtonPanel.setVisible(false);
		aboutButtonPanel.setVisible(false);

		Game game = new Game();

		window.add(game, BorderLayout.CENTER);
	}

	public class AnotherScreenHandler implements ActionListener {

		public void actionPerformed(ActionEvent event) {

			if (event.getActionCommand().equals("LEVEL")) {
				window.setVisible(false);
				Level level = new Level();
				level.window.setVisible(true);
				window.dispose();
			} else if (event.getActionCommand().equals("HIGHSCORE")) {
				window.setVisible(false);
				HighScore highScore = new HighScore();
				highScore.window.setVisible(true);
				window.dispose();
			} else if (event.getActionCommand().equals("ABOUT")) {
				window.setVisible(false);
				About about = new About();
				about.window.setVisible(true);
				window.dispose();
			}
		}
	}
}
