package no.hvl.dat100ptc.oppgave5;

import javax.swing.JOptionPane;

import easygraphics.EasyGraphics;
import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave2.GPSData;
import no.hvl.dat100ptc.oppgave2.GPSDataFileReader;
import no.hvl.dat100ptc.oppgave3.GPSUtils;
import no.hvl.dat100ptc.oppgave4.GPSComputer;

public class ShowSpeed extends EasyGraphics {
			
	private static final int MARGIN = 50;
	private static final int BARHEIGHT = 200; // assume no speed above 200 km/t

	private GPSComputer gpscomputer;
	private GPSPoint[] gpspoints;
	
	public ShowSpeed() {

		String filename = JOptionPane.showInputDialog("GPS data filnavn: ");
		gpscomputer = new GPSComputer(filename);

		gpspoints = gpscomputer.getGPSPoints();
		
	}
	
	// read in the files and draw into using EasyGraphics
	public static void main(String[] args) {
		launch(args);
	}

	public void run() {

		int N = gpspoints.length-1; // number of data points
		
		makeWindow("Speed profile", 2*MARGIN + 2 * N, 2 * MARGIN + BARHEIGHT);
		
		showSpeedProfile(MARGIN + BARHEIGHT,N);
	}
	
	public void showSpeedProfile(int ybase, int N) {

		// get segments speeds from the GPS computer object		
		
	double[] speeds = gpscomputer.speeds();
	double snitt = 0;
	int linje = 0;
	for (int i = 0; i<speeds.length - 1; i++) {
 linje = linje + (int)speeds[i];
 }
	
		linje = linje/speeds.length;
	

	int x = MARGIN,y;
	
	
	
	int end = 0;
int speed = 0;
		for(int i = 0; i < gpspoints.length - 1; i++) {
	           
	        speed = (int) speeds[i];
	        	
	        setColor(0,0,255);
	        drawLine(x+i*2, ybase, x+i*2,  ybase-speed );
	          end +=x+i*2;  
	        }
		setColor(0,255,4);
		drawLine(x,ybase -linje,end, ybase- linje);
	}
}
