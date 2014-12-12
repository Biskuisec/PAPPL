/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pappl;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Polygon;
import java.util.ArrayList;

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

    public Shadow(SunPosition sun1, double lat, double lon) {
        this.sun = sun1;
        this.altitude = SunPosition.getPosition(sun1.getDate(),lat,lon).get(0);
        this.azimuth = SunPosition.getPosition(sun1.getDate(),lat,lon).get(1);
        
    }
    
    

    /**
     * On calcule la direction du soleil
     * @param altitude
     * @param azimuth
     * @return
     */
    public Coordinate calculateDirection(double altitude, double azimuth) {
        Coordinate direction = new Coordinate();
        double length = 1 / (Math.tan(altitude));
        direction.x = Math.cos(azimuth) * length;
        direction.y = Math.sin(azimuth) * length;

        return direction;
    }

    public Coordinate projection(Coordinate c, double h, Coordinate direction) {
        Coordinate project = new Coordinate();
        project.x = c.x + direction.x * h;
        project.y = c.y + direction.y * h;
        return project;
    }

    public void createShadow(ArrayList<Coordinate> base, double height, Coordinate direction) {
        Coordinate a = new Coordinate(0, 0);
        Coordinate b = new Coordinate(0, 0);
        Coordinate _a = new Coordinate(0, 0);
        Coordinate _b = new Coordinate(0, 0);

        for (int i = 0; i < base.size() - 1; i++) {
            a.x = base.get(i).x - ORIGIN_X;
            a.y = base.get(i).y - ORIGIN_Y;
            b.x = base.get(i + 1).x - ORIGIN_X;
            b.y = base.get(i + 1).y - ORIGIN_Y;

            _a.x = projection(a, height, direction).x;
            _a.y = projection(a, height, direction).y;
            _b.x = projection(b, height, direction).x;
            _b.y = projection(b, height, direction).y;

            System.out.println(_a);
            System.out.println(_b);





        }
    }
}
