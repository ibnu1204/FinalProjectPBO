import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class About {
    public JFrame window;
    private Container con;
    private JPanel aboutNamePanel, aboutTextPanel, backButtonPanel;
    private JLabel aboutNameLabel;
    private Font titleFont = new Font("Times New Roman", Font.PLAIN, 80);
    private Font normalFont = new Font("Times New Roman", Font.PLAIN, 20);
    private JButton backButton;
    private JTextArea aboutTextArea;

    /*StartScreenHandler tsHandler = new StartScreenHandler();
    LevelScreenHandler lsHandler = new LevelScreenHandler();
    HighScoreScreenHandler hssHandler = new HighScoreScreenHandler();
    BackHandler bHandler = new BackHandler();
    */
	private AboutHandler aboutHandler = new AboutHandler();


    public About() {

        window = new JFrame("Run & Hide");
		window.setSize(800, 800);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setBackground(Color.black);
		window.setLayout(null);
		window.setLocationRelativeTo(null);
		con = window.getContentPane();

        // About
		aboutNamePanel = new JPanel();
		aboutNamePanel.setBounds(200, 100, 400, 100);
		aboutNamePanel.setBackground(Color.black);
		aboutNameLabel = new JLabel("ABOUT");
		aboutNameLabel.setForeground(Color.white);
		aboutNameLabel.setFont(titleFont);

        // About Text
        aboutTextPanel = new JPanel();
        aboutTextPanel.setBounds(100, 250, 600, 300);
        aboutTextPanel.setBackground(Color.black);
			
		aboutTextArea = new JTextArea("Run & Hide adalah game yang dimainkan dengan tujuan untuk mengumpulkan semua makanan yang ada di dalam game tanpa tertangkap oleh penjaga. Game ini terdiri dari 3 level, yaitu Easy, Medium dan Hard. Setiap levelnya dibedakan oleh banyaknya penjaga, makanan, dan rintangan yang ada.");
		aboutTextArea.setBounds(100, 100, 600, 300);
		aboutTextArea.setBackground(Color.black);
		aboutTextArea.setForeground(Color.white);
		aboutTextArea.setFont(normalFont);
		aboutTextArea.setLineWrap(true);
		aboutTextArea.setWrapStyleWord(true); 
		aboutTextArea.setEditable(false);

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
        aboutTextPanel.add(aboutTextArea);
		backButtonPanel.add(backButton);

		con.add(aboutNamePanel);
        con.add(aboutTextPanel);
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