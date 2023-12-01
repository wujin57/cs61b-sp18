import java.util.Scanner;
import java.io.File;
import java.io.IOException;
public class NBody {
	
	public static double readRadius(String fileName) {
		double radius = 0.0;
		try {
			Scanner scanner = new Scanner(new File(fileName));
			int numberOfPlanets = scanner.nextInt();
			radius = scanner.nextDouble();
			scanner.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return radius;
	}
	
	public static Planet[]  readPlanets(String fileName) {
		In in = new In(fileName);
		int numberOfPlanets = in.readInt();
		double radius = in.readDouble();
		Planet[] planets = new Planet[numberOfPlanets];
		
		for (int i = 0; i < numberOfPlanets; i++) {
			double xPos = in.readDouble();
            		double yPos = in.readDouble();
            		double xVel = in.readDouble();
            		double yVel = in.readDouble();
            		double mass = in.readDouble();
            		String imgFileName = in.readString();
           		planets[i] = new Planet(xPos, yPos, xVel, yVel, mass, imgFileName);	
		}
		return planets;
	}
	
	public static void main(String[] args) {
		StdAudio.play("audio/2001.mid");
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		double radius = readRadius(filename);
		Planet[] planets = readPlanets(filename);
		StdDraw.setScale(-radius, radius);
		StdDraw.clear();
		StdDraw.picture(0, 0,  "images/starfield.jpg");
		for (Planet p : planets) {
			p.draw();
		}
		StdDraw.enableDoubleBuffering();	
		double time = 0;
		
		while (time <= T) {
			double[] xForce = new double[planets.length];
			double[] yForce = new double[planets.length];

			for(int i = 0; i < planets.length; i++) {
				planets[i].update(dt, xForce[i], yForce[i]);
			}
			
			StdDraw.setScale(-radius, radius);
			StdDraw.clear();
			StdDraw.picture(0, 0, "images/starfield.jpg");
			
			for(Planet planet : planets) {
				planet.draw();
			}

			StdDraw.show();
			StdDraw.pause(10);
			time += dt;
		
		}
		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < planets.length; i++) {
		StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
   		planets[i].xxPos, planets[i].yyPos, planets[i].xxVel, planets[i].yyVel, planets[i].mass, planets[i].imgFileName);	
		}		
	}	
}
