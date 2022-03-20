import java.awt.*;


/*
 * Overview: classe che implementa l'oggetto palla
 */
class BallObject {
	
	//componenti X e Y della palla
	double dblX; 
	double dblY; 
	//componenti dell'accelerazione della palla in stats
	double dblAccx; 
	double dblAccy; 
	//altezza
	double dblH; 
	//componenti della velocità della palla
	double dblVx; 
	double dblVy;
	//componenti X e Y i-esime
	double dblXi ; 
	double dblYi;

	double dblTimeCount=0; 
	double dblTimeCurrent=0;
	
	public double getX(){ 
		return dblX; 
	}
	public double getY(){ 
		return dblY; 
	}
	public double getAx() { 
		return dblAccx;
	}
	public double getAy() { 
		return dblAccy;
	}

	//metodo per il movimento della palla 
	public void move() {
		
	
		dblTimeCount= dblTimeCount+dblTimeCurrent;
		if(dblTimeCount==dblTimeCurrent){  
			dblY= dblYi+dblVy*dblTimeCurrent;
			dblX= dblXi+dblVx*dblTimeCurrent;
		}
		else { 
			
			dblVy=dblVy+ToPixel(dblAccy*100)*dblTimeCurrent; 
			dblVx=dblVx+ToPixel(dblAccx*100)*dblTimeCurrent; 
			
			dblY= dblY+dblVy*dblTimeCurrent;
			dblX= dblX+dblVx*dblTimeCurrent;
			
		}
		
	}
	
	
	public void draw (Graphics2D g) {
		
		g.setColor(Color.YELLOW);
		g.fillOval((int)(dblX),(int)(dblY),ToPixel(20),ToPixel(20));
		
	} 
	
	public void invisible_draw (Graphics2D g) {
		
		g.setColor(Color.BLACK);
		g.fillOval((int)(dblX),(int)(dblY),ToPixel(2),ToPixel(2));
	} 
	
	public void reset (double x, double y) {
		
		dblX =x; 
		dblY =y; 
		dblTimeCount=0;
		
	}
	
	//metodo che cambia le coordinate della palla in base al braccio
	public void changePoint( double X, double Y, double angle) {
		
		dblX = X; 
		dblY = Y;
		dblXi=dblX;
		dblYi=dblY;
	}
	
	//Costruttore dell'oggetto ball
	public BallObject ( double X, double Y, double h,double vx, double vy, double accx, double accy ) { 
		dblX = X-10; 
		dblY = Y-10; 
		dblH = h; 
		dblAccx = accx; 
		dblAccy = accy; 
		dblVx=vx;
		dblVy=vy;
		dblXi=dblX;
		dblYi=dblY;
		dblTimeCurrent= 0.01;
	}
	
	//100 pixel : 50 cm = x : cm
	public int ToPixel(double cm) {
		return (int)cm*100/50;
	}
	//100 pixel : 0,5 m = pixel : x
	public double ToMeters(double pixel) {
		return pixel * 0.5/100;
	}
}