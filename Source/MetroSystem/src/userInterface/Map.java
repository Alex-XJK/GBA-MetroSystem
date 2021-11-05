package userInterface;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.*;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.examples.hslf.Graphics2DDemo;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Dimension;
import java.awt.*;

public class Map extends JPanel implements ActionListener {

    String area = null;
    JTextField from_station = null;
    JTextField to_station = null;

    private ArrayList<JButton> allButtons = new ArrayList<JButton>();
    private ArrayList<JLabel> allLabels = new ArrayList<JLabel>();

    // to_zx: Initialize three different JPanel for different language option -
    // provide parameter
    public Map(String lan, String area) {

        super();

        this.area = area;

        // setLayout(new FlowLayout(FlowLayout.CENTER));
        setLayout(null);
        this.setPreferredSize(getPreferredSize());

        try {
            generateButtons(lan, area);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        for (JButton b : allButtons) {
            add(b);
        }

        for (JLabel l : allLabels) {
            add(l);
        }
    }

    /*
     * public static void main(String[] args) { JFrame win = new JFrame();
     * 
     * win.getContentPane().add(new SZMap()); // win.add(checkButton);
     * 
     * win.setBounds(200, 200, 200, 200); win.setVisible(true); win.pack();
     * win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     * 
     * }
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

            b.setBounds(bx, by, 5, 5);

            b.setName(s);

            JLabel l = new JLabel(s);
            l.setBounds(bx + 5, by + 5, 20, 20);
            l.setFont(new Font("Serif", Font.PLAIN, 5));

            allButtons.add(b);
            allLabels.add(l);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (from_station.getText().equals("")) {
            from_station.setText(((JButton) (e.getSource())).getName());
        } else {
            to_station.setText(((JButton) (e.getSource())).getName());
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(800, 800);
    };

    public void linkTextFieldComponent(JTextField from_station, JTextField to_station) {
        this.from_station = from_station;
        this.to_station = to_station;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        String base = System.getProperty("user.dir");
        // g2.drawLine(jButton1.getX(), jButton1.getY(), jButton2.getX(),
        // jButton2.getY());
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLACK);

        FileInputStream lineStream = null;
        try {
            lineStream = new FileInputStream(new File(base + "/data/edges_" + area + ".xlsx"));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        XSSFWorkbook wb = null;
        try {
            wb = new XSSFWorkbook(lineStream);
        } catch (IOException e) {
            // TODO Auto-generated catch block
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

            // System.out.println(x);
        }

    }

    public void showResult(ArrayList<Integer> stations_on_path) {
        // TODO Auto-generated method stub
        Graphics g = getGraphics();
        paint(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(3));
        g2.setColor(Color.RED);

        for (int i = 0; i < stations_on_path.size() - 1; i++) {
            Point p1 = SwingUtilities.convertPoint(allButtons.get(stations_on_path.get(i)), 0, 0, this);
            Point p2 = SwingUtilities.convertPoint(allButtons.get(stations_on_path.get(i + 1)), 0, 0, this);
            g2.drawLine(p1.x + 3, p1.y + 3, p2.x + 3, p2.y + 3);
        }
    }

}
