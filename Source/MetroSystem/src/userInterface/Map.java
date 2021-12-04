package userInterface;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.Graphics;
import java.awt.Point;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.awt.Graphics2D;
import java.awt.Dimension;
import java.awt.*;

/**
 * Render the detailed route and site content inside the map.
 */
public class Map extends JPanel implements ActionListener {

    String area = null;
    String lan = null;
    JTextField from_station = null;
    JTextField to_station = null;

    private ArrayList<JButton> allButtons = new ArrayList<JButton>();
    private ArrayList<JLabel> allLabels = new ArrayList<JLabel>();

    public Map(String lan, String area) {

        super();

        this.area = area;
        this.lan = lan;

        setLayout(null);
        this.setPreferredSize(getPreferredSize());

        try {
            generateButtons(lan, area);

        } catch (IOException e) {
            e.printStackTrace();
        }

        for (JButton b : allButtons) {
            add(b);
            b.addActionListener(this);
        }

        for (JLabel l : allLabels) {
            add(l);
        }
    }

    /**
     * Generate buttons for each subway station.
     * @param lan   Current language mode
     * @param area  City code, used when switching map of different cities
     * @throws IOException If the required data file cannot be found at the desired place
     */
    public void generateButtons(String lan, String area) throws IOException {
        String base = System.getProperty("user.dir");
        FileInputStream fis = new FileInputStream(
                new File(base + "/data/stations_" + area + ".xlsx"));

        XSSFWorkbook wb = new XSSFWorkbook(fis);

        XSSFSheet sheet = wb.getSheetAt(0);
        boolean firstLine = true;

        for (Row row : sheet) {
            if (firstLine) {
                firstLine = false;
                continue;
            }
            int bx = (int) (row.getCell(3).getNumericCellValue() * 0.6);
            if (bx == 0)
                break;
            JButton b = new JButton();

            int by = (int) (row.getCell(4).getNumericCellValue() * 0.6);
            String s = null;
            switch (lan) {
                case ("en"):
                    s = row.getCell(0).getStringCellValue();
                    break;
                case ("hk"):
                    s = row.getCell(1).getStringCellValue();
                    break;
                case ("ch"):
                    s = row.getCell(2).getStringCellValue();
                    break;
            }

            b.setBounds(bx, by, 10, 10);

            b.setName(s);

            JLabel l = new JLabel(s);
            l.setBounds(bx + 5, by + 5, 20, 20);
            l.setFont(new Font("Serif", Font.PLAIN, 5));

            allButtons.add(b);
            allLabels.add(l);
        }
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(800, 800);
    };

    public void linkTextFieldComponent(JTextField from_station, JTextField to_station) {
        this.from_station = from_station;
        this.to_station = to_station;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (from_station.getText().equals("")) {
            from_station.setText(((JButton) (e.getSource())).getName());
        } else {
            to_station.setText(((JButton) (e.getSource())).getName());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        String base = System.getProperty("user.dir");
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLACK);

        FileInputStream lineStream = null;
        try {
            lineStream = new FileInputStream(new File(base + "/data/edges_" + area + ".xlsx"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        XSSFWorkbook wb = null;
        try {
            wb = new XSSFWorkbook(lineStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        XSSFSheet sheet = wb.getSheetAt(0);

        boolean firstLine = true;
        for (Row row : sheet) {
            if (firstLine) {
                firstLine = false;
                continue;
            }
            int x = (int) row.getCell(0).getNumericCellValue();
            if (x == 0)
                break;
            int y = (int) row.getCell(1).getNumericCellValue();
            Point p1 = SwingUtilities.convertPoint(allButtons.get(x - 1), 0, 0, this);
            Point p2 = SwingUtilities.convertPoint(allButtons.get(y - 1), 0, 0, this);
            g2.drawLine(p1.x + 3, p1.y + 3, p2.x + 3, p2.y + 3);

            // System.out.print("successfully painting!");
        }

    }

    public String showResult(ArrayList<Integer> stations_on_path) {
        // TODO Auto-generated method stub
        Graphics g = getGraphics();
        // paint(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(3));
        g2.setColor(Color.RED);

        for (int i = 0; i < stations_on_path.size() - 1; i++) {
            Point p1 = SwingUtilities.convertPoint(allButtons.get(stations_on_path.get(i)), 0, 0, this);
            Point p2 = SwingUtilities.convertPoint(allButtons.get(stations_on_path.get(i + 1)), 0, 0, this);
            g2.drawLine(p1.x + 3, p1.y + 3, p2.x + 3, p2.y + 3);
        }
        return "successfully showing the result!";
    }

    public String getArea() {
        return area;
    }

    public String getLan() {
        return lan;
    }

    public int getButtonNumber() {
        return allButtons.size();
    }

    public int getLabelNumber() {
        return allLabels.size();
    }

    public JTextField getFrom_station() {
        return from_station;
    }

    public JTextField getTo_station() {
        return to_station;
    }

}
