package userInterface;

import metroSystem.*;
import org.apache.commons.collections4.functors.ExceptionPredicate;

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

        Stack<String> lang = new Stack<>();
        lang.push("en");
        Stack<String> area = new Stack<>();
        area.push("hk");

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
        JButton reset = new JButton("Reset");
        JButton btnExit = new JButton("Exit");
        JLabel user = new JLabel("<html><h1>Jacky</h1><p>Registered user</p><br></html>");
        String modes[] = { "Least Station", "Least Time"};
        JLabel lc = new JLabel("Criteria: ");
        JComboBox select = new JComboBox(modes);
        select.setMaximumSize(new Dimension(120, 25));

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
        sideBar.add(langcn);
        sideBar.add(langhk);
        sideBar.add(langen);
        sideBar.add(reset);
        sideBar.add(btnExit);
        // sideBar.add(lc);
        // sideBar.add(select);

        frame.add(sideBar);

        frame.setSize(400, 200);
        JPanel jp = new JPanel();

        JLabel from = new JLabel("From: ");
        JTextField from_text = new JTextField("",10);
        JLabel to = new JLabel("To: ");
        JTextField to_text = new JTextField("",10);

        JLabel date = new JLabel("<html>&emsp;" + dtf.format(now) + "</html>");
        JLabel result = new JLabel("[Path]");

        jp.add(from);
        jp.add(from_text);
        jp.add(to);
        jp.add(to_text);
        jp.add(date);
        // jp.add(result);

        String base = System.getProperty("user.dir");
        System.out.println("Current workspace: " + base);

        // ImageIcon szimg = new ImageIcon(ImageIO.read(new File(base + "/data/sz.png")));
        // ImageIcon hkimg = new ImageIcon(ImageIO.read(new File(base + "/data/hk.jpg")));
        // JLabel picLabel = new JLabel(hkimg);

        JButton btnhk = new JButton("Traffic Map (HK)");
        JButton btnsz = new JButton("Traffic Map (SZ)");

        JButton find_path = new JButton("Find Path");

        // jp.add(username);
        // jp.add(picLabel);

        // MTR_eng mtr_en = new MTR_eng();
        // Szbutton mtr_sz = new Szbutton();
        Map mtr_en_hk = new Map("en", "HK");
        mtr_en_hk.linkTextFieldComponent(from_text, to_text);

        Map mtr_en_sz = new Map("en", "SZ");
        mtr_en_sz.linkTextFieldComponent(from_text, to_text);

        Map mtr_hk_hk = new Map("hk", "HK");
        mtr_hk_hk.linkTextFieldComponent(from_text, to_text);

        Map mtr_hk_sz = new Map("hk", "SZ");
        mtr_hk_sz.linkTextFieldComponent(from_text, to_text);

        Map mtr_ch_hk = new Map("ch", "HK");
        mtr_ch_hk.linkTextFieldComponent(from_text, to_text);

        Map mtr_ch_sz = new Map("ch", "SZ");
        mtr_ch_sz.linkTextFieldComponent(from_text, to_text);

        Image sz_bg = ImageIO.read(new File(base + "/data/sz.png"));
        // BackgroundPanel mtr_sz_bg = new BackgroundPanel(sz_bg, 2);
        // jp.add(mtr_en.getPanel());

        JScrollPane scrollPane = new JScrollPane(mtr_en_hk);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        // scrollPane.setSize(500, 300);
        JPanel contentPane = new JPanel();
        contentPane.setPreferredSize(new Dimension(800, 800));
        contentPane.add(mtr_en_hk);
        jp.add(contentPane);
        jp.add(lc);
        jp.add(select);

        btnsz.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                area.push("sz");
                contentPane.removeAll();
                if (lang.peek().equals("en"))
                    contentPane.add(mtr_en_sz);
                else if (lang.peek().equals("hk"))
                    contentPane.add(mtr_hk_sz);
                else if (lang.peek().equals("ch"))
                    contentPane.add(mtr_ch_sz);
                contentPane.revalidate();
                contentPane.repaint();
            }
        });

        btnhk.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                area.push("hk");
                contentPane.removeAll();
                if (lang.peek().equals("en"))
                    contentPane.add(mtr_en_hk);
                else if (lang.peek().equals("hk"))
                    contentPane.add(mtr_hk_hk);
                else if (lang.peek().equals("ch"))
                    contentPane.add(mtr_ch_hk);
                contentPane.revalidate();
                contentPane.repaint();
            }
        });

        find_path.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // result.setText("new Value");
                MetroSystem m;
                m = MetroSystem.getInstance();
                m.setSystemLanguage(Language.English);
                Criteria c = null;
                if (select.getSelectedItem().toString().equals("Least Station")
                        || select.getSelectedItem().toString().equals("最少站数")
                        || select.getSelectedItem().toString().equals("最少站數")
                )
                    c = new CrtStation();
                else if (select.getSelectedItem().toString().equals("Least Time")
                        || select.getSelectedItem().toString().equals("最短时间")
                        || select.getSelectedItem().toString().equals("最短時間")
                )
                    c = new CrtTime();
                ArrayList<Integer> res = new ArrayList<>();
                Station startStation = null;
                Station endStation = null;
                String from_str = from_text.getText();
                String to_str = to_text.getText();
                System.out.println(from_str + " --> " + to_str);
                System.out.println(select.getSelectedItem().toString());
                try {
                    if (lang.peek().equals("en"))
                        startStation = m.getDatabase().getStationByName(from_str, Language.English, AdministratorHK.getInstance());
                    else if (lang.peek().equals("hk"))
                        startStation = m.getDatabase().getStationByName(from_str, Language.TraditionalChinese, AdministratorHK.getInstance());
                    else if (lang.peek().equals("ch"))
                        startStation = m.getDatabase().getStationByName(from_str, Language.SimplifiedChinese, AdministratorHK.getInstance());
                } catch (ExStationNotFound ex) {
                    try {
                        if (lang.peek().equals("en"))
                            startStation = m.getDatabase().getStationByName(from_str, Language.English, AdministratorSZ.getInstance());
                        else if (lang.peek().equals("hk"))
                            startStation = m.getDatabase().getStationByName(from_str, Language.TraditionalChinese, AdministratorSZ.getInstance());
                        else if (lang.peek().equals("ch"))
                            startStation = m.getDatabase().getStationByName(from_str, Language.SimplifiedChinese, AdministratorSZ.getInstance());
                    } catch (ExStationNotFound exc) {
                        startStation = null;
                        exc.printStackTrace();
                    }
                }

                try {
                    if (lang.peek().equals("en"))
                        endStation = m.getDatabase().getStationByName(to_str, Language.English, AdministratorHK.getInstance());
                    else if (lang.peek().equals("hk"))
                        endStation = m.getDatabase().getStationByName(to_str, Language.TraditionalChinese, AdministratorHK.getInstance());
                    else if (lang.peek().equals("ch"))
                        endStation = m.getDatabase().getStationByName(to_str, Language.SimplifiedChinese, AdministratorHK.getInstance());
                } catch (ExStationNotFound ex) {
                    try {
                        if (lang.peek().equals("en"))
                            endStation = m.getDatabase().getStationByName(to_str, Language.English, AdministratorSZ.getInstance());
                        else if (lang.peek().equals("hk"))
                            endStation = m.getDatabase().getStationByName(to_str, Language.TraditionalChinese, AdministratorSZ.getInstance());
                        else if (lang.peek().equals("ch"))
                            endStation = m.getDatabase().getStationByName(to_str, Language.SimplifiedChinese, AdministratorSZ.getInstance());
                    } catch (ExStationNotFound exc) {
                        endStation = null;
                        exc.printStackTrace();
                    }
                }

                res = c.findRoute(startStation.getId(), endStation.getId());
                ArrayList<Integer> hkres = new ArrayList<>();
                ArrayList<Integer> szres = new ArrayList<>();
                for(int id: res) {
                    if (id >= 98)
                        szres.add(id - 98);
                    else
                        hkres.add(id - 1);
                }
                if (!hkres.isEmpty()) {
                    System.out.println("This route has passed Hong Kong region. Points are: ");
                    System.out.println(hkres);
                    try {
                        if (lang.peek().equals("en"))
                            mtr_en_hk.showResult(hkres);
                        else if (lang.peek().equals("hk"))
                            mtr_hk_hk.showResult(hkres);
                        else if (lang.peek().equals("ch"))
                            mtr_ch_hk.showResult(hkres);
                    } catch (Exception ignored) {
                    }
                }
                if (!szres.isEmpty()) {
                    System.out.println("This route has passed Shen Zhen region. Points are: ");
                    System.out.println(szres);
                    try {
                        if (lang.peek().equals("en"))
                            mtr_en_sz.showResult(szres);
                        else if (lang.peek().equals("hk"))
                            mtr_hk_sz.showResult(szres);
                        else if (lang.peek().equals("ch"))
                            mtr_ch_sz.showResult(szres);
                    } catch (Exception ignored) {
                    }
                }

                ArrayList<String> allres = new ArrayList<>();
                for (int x : res) {
                    try {
                        allres.add(m.getDatabase().getStationById(x).getName());
                    } catch (ExStationNotFound ex) {
                        ex.printStackTrace();
                    }
                }
                System.out.println("All Stations are: ");
                System.out.println(allres.toString());
                result.setText("<html>&emsp;<b>" + from_str + " --> " + to_str + "<br>" + allres.toString() + "</b></html>");
                System.out.println();
            }
        });


        jp.add(btnhk);

        btnExit.setBackground(Color.RED);
        find_path.setBackground(Color.GREEN);
        reset.setBackground(Color.YELLOW);

        jp.add(btnsz);
        jp.add(find_path);
        // jp.add(find_path);
        // jp.add(reset);

        // Language settings
        langcn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                lang.push("ch");
                btnMap.setText("地图");
                btnExit.setText("退出");
                from.setText("起始地");
                to.setText("目的地");
                btnhk.setText("香港地铁线路图");
                btnsz.setText("深圳地铁线路图");
                find_path.setText("查找路径");
                user.setText("<html><h1>Jacky</h1><p>注册用户</p><br></html>");
                reset.setText("重设");
                contentPane.removeAll();
                if (area.peek().equals("sz"))
                    contentPane.add(mtr_ch_sz);
                else if (area.peek().equals("hk"))
                    contentPane.add(mtr_ch_hk);
                contentPane.revalidate();
                contentPane.repaint();
                from_text.setText("");
                to_text.setText("");
                select.removeItemAt(0);
                select.removeItemAt(0);
                select.addItem("最少站数");
                select.addItem("最短时间");
                lc.setText("标准: ");
            }
        });

        langhk.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                lang.push("hk");
                btnMap.setText("地圖");
                btnExit.setText("退出");
                from.setText("起始地");
                to.setText("目的地");
                btnhk.setText("香港地鐵線路圖");
                btnsz.setText("深圳地鐵線路圖");
                find_path.setText("查找路徑");
                user.setText("<html><h1>Jacky</h1><p>註冊用戶</p><br></html>");
                reset.setText("重設");
                contentPane.removeAll();
                if (area.peek().equals("sz"))
                    contentPane.add(mtr_hk_sz);
                else if (area.peek().equals("hk"))
                    contentPane.add(mtr_hk_hk);
                contentPane.revalidate();
                contentPane.repaint();
                from_text.setText("");
                to_text.setText("");
                select.removeItemAt(0);
                select.removeItemAt(0);
                select.addItem("最少站數");
                select.addItem("最短時間");
                lc.setText("準則: ");
            }
        });

        langen.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                lang.push("en");
                btnMap.setText("Map");
                btnExit.setText("Exit");
                from.setText("From: ");
                to.setText("To: ");
                btnhk.setText("Traffic Map (HK)");
                btnsz.setText("Traffic Map (SZ)");
                find_path.setText("Find Path");
                user.setText("<html><h1>Jacky</h1><p>Registered user</p><br></html>");
                reset.setText("Reset");
                contentPane.removeAll();
                if (area.peek().equals("sz"))
                    contentPane.add(mtr_en_sz);
                else if (area.peek().equals("hk"))
                    contentPane.add(mtr_en_hk);
                contentPane.revalidate();
                contentPane.repaint();
                from_text.setText("");
                to_text.setText("");
                select.removeItemAt(0);
                select.removeItemAt(0);
                select.addItem("Least Station");
                select.addItem("Least Time");
                lc.setText("Criteria: ");
            }
        });

        reset.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                lang.push("en");
                area.push("hk");
                from_text.setText("");
                to_text.setText("");
                contentPane.removeAll();
                contentPane.add(mtr_en_hk);
                contentPane.revalidate();
                contentPane.repaint();
                btnMap.setText("Map");
                btnExit.setText("Exit");
                from.setText("From: ");
                to.setText("To: ");
                btnhk.setText("Traffic Map (HK)");
                btnsz.setText("Traffic Map (SZ)");
                find_path.setText("Find Path");
                user.setText("<html><h1>Jacky</h1><p>Registered user</p><br></html>");
                reset.setText("Reset");
                select.removeItemAt(0);
                select.removeItemAt(0);
                select.addItem("Least Station");
                select.addItem("Least Time");
                lc.setText("Criteria: ");
            }
        });


        // btn3.setEnabled(false);
        // Dimension preferredSize=new Dimension(160, 60);
        // btn4.setPreferredSize(preferredSize);
        // btn4.setVerticalAlignment(SwingConstants.BOTTOM);

        // jp.add(mtr_sz_bg);
        frame.add(jp);

        // MTR_eng mtr_en = new MTR_eng();
        // frame.add(mtr_en.getPanel());

        frame.setBounds(300, 300, 600, 300);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

}
