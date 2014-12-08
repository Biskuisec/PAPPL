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

/**
 *
 * @author avinesse
 */
public class PAPPL {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArrayList<ArrayList<Coordinate>> faces = new ArrayList<>();
        ArrayList<Coordinate> sol = new ArrayList<>();
        faces.add(sol);
        ArrayList<Coordinate> plafond = new ArrayList<>();
        faces.add(plafond);
        Coordinate origine = new Coordinate(0,0,0);
        Cube cube = new Cube(origine, 1);
        
        cube.construireCube(origine, 1);
        ArrayList<Coordinate> carre = cube.construireBase(origine, 1);
        
        
        
        Coordinate direction = new Coordinate (0,0,0);
        double altitude = (Math.PI)/2;
        double azimuth = Math.PI;
        
        Shadow shadow = new Shadow(direction,altitude,azimuth);
        
        double length = 1/(Math.tan(altitude));
        //System.out.println(length);
        direction.x= Math.cos(azimuth)*length;
        direction.y= Math.sin(azimuth)*length;
        //System.out.println(direction);
        double height = 5;
        
        Coordinate a = new Coordinate (0,0);
        Coordinate b = new Coordinate (0,0);
        Coordinate _a = new Coordinate (0,0);
        Coordinate _b = new Coordinate (0,0);
        

        //System.out.println(carre.getCoordinate());
        
        


for (int i = 0; i < carre.size()-1; i ++) {
a.x = carre.get(i).x-Shadow.ORIGIN_X;
a.y = carre.get(i).y-Shadow.ORIGIN_Y;

b.x = carre.get(i+1).x-Shadow.ORIGIN_X;
b.y = carre.get(i+1).y-Shadow.ORIGIN_Y;

_a.x = shadow.projection(a, height);
_a.y = a.y + direction.y*height;
_b.x = b.x + direction.x*height;
_b.y = b.y + direction.y*height;

    System.out.println(_a);
    System.out.println(_b);

/*f (minHeight) {
a = Shadows.project(a, minHeight);
b = Shadows.project(b, minHeight);
}*/
// mode 0: floor edges, mode 1: roof edges
/*if ((b.x-a.x) * (_a.y-a.y) > (_a.x-a.x) * (b.y-a.y)) {
if (mode === 1) {
context.lineTo(a.x, a.y);
}
mode = 0;
if (!i) {
context.moveTo(a.x, a.y);
}
context.lineTo(b.x, b.y);
} else {
if (mode === 0) {
context.lineTo(_a.x, _a.y);
}
mode = 1;
if (!i) {
context.moveTo(_a.x, _a.y);
}
context.lineTo(_b.x, _b.y);
}
}
if (innerPolygons) {
for (i = 0, il = innerPolygons.length; i < il; i++) {
this._ringAbs(context, innerPolygons[i]);
}
}
},
shadowMask: function(context, polygon, innerPolygons) {
this._ringAbs(context, polygon);
if (innerPolygons) {
for (var i = 0, il = innerPolygons.length; i < il; i++) {
this._ringAbs(context, innerPolygons[i]);
}
}
},*/
        
        
}   
        
    }
}
