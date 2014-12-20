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

/**
 *
 * @author avinesse
 */
public class Cube {
    
    private static final GeometryFactory GF = new GeometryFactory();
    //private Coordinate c;
    //private int l;

    public Cube() {
        /*this.c = c;
        this.l = l;*/
    }
    
    /**
     * Construction de la base d'un cube (carré) à partir de son origine et de la longueur de l'arête
     * @param c
     * @param l
     * @return 
     */
public Polygon construireBase (Coordinate c, int l){
    ArrayList<Coordinate> pointsSol = new ArrayList<>();
        pointsSol.add(c);
        pointsSol.add(new Coordinate(c.x+l,c.y,c.z));
        pointsSol.add(new Coordinate(c.x+l,c.y+l,c.z));
        pointsSol.add(new Coordinate(c.x,c.y+l,c.z));
        pointsSol.add(c);
        
       
        
        Polygon sol = GF.createPolygon(new LinearRing(new CoordinateArraySequence(pointsSol
        .toArray(new Coordinate[pointsSol.size()])), GF), null);
        
        return sol;
}
 
    /**
     * Construction d'un cube à partir de son origine et longueur de son arête
     * Gestion du 3D par JTS ?
     * @param c
     * @param l
     * @return 
     */
/*public GeometryCollection construireCube(Coordinate c, int l) {
    
  
    
    ArrayList<Coordinate> pointsSol = new ArrayList<>();
        pointsSol.add(c);
        pointsSol.add(new Coordinate(c.x+l,c.y,c.z));
        pointsSol.add(new Coordinate(c.x+l,c.y+l,c.z));
        pointsSol.add(new Coordinate(c.x,c.y+1,c.z));
        pointsSol.add(c);
        
       
        
        Polygon sol = GF.createPolygon(new LinearRing(new CoordinateArraySequence(pointsSol
        .toArray(new Coordinate[pointsSol.size()])), GF), null);
       
        
        ArrayList<Coordinate> pointsMur1 = new ArrayList<>();
        pointsMur1.add(c);
        pointsMur1.add(new Coordinate(c.x+l,c.y,c.z));
        pointsMur1.add(new Coordinate(c.x+l,c.y,c.z+l));
        pointsMur1.add(new Coordinate(c.x,c.y,c.z+l));
        pointsMur1.add(c);
        
        Polygon mur1 = GF.createPolygon(new LinearRing(new CoordinateArraySequence(pointsMur1
        .toArray(new Coordinate[pointsMur1.size()])), GF), null);
        
        ArrayList<Coordinate> pointsMur2 = new ArrayList<>();
        pointsMur2.add(new Coordinate(c.x,c.y+l,c.z));
        pointsMur2.add(new Coordinate(c.x,c.y+l,c.z+l));
        pointsMur2.add(new Coordinate(c.x+l,c.y+l,c.z+l));
        pointsMur2.add(new Coordinate(c.x+l,c.y+l,c.z));
        pointsMur2.add(new Coordinate(c.x,c.y+l,c.z));
        
        Polygon mur2 = GF.createPolygon(new LinearRing(new CoordinateArraySequence(pointsMur2
        .toArray(new Coordinate[pointsMur2.size()])), GF), null);
        
        ArrayList<Coordinate> pointsMur3 = new ArrayList<>();
        pointsMur3.add(new Coordinate(c.x+l,c.y+l,c.z));
        pointsMur3.add(new Coordinate(c.x+l,c.y+l,c.z+l));
        pointsMur3.add(new Coordinate(c.x+l,c.y,c.z+l));
        pointsMur3.add(new Coordinate(c.x+l,c.y,c.z));
        pointsMur3.add(new Coordinate(c.x+l,c.y+l,c.z));
        
        
        Polygon mur3 = GF.createPolygon(new LinearRing(new CoordinateArraySequence(pointsMur3
        .toArray(new Coordinate[pointsMur3.size()])), GF), null);
        
        ArrayList<Coordinate> pointsMur4 = new ArrayList<>();
        pointsMur4.add(new Coordinate(c.x+l,c.y,c.z));
        pointsMur4.add(new Coordinate(c.x+l,c.y,c.z+l));
        pointsMur4.add(new Coordinate(c.x,c.y,c.z+l));
        pointsMur4.add(new Coordinate(c.x,c.y,c.z));
        pointsMur4.add(new Coordinate(c.x+l,c.y,c.z));

        
        Polygon mur4 = GF.createPolygon(new LinearRing(new CoordinateArraySequence(pointsMur4
        .toArray(new Coordinate[pointsMur4.size()])), GF), null);
        
        ArrayList<Coordinate> pointsToit = new ArrayList<>();
        pointsToit.add(new Coordinate(c.x,c.y,c.z+l));
        pointsToit.add(new Coordinate(c.x+l,c.y,c.z+l));
        pointsToit.add(new Coordinate(c.x+l,c.y+l,c.z+l));
        pointsToit.add(new Coordinate(c.x,c.y+1,c.z+l));
        pointsToit.add(new Coordinate(c.x,c.y,c.z+l));
        
        Polygon toit = GF.createPolygon(new LinearRing(new CoordinateArraySequence(pointsToit
        .toArray(new Coordinate[pointsToit.size()])), GF), null);
        
        Geometry[] faces = new Geometry[6];
        faces[0] = sol;
        faces[1] = mur1;
        faces[2] = mur2;
        faces[3] = mur3;
        faces[4] = mur4;
        faces[5] = toit;
        
        GeometryCollection cube = new GeometryCollection(faces,GF);
        System.out.println(cube);
        System.out.println(cube.getNumPoints());
        return cube;
        
}*/
    
}
