/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pappl;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.LinearRing;
import com.vividsolutions.jts.geom.MultiPolygon;
import com.vividsolutions.jts.geom.Polygon;
import com.vividsolutions.jts.geom.impl.CoordinateArraySequence;
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
        Coordinate direction1 = new Coordinate(0,-1);
        Coordinate direction2 = new Coordinate(-Math.cos(Math.PI/4),-Math.cos(Math.PI/4));
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
        Coordinate c =new Coordinate(0,0);
        double h =1;
        Coordinate direction = new Coordinate(1,1);
        Shadow instance = new Shadow(0,0);
        Coordinate expResult = new Coordinate(1,1);
        Coordinate result = instance.projection(c, h, direction);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of createShadow method, of class Shadow.
     */
    public void testCreateShadow() {
        System.out.println("createShadow");
        GeometryFactory GF = new GeometryFactory();
        Cube cube=new Cube();
        Shadow shadow = new Shadow(0.0738274274,0.84002696898487);
        Polygon base;
        Polygon hole;
        Coordinate origine = new Coordinate (0,0);
        Coordinate origineHole = new Coordinate (1,1);
        base = cube.construireBase(origine,4);
        LinearRing shell = (LinearRing) base.getExteriorRing();
        hole = cube.construireBase(origineHole,2);
        LinearRing[] holes = new LinearRing[1];
        LinearRing holeRing = (LinearRing) hole.getExteriorRing();
        holes[0]=holeRing;
        Polygon test = new Polygon(shell,holes,GF);
        System.out.println(test);
        
        double height = 1;
        double height2 = 2;
        Coordinate direction = new Coordinate(0,-1);
        Polygon result;
        Polygon result2;
        Coordinate origineExpResult= new Coordinate(0,-1);
        result = shadow.createShadow(test, height, direction); 
        //result2 = shadow.createShadow(base, height2, direction); 
        //Polygon expResult;
        
        ArrayList<Coordinate> polygonPoints = new ArrayList<>();
        Coordinate p1 = new Coordinate(0,-2);
        Coordinate p2 = new Coordinate(1,-2);
        Coordinate p3 = new Coordinate(1,0);
        Coordinate p4 = new Coordinate(0,0);
        polygonPoints.add(p1);
        polygonPoints.add(p2);
        polygonPoints.add(p3);
        polygonPoints.add(p4);
        polygonPoints.add(p1);
        Polygon expResult2 = GF.createPolygon(new LinearRing(new CoordinateArraySequence(polygonPoints.toArray(new Coordinate[polygonPoints.size()])), GF), null);
        Polygon expResult = GF.createPolygon(new LinearRing(new CoordinateArraySequence(polygonPoints.toArray(new Coordinate[polygonPoints.size()])), GF), null);
    
        //expResult = cube.construireBase(origineExpResult,1);

        assertEquals(expResult,result);
        //assertEquals(expResult2,result2);
    }
    
}
