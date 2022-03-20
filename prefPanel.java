import javax.swing.JPanel;
import javax.swing.*;
import javax.swing.JOptionPane;

class prefPanel extends JPanel  { 

	private static final long serialVersionUID = 1L;
	
	//componenti
	JPanel thepanel; 
	JFrame theframe; 
	JOptionPane thepane = new JOptionPane();
	
	//etichette testuali 
	JLabel[] labels = new JLabel[3];
	JTextField YA; 
	JTextField XA;
	JTextField T;  
	
	//bottoni
	JButton Ok ; 
	JButton Cancel; 
	
	//variabili
	double dblYA = 9.8;
	double dblXA = 0;
	double dblT = 0.01;

	public void show () { 
		theframe.setVisible(true);
		theframe.setAlwaysOnTop(true);
	}
	
	public double getXA () { 
		return dblXA;
	}
	public double getYA () { 
		return dblYA;
	}
	public double getT () { 
		return dblT;
	}
	
	@SuppressWarnings("static-access")
	public void update(){
		try{
			dblYA = Double.parseDouble(YA.getText());
			dblXA = Double.parseDouble(XA.getText());
			dblT = Double.parseDouble(T.getText())/100;
			theframe.setVisible(false);
			
		}catch(Exception e ) {
			JOptionPane.showMessageDialog(theframe, "CAMPI VUOTI O VALORI ERRATI","Input Warning",	
			thepane.ERROR_MESSAGE); 
		}
	}
	
	
	//Contructor 
	public prefPanel(Boolean blnShow) { 
		
		//THEFRAME
		theframe = new JFrame("Preferences"); 
		theframe.setSize(300,250);
		theframe.setResizable(false);
		
		//THEPANEL
		thepanel = new JPanel(); 
		thepanel.setLayout(null);
		this.theframe.setContentPane(this.thepanel);
		
		//THEPANE
		this.theframe.add(thepane);
		
	}
	
	
}




