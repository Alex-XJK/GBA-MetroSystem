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

public class SZMap extends JPanel implements ActionListener {

    boolean result = false;

    private ArrayList<JButton> allButtons = new ArrayList<JButton>();
    private ArrayList<JLabel> allLabels = new ArrayList<JLabel>();
    private JTextField checkButton = new JTextField();

    public SZMap() {

        super();

        // setLayout(new FlowLayout(FlowLayout.CENTER));
        setLayout(null);
        this.setPreferredSize(getPreferredSize());

        try {
            generateButtons();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        for (JButton b : allButtons) {
            add(b);
            b.addActionListener(this);
        }

        for (JLabel l : allLabels) {
            add(l);
        }

        add(checkButton);
        checkButton.setBounds(new Rectangle(800, 800, 100, 100));
    }

    public static void main(String[] args) {
        JFrame win = new JFrame();

        win.getContentPane().add(new SZMap());
        // win.add(checkButton);

        win.setBounds(200, 200, 200, 200);
        win.setVisible(true);
        win.pack();
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void generateButtons() throws IOException {
        String base = System.getProperty("user.dir");
        FileInputStream fis = new FileInputStream(new File(base + "/Source/MetroSystem/data/stations_SZX_Y.xlsx"));

        XSSFWorkbook wb = new XSSFWorkbook(fis);

        XSSFSheet sheet = wb.getSheetAt(0);

        for (Row row : sheet) {
            int bx = (int) (row.getCell(0).getNumericCellValue() * 0.5);
            if (bx == 0)
                break;
            JButton b = new JButton();

            int by = (int) (row.getCell(1).getNumericCellValue() * 0.5);
            String s = row.getCell(2).getStringCellValue();
            b.setBounds(bx, by, 5, 5);

            // System.out.println(x);
            // System.out.println(s);

            b.setName(s);

            JLabel l = new JLabel(s);
            // int lx = (int) row.getCell(3).getNumericCellValue();
            // int ly = (int) row.getCell(4).getNumericCellValue();

            // b.setText(s);
            // b.setMargin(new Insets(0,0,0,0));
            // b.setFont(new Font("Arial", Font.PLAIN, 4));
            l.setBounds(bx + 6, by + 6, 15, 15);
            l.setFont(new Font("Serif", Font.PLAIN, 5));

            allButtons.add(b);
            allLabels.add(l);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        checkButton.setText(((JButton) (e.getSource())).getName());
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(800, 800);
    };

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        String base = System.getProperty("user.dir");
        // g2.drawLine(jButton1.getX(), jButton1.getY(), jButton2.getX(),
        // jButton2.getY());
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLUE);

        FileInputStream lineStream = null;
        try {
            lineStream = new FileInputStream(new File(base + "/Source/MetroSystem/data//edges_SZ.xlsx"));
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

    public void showResult() {
        // TODO Auto-generated method stub
        Graphics g = getGraphics();
        paint(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(4));
        g2.setColor(Color.RED);

        for (int i = 0; i < 6; i++) {
            if (result = true) {
                Point p1 = SwingUtilities.convertPoint(allButtons.get(i), 0, 0, this);
                Point p2 = SwingUtilities.convertPoint(allButtons.get(i + 1), 0, 0, this);
                g2.drawLine(p1.x + 3, p1.y + 3, p2.x + 3, p2.y + 3);
            }
        }

        g2.setColor(Color.GRAY);

        for (int i = 7; i < 10; i++) {
            if (result = true) {
                Point p1 = SwingUtilities.convertPoint(allButtons.get(i), 0, 0, this);
                Point p2 = SwingUtilities.convertPoint(allButtons.get(i + 1), 0, 0, this);
                g2.drawLine(p1.x + 3, p1.y + 3, p2.x + 3, p2.y + 3);
            }
        }
    }

    public void setResult(boolean isResult) {
        result = isResult;
    }

}
