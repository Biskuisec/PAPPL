/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pappl;

import java.util.ArrayList;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.CoordinateSequence;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryCollection;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.LinearRing;
import com.vividsolutions.jts.geom.MultiLineString;
import com.vividsolutions.jts.geom.MultiPolygon;
import com.vividsolutions.jts.geom.Polygon;
import com.vividsolutions.jts.geom.impl.CoordinateArraySequence;
import com.vividsolutions.jts.operation.polygonize.Polygonizer;
import java.util.Collection;

/**
 *
 * @author avinesse
 */
public class Shadow {

    final int ORIGIN_X = 0;
    final int ORIGIN_Y = 0;
    private double altitude;
    private double azimuth;
    private SunPosition sun;

    /**
     * test constructor
     *
     * @param altitude
     * @param azimuth
     */
    public Shadow(double altitude, double azimuth) {
        this.altitude = altitude;
        this.azimuth = azimuth;
    }

    /**
     * Constructor
     *
     * @param sun1 : sun position
     * @param lat : latitude
     * @param lon : longitude
     */
    public Shadow(SunPosition sun1, double lat, double lon) {
        this.sun = sun1;
        this.altitude = SunPosition.getPosition(sun1.getDate(), lat, lon).get(0);
        this.azimuth = SunPosition.getPosition(sun1.getDate(), lat, lon).get(1);

    }

    /**
     * Calculation of the sun direction
     *
     * @param altitude
     * @param azimuth
     * @return sun coordinates
     */
    public Coordinate calculateDirection(double altitude, double azimuth) {
        Coordinate direction = new Coordinate();
        double length = 1.0 / (Math.tan(altitude));
        direction.x = -Math.cos(-azimuth) * length;
        direction.y = -Math.sin(-azimuth) * length;

        return direction;
    }

    /**
     * Calculating the coordinates of the shadow of a 2D point with a height
     * according to the position of the sun
     *
     * @param c : coordiantes of the point whose shade is calculated
     * @param h : height of the point
     * @param direction : sun direction
     * @return : shadow coordinate
     */
    public Coordinate projection(Coordinate c, double h, Coordinate direction) {
        Coordinate project = new Coordinate();
        // calculation of the coordinates
        project.x = c.x + direction.x * h;
        project.y = c.y + direction.y * h;
        return project;
    }

    /**
     * calculation of the shadow for the whole base
     *
     * @param polygon
     * @param height of the base
     * @param direction of the sun
     * @return
     */
    //Creating a GeometryFactory, a LisneString and a list of coordinates which is necessary for the creation of a polygon.
    public Polygon createShadow(Polygon polygon, double height, Coordinate direction) {

        GeometryFactory factory = polygon.getFactory();
        final LineString shell = polygon.getExteriorRing();
        // initializing the coordinates of the shadow
        Polygon[] shadows = new Polygon[shell.getNumPoints() - 1];

        Coordinate a = new Coordinate(0, 0);
        Coordinate b = new Coordinate(0, 0);
        // Shade is calculated from the edges of the polygon, a and b are the ends of the edges
        for (int i = 0; i < shell.getNumPoints() - 1; i++) {
            ArrayList<Coordinate> shadowPoints = new ArrayList<>();
            a.x = shell.getCoordinateN(i).x - ORIGIN_X;
            a.y = shell.getCoordinateN(i).y - ORIGIN_Y;
            b.x = shell.getCoordinateN(i + 1).x - ORIGIN_X;
            b.y = shell.getCoordinateN(i + 1).y - ORIGIN_Y;

            // Addition to the list of coordinates of the future shadow polygon 
            shadowPoints.add(new Coordinate(shell.getCoordinateN(i).x - ORIGIN_X, shell.getCoordinateN(i).y - ORIGIN_Y));
            shadowPoints.add(new Coordinate(projection(a, height, direction).x, projection(a, height, direction).y));
            shadowPoints.add(new Coordinate(projection(b, height, direction).x, projection(b, height, direction).y));
            shadowPoints.add(new Coordinate(shell.getCoordinateN(i + 1).x - ORIGIN_X, shell.getCoordinateN(i + 1).y - ORIGIN_Y));
            shadowPoints.add(new Coordinate(shell.getCoordinateN(i).x - ORIGIN_X, shell.getCoordinateN(i).y - ORIGIN_Y));

            //one polygon of shadow is created for each side of the base
            Polygon shadowPart = factory.createPolygon(new LinearRing(new CoordinateArraySequence(shadowPoints.toArray(new Coordinate[shadowPoints.size()])), factory), null);

            shadows[i] = shadowPart;

        }
        //geometry collection containing all the shadow polygons
        GeometryCollection geometryCollection = new GeometryCollection(shadows, factory);

        //union of all the polygons in the geometryCollection
        Polygon finalBase = (Polygon) geometryCollection.buffer(0);

        //exterior shell of the Polygon
       
        LineString finalGeom = (LineString) finalBase.getExteriorRing();
        
        LinearRing finalShell = new LinearRing(finalGeom.getCoordinateSequence(), factory);

        //adding the holes
        int nbOfHoles = polygon.getNumInteriorRing();
        LinearRing[] holes = new LinearRing[nbOfHoles];
        for (int i = 0; i < nbOfHoles; i++) {
            final LineString hole = polygon.getInteriorRingN(i);
            ArrayList<Coordinate> holePoints = new ArrayList<>();
            for (int j = 0; j < hole.getNumPoints(); j++) {
                a.x = hole.getCoordinateN(j).x - ORIGIN_X;
                a.y = hole.getCoordinateN(j).y - ORIGIN_Y;
 
                holePoints.add(new Coordinate(projection(a, height, direction).x, projection(a, height, direction).y));
                System.out.println(holePoints);

            }
            LinearRing holePart = new LinearRing(new CoordinateArraySequence(holePoints.toArray(new Coordinate[holePoints.size()])), factory);
            CoordinateSequence coordSeq = hole.getCoordinateSequence();
            LinearRing originalHole = new LinearRing(coordSeq,factory);
            Polygon holePartArea = new Polygon(holePart,null, factory);
            Polygon actualHole = new Polygon(originalHole,null,factory);
            Polygon shadowHole = (Polygon) actualHole.intersection(holePartArea);
            LineString outline = shadowHole.getExteriorRing();
            CoordinateSequence coordSeqHole = outline.getCoordinateSequence();
            LinearRing finalHole = new LinearRing(coordSeqHole,factory);
            holes[i] = finalHole;
        }

        Polygon finalShadow = new Polygon(finalShell, holes, factory);
        //System.out.println(finalShadow);
        return finalShadow;

    }
}
