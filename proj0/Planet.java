public class Planet {
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;

	public Planet(double xP, double yP, double xV,	double yV, double m, String img) {
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}
	
	public Planet(Planet b) {
		this.xxPos = b.xxPos;
 		this.yyPos = b.yyPos;
       	 	this.xxVel = b.xxVel;
	        this.yyVel = b.yyVel;
       		this.mass = b.mass;
       		this.imgFileName = b.imgFileName;
	}

	public double calcDistance(Planet b) {
		double dx = this.xxPos - b.xxPos;       
                double dy = this.yyPos - b.yyPos;
                double r = Math.sqrt( dx * dx + dy * dy);
		return r;
	}

	public double calcForceExertedBy(Planet b) {
		double r = this.calcDistance(b);
		double G = 6.67e-11;
		return G * this.mass * b.mass/ (r * r);	
 	
	} 
	private boolean equals(Planet b) {
		if (this == b) return true;
		if (xxPos == b.xxPos && yyPos == b.yyPos && xxVel == b.xxVel
		    && yyVel == b.yyVel && mass == b.mass && imgFileName == b.imgFileName) return true;
		return false;
	}

	public double calcForceExertedByX(Planet b) {
		
		return calcForceExertedBy(b) * (b.xxPos - xxPos)/calcDistance(b);
	}

	public double calcForceExertedByY(Planet b) {
                return calcForceExertedBy(b) * (b.yyPos - yyPos)/calcDistance(b);

        }

	public double calcNetForceExertedByX(Planet[] allPlanets) {
		double result = 0;
		for (int i = 0; i < allPlanets.length; i++) {
			if(!equals(allPlanets[i])) 				
				result += calcForceExertedByX(allPlanets[i]);
		}
		return result;
 
	}

	public double calcNetForceExertedByY(Planet[] allPlanets) {
                double result = 0;
                for (int i = 0; i < allPlanets.length; i++) {
                	if(!equals(allPlanets[i])) 	
				result += calcForceExertedByY(allPlanets[i]);
                
                }
                return result;

        }
	
	public void update(double dt, double fX, double fY) {
		double ax = fX / mass;
		double ay = fY / mass;
		xxVel += ax * dt;
		yyVel += ay * dt;
		xxPos += xxVel * dt;
		yyPos += yyVel * dt;    	
	}
	
	public void draw() {
		StdDraw.picture(xxPos, yyPos,"images/" +  imgFileName);
}
		
}
