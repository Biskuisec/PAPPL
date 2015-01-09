/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pappl;

import java.util.ArrayList;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.CoordinateSequence;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.LinearRing;
import com.vividsolutions.jts.geom.Polygon;
import com.vividsolutions.jts.geom.impl.CoordinateArraySequence;

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
     * Constructeur de test
     *
     * @param altitude
     * @param azimuth
     */
    public Shadow(double altitude, double azimuth) {
        this.altitude = altitude;
        this.azimuth = azimuth;
    }

    /**
     * Constructeur
     *
     * @param sun1 : position du soleil
     * @param lat : latitude
     * @param lon : longitude
     */
    public Shadow(SunPosition sun1, double lat, double lon) {
        this.sun = sun1;
        this.altitude = SunPosition.getPosition(sun1.getDate(), lat, lon).get(0);
        this.azimuth = SunPosition.getPosition(sun1.getDate(), lat, lon).get(1);

    }

    /**
     * On calcule la direction du soleil
     *
     * @param altitude
     * @param azimuth
     * @return les coordonnées du soleil
     */
    public Coordinate calculateDirection(double altitude, double azimuth) {
        Coordinate direction = new Coordinate();
        double length = 1.0 / (Math.tan(altitude));
        direction.x = Math.cos(azimuth) * length;
        direction.y = Math.sin(azimuth) * length;

        return direction;
    }

    /**
     * Calcul des coordonnées de l'ombre d'un point 2D avec une hauteur en
     * fonction de la position du soleil
     *
     * @param c : coordonnées du point dont il faut calculer l'ombre
     * @param h : hauteur de ce point
     * @param direction : direction du soleil
     * @return : les coordonnées calculées du point de l'ombre
     */
    public Coordinate projection(Coordinate c, double h, Coordinate direction) {
        Coordinate project = new Coordinate();
        // calcul des coordonnées
        project.x = c.x + direction.x * h;
        project.y = c.y + direction.y * h;
        return project;
    }

    /**
     * Calcul de l'ombre pour une base toute entière
     *
     * @param base dont il faut calculer l'ombre
     * @param height : hauteur de la base
     * @param direction du soleil
     */
    public Polygon createShadow(Polygon polygon, double height, Coordinate direction) {

        GeometryFactory factory = polygon.getFactory();
        final LineString shell = polygon.getExteriorRing();
        ArrayList<Coordinate> shadowPoints = new ArrayList<>();

        Coordinate a = new Coordinate(0, 0);
        Coordinate b = new Coordinate(0, 0);

        for (int i = 0; i < shell.getNumPoints() - 1; i++) {
            a.x = shell.getCoordinateN(i).x - ORIGIN_X;
            a.y = shell.getCoordinateN(i).y - ORIGIN_Y;
            b.x = shell.getCoordinateN(i + 1).x - ORIGIN_X;
            b.y = shell.getCoordinateN(i + 1).y - ORIGIN_Y;



            shadowPoints.add(new Coordinate(projection(a, height, direction).x, projection(a, height, direction).y));
            if (i == shell.getNumPoints() - 2) {
                shadowPoints.add(new Coordinate(projection(b, height, direction).x, projection(b, height, direction).y));
            }


        }

        Polygon shadowBuilding = factory.createPolygon(new LinearRing(new CoordinateArraySequence(shadowPoints.toArray(new Coordinate[shadowPoints.size()])), factory), null);
        return shadowBuilding;
    }
}
