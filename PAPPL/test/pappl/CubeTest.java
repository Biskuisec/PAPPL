/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pappl;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Polygon;
import java.util.ArrayList;
import java.util.Arrays;
import junit.framework.TestCase;

/**
 *
 * @author Anouk
 */
public class CubeTest extends TestCase {
    
    public CubeTest(String testName) {
        super(testName);
    }

    /**
     * Test of construireBase method, of class Cube.
     */
    public void testConstruireBase() {
        System.out.println("construireBase");
        Cube instance = new Cube();
        Coordinate c1 = new Coordinate(0,0);
        int l1 = 1;
        // We construct a polygon whose side is l1 and  origin c1;
        Polygon result1 = instance.construireBase(c1, l1);
        assertEquals(1.0, result1.getArea());
        //test we get the right surface
        
        
        Coordinate c2 = new Coordinate(1,1);
        int l2 = 2;
        Polygon result2 = instance.construireBase(c2, l2);
        ArrayList<Coordinate> coordExp = new ArrayList<>();
        ArrayList<Coordinate> coordRes = new ArrayList<>();
        for (Coordinate c:result2.getCoordinates())
        {
            coordRes.add(c);
        }
        Coordinate c3 = new Coordinate (3,1);
        Coordinate c4 = new Coordinate (3,3);
        Coordinate c5 = new Coordinate (1,3);
        coordExp.add(c2);
        coordExp.add(c3);
        coordExp.add(c4);
        coordExp.add(c5);
        coordExp.add(c2);
        assertEquals(coordExp,coordRes);
       //test we get the right coordinates
        
    }
    
}
