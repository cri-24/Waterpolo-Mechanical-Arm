import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.geom.QuadCurve2D;

/*
 * Overview: classe che gestisce il disegno e il movimento dei componenti dell'interfaccia
 */

public class JAmnimationPanel extends JPanel { 

		private static final long serialVersionUID = 1L;
		boolean blnfirst=true; 
		boolean first=true;
		int intHeight; 
		int intWidth;
		double H;
		double W;
		
		boolean traj=false;
		boolean move_NO=true;
		boolean reset_ball=false;
		Trajectory traiettoria;
		int Angle3final=90;
		double Angle1=90;
		double Angle2=90;
		double Angle3=90;
		
		int ctrlx1=150;
		int ctrly1=300;
		
		int ctrlx2=240;
		int ctrly2=350;
		
		JLabel lblVelocita;
		private Image img_sfondo=new ImageIcon(this.getClass().getResource("/sfondo2.jpg")).getImage();
	
		//coordinate del punto finale del braccio
		Point p= new Point(150,500);
		Point puntoArrivo=new Point();
	
		//Braccio
		Arm arm;
		Graphics2D graphics;

		//creazione della palla
		BallObject ball = new BallObject (50,300,0,0,0,0,9.8);
		Color acqua = new Color(105, 130, 220);
		
		//metodo che disegna l'interfaccia
		public void paintComponent (Graphics g)  {
		
			Graphics2D graphics = (Graphics2D) g; 
			this.graphics=graphics;
			
			
			g.drawImage(img_sfondo, 0, 0, this);
			
			//acqua
			g.setColor(acqua);
			
			g.fillRect(0, 500,1280, 30);
			
			
			if(arm!=null) {	
				
				//disegno del braccio
				arm.onDraw(graphics);
				graphics = (Graphics2D) g;
			
				if(!blnfirst && arm.getAngle3()<=Angle3final && arm.getAngle3()!=0) { 
					//disegno e movimento della palla
					ball.draw(graphics);
					ball.move();	
				}
				else if(reset_ball==true) {
					ball.draw(graphics);
					reset_ball=false;
				}
				
				else {
					p=arm.getFinalPoint();
					ball.changePoint(p.x ,p.y,arm.getAngle3());
					ball.draw(graphics);
					g.fillRect((int)ball.dblX,(int)ball.dblY,2,2);
				}
				blnfirst = false;
				}
			
			//disegno della traiettoria
			if(traj==true) {
				if(Angle3final!=0 ) { 
					
			
					traiettoria.setFinalPoint(traiettoria.p);
					
					ctrlx1=(traiettoria.InitialPoint.x+traiettoria.FinalPoint.x)/2;
					ctrly1=(traiettoria.InitialPoint.y+traiettoria.FinalPoint.y)/2-10;
					
					graphics.setStroke((Stroke) new BasicStroke(4.0f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER, 10.0f, new float[] {16.0f, 20.0f}, 0.0f ));
					graphics.setColor(Color.pink);
					QuadCurve2D graph = new QuadCurve2D.Float();
					graph.setCurve(traiettoria.InitialPoint.x,traiettoria.InitialPoint.y, ctrlx1, ctrly1, traiettoria.FinalPoint.x, traiettoria.FinalPoint.y);
					graphics.draw(graph);
					graphics.setColor(Color.blue);
					graphics.fillOval(traiettoria.InitialPoint.x,traiettoria.InitialPoint.y, 20, 20);
					traiettoria.invisible_ball.changePoint(traiettoria.FinalPoint.x, traiettoria.FinalPoint.y, Angle3final);
					
					while(move_NO==true && traiettoria.invisible_ball.getY()<485) {
						puntoArrivo.x=(int) traiettoria.invisible_ball.dblX;
						puntoArrivo.y=(int) traiettoria.invisible_ball.dblY;
						traiettoria.invisible_ball.invisible_draw(graphics);
						traiettoria.invisible_ball.move();
					}
					
					move_NO=false;
			
					graphics.setColor(Color.red);
					graphics.fillOval(puntoArrivo.x, puntoArrivo.y, 20, 20);			
				}
			}
			
		}
		public void create_arm(double H) {
			arm = new Arm (0, 500, H, 0.186, 0.146, 0.108);
			/*arm.getLink1().THETA=91;
			arm.getLink2().THETA=91;
			arm.getLink3().THETA=91;*/
			
		}
			
		public void setSize( int intH, int intW) { 
			intHeight = intH; 
			intWidth = intW;
		}
		
		public void drawTraj() {
			traiettoria = new Trajectory();
			
			this.add(traiettoria);
			
			if(arm.getLink1().THETA_VECT.get(arm.getLink1().THETA_VECT.size()-1)<30) {
				traiettoria.FinalAngle1=30;
			}
			else
				traiettoria.FinalAngle1=arm.getLink1().THETA_VECT.get(arm.getLink1().THETA_VECT.size()-1);
			
			if(arm.getLink2().THETA_VECT.get(arm.getLink2().THETA_VECT.size()-1)<30) {
				traiettoria.FinalAngle2=30;
			}
			else
				traiettoria.FinalAngle2=arm.getLink2().THETA_VECT.get(arm.getLink2().THETA_VECT.size()-1);

			
			traiettoria.FinalAngle3=arm.getLink3().THETA_VECT.get(arm.getLink3().THETA_VECT.size()-1);
			
			traiettoria.l1=arm.ToPixel(arm.lung1);
			traiettoria.l2=arm.ToPixel(arm.lung2);
			traiettoria.l3=arm.ToPixel(arm.lung3);
			traiettoria.first=true;
			
			
			
		
				
			traiettoria.InitialPoint=arm.getFinalPoint();
			
			traiettoria.invisible_ball.changePoint(traiettoria.InitialPoint.x ,traiettoria.InitialPoint.y ,Angle3final);
		
			first=false;
	
	
			if(traiettoria.invisible_ball.getY()>= 485){
				traiettoria.invisible_ball.dblY = 485;
				traj = false;
			}
		
		}
		
		//costruttore 
		public JAmnimationPanel(){ 
		
			super();
			setLayout(null);
	
		}
}

		

