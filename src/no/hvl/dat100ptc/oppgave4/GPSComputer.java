package no.hvl.dat100ptc.oppgave4;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave2.GPSData;
import no.hvl.dat100ptc.oppgave2.GPSDataConverter;
import no.hvl.dat100ptc.oppgave2.GPSDataFileReader;
import no.hvl.dat100ptc.oppgave3.GPSUtils;

public class GPSComputer {
	
	private GPSPoint[] gpspoints;
	
	public GPSComputer(String filename) {

		GPSData gpsdata = GPSDataFileReader.readGPSFile(filename);
		gpspoints = gpsdata.getGPSPoints();

	}

	public GPSComputer(GPSPoint[] gpspoints) {
		this.gpspoints = gpspoints;
	}
	
	public GPSPoint[] getGPSPoints() {
		return this.gpspoints;
	}
	
	// beregn total distances (i meter)
	public double totalDistance() {

		double dist = 0;
		
int i = 0;
for(i=0; i<gpspoints.length-1; i++) {
	
	
	dist += GPSUtils.distance(gpspoints[i], gpspoints[i+1]);
}
return dist;
}

	// beregn totale høydemeter (i meter)
	public double totalElevation() {

		double elevation = 0;

		elevation = gpspoints[gpspoints.length-1].getElevation();
			
		
return elevation;
	}

	// beregn total tiden for hele turen (i sekunder)
	public int totalTime() {

int time = 0;

time = gpspoints[gpspoints.length-1].getTime() - gpspoints[0].getTime();
return time;

	}
		
	// beregn gjennomsnitshastighets mellom hver av gps punktene

	public double[] speeds() {
	double [] averageSpeed = new double [gpspoints.length-1];
	for (int i = 0; i<gpspoints.length -1 ; i++) {
		averageSpeed[i] = GPSUtils.speed(gpspoints[i], gpspoints[i+1]);
		
	
	}
	return averageSpeed;
		
 
	}
	
	public double maxSpeed() {
		
		double maxspeed = 0;
		int i = 0;
		for ( i = 0; i<gpspoints.length -1; i++) 
		
			if(GPSUtils.speed(gpspoints[i], gpspoints[i+1])> maxspeed)
					maxspeed = GPSUtils.speed(gpspoints[i], gpspoints[i+1]);
					
		return maxspeed;
	}

	public double averageSpeed() {

		double average = 0;
		 average = totalDistance()/totalTime();
		return average *3.6;
	}

	/*
	 * bicycling, <10 mph, leisure, to work or for pleasure 4.0 bicycling,
	 * general 8.0 bicycling, 10-11.9 mph, leisure, slow, light effort 6.0
	 * bicycling, 12-13.9 mph, leisure, moderate effort 8.0 bicycling, 14-15.9
	 * mph, racing or leisure, fast, vigorous effort 10.0 bicycling, 16-19 mph,
	 * racing/not drafting or >19 mph drafting, very fast, racing general 12.0
	 * bicycling, >20 mph, racing, not drafting 16.0
	 */

	// conversion factor m/s to miles per hour
	public static double MS = 2.236936;

	// beregn kcal gitt weight og tid der kjøres med en gitt hastighet
	public double kcal(double weight, int secs, double speed) {

		double kcal;

		// MET: Metabolic equivalent of task angir (kcal x kg-1 x h-1)
		double met = 0;		
		double speedmph = speed * MS;
double hours = secs/3600.0;
if(speedmph<10) {
	met=4.0;
}
else if (speedmph<=10 && speedmph>12) {
	met = 6.0;
}
else if (speedmph<=12 && speedmph>14) {
	met = 8.0;
}	
else if (speedmph<=14 && speedmph>16) {
	met = 10.0;
}
else {
	met = 16.0;
}
kcal = weight * hours * met;

return kcal;
	}
	

	public double totalKcal(double weight) {

		double totalkcal = 0;
	for(int i = 0; i<gpspoints.length-1; i++) {
	totalkcal = totalkcal + kcal(weight, gpspoints[i+1].getTime() - gpspoints[i].getTime(), GPSUtils.speed(gpspoints[i], gpspoints[i+1]));

		
	}
	
		
		return totalkcal;
	}
	
	private static double WEIGHT = 80.0;
	
	public void displayStatistics() {


		System.out.println("==========================================");
        System.out.println("Total time     :" + GPSUtils.formatTime(totalTime()));
        System.out.println("Total distance :" + GPSUtils.formatDouble (totalDistance()/1000) + " km");
        System.out.println("Total elevation:" + totalElevation() + "m");
        System.out.println("Max speed      :" +  GPSUtils.formatDouble(maxSpeed()) + " km/t");
        System.out.println("Average speed  :" +  GPSUtils.formatDouble(averageSpeed()) + " km/t");
        System.out.println("Energy         :" +   GPSUtils.formatDouble(totalKcal(WEIGHT)));
        System.out.println("==========================================");

	}

}
