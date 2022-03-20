import java.awt.Graphics2D;
import java.awt.Point;
import javax.swing.JPanel;

/*
 * Overview: classe che gestisce la traiettoria della palla
 */

public class Trajectory extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	double FinalAngle1=0;
	double FinalAngle2=0;
	double FinalAngle3=0;
	
	//punto finale della mano
	Point FinalPoint=new Point(0,0);
	Point InitialPoint;

	BallObject invisible_ball=new BallObject(50,300,0,0,0,0,9.8);
	Point p=new Point(150,500);
	
	//lunghezze del braccio, avambraccio e mano
	double l1;
	double l2;
	double l3;
	
	boolean first=false;
	Graphics2D graphics;
			
	public void setFinalPoint(Point p) {
		
		FinalPoint.x= (int) (p.x+l1 * Math.cos(Math.toRadians(FinalAngle1)));
		FinalPoint.y= (int) (p.y-l1 * Math.sin(Math.toRadians(FinalAngle1)));
		
		FinalPoint.x= (int) (FinalPoint.x+l2 * Math.cos(Math.toRadians(FinalAngle2)));
		FinalPoint.y= (int) (FinalPoint.y-l2 * Math.sin(Math.toRadians(FinalAngle2)));
		
		FinalPoint.x= (int) (FinalPoint.x+l3 * Math.cos(Math.toRadians(FinalAngle3)));
		FinalPoint.y= (int) (FinalPoint.y-l3 * Math.sin(Math.toRadians(FinalAngle3)));
		 
	}
		
	public Trajectory(){
		super();
		setLayout(null);
		
	}
}
