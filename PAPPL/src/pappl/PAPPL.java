/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pappl;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryCollection;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LinearRing;
import com.vividsolutions.jts.geom.Polygon;
import com.vividsolutions.jts.geom.impl.CoordinateArraySequence;
import java.util.ArrayList;
import java.lang.Math.*;
import java.util.Date;

/**
 *
 * @author avinesse
 */
public class PAPPL {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
 
        /*ArrayList<ArrayList<Coordinate>> faces = new ArrayList<>();
         ArrayList<Coordinate> sol = new ArrayList<>();
         faces.add(sol);
         ArrayList<Coordinate> plafond = new ArrayList<>();
         faces.add(plafond);*/
        Coordinate origine = new Coordinate(0, 0, 0);
        Cube cube = new Cube();

        //cube.construireCube(origine, 1);
        Polygon carre = cube.construireBase(origine, 4);


/**
 * On simule la position du soleil
 */
        
        /*double altitude = (Math.PI) / 2;
        double azimuth = Math.PI;

        Shadow shadow = new Shadow(altitude, azimuth);

        Coordinate direction = shadow.calculateDirection(altitude,azimuth);
        double height = 5;



/**
 * création de l'ombre
 */
        //shadow.createShadow(carre, height,direction); 

        /*Date now = new Date(new java.util.Date().getTime());
        System.out.println(new java.util.Date().getTime());
        //SunPosition soleil = new SunPosition(now);
        double lat = 47.2484747;
        double lng =  -1.5485036 ;
        
        
        System.out.println(SunPosition.getPosition(now, lat, lng).get(0));
        System.out.println(SunPosition.getPosition(now, lat, lng).get(1));*/
        Shadow shadow = new Shadow(0,0);
        Coordinate direction = new Coordinate (0,-1);
        shadow.createShadow(carre, 1, direction);
        


    }
}
