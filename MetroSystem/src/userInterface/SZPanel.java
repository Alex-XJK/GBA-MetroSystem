import java.util.ArrayList;
import java.util.EventListener;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.File;  
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;  
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

public class SZPanel extends JPanel implements ActionListener {

    private ArrayList<JButton> allButtons = new ArrayList<JButton>();
    private ArrayList<JLabel> allLabels = new ArrayList<JLabel>();
    private JTextField checkButton = new JTextField();



    public SZPanel() {
        super();
        setLayout(null);
        try {
            generateButtons();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        for(JButton b : allButtons) {
            add(b);
            b.addActionListener(this);
        }

        for(JLabel l : allLabels) {
            add(l);
        }

        add(checkButton);
        checkButton.setBounds(new Rectangle(800,800,100,100));
    }

    public static void main(String[] args) {
        JFrame win = new JFrame();

        win.getContentPane().add(new SZPanel());

        //win.add(checkButton);

        win.setBounds(200, 200, 200, 200);
        win.setVisible(true);
        win.pack();
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }

    public void generateButtons() throws IOException 
    {
        FileInputStream fis=new FileInputStream(new File("./SheetFile/stations_SZX_Y.xlsx"));  
        
        XSSFWorkbook wb=new XSSFWorkbook(fis);   
        
        XSSFSheet sheet=wb.getSheetAt(0);  
        

        for(Row row: sheet)    
        {  
            JButton b = new JButton();
            int bx = (int) row.getCell(0).getNumericCellValue();
            if (bx == 0) break;
            int by = (int) row.getCell(1).getNumericCellValue();
            String s = row.getCell(2).getStringCellValue();
            b.setBounds(bx, by,5, 5);

            //System.out.println(x);
            //System.out.println(s);

            b.setName(s);

            JLabel l = new JLabel(s);
            //int lx = (int) row.getCell(3).getNumericCellValue();
            //int ly = (int) row.getCell(4).getNumericCellValue();

            //b.setText(s);
            //b.setMargin(new Insets(0,0,0,0));
            //b.setFont(new Font("Arial", Font.PLAIN, 4));
            l.setBounds(bx + 6, by + 6, 15, 15);
            l.setFont(new Font("Serif", Font.PLAIN, 5));

            allButtons.add(b);
            allLabels.add(l);
        }
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        checkButton.setText(   (    (JButton)(e.getSource())    ).getName()    );
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g1 = (Graphics2D) g;
        //g2.drawLine(jButton1.getX(), jButton1.getY(), jButton2.getX(), jButton2.getY());
        g1.setColor(Color.BLUE);



        FileInputStream lineStream = null;
        try {
            lineStream = new FileInputStream(new File("./SheetFile/edges_SZ.xlsx"));
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
        
        XSSFSheet sheet=wb.getSheetAt(0);  
        

        for(Row row: sheet)    
        {  
            int x = (int) row.getCell(0).getNumericCellValue();
            if (x == 0) break;
            int y = (int) row.getCell(1).getNumericCellValue();
            Point p1 = SwingUtilities.convertPoint(allButtons.get(x-1), 0, 0, this);
            Point p2 = SwingUtilities.convertPoint(allButtons.get(y-1), 0, 0, this);
            g1.drawLine(p1.x + 3, p1.y + 3, p2.x + 3, p2.y + 3);

            //System.out.println(x);
        }

    
    }
    
}
