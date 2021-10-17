import javax.swing.*;
import java.awt.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;



public class sample implements Runnable {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new sample());
    }
    
    @Override
    public void run() {
        JFrame frame = new JFrame("MTR");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        frame.add(defineTopPanel(), BorderLayout.BEFORE_FIRST_LINE);
        
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }
    
    private JPanel defineTopPanel() {
        JPanel panel = new JPanel();
        panel.add(new DotMatrixDigit(1, Color.BLUE, Color.WHITE));
        return panel;
    }
    
    
    /**
     * 
     * @author Gilbert G. Le Blanc
     * @version 1.8 - 20 October 2020
     *
     * @see JPanel
     * @see Color
     */
    public class DotMatrixDigit extends JPanel {

        private static final long serialVersionUID = 1L;
        private int digit;
        private int dotWidth;
        private int pixelWidth;
        private int margin;
        
        private final Color dotColor;
        
        private int[][][] matrices;
        
        private String[] Line1stations = {"Tuen Mun","Siu Hong","Tin Shui Wai","Long Ping","Yuen Long","Kan Sheung Road","Tsuen Wan West"};
        /**
         * @param digit           - The initial digit to display from 0 through 9.
         * @param dotColor        - The <code>Color</code> of the dots.
         * @param backgroundColor - The background <code>Color</code> of the dot matrix
         *                        digit panel.
         *                        
         */
        public DotMatrixDigit(int digit, Color dotColor, Color backgroundColor) {
            this.digit = digit;
            this.dotColor = dotColor;
            
            this.dotWidth = 10;
            this.pixelWidth = 150;
            this.margin = dotWidth;
            this.matrices = defineDigitMatricies();
            
            int width = 4 * pixelWidth + dotWidth + margin + margin;
            int height = 6 * pixelWidth + dotWidth + margin + margin;
            this.setBackground(backgroundColor);
            this.setPreferredSize(new Dimension(width, height));
        }
        
        private int[][][] defineDigitMatricies() {
            int[][][] matrices = new int[10][][];
            //Add lines
            matrices[1] = Line1();

            
            return matrices;
        }

        
        private int[][] Line1() { 
            int[][] matrix = new int[7][];
            matrix[0] = new int[] {50, 400};
            matrix[1] = new int[] {50, 200};
            matrix[2] = new int[] {100, 50};
            matrix[3] = new int[] {150, 120};
            matrix[4] = new int[] {150, 200};
            matrix[5] = new int[] {150, 350};
            matrix[6] = new int[] {200, 500};
            return matrix;
        }


        
        public void setDigit(int digit) {
            this.digit = digit;
            this.repaint();
        }
        
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            int prevX = 0;
            int prevY = 0;
            g.setColor(dotColor);
            for (int row = 0; row < matrices[digit].length; row++) {
                int x = 0,y = 0;
                for (int column = 0; column < matrices[digit][row].length; column++) {
                    x = matrices[digit][row][0];
                    y = matrices[digit][row][1];
                    g.fillOval(x, y, dotWidth, dotWidth);
                    if(prevX!=0 && prevY!= 0){
                        g2.drawLine(prevX + 5, prevY + 5, x + 5, y + 5);
                    }
                    prevX = x;
                    prevY = y;                  
                } 
                /*  
                String station = Line1stations[row];
                JLabel l = new JLabel(station);
                l.setHorizontalAlignment(x);
                l.setVerticalAlignment(y);
                add(l);
                l.setSize(100,100);
                setVisible(true);    
                */
            }
        }
        
    }

}