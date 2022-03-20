
import javax.swing.*;
import java.awt.event.*;

/*
 * Overview: classe che gestisce le statistiche in tempo reale
 */

class StatWindow extends JPanel implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	
	JPanel thepanel; 
	JFrame theframe; 
	prefPanel window3;
	JLabel[] labels = new JLabel[7];
	Double [] values  = new Double [7]; 
	
	JButton Vx; 
	JButton Vy; 
	JButton Dx;
	JButton Dy;
	JButton Ax;
	JButton Ay;  
	JButton T;

	JMenuBar menubar; 
	JMenu filemenu;
	JMenuItem fileitems; 

	JOptionPane thepane = new JOptionPane();
	
	public void actionPerformed(ActionEvent e) { 
		
		if(e.getActionCommand().equalsIgnoreCase("About")) { 	
			JOptionPane.showMessageDialog(theframe, "PROGETTO FSC\n"
												+"2020/2021\n"
												+"CRISTIANA, SELENE, MARCO, MATTEO \n" 
												+"PROF. SANDRO RAMBALDI" , " Credits",
												JOptionPane.INFORMATION_MESSAGE);
		}
	
		else if(e.getActionCommand().equalsIgnoreCase("Preferences")) { 
			window3.show();	
		}
		
	}

	public void update ( double vy, double vx, double dx, double dy, double ax, double ay, double t) {
		
		
		values[0] = ToMeters(vx);
		values[1] = ToMeters(vy);
		if(values[1]<0) {
			values[1]=0.0;
		}
		values[2] = ax;
		values[3] = -ay;
		values[4] = ToMeters(dx);
		values[5] = ToMeters(dy);
		values[6] = t;
		
		Double.toString(values[0]);
		this.Vx.setText(String.format("%.3f", values[0]));
		Double.toString(values[1]);
		this.Vy.setText(String.format("%.3f", -values[1]));
		Double.toString(values[2]);
		this.Ax.setText(String.format("%.3f", values[2]));
		Double.toString(values[3]);
		this.Ay.setText(String.format("%.3f", values[3]));
		Double.toString(values[4]);
		this.Dx.setText(String.format("%.3f", values[4]));
		Double.toString(values[5]);
		this.Dy.setText(String.format("%.3f", values[5]));
		Double.toString(values[6]);
		this.T.setText(String.format("%.3f", values[6]));
		
	}
	
	//100 pixel : 0,5 m = pixel : x
	public double ToMeters(double pixel) {
		return pixel * 0.5/100;
	}
		
	//Constructor 
	public StatWindow() {
		
		window3 = new prefPanel(false); 
		
		//THEFRAME
		theframe = new JFrame("Statistics");
		theframe.setAlwaysOnTop(true);
		theframe.setSize(300,500);
		theframe.setResizable(false);
		
		//THEPANEL
		thepanel = new JPanel(); 
		thepanel.setLayout(null);
		
		this.theframe.setContentPane(this.thepanel);
		
		//MENUS
		this.menubar = new JMenuBar();
		this.menubar.setBounds(0,0,350,20);
		this.menubar.setVisible(true);
		this.thepanel.add(this.menubar);
		
		this.filemenu = new JMenu ("MENU"); 
        this.menubar.add(this.filemenu);
		
        this.fileitems = new JMenuItem("About");
        this.filemenu.add(fileitems);
        this.fileitems.addActionListener(this);
        this.fileitems = new JMenuItem("Preferences");
        this.filemenu.add(fileitems);
        this.fileitems.addActionListener(this);
        
        //BUTTONS
		this.Vx = new JButton ("Vx"); 
		this.Vx.setBounds(210,40,70,40);
		this.Vx.setEnabled(false);
		this.thepanel.add(this.Vx); 
		
		this.Vy = new JButton ("Vy"); 
		this.Vy.setBounds(210,100,70,40);
		this.Vy.setEnabled(false);
		this.thepanel.add(this.Vy); 
		  
		
		this.Ax = new JButton ("Ax"); 
		this.Ax.setBounds(210,160,70,40);
		this.Ax.setEnabled(false);
		this.thepanel.add(this.Ax); 
		
		this.Ay = new JButton ("Ay"); 
		this.Ay.setBounds(210,220,70,40);
		this.Ay.setEnabled(false);
		this.thepanel.add(this.Ay);
		
		this.Dx = new JButton ("Dx"); 
		this.Dx.setBounds(210,280,70,40);
		this.Dx.setEnabled(false);
		this.thepanel.add(this.Dx); 
		
		this.Dy = new JButton ("Dy"); 
		this.Dy.setBounds(210,340,70,40);
		this.Dy.setEnabled(false);
		this.thepanel.add(this.Dy);
		
		this.T = new JButton ("Time"); 
		this.T.setBounds(210,400,70,40);
		this.T.setEnabled(false);
		this.thepanel.add(this.T);
		
		//LABELS
		for(int i = 0 ; i<7;i++){
			labels[i] = new JLabel();
			labels[i].setBounds(20,40+60*i,150,40);
			labels[i].setVisible(true);
			thepanel.add(labels[i]);
		}
		
		labels[0].setText("X-axis Vel. (m/s)"); 
		labels[1].setText("Y-axis Vel. (m/s)"); 
		labels[2].setText("X-axis Acc. (m/s^2)"); 
		labels[3].setText("Y-axis Acc. (m/s^2)"); 
		labels[4].setText("X-axis Displ. (m)"); 
		labels[5].setText("Y-axis Displ. (m)"); 
		labels[6].setText("Time Elapsed (s)"); 
		
		
		//INIZIALMENTE NON SI VEDE, SE CLICK SU STATS SI VEDE SOPRA
		theframe.setVisible(false);
	
		
	}
	
	
}
