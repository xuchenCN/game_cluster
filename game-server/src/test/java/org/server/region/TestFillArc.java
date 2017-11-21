package org.server.region;

import java.awt.Color;  
import java.awt.Graphics;  
  
import javax.swing.JFrame;  
import javax.swing.JPanel;  
  
public class TestFillArc extends JFrame{  
    public TestFillArc(){  
        panel p = new panel();  
        this.add(p);  
        this.setTitle("my first");  
        this.setLocationRelativeTo(null);  
        this.setSize(600,600);  
        this.setVisible(true);  
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
          
    }  
  
    public static void main(String[] args) {  
        new TestFillArc();  
    }  
}  
class panel extends JPanel{  
    protected void paintComponent(Graphics g){  
        int CenterX,CenterY;  
        int r;  
        CenterX = this.getWidth();  
        CenterY = this.getHeight();  
        r = this.getHeight()/4;  
        super.paintComponent(g);  
        g.setColor(Color.blue);  
        g.fillArc(200, 200, 100, 100, 30, 120);  
//        g.fillArc(CenterX/6, CenterY/6, 2*r, 2*r, 90, 60);  
//        g.fillArc(CenterX/6, CenterY/6, 2*r, 2*r, 180, 60);  
//        g.fillArc(CenterX/6, CenterY/6, 2*r, 2*r, 280, 60);  
//        g.fillArc(CenterX/6, CenterY/6, 2*r, 2*r, 360, 60);  
        g.drawString(""+CenterX+" "+CenterY, 0, CenterY);  
        g.setColor(Color.red);
        g.fillOval(225, 208, 5, 5);
    }
}