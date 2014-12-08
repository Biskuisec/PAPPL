/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pappl;

import com.vividsolutions.jts.geom.Coordinate;

/**
 *
 * @author avinesse
 */
public class Shadow {
    
    final static int ORIGIN_X =0;
    final static int ORIGIN_Y =0;
    private Coordinate direction;
    private double altitude;
    private double azimuth;

    /**
     * Constructeur de test
     * @param direction
     * @param altitude
     * @param azimuth 
     */
    public Shadow(Coordinate direction, double altitude, double azimuth) {
        this.direction = direction;
        this.altitude = altitude;
        this.azimuth = azimuth;
    }
    
    
    public Coordinate calculateDirection (double altitude, double azimuth)
    {
        Coordinate direction = new Coordinate ();
        double length = 1/(Math.tan(altitude));
        direction.x= Math.cos(azimuth)*length;
        direction.y= Math.sin(azimuth)*length;
        
        return direction;
    }

    
   public Coordinate projection (Coordinate c, double h)
   {
       Coordinate project = new Coordinate ();
       project.x = c.x + direction.x*height; 
       project.y = c.y + direction.y*height;
       return project;
   }
        
        
        int height = 5;
        
        Coordinate a = new Coordinate (0,0);
        Coordinate b = new Coordinate (0,0);
        Coordinate _a = new Coordinate (0,0);
        Coordinate _b = new Coordinate (0,0);
        
        
        
        //System.out.println(carre.getCoordinate());        
        //System.out.println(carre.getCoordinate());
        
        


for (int i = 0; i < carre.size()-1; i ++) {
a.x = carre.get(i).x-ORIGIN_X;
a.y = carre.get(i).y-ORIGIN_Y;

b.x = carre.get(i+1).x-ORIGIN_X;
b.y = carre.get(i+1).y-ORIGIN_Y;



    System.out.println(_a);
    System.out.println(_b);
 
}
