/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pappl;

import com.vividsolutions.jts.geom.Coordinate;
import java.util.ArrayList;
import junit.framework.TestCase;
import java.lang.Math.*;

/**
 *
 * @author Anouk
 */
public class ShadowTest extends TestCase {
    
    public ShadowTest(String testName) {
        super(testName);
    }

    /**
     * Test of calculateDirection method, of class Shadow.
     * Problèmes d'arrondis
     */
    public void testCalculateDirection() {
        System.out.println("calculateDirection");
        Shadow instance = new Shadow(0,0);
        Coordinate direction1 = new Coordinate(0,1);
        Coordinate direction2 = new Coordinate(Math.cos(Math.PI/4),Math.cos(Math.PI/4));
        assertEquals(direction1.x, instance.calculateDirection((Math.PI)/4,(Math.PI)/2).x,0.000001);
        assertEquals(direction1.y, instance.calculateDirection((Math.PI)/4,(Math.PI)/2).y,0.000001);
        assertEquals(direction2.x, instance.calculateDirection((Math.PI)/4,(Math.PI)/4).x,0.000001);
        assertEquals(direction2.y, instance.calculateDirection((Math.PI)/4,(Math.PI)/4).y,0.000001);

    }

    /**
     * Test of projection method, of class Shadow.
     */
    public void testProjection() {
        System.out.println("projection");
        Coordinate c = null;
        double h = 0.0;
        Coordinate direction = null;
        Shadow instance = null;
        Coordinate expResult = null;
        Coordinate result = instance.projection(c, h, direction);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createShadow method, of class Shadow.
     */
    public void testCreateShadow() {
        System.out.println("createShadow");
        ArrayList<Coordinate> base = null;
        double height = 0.0;
        Coordinate direction = null;
        Shadow instance = null;
        instance.createShadow(base, height, direction);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
