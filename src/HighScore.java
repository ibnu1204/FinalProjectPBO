import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HighScore {
	public JFrame window;
	private Container con;
	private JPanel highScoreNamePanel, backButtonPanel;
	private JLabel highScoreNameLabel;
	private Font titleFont = new Font("Times New Roman", Font.PLAIN, 90);
	private Font normalFont = new Font("Times New Roman", Font.PLAIN, 28);
	private JButton backButton;

	/*
	 * StartScreenHandler tsHandler = new StartScreenHandler();
	 * LevelScreenHandler lsHandler = new LevelScreenHandler();
	 * HighScoreScreenHandler hssHandler = new HighScoreScreenHandler();
	 * BackHandler bHandler = new BackHandler();
	 */
	private HighScoreHandler hsHandler = new HighScoreHandler();

	public HighScore() {

		window = new JFrame("Run & Hide");
		window.setSize(800, 800);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setBackground(Color.black);
		window.setLayout(null);
		window.setLocationRelativeTo(null);
		con = window.getContentPane();

		// HighScore
		highScoreNamePanel = new JPanel();
		highScoreNamePanel.setBounds(100, 100, 600, 100);
		highScoreNamePanel.setBackground(Color.black);
		highScoreNameLabel = new JLabel("HIGHSCORE");
		highScoreNameLabel.setForeground(Color.white);
		highScoreNameLabel.setFont(titleFont);

		// Back
		backButtonPanel = new JPanel();
		backButtonPanel.setBounds(50, 600, 150, 50);
		backButtonPanel.setBackground(Color.black);
		backButton = new JButton("BACK");
		backButton.setBackground(Color.black);
		backButton.setForeground(Color.white);
		backButton.setFont(normalFont);
		backButton.addActionListener(hsHandler);
		backButton.setFocusPainted(false);

		highScoreNamePanel.add(highScoreNameLabel);
		backButtonPanel.add(backButton);

		con.add(highScoreNamePanel);
		con.add(backButtonPanel);
	}

	public class HighScoreHandler implements ActionListener {

		public void actionPerformed(ActionEvent event) {

			if (event.getActionCommand().equals("BACK")) {
				window.setVisible(false);
				Welcome welcome = new Welcome();
				welcome.window.setVisible(true);
				window.dispose();
			}
		}
	}
}