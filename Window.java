
import java.awt.event.ActionListener;
import java.awt.event.MouseMotionListener;

import javax.swing.event.ChangeListener;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.event.*;

/*
 * Overview: classe che gestisce l'interazione con l'utente
 */
public class Window extends JFrame implements ActionListener, MouseMotionListener,ChangeListener {
	 
	
	
	private static final long serialVersionUID = 1L;

			JFrame theframe;

			JAmnimationPanel thepanel;
			JPanel thepanel1; 
			Timer animtimer;
			
			int intWidth = 1280; 
			int intHeight = 768; 
			
			int FinalAngle;
			
			//lunghezze braccio 
			double l1;
			double l2;
			double l3;
			
			
			//la palla parte solo se click su start, quindi go=true
			boolean go = false;
			//terminazione errore dati input
			boolean error=false;
			//solo go, senza calcola traiettoria
			boolean only_go=true;
			
			int t=0;
			
			JButton start = new JButton ();
			JButton stop = new JButton ();
			JButton reset = new JButton ();
			JButton stats = new JButton ();
			JButton reset2 = new JButton ();
			
			JButton inzia= new JButton ();
			JButton btnTraiet=new JButton();
			JSlider startv = new JSlider(); 
			JSlider angle = new JSlider(); 
			

			JLabel startvelocity = new JLabel (); 
			JLabel startangle = new JLabel();
			JLabel labelangle2 = new JLabel();
			JLabel labelangle3 = new JLabel();
			JLabel lblFinalangle = new JLabel();
			JLabel lblInserisciForze = new JLabel();
			JLabel lblangle3 = new JLabel();
			JLabel lblangle2 = new JLabel();
			JLabel lblangle1 = new JLabel();
			JLabel lblangle4 = new JLabel();
			
			JLabel vXdisplay = new JLabel();
			JLabel vYdisplay = new JLabel();
			
			JLabel lblForza1 = new JLabel();
			JLabel lblForza2 = new JLabel();
			JLabel lblForza3 = new JLabel();
			
			JSlider Forza1 = new JSlider(); 
			JSlider Forza2 = new JSlider(); 
			JSlider Forza3 = new JSlider(); 
			
			double dblForza1 ;
			double dblForza2;
			double dblForza3 ;
			
			//creo il pannello delle statisitiche
			StatWindow window2 = new StatWindow();
			
			//creo la finestra per peso e altezza
			Parameters theframe1 = new Parameters();
		
			double dblAngle = 90 ;
			double dblAngle2 = 90 ;
			double dblAngle3 = 90 ;
			double dblAngle4 = 31 ;
			
			double dblVelocity = 0 ;
			
			private JSlider angle1;
			private JSlider angle2;
			private JSlider angle3;
			private JSlider angle4;
			
			private JButton inizia;
			//private JTextField textFieldFinal;
			private JLabel lblInserireGliAngoli;
			private JLabel lblAngFinaleMano;
			private JLabel lblVelocita;
			
			double Vxmetri;
			double Vymetri;
			
			double ctrlAngle1=90;
			double ctrlAngle2=90;
			double ctrlAngle3=90;
	
			double tmpAngle1=90;
			double tmpAngle2=90;
			double tmpAngle3=90;
			
			boolean RK_OK=false;
			boolean reset_slider=false;
			boolean res2=false;
			
			int j=0;
			
			public int width()  {
				return intWidth;
			}
			
			public int height() { 
				return intHeight;
			}
			
			public void updateBallPrefPane() { 
				
				thepanel.ball.dblTimeCurrent = window2.window3.dblT;
				
				thepanel.ball.dblAccx = window2.window3.dblXA; 
				thepanel.ball.dblAccy = window2.window3.dblYA; 		
			} 
	
			//aggiornamento valori slider
			public void stateChanged(ChangeEvent e) {
				
				JSlider source = (JSlider) e.getSource();
				
				thepanel.ball.dblTimeCount = 0; 
			
				if(reset_slider==false) {
					
					//BRACCIO
					if( source == angle1) {
						
						//calcolo del punto finale del braccio
						thepanel.p=thepanel.arm.getFinalPoint();			
						thepanel.ball.changePoint(thepanel.p.x, thepanel.p.y, angle3.getValue());
						dblAngle = source.getValue();
						tmpAngle1=dblAngle;
						angle2.setMaximum((int)dblAngle);
						//impostazione dell'angolo
						thepanel.arm.setAngle(dblAngle,thepanel.arm.getLink1());
						//movimento del braccio in base all'angolo
						thepanel.arm.onTranslate();
						thepanel.arm.getLink1().THETA=dblAngle;
						thepanel.repaint();
						startangle.setText(Double.toString(dblAngle));
					}	
					//AVAMBRACCIO
					if(source == angle2) {
						
						//calcolo del punto finale				
						thepanel.p=thepanel.arm.getFinalPoint();			
						thepanel.ball.changePoint(thepanel.p.x, thepanel.p.y, angle3.getValue());
						dblAngle2 = source.getValue();	
						tmpAngle2=dblAngle2;
						//impostazione dell'angolo
						thepanel.arm.setAngle(dblAngle2,thepanel.arm.getLink2());
						//movimento del braccio in base all'angolo
						thepanel.arm.onTranslate();
						thepanel.arm.getLink2().THETA=dblAngle2;							
						thepanel.repaint();
						labelangle2.setText(Double.toString(dblAngle2));
						}
					
					//MANO
					if(source == angle3) {
						
						dblAngle3 = source.getValue();	
						tmpAngle3=dblAngle3;
						angle4.setMaximum((int)dblAngle3);
						thepanel.p=thepanel.arm.getFinalPoint();			
						thepanel.ball.changePoint(thepanel.p.x, thepanel.p.y, angle3.getValue());
						//impostazione dell'angolo
						thepanel.arm.setAngle(dblAngle3,thepanel.arm.getLink3());
						//movimento del braccio in base all'angolo
						thepanel.arm.onTranslate();
						if(dblAngle3==90) {
							thepanel.arm.getLink3().THETA=dblAngle3+1;
						}
						else thepanel.arm.getLink3().THETA=dblAngle3;
						thepanel.repaint();
						labelangle3.setText(Double.toString(dblAngle3));
					}
					
					//MANO FINALE
					if(source == angle4) {
						
						dblAngle4 = source.getValue();	
						lblangle4.setText(Double.toString(dblAngle4));

					}
					
					//FORZA DEL BRACCIO
					if(source == Forza1) {
						
						dblForza1=source.getValue();
						thepanel.arm.getLink1().FORCE=dblForza1;
						lblForza1.setText(Double.toString(dblForza1));
					}
					
					//FORZA DELL'AVAMBRACCIO
					if(source == Forza2) {
						dblForza2=source.getValue();
						thepanel.arm.getLink2().FORCE=dblForza2;
						lblForza2.setText(Double.toString(dblForza2));
					}
					
					//FORZA DELLA MANO
					if(source == Forza3) {
						dblForza3=source.getValue();
						thepanel.arm.getLink3().FORCE=dblForza3;
						lblForza3.setText(Double.toString(dblForza3));
					}
				}	
				
				go=false;	
			}
			
