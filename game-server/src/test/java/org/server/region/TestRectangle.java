package org.server.region;

import java.awt.Rectangle;
import java.awt.geom.Arc2D;

public class TestRectangle {

  public static void main(String[] args) {
    Rectangle rect = new Rectangle(0,0,128,128);
    Rectangle rect1 = new Rectangle(1,1,1,1);
    
    System.out.println(rect.intersects(rect1));
    System.out.println(rect.getLocation());
    System.out.println(rect.getSize());
    
    
  }

}
