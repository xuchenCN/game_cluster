package org.server.region;

import java.awt.Point;
import java.awt.geom.Arc2D;

public class TestArcPoint {

	public static void main(String[] args) {
		Arc2D.Float arc2d = new Arc2D.Float(200,200,100,100,30,120,Arc2D.PIE);
    System.out.println(arc2d.contains(new Point(215,208)));
	}

}
