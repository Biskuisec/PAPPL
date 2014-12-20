/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pappl;

import java.util.ArrayList;
import java.util.Date;
import junit.framework.TestCase;

/**
 *
 * @author Anouk
 */
public class SunPositionTest extends TestCase {
    
    public SunPositionTest(String testName) {
        super(testName);
    }

    /**
     * Test of getDate method, of class SunPosition.
     */
    public void testGetDate() {
        System.out.println("getDate");
        SunPosition instance = null;
        Date expResult = null;
        Date result = instance.getDate();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDate method, of class SunPosition.
     */
    public void testSetDate() {
        System.out.println("setDate");
        Date date = null;
        SunPosition instance = null;
        instance.setDate(date);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPosition method, of class SunPosition.
     */
    public void testGetPosition() {
        System.out.println("getPosition");
        Date date;
        date = new Date(1419090123235L); //20 décembre 2014 à 16h40
        double lat = 47.2484747; //position Bat A ECN
        double lng = -1.5485036;
        ArrayList<Double> expResult = new ArrayList<>();
        
        //résultats tirés de http://www.sunearthtools.com/dp/tools/pos_sun.php
        double expAlt = 0.0738274274; //4,23 degrés
        double expAzim = 0.84002696898487; //228,13 degrés à partir Sud, donc 48,13 degrés à partir du nord
        
        expResult.add(expAlt);
        expResult.add(expAzim);
        ArrayList<Double> result = SunPosition.getPosition(date, lat, lng);
        assertEquals(expResult.get(0), result.get(0), 0.01);
        assertEquals(expResult.get(1), result.get(1), 0.01);


    }
    
}
