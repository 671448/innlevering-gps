package no.hvl.dat100ptc.oppgave2;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import static java.lang.Integer.*;

public class GPSDataConverter {

	// konverter tidsinformasjon i gps data punkt til antall sekunder fra midnatt
	// dvs. ignorer information om dato og omregn tidspunkt til sekunder
	// Eksempel - tidsinformasjon (som String): 2017-08-13T08:52:26.000Z
    // skal omregnes til sekunder (som int): 8 * 60 * 60 + 52 * 60 + 26 
	
	private static int TIME_STARTINDEX = 11; // posisjon for start av tidspunkt i timestr

	public static int toSeconds(String timestr) {
		
		int secs;
        int hr, min, sec;
        String hrS, minS, secS;

        hrS = timestr.substring(11,13);
        minS = timestr.substring(14,16);
        secS = timestr.substring(17,19);
        hr = parseInt(hrS);
        min = parseInt(minS);
        sec = parseInt(secS);

        secs = hr * 60 *60 + min*60 + sec;
        return secs;
		
	}

	public static GPSPoint convert(String timeStr, String latitudeStr, String longitudeStr, String elevationStr) {

	int time = toSeconds(timeStr);
	double latitude = Double.parseDouble(latitudeStr);
	double longitude = Double.parseDouble(longitudeStr);
	double elevation = Double.parseDouble(elevationStr);
	
	GPSPoint converted = new GPSPoint(time,latitude,longitude,elevation);
	
	return converted;
	}
	
}
