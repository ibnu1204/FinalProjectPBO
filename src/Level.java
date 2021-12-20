import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Level {
    public JFrame window;
    private Container con;
    private JPanel mainPanel = new JPanel(new BorderLayout()), levelNamePanel, easyButtonPanel, mediumButtonPanel, hardButtonPanel, backButtonPanel;
    private JLabel levelNameLabel;
    private Font titleFont = new Font("Times New Roman", Font.PLAIN, 90);
    private Font normalFont = new Font("Times New Roman", Font.PLAIN, 28);
    private JButton easyButton, mediumButton, hardButton, backButton;
    private static int chl = 0;

	private LevelHandler lHandler = new LevelHandler();
	private BackHandler bHandler = new BackHandler();


    public Level() {

        window = new JFrame("Run & Hide");
		window.setSize(800, 800);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setBackground(Color.black);
		window.setLayout(new BorderLayout());
		window.setLocationRelativeTo(null);
		con = window.getContentPane();
		mainPanel.setBackground(Color.black);

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
		backButton.addActionListener(bHandler);
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
		con.add(mainPanel);
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
	
    public static int getLevel() {
        return chl;
    }

	
	public void GameScreen() {
		mainPanel.setVisible(false);
		levelNamePanel.setVisible(false);
		easyButtonPanel.setVisible(false);
		mediumButtonPanel.setVisible(false);
		hardButtonPanel.setVisible(false);
		backButtonPanel.setVisible(false);
		Game game = new Game();
		window.add(game, BorderLayout.CENTER);
	}
	
	public class BackHandler implements ActionListener {

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
