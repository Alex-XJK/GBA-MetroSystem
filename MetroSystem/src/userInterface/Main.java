package MetroSystem.src.userInterface;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import javax.imageio.*;
import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    
    public static void main(String[] args) throws IOException { 

        // Theme parity
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            // System.out.println(UIManager.getSystemLookAndFeelClassName());
            // System.out.println(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // JLabel spacer;

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
        LocalDateTime now = LocalDateTime.now();  
        // System.out.println(dtf.format(now));

        JFrame frame=new JFrame("Traffic Map");

        JPanel sideBar = new JPanel();
        sideBar.setBounds(0, 0, 180, 1000);
        sideBar.setLayout(new GridBagLayout());
        JButton btnMap = new JButton("Map");
        JButton langcn = new JButton("简体中文");
        JButton langhk = new JButton("繁體中文");
        JButton langen = new JButton("English");
        JButton btnExit = new JButton("Exit");
        JLabel user = new JLabel("<html><h1>Jacky</h1><p>Registered user</p><br></html>");

        // BufferedImage myProfile = ImageIO.read(new File("profilePic.jpg"));
        // JLabel profileLabel = new JLabel(new ImageIcon(myProfile));

        btnExit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // sideBar.add(profileLabel);
        sideBar.setLayout(new BoxLayout(sideBar, BoxLayout.Y_AXIS));
        sideBar.add(user);
        sideBar.add(btnMap);
        sideBar.add(langcn);
        sideBar.add(langhk);
        sideBar.add(langen);
        sideBar.add(btnExit);

        frame.add(sideBar);

        frame.setSize(400, 200);
        JPanel jp = new JPanel();

        JLabel from = new JLabel("From: ");
        JTextField from_text = new JTextField("Kowloon Tong");
        JLabel to = new JLabel("To: ");
        JTextField to_text = new JTextField("Causeway Bay");

        JLabel date = new JLabel("<html>&emsp;" + dtf.format(now) + "</html>");

        jp.add(from);
        jp.add(from_text);
        jp.add(to);
        jp.add(to_text);
        jp.add(date);



        ImageIcon szimg = new ImageIcon(ImageIO.read(new File("./MetroSystem/data/sz.png")));
        ImageIcon hkimg = new ImageIcon(ImageIO.read(new File("./MetroSystem/data/hk.jpg")));
        JLabel picLabel = new JLabel(hkimg);

        JButton btnhk = new JButton("Traffic Map (HK)");
        JButton btnsz = new JButton("Traffic Map (SZ)");

        btnsz.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                picLabel.setIcon(szimg);
            }
        });

        btnhk.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                picLabel.setIcon(hkimg);
            }
        });

        JButton btn3 = new JButton("Clear");  
        
        // jp.add(username);
        jp.add(picLabel);

        jp.add(btnhk);

        btnExit.setBackground(Color.RED);

        jp.add(btnsz);
        jp.add(btn3);

        // Language settings
        langcn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                btnMap.setText("地图");
                btnExit.setText("退出");
                from.setText("起始地");
                to.setText("目的地");
                btnhk.setText("香港地铁线路图");
                btnsz.setText("深圳地铁线路图");
                btn3.setText("清除");
                user.setText("<html><h1>Jacky</h1><p>注册用户</p><br></html>");
            }
        });

        langhk.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                btnMap.setText("地圖");
                btnExit.setText("退出");
                from.setText("起始地");
                to.setText("目的地");
                btnhk.setText("香港地鐵線路圖");
                btnsz.setText("深圳地鐵線路圖");
                btn3.setText("清除");
                user.setText("<html><h1>Jacky</h1><p>註冊用戶</p><br></html>");
            }
        });

        langen.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                btnMap.setText("Map");
                btnExit.setText("Exit");
                from.setText("From: ");
                to.setText("To: ");
                btnhk.setText("Traffic Map (HK)");
                btnsz.setText("Traffic Map (SZ)");
                btn3.setText("Clear");
                user.setText("<html><h1>Jacky</h1><p>Registered user</p><br></html>");
            }
        });


        // btn3.setEnabled(false); 
        // Dimension preferredSize=new Dimension(160, 60);
        // btn4.setPreferredSize(preferredSize); 
        // btn4.setVerticalAlignment(SwingConstants.BOTTOM);

        frame.add(jp);
        frame.setBounds(300, 300, 600, 300);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

}