			//calcolo della componente y della velocità
			public double vY(double v, double ang) { 
				if((Math.sin(Math.toRadians(ang)))>0.0000000001){
					return -(v*(Math.sin(Math.toRadians(ang))));
				}
				else {
					return 0; 
				}	
			}
		
			//calcolo della componente x della velocità
			public double vX(double v, double ang) { 
				return (v*(Math.cos(Math.toRadians(ang))));	
			}
			
			public void mouseMoved(MouseEvent e){
				
			}
			public void mouseDragged(MouseEvent e){
				
			}
			public void mouseExited(MouseEvent e){
			
			}
			public void mouseEntered(MouseEvent e) { 
				
			}
			
			//EVENTS
			public void actionPerformed(ActionEvent e){
				
				if (e.getSource() == animtimer && go ) {
					
					thepanel.traj=false;
				
					if(thepanel.ball.getY()>= 480){
						thepanel.ball.dblY = 480;
				
						go = false;
						
					}
					//terminazione del braccio
					if(j<thepanel.arm.getLink3().THETA_VECT.size() && thepanel.arm.getLink1().THETA_VECT.get(j)>30) {
						thepanel.arm.setAngle(thepanel.arm.getLink1().THETA_VECT.get(j),thepanel.arm.getLink1());
						startangle.setText(Double.toString(thepanel.arm.getLink1().localAngle));
						thepanel.p=thepanel.arm.getFinalPoint();
						thepanel.ball.changePoint(thepanel.p.x , thepanel.p.y, dblAngle3);
						thepanel.arm.onTranslate();
					}
					
					//terminazione dell'avambraccio 
					if(j<thepanel.arm.getLink3().THETA_VECT.size() && thepanel.arm.getLink2().THETA_VECT.get(j)>30) {
						thepanel.arm.setAngle(thepanel.arm.getLink2().THETA_VECT.get(j),thepanel.arm.getLink2());
						labelangle2.setText(Double.toString(thepanel.arm.getLink2().localAngle)
);
						thepanel.p=thepanel.arm.getFinalPoint();
						thepanel.ball.changePoint(thepanel.p.x , thepanel.p.y,dblAngle3  );
						thepanel.arm.onTranslate();
					}
					
					//terminazione della mano
					if(j<thepanel.arm.getLink3().THETA_VECT.size()) {
						thepanel.arm.setAngle(thepanel.arm.getLink3().THETA_VECT.get(j),thepanel.arm.getLink3());
						labelangle3.setText(Double.toString(thepanel.arm.getLink3().localAngle));
						thepanel.arm.onTranslate();
						thepanel.p=thepanel.arm.getFinalPoint();
						thepanel.ball.changePoint(thepanel.p.x , thepanel.p.y,dblAngle3);
					}
					else {
						
						this.window2.update(thepanel.ball.dblVy,thepanel.ball.dblVx,thepanel.ball.dblX-thepanel.p.x,thepanel.ball.dblY-thepanel.p.y,thepanel.ball.dblAccx,thepanel.ball.dblAccy,thepanel.ball.dblTimeCount);
						String vx1=String.format("%.3f",thepanel.ball.ToMeters(thepanel.ball.dblVx));
						String vx2=String.format("%.3f",-thepanel.ball.ToMeters(thepanel.ball.dblVy));
						vXdisplay.setText(vx1+"M/S in X Axis");
						vYdisplay.setText(vx2+"M/S in Y Axis");
						if(go==false && t>1) {
							thepanel.ball.dblVx =0;
							thepanel.ball.dblVy =0;
							this.window2.update(thepanel.ball.dblVy,thepanel.ball.dblVx,thepanel.ball.dblX-thepanel.p.x,thepanel.ball.dblY-thepanel.p.y,thepanel.ball.dblAccx,thepanel.ball.dblAccy,thepanel.ball.dblTimeCount);
							vXdisplay.setText("0,000 M/S in X Axis");
							vYdisplay.setText("0,000 M/S in Y Axis");
						}
						
					}
					j++;
					thepanel.repaint();
				}
				
				
				
				
				//START
				if (e.getSource() == start ) {
					
					if(dblAngle3==90 || dblAngle2==90 || dblAngle==90) {
						thepanel.arm.getLink3().THETA=dblAngle3+1;
						thepanel.arm.getLink2().THETA=dblAngle2;
						thepanel.arm.getLink1().THETA=dblAngle;
					}
					
					
					res2=false;
					t++;
					
					if(dblAngle<dblAngle2) {
						JOptionPane.showMessageDialog(theframe1, "L'angolo inziale del braccio deve essere maggiore dell'angolo inziale dell'avambraccio");
						error=true;
					}
					/*if(textFieldFinal.getText().isEmpty()) {
						
						JOptionPane.showMessageDialog(theframe1, "Inserisci l'angolo finale, prima di procedere");	
						error=true;
						if((dblAngle==0 || dblAngle2==0 || dblAngle3==0)) {
							JOptionPane.showMessageDialog(theframe1, "Angoli iniziali non validi: inserire valori > 0 gradi ");
							error=true;
							
						}
					}*/
					
					if((dblAngle==0 || dblAngle2==0 || dblAngle3==0)) {
						JOptionPane.showMessageDialog(theframe1, "Angoli iniziali non validi: inserire valori > 0 gradi ");
						error=true;
						/*if(textFieldFinal.getText().isEmpty()) {
							error=true;
							JOptionPane.showMessageDialog(theframe1, "Inserisci l'angolo finale, prima di procedere");	
						}*/
						
					}
					
					/*else if(!textFieldFinal.getText().equals("") && ( Integer.parseInt (textFieldFinal.getText())<30 || Integer.parseInt(textFieldFinal.getText())>180)) {
						JOptionPane.showMessageDialog(theframe1, "Angolo finale non valido: inserire un angolo nel range [31,Angolo iniziale mano]");	
						textFieldFinal.setText(null);
						error=true;
					}
					
					else if(!textFieldFinal.getText().equals("") && dblAngle3<=Integer.parseInt(textFieldFinal.getText())){
						JOptionPane.showMessageDialog(theframe1, "Angolo finale non valido: inserire un valore minore dell'angolo iniziale della mano");	
						textFieldFinal.setText(null);
						error=true;
					}*/
					
					else if(!error){
				
						go=true;
						
						FinalAngle=(int)dblAngle4;
						thepanel.Angle3final=FinalAngle;
						thepanel.Angle1=dblAngle;
						thepanel.Angle2=dblAngle2;
						thepanel.ball.draw(thepanel.graphics);
						
						//forze
						thepanel.arm.getLink1().FORCE=dblForza1;
						thepanel.arm.getLink2().FORCE=dblForza2;
						thepanel.arm.getLink3().FORCE=dblForza3;
						
						//calcolo Runge Kutta
						
						if(RK_OK==false || ctrlAngle1!=dblAngle || ctrlAngle2!=dblAngle2 ||  ctrlAngle3!=dblAngle3) {
							
							exec_RK(thepanel.arm.getLink3(),FinalAngle);
							exec_RK(thepanel.arm.getLink1(),FinalAngle);
							exec_RK(thepanel.arm.getLink2(),FinalAngle);
							
							//thepanel.ball.dblVx=(thepanel.arm.getLink1().OMEGA_VECT.get(thepanel.arm.getLink1().OMEGA_VECT.size()-1)) * thepanel.arm.ToPixel(thepanel.arm.lung1) + (thepanel.arm.getLink2().OMEGA_VECT.get(thepanel.arm.getLink2().OMEGA_VECT.size()-1)) * (thepanel.arm.lung2) + thepanel.ball.ToPixel(thepanel.arm.getLink3().OMEGA_VECT.get(thepanel.arm.getLink3().OMEGA_VECT.size()-1) * thepanel.arm.ToPixel(thepanel.arm.lung3)) * Math.sin(Math.toRadians(FinalAngle));
							//thepanel.ball.dblVy=(thepanel.arm.getLink1().OMEGA_VECT.get(thepanel.arm.getLink1().OMEGA_VECT.size()-1)) * thepanel.arm.ToPixel(thepanel.arm.lung1) + (thepanel.arm.getLink2().OMEGA_VECT.get(thepanel.arm.getLink2().OMEGA_VECT.size()-1)) * (thepanel.arm.lung2) + thepanel.ball.ToPixel(thepanel.arm.getLink3().OMEGA_VECT.get(thepanel.arm.getLink3().OMEGA_VECT.size()-1) * thepanel.arm.ToPixel(thepanel.arm.lung3)) * (Math.cos(Math.toRadians(FinalAngle)));
							
							thepanel.ball.dblVx=(thepanel.arm.getLink1().OMEGA_VECT.get(thepanel.arm.getLink1().OMEGA_VECT.size()-1) * thepanel.arm.ToPixel(thepanel.arm.lung1) + thepanel.arm.getLink2().OMEGA_VECT.get(thepanel.arm.getLink2().OMEGA_VECT.size()-1) * thepanel.arm.ToPixel(thepanel.arm.lung2) + thepanel.arm.getLink3().OMEGA_VECT.get(thepanel.arm.getLink3().OMEGA_VECT.size()-1) * thepanel.arm.ToPixel(thepanel.arm.lung3)) * Math.sin(Math.toRadians(FinalAngle));
							thepanel.ball.dblVy=(thepanel.arm.getLink1().OMEGA_VECT.get(thepanel.arm.getLink1().OMEGA_VECT.size()-1) * thepanel.arm.ToPixel(thepanel.arm.lung1) + thepanel.arm.getLink2().OMEGA_VECT.get(thepanel.arm.getLink2().OMEGA_VECT.size()-1) * thepanel.arm.ToPixel(thepanel.arm.lung2) + thepanel.arm.getLink3().OMEGA_VECT.get(thepanel.arm.getLink3().OMEGA_VECT.size()-1) * thepanel.arm.ToPixel(thepanel.arm.lung3)) * Math.cos(Math.toRadians(FinalAngle));
						
							RK_OK=true;
						}
						
					
					}
				}
				
				//STOP
				if (e.getSource() == stop ) { 
					go=false;
				}
				
				//RESTART
				if (e.getSource() == reset ) { 
					
					RK_OK=false;
					error=false;
					t=0;
					j=0;
					//azzeramento array LINK1
					thepanel.arm.getLink1().THETA_VECT=null;
					thepanel.arm.getLink1().OMEGA_VECT=null;
					thepanel.arm.getLink1().TIME_VECT=null;
					
					//azzeramento array LINK2
					thepanel.arm.getLink2().THETA_VECT=null;
					thepanel.arm.getLink2().OMEGA_VECT=null;
					thepanel.arm.getLink2().TIME_VECT=null;
					
					//azzeramento array LINK3
					thepanel.arm.getLink3().THETA_VECT=null;
					thepanel.arm.getLink3().OMEGA_VECT=null;
					thepanel.arm.getLink3().TIME_VECT=null;
					
					thepanel.arm.getLink1().THETA = 91;
					thepanel.arm.getLink1().OMEGA = 0;
					thepanel.arm.getLink1().TIME = 0;
					 
					thepanel.arm.getLink2().THETA = 91;
					thepanel.arm.getLink2().OMEGA = 0;
					thepanel.arm.getLink2().TIME = 0;
					
					thepanel.arm.getLink3().THETA = 91;
					thepanel.arm.getLink3().OMEGA = 0;
					thepanel.arm.getLink3().TIME = 0;
					 
					thepanel.move_NO=true;
					
					//azzeramento sliders
					reset_slider=true;
					angle1.setValue(90);
					dblAngle=90;
					startangle.setText("90.0");
					angle2.setValue(90);
					dblAngle2=90;
					labelangle2.setText("90.0");
					angle3.setValue(90);
					dblAngle3=90;
					
					/*thepanel.Angle1=90;
					thepanel.Angle2=90;
					thepanel.Angle3=90;
					*/
					tmpAngle1=dblAngle;
					tmpAngle2=dblAngle2;
					tmpAngle3=dblAngle3;
					
					labelangle3.setText("90.0");
					
					lblangle4.setText("31.0");
					
					thepanel.ball.dblVx= 0;
					thepanel.ball.dblVy=0;
					
					if(only_go==false) {
						thepanel.traiettoria.invisible_ball.dblVx=0;
						thepanel.traiettoria.invisible_ball.dblVy=0;
						only_go=true;
					}
					
					vXdisplay.setText("0.0M/S in X Axis");
					vYdisplay.setText("0.0M/S in Y Axis");
				
					Forza1.setValue(0);
					Forza2.setValue(0);
					Forza3.setValue(0);
					lblForza1.setText("0.0 ");
					lblForza2.setText("0.0 ");
					lblForza3.setText("0.0 ");
					dblForza1=0;
					dblForza2=0;
					dblForza3=0;
					
					Point p_arm=new Point(150,500);
					thepanel.arm.getLink1().reset(p_arm);
					p_arm.x=thepanel.arm.getLink1().getEndPointGlobal().x;
					p_arm.y=thepanel.arm.getLink1().getEndPointGlobal().y;
					thepanel.arm.getLink2().reset(p_arm);
					p_arm.x=thepanel.arm.getLink2().getEndPointGlobal().x;
					p_arm.y=thepanel.arm.getLink2().getEndPointGlobal().y;
					thepanel.arm.getLink3().reset(p_arm);
					
					thepanel.arm.getLink1().localAngle=90;
					thepanel.arm.getLink2().localAngle=90;
					thepanel.arm.getLink3().localAngle=90;

					this.window2.update(thepanel.ball.dblVy,thepanel.ball.dblVx,0,0,thepanel.ball.dblAccx,thepanel.ball.dblAccy,0);

					//posizione della palla 
					thepanel.p.x=150;
					thepanel.p.y=500-thepanel.arm.ToPixel(thepanel.arm.lung1)-thepanel.arm.ToPixel(thepanel.arm.lung2)-thepanel.arm.ToPixel(thepanel.arm.lung3);
					thepanel.ball.changePoint(thepanel.p.x,thepanel.p.y,90);
					
					if(thepanel.traj==true) {
						thepanel.traiettoria.invisible_ball.changePoint(thepanel.p.x,thepanel.p.y,90);
					}
					thepanel.reset_ball=true;
					thepanel.repaint();
					
					//azzeramento angolo finale
					angle4.setValue(31);
					dblAngle4=31;
					thepanel.traj=false;
					reset_slider=false;
					thepanel.repaint();
				
				}
				
				//RESET2
				if (e.getSource() == reset2 ) { 
					RK_OK=false;
					res2=true;
					error=false;
					t=0;
					j=0;
					//azzeramento array LINK1
					thepanel.arm.getLink1().THETA_VECT=null;
					thepanel.arm.getLink1().OMEGA_VECT=null;
					thepanel.arm.getLink1().TIME_VECT=null;
					
					//azzeramento array LINK2
					thepanel.arm.getLink2().THETA_VECT=null;
					thepanel.arm.getLink2().OMEGA_VECT=null;
					thepanel.arm.getLink2().TIME_VECT=null;
					
					//azzeramento array LINK3
					thepanel.arm.getLink3().THETA_VECT=null;
					thepanel.arm.getLink3().OMEGA_VECT=null;
					thepanel.arm.getLink3().TIME_VECT=null;
					
					thepanel.arm.getLink1().THETA =tmpAngle1;
					thepanel.arm.getLink1().OMEGA = 0;
					thepanel.arm.getLink1().TIME = 0;
					 
					thepanel.arm.getLink2().THETA = tmpAngle2;
					thepanel.arm.getLink2().OMEGA = 0;
					thepanel.arm.getLink2().TIME = 0;
					
					if(tmpAngle3==90)
						thepanel.arm.getLink3().THETA = tmpAngle3+1;
					else
						thepanel.arm.getLink3().THETA = tmpAngle3;
						
					thepanel.arm.getLink3().OMEGA = 0;
					thepanel.arm.getLink3().TIME = 0;
					 
					thepanel.move_NO=true;
					
					startangle.setText(Double.toString(tmpAngle1));
					labelangle2.setText(Double.toString(tmpAngle2));
					labelangle3.setText(Double.toString(tmpAngle3));
					lblangle4.setText(Double.toString(dblAngle4));
					//azzeramento sliders
					/*reset_slider=true;
					angle1.setValue(90);
					dblAngle=90;
					startangle.setText("90.0");
					angle2.setValue(90);
					dblAngle2=90;
					labelangle2.setText("90.0");
					angle3.setValue(90);
					dblAngle3=90;
					labelangle3.setText("90.0");*/
					
					
					thepanel.ball.dblVx= 0;
					thepanel.ball.dblVy=0;
					
					if(only_go==false) {
						thepanel.traiettoria.invisible_ball.dblVx=0;
						thepanel.traiettoria.invisible_ball.dblVy=0;
						only_go=true;
					}
					
					vXdisplay.setText("0.0M/S in X Axis");
					vYdisplay.setText("0.0M/S in Y Axis");
				
					/*Forza1.setValue(0);
					Forza2.setValue(0);
					Forza3.setValue(0);
					lblForza1.setText("0.0 N");
					lblForza2.setText("0.0 N");
					lblForza3.setText("0.0 N");
					dblForza1=0;
					dblForza2=0;
					dblForza3=0;*/
					
					/*Point p_arm=new Point(150,500);
					//thepanel.arm.getLink1().reset(p_arm);
					p_arm.x=thepanel.arm.getLink1().getEndPointGlobal().x;
					p_arm.y=thepanel.arm.getLink1().getEndPointGlobal().y;
					//thepanel.arm.getLink2().reset(p_arm);
					p_arm.x=thepanel.arm.getLink2().getEndPointGlobal().x;
					p_arm.y=thepanel.arm.getLink2().getEndPointGlobal().y;
					//thepanel.arm.getLink3().reset(p_arm);*/
					
					//impostazione dell'angolo
					thepanel.arm.setAngle(dblAngle,thepanel.arm.getLink1());
					thepanel.arm.setAngle(dblAngle2,thepanel.arm.getLink2());
					thepanel.arm.setAngle(dblAngle3,thepanel.arm.getLink3());
					//movimento del braccio in base all'angolo
					thepanel.arm.onTranslate();
					
					this.window2.update(thepanel.ball.dblVy,thepanel.ball.dblVx,0,0,thepanel.ball.dblAccx,thepanel.ball.dblAccy,0);

					//posizione della palla 
					thepanel.p.x=thepanel.arm.getLink3().getEndPointGlobal().x;
					thepanel.p.y=thepanel.arm.getLink3().getEndPointGlobal().y;
					thepanel.ball.changePoint(thepanel.p.x,thepanel.p.y,90);
					
					if(thepanel.traj==true) {
						thepanel.traiettoria.invisible_ball.changePoint(thepanel.p.x,thepanel.p.y,90);
					}
					thepanel.reset_ball=true;
					thepanel.repaint();
					
					//azzeramento angolo finale
					angle4.setValue(31);
					dblAngle4=31;
					thepanel.traj=false;
					reset_slider=false;
					thepanel.repaint();
				
				}
				
				//STATS
				if(e.getSource()==stats) { 
					
					thepanel1.add(window2);
					window2.theframe.setVisible(true);
					
				}
				
				//TRAIETTORIA
				if(e.getSource()==btnTraiet) {
					only_go=false;
					if(dblAngle3==90 || dblAngle2==90 || dblAngle==90) {
						thepanel.arm.getLink3().THETA=dblAngle3+1;
						thepanel.arm.getLink2().THETA=dblAngle2;
						thepanel.arm.getLink1().THETA=dblAngle;
					}
					
					error=false;
					
					if(dblAngle<dblAngle2 ) {
						JOptionPane.showMessageDialog(theframe1, "L'angolo inziale del braccio deve essere maggiore dell'angolo inziale dell'avambraccio");
						error=true;
					}
					
					/*if(textFieldFinal.getText().isEmpty()) {
						
						JOptionPane.showMessageDialog(theframe1, "Inserisci l'angolo finale, prima di procedere");	
						error=true;
						
						if((dblAngle==0 || dblAngle2==0 || dblAngle3==0)) {
							JOptionPane.showMessageDialog(theframe1, "Angoli iniziali non validi: inserire valori > 0 gradi ");
							error=true;
							
						}
					}*/
					
					if((dblAngle==0 || dblAngle2==0 || dblAngle3==0)) {
						JOptionPane.showMessageDialog(theframe1, "Angoli iniziali non validi: inserire valori > 0 gradi ");
						/*if(textFieldFinal.getText().isEmpty()) {
							
							JOptionPane.showMessageDialog(theframe1, "Inserisci l'angolo finale, prima di procedere");	
							error=true;
						}*/
						
					}
					
					
					/*else if(!textFieldFinal.getText().equals("") && (Integer.parseInt (textFieldFinal.getText())<30 || Integer.parseInt(textFieldFinal.getText())>180)) {
						JOptionPane.showMessageDialog(theframe1, "Angolo finale non valido: inserire un angolo nel range [31,Angolo iniziale mano]");	
						textFieldFinal.setText(null);
						error=true;
					}
					
					else if(!textFieldFinal.getText().equals("") && dblAngle3<=Integer.parseInt(textFieldFinal.getText())){
						JOptionPane.showMessageDialog(theframe1, "Angolo finale non valido: inserire un valore maggiore dell'angolo iniziale della mano");	
						textFieldFinal.setText(null);
						error=true;
					}*/
					
					else if (error==false){
						
						if(res2==true){
							dblAngle=tmpAngle1;
							dblAngle2=tmpAngle2;
							dblAngle3=tmpAngle3;
						}
						
						ctrlAngle1 = dblAngle;
						ctrlAngle2 = dblAngle2;
						ctrlAngle3 = dblAngle3;
						
						
						FinalAngle=(int)dblAngle4;
						thepanel.Angle3final=FinalAngle;
						
						//forze
						thepanel.arm.getLink1().FORCE=dblForza1;
						thepanel.arm.getLink2().FORCE=dblForza2;
						thepanel.arm.getLink3().FORCE=dblForza3;
					
						//RUNGE KUTTA
						exec_RK(thepanel.arm.getLink3(),FinalAngle);
						exec_RK(thepanel.arm.getLink1(),FinalAngle);
						exec_RK(thepanel.arm.getLink2(),FinalAngle);
					
						RK_OK=true;
						
						//disegno della traiettoria
						thepanel.drawTraj();
						
						thepanel.ball.dblVx=(thepanel.arm.getLink1().OMEGA_VECT.get(thepanel.arm.getLink1().OMEGA_VECT.size()-1) * thepanel.arm.ToPixel(thepanel.arm.lung1) + thepanel.arm.getLink2().OMEGA_VECT.get(thepanel.arm.getLink2().OMEGA_VECT.size()-1) * thepanel.arm.ToPixel(thepanel.arm.lung2) + thepanel.arm.getLink3().OMEGA_VECT.get(thepanel.arm.getLink3().OMEGA_VECT.size()-1) * thepanel.arm.ToPixel(thepanel.arm.lung3)) * Math.sin(Math.toRadians(FinalAngle));
						thepanel.ball.dblVy=(thepanel.arm.getLink1().OMEGA_VECT.get(thepanel.arm.getLink1().OMEGA_VECT.size()-1) * thepanel.arm.ToPixel(thepanel.arm.lung1) + thepanel.arm.getLink2().OMEGA_VECT.get(thepanel.arm.getLink2().OMEGA_VECT.size()-1) * thepanel.arm.ToPixel(thepanel.arm.lung2) + thepanel.arm.getLink3().OMEGA_VECT.get(thepanel.arm.getLink3().OMEGA_VECT.size()-1) * thepanel.arm.ToPixel(thepanel.arm.lung3)) * Math.cos(Math.toRadians(FinalAngle));
						
						
						thepanel.traiettoria.invisible_ball.dblVx=(thepanel.arm.getLink1().OMEGA_VECT.get(thepanel.arm.getLink1().OMEGA_VECT.size()-1) * thepanel.arm.ToPixel(thepanel.arm.lung1) + thepanel.arm.getLink2().OMEGA_VECT.get(thepanel.arm.getLink2().OMEGA_VECT.size()-1) * thepanel.arm.ToPixel(thepanel.arm.lung2) + thepanel.arm.getLink3().OMEGA_VECT.get(thepanel.arm.getLink3().OMEGA_VECT.size()-1) * thepanel.arm.ToPixel(thepanel.arm.lung3)) * Math.sin(Math.toRadians(FinalAngle));
						thepanel.traiettoria.invisible_ball.dblVy=(thepanel.arm.getLink1().OMEGA_VECT.get(thepanel.arm.getLink1().OMEGA_VECT.size()-1) * thepanel.arm.ToPixel(thepanel.arm.lung1) + thepanel.arm.getLink2().OMEGA_VECT.get(thepanel.arm.getLink2().OMEGA_VECT.size()-1) * thepanel.arm.ToPixel(thepanel.arm.lung2) + thepanel.arm.getLink3().OMEGA_VECT.get(thepanel.arm.getLink3().OMEGA_VECT.size()-1) * thepanel.arm.ToPixel(thepanel.arm.lung3)) * Math.cos(Math.toRadians(FinalAngle));
						
						thepanel.traj=true;
						thepanel.repaint();
					}
					
					
					
				}
			}
			
			
			//COSTRUTTORE DI WINDOW
			public Window() { 
				
				//FRAME PARAMETRI
				theframe1.theframe1.setVisible(true);
				
				theframe = new JFrame("Waterpolo"); 
				theframe.setSize(intWidth,intHeight);
				theframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				theframe.setResizable(false);
				theframe.setVisible(true);
			
				//THEPANEL1
				thepanel1 = new JPanel(); 
				thepanel1.setForeground(new Color(0, 0, 255));
				//sfondo celestino
				thepanel1.setBackground(new Color(224, 255, 255));
				thepanel1.setLayout(null);
				thepanel1.addMouseMotionListener(this);
				thepanel1.setSize(intWidth,intHeight);
				
				animtimer = new Timer(0,this);
				animtimer.start();
				this.theframe.getContentPane().add(this.thepanel1);
				
				//START BUTTON
				this.start = new JButton ("GO");
				start.setForeground(new Color(0, 0, 0));
				this.start.setBorder(new LineBorder(new Color(0, 0, 0)));
				
				this.start.setBounds (1280-200,560,70,30);
				this.thepanel1.add(this.start);
				this.start.setVisible(false);
				this.start.addActionListener(this);
				this.start.setBackground(new Color(51, 204, 51));
				this.start.setOpaque(true);
				
				//STOP BUTTON
				this.stop = new JButton ("STOP");
				stop.setForeground(new Color(255, 255, 255));
				this.stop.setBorder(new LineBorder(new Color(0, 0, 0)));
				this.stop.setBounds (1280-290,600,70,30);
				this.thepanel1.add(this.stop);
				this.stop.setBackground(new Color(255, 51, 102));
				this.stop.addActionListener(this);
				this.stop.setOpaque(true);
				this.stop.setVisible(false);
				
				//RESTART BUTTON
				this.reset = new JButton ("RESET");
				reset.setForeground(new Color(255, 255, 255));
				this.reset.setBorder(new LineBorder(new Color(0, 0, 0)));
				this.reset.setBounds (1280-200,600,70,30);
				this.thepanel1.add(this.reset);
				this.reset.setBackground(new Color(0, 0, 0));
				this.reset.addActionListener(this);
				this.reset.setOpaque(true);
				this.reset.setVisible(false);
				
				//RESET2 BUTTON
				this.reset2 = new JButton ("RESTART");
				reset2.setForeground(new Color(255, 255, 255));
				this.reset2.setBorder(new LineBorder(new Color(0, 0, 0)));
				this.reset2.setBounds (1280-290,640,70,30);
				this.thepanel1.add(this.reset2);
				this.reset2.setBackground(new Color(100, 0, 0));
				this.reset2.addActionListener(this);
				this.reset2.setOpaque(true);
				this.reset2.setVisible(false);
				
				//STATS BUTTON
				this.stats = new JButton ("STATS");
				this.stats.setBorder(new LineBorder(new Color(0, 0, 0)));
				this.stats.setBounds (1280-200,600,70,30);
				this.thepanel1.add(this.stats);
				this.stats.setBackground(new Color(255, 255, 102));
				this.stats.addActionListener(this);
				this.stats.setOpaque(true);
				this.stats.setVisible(false);
				
				//ANGLE LABELS
				lblangle1 = new JLabel ("Braccio:",JLabel.LEFT);
				lblangle1.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
				lblangle1.setForeground(new Color(0, 0, 255));
				lblangle1.setVisible(false); 
				lblangle1.setBounds(20,560,50,16);
				this.thepanel1.add(lblangle1);
				
				//ANGOLO1
				this.angle1 = new JSlider (0,60,180,90);
				this.angle1.setBounds(80,560,160,30);
				this.angle1.addChangeListener(this);
				this.thepanel1.add(this.angle1);
				this.angle1.setVisible(false); 
				
				//ANGLE LABELS
				lblangle2 = new JLabel ("Avambraccio:",JLabel.LEFT);
				lblangle2.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
				lblangle2.setForeground(new Color(0, 0, 255));
				lblangle2.setVisible(false); 
				lblangle2.setBounds(20,600,70,16);
				this.thepanel1.add(lblangle2);
				//ANGOLO2
				angle2 = new JSlider (0,60,90,90);
				angle2.setBounds(80, 600, 160, 30);
				this.angle2.addChangeListener(this);
				this.angle2.setVisible(false);
				thepanel1.add(angle2);
				
				//ANGLE LABELS
				lblangle3 = new JLabel ("Mano :",JLabel.LEFT);
				lblangle3.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
				lblangle3.setForeground(new Color(0, 0, 255));
				lblangle3.setVisible(false); 
				lblangle3.setBounds(20,640,50,16);
				this.thepanel1.add(lblangle3);
				//ANGOLO3
				angle3 = new JSlider (0,40,180,90);
				angle3.setBounds(80, 640, 160, 30);
				this.angle3.addChangeListener(this);
				this.angle3.setVisible(false);
				thepanel1.add(angle3);
				
				//ANGOLO4
				angle4 = new JSlider (0,31,89,31);
				angle4.setBounds(360, 640, 90, 30);
				this.angle4.addChangeListener(this);
				this.angle4.setVisible(false);
				thepanel1.add(angle4);
				
				//ANGLE LABELS
				lblangle4 = new JLabel ("31.0",JLabel.LEFT);
				lblangle4.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
				lblangle4.setForeground(new Color(0, 0, 255));
				lblangle4.setVisible(false); 
				lblangle4.setBounds(450,640,58,16);
				this.thepanel1.add(lblangle4);
				
				//ANGLE LABELS
				this.startangle = new JLabel ("90.0",JLabel.LEFT);
				startangle.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
				startangle.setForeground(new Color(0, 0, 255));
				this.startangle.setVisible(false); 
				this.startangle.setBounds(250,565,58,16);
				this.thepanel1.add(this.startangle);
				
				this.labelangle2 = new JLabel ("90.0",JLabel.LEFT);
				labelangle2.setBounds(250, 605, 58, 16);
				labelangle2.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
				labelangle2.setForeground(new Color(0, 0, 255));
				labelangle2.setVisible(false); 
				thepanel1.add(labelangle2);
				
				this.labelangle3 = new JLabel ("90.0",JLabel.LEFT);
				labelangle3.setBounds(250, 645, 58, 16);
				labelangle3.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
				labelangle3.setForeground(new Color(0, 0, 255));
				labelangle3.setVisible(false); 
				thepanel1.add(labelangle3);
				
				//STARTING VELOCITY X AXIS LABEL
				this.vXdisplay = new JLabel ("v1",JLabel.LEFT);
				this.vXdisplay.setVisible(false); 
				this.vXdisplay.setBounds(775,560,242,50);
				this.thepanel1.add(this.vXdisplay);
				
				//STARTING VELOCITY Y AXIS LABEL
				this.vYdisplay = new JLabel ("v2",JLabel.LEFT);
				this.vYdisplay.setVisible(false); 
				this.vYdisplay.setBounds(775,600,242,50);
				this.thepanel1.add(this.vYdisplay);
				
				//INIZIA
				this.inizia = new JButton("INIZIA");
				this.inizia.setBackground(new Color(51, 204, 51));
				this.inizia.setOpaque(true);
				this.inizia.setFont(new Font("Lucida Grande", Font.PLAIN, 38));
				this.inizia.setBounds(516, 286, 224, 100);
				this.inizia.setBorder(new LineBorder(new Color(0, 0, 0)));
				thepanel1.add(inizia);
				
				lblFinalangle = new JLabel("Range [31,Mano]");
				lblFinalangle.setBounds(370, 615, 191, 20);
				lblFinalangle.setFont(new Font("Lucida Grande", Font.BOLD, 8));
				thepanel1.add(lblFinalangle);
				lblFinalangle.setVisible(false);
				
				/*textFieldFinal = new JTextField();
				textFieldFinal.setBounds(360, 640, 90, 30);
				thepanel1.add(textFieldFinal);
				textFieldFinal.setColumns(10);
				textFieldFinal.setVisible(false);*/
				
				lblInserireGliAngoli = new JLabel("SET INITIAL ANGLES:");
				lblInserireGliAngoli.setBounds(105, 540, 191, 16);
				lblInserireGliAngoli.setFont(new Font("Lucida Grande", Font.BOLD, 9));
				thepanel1.add(lblInserireGliAngoli);
				lblInserireGliAngoli.setVisible(false);
				
				lblAngFinaleMano = new JLabel("SET FINAL ANGLE (HAND):");
				lblAngFinaleMano.setBounds(350, 540, 191, 16);
				lblAngFinaleMano.setFont(new Font("Lucida Grande", Font.BOLD, 9));
				thepanel1.add(lblAngFinaleMano);
				lblAngFinaleMano.setVisible(false);
				
				lblVelocita = new JLabel("VELOCITY:");
				lblVelocita.setFont(new Font("Lucida Grande", Font.BOLD, 9));
				lblVelocita.setBounds(797, 540, 88, 16);
				thepanel1.add(lblVelocita);
				lblVelocita.setVisible(false);
				
				this.btnTraiet= new JButton("TRAJECTORY");
				this.btnTraiet.addActionListener(this);
				this.btnTraiet.setBackground(new Color(0, 191, 255));
				this.btnTraiet.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
				this.btnTraiet.setOpaque(true);
				this.btnTraiet.setBorder(new LineBorder(new Color(0, 0, 0)));
				this.btnTraiet.setBounds(1280-290,560,70,30);
				thepanel1.add(btnTraiet);
				
				lblInserisciForze = new JLabel("SET FORCES(N):");
				lblInserisciForze.setFont(new Font("Lucida Grande", Font.BOLD, 9));
				lblInserisciForze.setBounds(590, 540, 110, 16);
				lblInserisciForze.setVisible(false);
				thepanel1.add(lblInserisciForze);
				
				
				Forza1 = new JSlider(0,0,1000,0);
				Forza1.setBounds(540, 560, 160, 30);
				this.Forza1.addChangeListener(this);
				thepanel1.add(Forza1);
				Forza1.setVisible(false);
				
				Forza2 = new JSlider(0,0,1000,0);
				Forza2.setBounds(540, 600, 160, 30);
				this.Forza2.addChangeListener(this);
				thepanel1.add(Forza2);
				Forza2.setVisible(false);
				
				Forza3 = new JSlider(0,0,400,0);
				Forza3.setBounds(540, 640, 160, 30);
				this.Forza3.addChangeListener(this);
				thepanel1.add(Forza3);
				Forza3.setVisible(false);
				
				lblForza1 = new JLabel("0.0");
				lblForza1.setForeground(Color.BLUE);
				lblForza1.setBounds(710, 565, 61, 16);
				thepanel1.add(lblForza1);
				lblForza1.setVisible(false);
				
				lblForza2 = new JLabel("0.0");
				lblForza2.setForeground(Color.BLUE);
				lblForza2.setBounds(710, 605, 61, 16);
				thepanel1.add(lblForza2);
				lblForza2.setVisible(false);
				
				lblForza3 = new JLabel("0.0");
				lblForza3.setForeground(Color.BLUE);
				lblForza3.setBounds(710, 645, 61, 16);
				thepanel1.add(lblForza3);
				this.btnTraiet.setVisible(false);
				lblForza3.setVisible(false);
				
				
				this.inizia.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						
							inizia.setVisible(false);
							
							//THEPANEL
							thepanel = new JAmnimationPanel(); 
							thepanel1.add(thepanel);
							thepanel.addMouseMotionListener(this);
							thepanel.setBounds(0,0,1280,530);
							thepanel.setBackground(Color.BLACK);
							
							//creazione del braccio
							thepanel.create_arm(theframe1.H);
							
							//inizializzazione palla
							thepanel.ball.reset(50,300);
							thepanel.repaint();
							vXdisplay.setText("0.0M/S in X Axis");
							vYdisplay.setText("0.0M/S in Y Axis");
							thepanel.setLayout(null);
							
							//BRACCIO
							thepanel.arm.getLink1().setB(thepanel.arm.lung1/100, 0.436);
							thepanel.arm.getLink1().setM(theframe1.W, 0.028);
							thepanel.arm.getLink1().setR(thepanel.arm.lung1/100, 0.13);
							thepanel.arm.getLink1().setI(thepanel.arm.lung1/100);
							thepanel.arm.getLink1().setA(thepanel.arm.lung1/100, 0.38);
							
							//AVAMBRACCIO
							thepanel.arm.getLink2().setB(thepanel.arm.lung2/100, 0.43);
							thepanel.arm.getLink2().setM(theframe1.W, 0.016);
							thepanel.arm.getLink2().setR(thepanel.arm.lung2/100, 0.17);
							thepanel.arm.getLink2().setI(thepanel.arm.lung2/100);
							thepanel.arm.getLink2().setA(thepanel.arm.lung2/100, 0.1);
							
							//MANO
							thepanel.arm.getLink3().setB(thepanel.arm.lung3/100, 0.506);
							thepanel.arm.getLink3().setM(theframe1.W, 0.006);
							thepanel.arm.getLink3().setR(thepanel.arm.lung3/100, 0.40);
							thepanel.arm.getLink3().setI(thepanel.arm.lung3/100);
							thepanel.arm.getLink3().setA(thepanel.arm.lung3/100, 0.1);
							
							angle1.setVisible(true);
							angle2.setVisible(true);
							angle3.setVisible(true);
							angle4.setVisible(true);
							
							start.setVisible(true);
							stats.setVisible(false);
							reset.setVisible(true);
							stop.setVisible(true);
							reset2.setVisible(true);
							
							
							startangle.setVisible(true);
							labelangle2.setVisible(true);
							labelangle3.setVisible(true);
							
							vXdisplay.setVisible(true);
							vYdisplay.setVisible(true);
							
							lblVelocita.setVisible(true);
							//textFieldFinal.setVisible(true);
							lblInserireGliAngoli.setVisible(true);
							lblAngFinaleMano.setVisible(true);
							
							btnTraiet.setVisible(true);
							
							lblFinalangle.setVisible(true);
							lblangle1.setVisible(true);
							lblangle2.setVisible(true);
							lblangle3.setVisible(true);
							lblForza1.setVisible(true);
							lblForza2.setVisible(true);
							lblForza3.setVisible(true);
							lblangle4.setVisible(true);
							Forza1.setVisible(true);
							Forza2.setVisible(true);
							Forza3.setVisible(true);
							
							lblInserisciForze.setVisible(true);

							
					}

					
						
						
					
				});
					
				
			}
			
			//RUNGE KUTTA
			public void exec_RK(Link link, double angle) {
	
				link.TIME_VECT=new ArrayList<Double>();
				link.THETA_VECT=new ArrayList<Double>();
				link.OMEGA_VECT=new ArrayList<Double>();
				
				int i=0;
				link.THETA_VECT.add(link.THETA);
				System.out.println("link.THETA "+link.THETA);
				while(thepanel.arm.getLink3().THETA_VECT.get(i)>angle) {
					
					link.OMEGA=link.omega_next_step();
					link.TIME+=link.h;
					link.THETA-=link.OMEGA*link.h*360/2/Math.PI;
					
					System.out.println("TIME "+ link.TIME + " " + "THETA " + link.THETA + " OMEGA " + link.OMEGA);
					
					link.OMEGA_VECT.add(link.OMEGA);
					link.TIME_VECT.add(link.TIME);
					link.THETA_VECT.add(link.THETA);
					i++;
				}		
			}
}




