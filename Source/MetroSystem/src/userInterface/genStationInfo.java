package userInterface;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.Image;

import java.io.File;
import java.io.IOException;


public class genStationInfo {
    public static void main(String[] args) {
        JFrame imgWin = new JFrame();

        BufferedImage map = null;
        // JLabel picContainerLabel = null;
        try {
            map = ImageIO.read(new File("./Images/szmap.jpg"));
            // picContainerLabel.setIcon(new ImageIcon(new ImageIcon("./Images/szmap.jpg").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        JLabel picContainerLabel = new JLabel();

        picContainerLabel.setBounds(100,200,2000,1000);
        Image resizedImg = map.getScaledInstance(picContainerLabel.getWidth(), picContainerLabel.getHeight(), Image.SCALE_SMOOTH);

        ImageIcon imageIcon = new ImageIcon(resizedImg);
        /* JLabel picContainerLabel = new JLabel(new ImageIcon(map)); */
        picContainerLabel.setIcon(imageIcon);
        picContainerLabel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x=e.getX();
                int y=e.getY();
                System.out.println(x+","+y);//these co-ords are relative to the component
            }

            @Override
            public void mousePressed(MouseEvent e) {
                // TODO Auto-generated method stub
                
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // TODO Auto-generated method stub
                
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // TODO Auto-generated method stub
                
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // TODO Auto-generated method stub
                
            }
        });

        imgWin.add(picContainerLabel);

        imgWin.setBounds(200, 200, 200, 200);
        imgWin.setVisible(true);
        imgWin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        
    }

}



/* package test;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class TestButtonDraw extends JPanel {

    JButton b1;
    JButton b2;

    public TestButtonDraw()
    {
        // setLayout(null);
        b1 = new JButton();
        b2 = new JButton();

        JLabel l1 = new JLabel("test1");
        //JLabel l1 = new JLabel("test1");


        b1.setBounds(0, 0, 5, 5);
        b2.setBounds(43, 43, 5, 5);
        l1.setBounds(100, 20,15,15);
        l1.setFont(new Font("Serif", Font.PLAIN, 8));
        //l1.setPreferredSize(new Dimension(20,20));


        add(b1);
        add(b2);
        add(l1);

    }
    public static void main(String[] args) {
        JFrame testF = new JFrame();

        JPanel imgWin = new TestButtonDraw();


        imgWin.setLayout(null);

        testF.getContentPane().add(imgWin);

        testF.setBounds(200, 200, 200, 200);
        testF.setVisible(true);
        testF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;

        Point p1 = SwingUtilities.convertPoint(b1, 0, 0, this);
        Point p2 = SwingUtilities.convertPoint(b2, 0, 0, this);

        g2.drawLine(p1.x + 2, p1.y + 2, p2.x + 2, p2.y + 2);
        //Line2D lin = new Line2D.Float(2, 2, 40, 40);
        //g2.draw(lin);
    }

} */