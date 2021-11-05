package userInterface;

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
import java.awt.event.*;

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

        JFrame frame = new JFrame("Traffic Map");

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
        JTextField from_text = new JTextField("",10);
        JLabel to = new JLabel("To: ");
        JTextField to_text = new JTextField("",10);

        JLabel date = new JLabel("<html>&emsp;" + dtf.format(now) + "</html>");

        jp.add(from);
        jp.add(from_text);
        jp.add(to);
        jp.add(to_text);
        jp.add(date);

        String base = System.getProperty("user.dir");
        System.out.println("Current workspace: " + base);

        //ImageIcon szimg = new ImageIcon(ImageIO.read(new File(base + "/data/sz.png")));
        //ImageIcon hkimg = new ImageIcon(ImageIO.read(new File(base + "/data/hk.jpg")));
        //JLabel picLabel = new JLabel(hkimg);

        JButton btnhk = new JButton("Traffic Map (HK)");
        JButton btnsz = new JButton("Traffic Map (SZ)");

        JButton btn3 = new JButton("Clear");

        btn3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                from_text.setText("");
                to_text.setText("");
            }
        });

        // jp.add(username);
        // jp.add(picLabel);

        //MTR_eng mtr_en = new MTR_eng();



        /*Map hk_en = new Map("en", "hk");
        ArrayList<JButton> hkButtons = hk_en.getAllButtons();
        for(JButton b: hkButtons) {
            b.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    from_text.setText( ((JButton) (e.getSource())).getName() );
                };
            }
            );
        }*/




        SZMap mtr_sz = new SZMap("en","sz");
        ArrayList<JButton> szButtons = mtr_sz.getAllButtons();
        for(JButton b: szButtons) {
            b.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if(from_text.getText().equals("")){
                        from_text.setText(((JButton) (e.getSource())).getName());
                    }
                    else{
                        to_text.setText(((JButton) (e.getSource())).getName());
                    }
                };
            }
            );
        }
        //SZMap mtr_en = new SZMap("en","hk");
        //Image sz_bg = ImageIO.read(new File(base + "/data/sz.png"));
        //BackgroundPanel mtr_sz_bg = new BackgroundPanel(sz_bg, 2);
        // jp.add(mtr_en.getPanel());

        //JScrollPane scrollPane = new JScrollPane(mtr_en.getPanel());
        //scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        //scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        // scrollPane.setSize(500, 300);
        JPanel contentPane = new JPanel(null);
        contentPane.setPreferredSize(new Dimension(800, 800));
        contentPane.add(mtr_sz);
        contentPane.setLocation(400, 400);
        jp.add(contentPane);

        btnsz.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // picLabel.setIcon(szimg);
                contentPane.removeAll();
                contentPane.add(mtr_sz);
                contentPane.revalidate();
                contentPane.repaint();
            }
        });

        //btnhk.addActionListener(new ActionListener() {

            // @Override
            // public void actionPerformed(ActionEvent e) {
            //     contentPane.removeAll();
            //     contentPane.add(mtr_en);
            //     contentPane.revalidate();
            //     contentPane.repaint();
            // }
        //});

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

        jp.add(mtr_sz);
        frame.add(jp);

        // MTR_eng mtr_en = new MTR_eng();
        // frame.add(mtr_en.getPanel());

        frame.setBounds(300, 300, 600, 300);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

}
