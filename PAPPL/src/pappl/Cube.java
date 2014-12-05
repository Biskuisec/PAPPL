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
    private ArrayList<ArrayList<Coordinate>> faces;
    
    

    public Cube(ArrayList<Coordinate> sommets) {
        this.faces = faces;
        
        
        
    }
    
public GeometryCollection construireCube(Coordinate c, int l) {
    
        for (int k=0;k<2;k++){
            for (int j=0;j<2;j++){
                for (int i=0;i<2;i++){
                Coordinate sommet = new Coordinate(c.x+1,c.y+j,c.z);
                faces.get(0).add(sommet);
            
                }
            }
        }
        
        Polygon sol = GF.createPolygon(new LinearRing(new CoordinateArraySequence((Coordinate[]) (faces.get(0))
        .toArray(new Coordinate[(faces.get(0)).size()])), GF), null);
        
        for (int i=4;i<8;i++){
            faces.get(5).add(sommets.get(i));
        }
        
        
        
    
       
    
    
        
        Polygon sol = GF.createPolygon(new LinearRing(new CoordinateArraySequence(pointsSol
        .toArray(new Coordinate[pointsSol.size()])), GF), null);
        
        ArrayList<Coordinate> pointsMur1 = new ArrayList<>();
        pointsMur1.add(new Coordinate(0,0,0));
        pointsMur1.add(new Coordinate(0,0,1));
        pointsMur1.add(new Coordinate(0,1,1));
        pointsMur1.add(new Coordinate(0,1,0));
        pointsMur1.add(new Coordinate(0,0,0));
        
        Polygon mur1 = GF.createPolygon(new LinearRing(new CoordinateArraySequence(pointsMur1
        .toArray(new Coordinate[pointsMur1.size()])), GF), null);
        
        ArrayList<Coordinate> pointsMur2 = new ArrayList<>();
        pointsMur2.add(new Coordinate(0,1,0));
        pointsMur2.add(new Coordinate(0,1,1));
        pointsMur2.add(new Coordinate(1,1,1));
        pointsMur2.add(new Coordinate(1,1,0));
        pointsMur2.add(new Coordinate(0,1,0));
        
        Polygon mur2 = GF.createPolygon(new LinearRing(new CoordinateArraySequence(pointsMur2
        .toArray(new Coordinate[pointsMur2.size()])), GF), null);
        
        ArrayList<Coordinate> pointsMur3 = new ArrayList<>();
        pointsMur3.add(new Coordinate(1,1,0));
        pointsMur3.add(new Coordinate(1,1,1));
        pointsMur3.add(new Coordinate(1,0,1));
        pointsMur3.add(new Coordinate(1,0,0));
        pointsMur3.add(new Coordinate(1,1,0));
        
        Polygon mur3 = GF.createPolygon(new LinearRing(new CoordinateArraySequence(pointsMur3
        .toArray(new Coordinate[pointsMur3.size()])), GF), null);
        
        ArrayList<Coordinate> pointsMur4 = new ArrayList<>();
        pointsMur4.add(new Coordinate(1,0,0));
        pointsMur4.add(new Coordinate(1,0,1));
        pointsMur4.add(new Coordinate(0,0,1));
        pointsMur4.add(new Coordinate(0,0,0));
        pointsMur4.add(new Coordinate(1,0,0));
        
        Polygon mur4 = GF.createPolygon(new LinearRing(new CoordinateArraySequence(pointsMur4
        .toArray(new Coordinate[pointsMur4.size()])), GF), null);
        
        ArrayList<Coordinate> pointsToit = new ArrayList<>();
        pointsToit.add(new Coordinate(1,1,0));
        pointsToit.add(new Coordinate(1,1,1));
        pointsToit.add(new Coordinate(1,0,1));
        pointsToit.add(new Coordinate(1,0,0));
        pointsToit.add(new Coordinate(1,1,0));
        
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
        
        return cube;
        
}
    
}
