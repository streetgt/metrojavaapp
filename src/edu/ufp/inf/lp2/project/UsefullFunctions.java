/*
 *
 * Projeto (projeto_lp2_1516) - Linguagens de Programaçao 2
 * 
 * edu.ufp.inf.lp2.project
 * UsefullFunctions.java
 *
 * 2015/2016 (c) Universidade Fernando Pessoa
 *
 */
package edu.ufp.inf.lp2.project;

/**
 *
 * @author tiagocardoso
 */
public final class UsefullFunctions {
    // DISTANCE HANDLING //
    private static final double _eQuatorialEarthRadius = 6378.1370D;
    private static final double _d2r = (Math.PI / 180D);
    // FINAL DISTANCE
    
    /**
     * Get Distance in KM between 2 coordinate points
     * 
     * @param lat1
     * @param long1
     * @param lat2
     * @param long2
     * @return 
     */
    public static double HaversineInKM(double lat1, double long1, double lat2, double long2)
    {
        double dlong = (long2 - long1) * _d2r;
        double dlat = (lat2 - lat1) * _d2r;
        double a = Math.pow(Math.sin(dlat / 2D), 2D) + Math.cos(lat1 * _d2r) * Math.cos(lat2 * _d2r) * Math.pow(Math.sin(dlong / 2D), 2D);
        double c = 2D * Math.atan2(Math.sqrt(a), Math.sqrt(1D - a));
        double d = _eQuatorialEarthRadius * c;

        return d;
    }
    
    /**
     * Get Distance in Meters between 2 coordinate points
     * @param lat1
     * @param long1
     * @param lat2
     * @param long2
     * @return 
     */
    public static double HaversineInM(double lat1, double long1, double lat2, double long2)
    {
        double dlong = (long2 - long1) * _d2r;
        double dlat = (lat2 - lat1) * _d2r;
        double a = Math.pow(Math.sin(dlat / 2D), 2D) + Math.cos(lat1 * _d2r) * Math.cos(lat2 * _d2r) * Math.pow(Math.sin(dlong / 2D), 2D);
        double c = 2D * Math.atan2(Math.sqrt(a), Math.sqrt(1D - a));
        double d = _eQuatorialEarthRadius * c;

        return d*1000;
    }
    
    /**
     * Get Time in minutes per meter
     * 
     * @param lat1
     * @param long1
     * @param lat2
     * @param long2
     * @return 
     */
    public static double CalculateTimeDistance(double distance, double speed)
    {
        return (distance / speed);
    }
    
    /**
     * Get Price per KM
     * 
     * @param lat1
     * @param long1
     * @param lat2
     * @param long2
     * @return 
     */
    public static double CalculatePriceDistance(double distance, double euro)
    {
        return ((distance / 1000) * euro);
    }
    
    /**
     * ARDUINO map() refactor
     * 
     * Re-maps a number from one range to another. 
     * That is, a value of fromLow would get mapped to toLow, a value of fromHigh to toHigh, values in-between to values in-between, etc.
     * 
     * @param x
     * @param in_min
     * @param in_max
     * @param out_min
     * @param out_max
     * @return 
     */
    public static int map(double x, double in_min, double in_max, int out_min, int out_max)
    {
        return (int) ((x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min);
    }
}
