/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pappl;

import java.util.ArrayList;
import java.util.Date;


/**
 * Code from https://github.com/mncaudill/SunCalc-Java/blob/master/src/main/java/com/nolancaudill/suncalc/SunCalc.java
 * Based on http://aa.quae.nl/en/reken/zonpositie.html
 * @author Bertille
 */
public class SunPosition {

    private Date date;

    public SunPosition(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    /* Constants */
    private final static double rad = Math.PI / 180;
    private final static double dayMs = 1000 * 60 * 60 * 24;
    private final static double J1970 = 2440588;
    private final static double J2000 = 2451545;
    private final static double M0 = rad * 357.5291;
    private final static double M1 = rad * 0.98560028;
    private final static double J0 = 0.0009;
    private final static double J1 = 0.0053;
    private final static double J2 = -0.0069;
    private final static double C1 = rad * 1.9148;
    private final static double C2 = rad * 0.0200;
    private final static double C3 = rad * 0.0003;
    private final static double P = rad * 102.9372;
    private final static double e = rad * 23.45;
    private final static double th0 = rad * 280.1600;
    private final static double th1 = rad * 360.9856235;

    private static double dateToJulianDate(Date date) {
        return date.getTime() / dayMs - 0.5 + J1970;
    }

   /* private static Date julianDateToDate(double j) {
        return new Date(Math.round((j + 0.5 - J1970) * dayMs));
    }*/

	// general sun calculations
    /*private static long getJulianCycle(double J, double lw) {
        return Math.round(J - J2000 - J0 - lw / (2 * Math.PI));
    }*/

    private static double getSolarMeanAnomaly(double Js) {
        return M0 + M1 * (Js - J2000);
    }

    private static double getEquationOfCenter(double M) {
        return C1 * Math.sin(M) + C2 * Math.sin(2 * M) + C3 * Math.sin(3 * M);
    }

    private static double getEclipticLongitude(double M, double C) {
        return M + P + C + Math.PI;
    }

    private static double getSunDeclination(double Ls) {
        return Math.asin(Math.sin(Ls) * Math.sin(e));
    }

	/*// calculations for sun times
    private static double getApproxTransit(double Ht, double lw, double n) {
        return J2000 + J0 + (Ht + lw) / (2 * Math.PI) + n;
    }

    private static double getSolarTransit(double Js, double M, double Ls) {
        return Js + (J1 * Math.sin(M)) + (J2 * Math.sin(2 * Ls));
    }

    private static double getHourAngle(double h, double phi, double d) {
        return Math.acos((Math.sin(h) - Math.sin(phi) * Math.sin(d))
                / (Math.cos(phi) * Math.cos(d)));
    }*/
    
    // calculations for sun position
    private static double getRightAscension(double Ls) {
        return Math.atan2(Math.sin(Ls) * Math.cos(e), Math.cos(Ls));
    }

    private static double getSiderealTime(double J, double lw) {
        return th0 + th1 * (J - J2000) - lw;
    }
    
    //azimuth calculé à partir du Sud ?
    private static double getAzimuth(double H, double phi, double d) {
        return Math.atan2(Math.sin(H),
                Math.cos(H) * Math.sin(phi) - Math.tan(d) * Math.cos(phi));
    }

    private static double getAltitude(double H, double phi, double d) {
        return Math.asin(Math.sin(phi) * Math.sin(d) + Math.cos(phi)
                * Math.cos(d) * Math.cos(H));
    }

    

	public static ArrayList<Double> getPosition(Date date, double lat,
            double lng) {
        double lw = rad * -lng;
        double phi = rad * lat;
        double J = dateToJulianDate(date);
        double M = getSolarMeanAnomaly(J);
        double C = getEquationOfCenter(M);
        double Ls = getEclipticLongitude(M, C);
        double d = getSunDeclination(Ls);
        double a = getRightAscension(Ls);
        double th = getSiderealTime(J, lw);
        double H = th - a;

        ArrayList<Double> ret = new ArrayList<>();
        ret.add(getAltitude(H, phi, d));
        ret.add(getAzimuth(H, phi, d));
        
        return ret;
    }

    /*
     * public static void main(String[] args) { Map<String, Double> res =
     * SunCalc.getPosition(new Date(1330560000), 33.0, -120.1);
     * System.out.println("azimuth: " + Double.toString(res.get("azimuth")));
     * System.out.println("altitude: " + Double.toString(res.get("altitude")));
     * 
     * Map<String, Date> res2 = SunCalc.getTimes(new Date(1330560000), 33.0,
     * -120.1); String[] res2_keys = res2.keySet().toArray(new String[0]);
     * 
     * for(int i = 0; i < res2_keys.length; i++) {
     * System.out.println(res2_keys[i] + " " + res2.get(res2_keys[i])); } }
     */
}
