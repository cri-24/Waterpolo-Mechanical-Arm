import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.util.ArrayList;

/*
 * Overview: classe che implementa i segmenti braccio, avambraccio e mano
 */

public class Link {
	
	private Polygon link;
	int xOriginal[];
	int yOriginal[];

	double l;
	
	double h = 0.01;
	double THETA = 0;
	double OMEGA = 0;
	double TIME = 0;
	public double FORCE;
	 
	public ArrayList<Double> TIME_VECT;
	public ArrayList<Double> THETA_VECT;
	public ArrayList<Double> OMEGA_VECT;

	double A = 0.05; //m
	double I = 5; //Kg*m^2
	double phi = 5; // gradi angolo
	double M; //massa KG
	double g = 9.81; //metri/s^2
	double b; //metri
	double r; //raggio
	
	private Point globalPosition;
	int length;
	double localAngle, globalAngle;
	
	public double f(double time, double OMEGA, double Force){
		
		//force in m*Kg/s^2 
		double c1= Force* A/I * Math.sin(Math.toRadians(phi)); 
		double c2= M/I * g * b; 
		
		if(THETA>=90) {
			return (c1 + c2 * Math.sin(Math.toRadians((THETA-90) + OMEGA * time * 360/2/Math.PI)));
		}
		else {
			return (c1 + c2 * Math.sin(Math.toRadians((90-THETA) + OMEGA * time * 360/2/Math.PI)));
		}
	}

	public double K1(){
		return f(TIME + h, OMEGA, FORCE);
	}

	public double K2(){
		return f(TIME + h/2, OMEGA+K1(), FORCE);
	}

	public double K3(){
		return f(TIME + h/2, OMEGA+K2(), FORCE); 
	}

	public double K4(){
		return f (TIME + h, OMEGA+K3(), FORCE);
	}

	public double omega_next_step(){
		return (OMEGA + h/6 * (K1() + 2*K2() + 2*K3() + K4()) );
	}
	
	public void SetLungh(double H, double k) {
		l=H*k;
	}
	
	public double GetLungh() {
		return l;
	}
	
	public void setB(double l, double k) {
		b=l*k;
	}
	
	public void setM(double m, double k) {
		M=m*k;
	}
	
	public void setI(double l) {
		I=M * (r * r + l * l)/4; 
	}
	
	public void setR(double l, double k) {
		r= l*k;
	}
	
	public void setA(double l, double k) {
		A=l*k;
	}
	
	
	
	//il braccio torna in posizione iniziale
		public void reset (Point p_arm) {
			globalPosition=p_arm;
			globalAngle=-90;
			this.onTranslate();
				
		}
	
	public Link( int globalX, int globalY, int length, int angle )
	{
		xOriginal = new int[]
		{ 0, 4, length - 4, length, length - 4, 4 };
		yOriginal = new int[]
		{ 0, 8, 8, 0, -8, -8 };
		link = new Polygon( xOriginal, yOriginal, yOriginal.length );

		globalPosition = new Point( globalX, globalY );
		this.length = length;
		this.localAngle = angle;
		
		this.onTranslate();
	}
	
	public void onTranslate()
	{
		for ( int i = 0; i < xOriginal.length; i++ )
		{

			double temp_x1 = xOriginal[i]
					* Math.cos( Math.toRadians( globalAngle ) ) - yOriginal[i]
					* Math.sin( Math.toRadians( globalAngle ) );
			double temp_y1 = xOriginal[i]
					* Math.sin( Math.toRadians( globalAngle ) ) + yOriginal[i]
					* Math.cos( Math.toRadians( globalAngle ) );

			link.xpoints[i] = (int) (temp_x1 + globalPosition.x);
			link.ypoints[i] = (int) (temp_y1 + globalPosition.y);
		}
	}
	
	public void setGlobalPosition( Point p, double angle )
	{
		this.globalPosition = p;
		this.globalAngle = - this.localAngle;
	}

	public double getAngle()
	{
		return this.localAngle;
	}
	
	public double getAngleRadians()
	{
		return Math.toRadians(this.localAngle);
	}

	//metodo che calcola il punto finale del braccio
	public Point getEndPointGlobal(){
		
		Point endpoint = new Point();
		
		endpoint.x = (int) (globalPosition.x + this.length
				* Math.cos( Math.toRadians( this.globalAngle )));
		endpoint.y = (int) (globalPosition.y + this.length
				* Math.sin( Math.toRadians( this.globalAngle ) ));
		return endpoint;
	}
	
	//metodo che disegna il braccio
	public void onDraw( Graphics g ){
		g.setColor(Color.PINK );
		g.fillPolygon(link);
	}

	
}