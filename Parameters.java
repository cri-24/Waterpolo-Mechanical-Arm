import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Parameters extends JPanel {


	private static final long serialVersionUID = 1L;
	JFrame theframe1;
	JTextField TxtAltezza;
	JTextField TxtPeso;
	
	double H;
	double W;
	

	
	public Parameters() {

		theframe1 = new JFrame("Human parmeters"); 
		theframe1.setSize(387,233);
		theframe1.setLocationRelativeTo(null);
		theframe1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		theframe1.setResizable(false);
		theframe1.setAlwaysOnTop(true);
		theframe1.getContentPane().setLayout(null);
		
		JLabel lblAltezza = new JLabel("Human height:");
		lblAltezza.setBounds(52, 26, 108, 16);
		theframe1.getContentPane().add(lblAltezza);
		
		JLabel lblInserirePeso = new JLabel("Human weight:");
		lblInserirePeso.setBounds(52, 64, 108, 16);
		theframe1.getContentPane().add(lblInserirePeso);
		
		TxtAltezza = new JTextField();
		
		TxtAltezza.setBounds(172, 21, 130, 26);
		theframe1.getContentPane().add(TxtAltezza);
		TxtAltezza.setColumns(10);
	
		TxtPeso = new JTextField();
		TxtPeso.setColumns(10);
		TxtPeso.setBounds(172, 59, 130, 26);
		theframe1.getContentPane().add(TxtPeso);
		
		JButton btnOk = new JButton("OK");
		btnOk.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
				if(TxtAltezza.getText().isEmpty() || TxtPeso.getText().isEmpty()) {
					
					JOptionPane.showMessageDialog(theframe1, "Inserisci i parametri, prima di procedere");	
				}
				
				else {
					H=Double.parseDouble(TxtAltezza.getText());
					W=Double.parseDouble(TxtPeso.getText());
					theframe1.setVisible(false);
					
				}
			}
		});
		btnOk.setBounds(108, 143, 85, 16);
		theframe1.getContentPane().add(btnOk);
		
		JButton btnAnnulla = new JButton("EXIT");
		btnAnnulla.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(JOptionPane.showConfirmDialog(theframe1,"Sicuro di voler uscire?","Uscire",	JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION) {
				System.exit(0);
				}
			}
			});
		btnAnnulla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAnnulla.setBounds(211, 143, 85, 16);
		theframe1.getContentPane().add(btnAnnulla);
		
		JLabel lblCm = new JLabel("cm");
		lblCm.setBounds(314, 26, 50, 16);
		theframe1.getContentPane().add(lblCm);
		
		JLabel lblKg = new JLabel("kg");
		lblKg.setBounds(314, 64, 50, 16);
		theframe1.getContentPane().add(lblKg);
		
	}
}
