import java.awt.EventQueue;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;

import javax.swing.JFrame;

public class StartPage {

	private JFrame frame;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartPage window = new StartPage();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public StartPage() {
		initialize();
	}

	
	private void initialize() {
		JFrame frame = new JFrame("Test");
        Image img = null;
        try {
             File f = new File("Images/HK1.jpg");
             img = ImageIO.read(f);
        } catch (Exception e) {
            System.out.println("Cannot read file: " + e);
        }
        
        BackgroundPanel background = new BackgroundPanel(img, 2);
   
        frame.add(background);   
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        
        frame.setVisible(true);
	}

}
