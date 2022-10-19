package no.hvl.dat100ptc.oppgave3;

import static java.lang.Math.*;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class GPSUtils {

	public static double findMax(double[] da) {

		double max; 
		
		max = da[0];
		
		for (double d : da) {
			if (d > max) {
				max = d;
			}
		}
		
		return max;
	}

	public static double findMin(double[] da) {

		double min;

	min = da[0];
		
		for (double d : da) {
			if (d < min) {
				min = d;

	}
		}
		return min;
	}

	public static double[] getLatitudes(GPSPoint[] gpspoints) {

		double[] latitude = new double[gpspoints.length];

        for (int i = 0; i < gpspoints.length; i++) {
            latitude[i] = gpspoints[i].getLatitude();

        }return latitude;
	}

	public static double[] getLongitudes(GPSPoint[] gpspoints) {

		double[] longitudes = new double[gpspoints.length];

        for (int i = 0; i < gpspoints.length; i++) {
            longitudes[i] = gpspoints[i].getLongitude();

        }return longitudes;
	}

	private static int R = 6371000; // jordens radius

	public static double distance(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		double lat1 = gpspoint1.getLatitude();
		double lat2 = gpspoint2.getLatitude();
		double lng1 = gpspoint1.getLongitude();
		double lng2 = gpspoint2.getLongitude();
	
	    double dLat = Math.toRadians(lat2-lat1);
	    double dLng = Math.toRadians(lng2-lng1);
	    double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
	               Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
	               Math.sin(dLng/2) * Math.sin(dLng/2);
	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
	    float dist = (float) (R * c);

	    return dist;
	}

	public static double speed(GPSPoint gpspoint1, GPSPoint gpspoint2) {
		double time1 = gpspoint1.getTime();
		double time2 = gpspoint2.getTime();
		
		double secs = time2-time1;
		
		double speed;
 double distanse = distance(gpspoint1, gpspoint2);
speed = distanse/secs;
speed = speed *3.6;
	return speed;
	}

	public static String formatTime(int secs) {

		String TIMESEP = ":";

        int timer = secs / (60 * 60);
        secs = (secs - timer * 3600);
        int min = secs / 60;
        secs = secs - min * 60;

        String timestr = String.format("%02d", timer);
        String minutt = String.format("%02d", min);
        String sek = String.format("%02d", secs);
        String totalt = String.format("%" + (10) + "s", timestr + TIMESEP + minutt + TIMESEP + sek);

        return totalt;
		    
		    
		    
	}
	private static int TEXTWIDTH = 10;

	public static String formatDouble(double d) {

		String str;
        double avrundet = Math.round(d*100)/100.00;
        str = "      " + avrundet;
        return str;
	}
}
