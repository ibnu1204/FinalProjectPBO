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

public class About {
    JFrame window;
    Container con;
    JPanel titleNamePanel, startButtonPanel, levelButtonPanel, highScoreButtonPanel, 
            aboutButtonPanel, levelNamePanel, highScoreNamePanel, aboutNamePanel, easyButtonPanel, 
            mediumButtonPanel, hardButtonPanel, backButtonPanel;
    JLabel titleNameLabel, levelNameLabel,aboutNameLabel, aboutMessageLabel, highScoreNameLabel;
    Font titleFont = new Font("Times New Roman", Font.PLAIN, 80);
    Font normalFont = new Font("Times New Roman", Font.PLAIN, 20);
    JButton startButton, levelButton, aboutButton, highScoreButton, easyButton, mediumButton, hardButton, backButton;
    JTextArea mainTextArea;

    /*StartScreenHandler tsHandler = new StartScreenHandler();
    LevelScreenHandler lsHandler = new LevelScreenHandler();
    HighScoreScreenHandler hssHandler = new HighScoreScreenHandler();
    BackHandler bHandler = new BackHandler();
    */
    AboutHandler aboutHandler = new AboutHandler();

    //ImageIcon logo = new ImageIcon(".//res//jackfrost.jpg");

    public static void main(String[] args) {

        new About();
    }

    public About() {

        window = new JFrame("Run & Hide 2");
		window.setSize(800, 800);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setBackground(Color.black);
		window.setLayout(null);
		window.setLocationRelativeTo(null);
		// window.setIconImage(logo.getImage());
		con = window.getContentPane();

        // About
		aboutNamePanel = new JPanel();
		aboutNamePanel.setBounds(100, 100, 500, 50);
		aboutNamePanel.setBackground(Color.black);
		aboutNameLabel = new JLabel("ABOUT");
		aboutNameLabel.setForeground(Color.white);
		aboutNameLabel.setFont(titleFont);

		aboutMessageLabel = new JLabel("Run & Hide ........");
		aboutMessageLabel.setForeground(Color.white);
        aboutMessageLabel.setFont(normalFont);

		// Back
		backButtonPanel = new JPanel();
		backButtonPanel.setBounds(50, 600, 150, 50);
		backButtonPanel.setBackground(Color.black);
		backButton = new JButton("BACK");
		backButton.setBackground(Color.black);
		backButton.setForeground(Color.white);
		backButton.setFont(normalFont);
		backButton.addActionListener(aboutHandler);
		backButton.setFocusPainted(false);

		aboutNamePanel.add(aboutNameLabel);
		backButtonPanel.add(backButton);

		con.add(aboutNamePanel);
		con.add(backButtonPanel);
    }

    public class AboutHandler implements ActionListener {

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