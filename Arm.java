
import java.awt.Graphics;
import java.awt.Point;

/*
 * Overview: classe che gestisce i tre segmenti: braccio, avambraccio e mano
 */

public class Arm {

	Link link1,link2,link3;
	double lung1, lung2, lung3;
	Point FinalPoint = new Point(0,0);
	Point p = new Point(150,500);
	
	//Costruttore
	public Arm(int globalX, int globalY, double H, double k1, double k2, double k3) {
	
		//In cm
		lung1=H*k1;
		lung2=H*k2;
		lung3=H*k3;
		
		link1 = new Link( globalX, globalY,ToPixel(lung1),90);
		link2 = new Link( globalX, globalY,ToPixel(lung2), 90 );
		link3 = new Link( globalX, globalY,ToPixel(lung3), 90 );
		
		this.onTranslate();
	}
	
	
	//100 pixel : 50 cm = x : cm
	public int ToPixel(double cm) {
		return (int)cm*100/50;
	}
	
	public void onTranslate()
	{
		link1.setGlobalPosition( new Point( 150, 500 ), 0);
		link1.onTranslate();
	
		link2.setGlobalPosition( link1.getEndPointGlobal(), link1.getAngle() );
		link2.onTranslate();
		
		link3.setGlobalPosition( link2.getEndPointGlobal(), link1.getAngle()+ link2.getAngle() );
		link3.onTranslate();
	
	}

	public void setAngle(double angle, Link l) {
		l.localAngle=(int)angle;
	}
	
	public Link getLink1() {
		return link1;
	}
	public Link getLink2() {
		return link2;
	}
	public Link getLink3() {
		return link3;
	}
	
	public void onDraw( Graphics g )
	{
		link1.onDraw( g );
		link2.onDraw( g );
		link3.onDraw( g );
		
	}
	
	public double getAngle1() {
		return link1.localAngle;
	}
	public double getAngle2() {
		return link2.localAngle;
	}
	public double getAngle3() {
		return link3.localAngle;
	}
	
	public Point getFinalPoint() {
	
		FinalPoint.x= (int) (p.x+ ToPixel(lung1) * Math.cos(Math.toRadians(link1.localAngle)));
		FinalPoint.y= (int) (p.y-ToPixel(lung1) * Math.sin(Math.toRadians(link1.localAngle)));
		
		FinalPoint.x= (int) (FinalPoint.x+ ToPixel(lung2) * Math.cos(Math.toRadians(link2.localAngle)));
		FinalPoint.y= (int) (FinalPoint.y-ToPixel(lung2) * Math.sin(Math.toRadians(link2.localAngle)));
		
		FinalPoint.x= (int) (FinalPoint.x+ ToPixel(lung3) * Math.cos(Math.toRadians(link3.localAngle)));
		FinalPoint.y= (int) (FinalPoint.y- ToPixel(lung3) * Math.sin(Math.toRadians(link3.localAngle)));
		
		return FinalPoint;
	}


	
}
