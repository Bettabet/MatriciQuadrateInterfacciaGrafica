package interfaccia;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class PannelloMatriceOutput extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JTextField[][] campiMatrice;
	private JLabel labelDeterminante;
	
	public PannelloMatriceOutput(int dimensione, double determinanante, double[][] outputMatrice) {
	
		setPreferredSize(new Dimension(190+(dimensione-2)*80,0));
		setLayout(new GridBagLayout());
		
		Border bordoInterno= BorderFactory.createTitledBorder("Matrice inversa");
		Border bordoEsterno= BorderFactory.createEmptyBorder(0, 10, 10, 10);
		Border bordoFinale= BorderFactory.createCompoundBorder(bordoEsterno, bordoInterno);
		setBorder(bordoFinale);
		
		
		if (outputMatrice!=null) {
			campiMatrice= new JTextField[dimensione][dimensione];
			for (int i=0; i<dimensione; i++) { 
				  for (int j=0; j<dimensione; j++) { 
					  campiMatrice[i][j]= new JTextField(Double.toString(outputMatrice[i][j]));
					  campiMatrice[i][j].setEnabled(false);
				  }
			}
		}
		
		
		labelDeterminante= new JLabel("Il determinanante è " + determinanante);
		labelDeterminante.setFont(new Font(labelDeterminante.getFont().getName(), labelDeterminante.getFont().getStyle(), 20));
		
		
		//Layout
		GridBagConstraints gbc= new GridBagConstraints();
				
		if (outputMatrice!=null) {
			for (int i=0; i<dimensione; i++) { 
				gbc.anchor=GridBagConstraints.FIRST_LINE_START;
				for (int j=0; j<dimensione; j++) { 
					    gbc.gridx=j; 
					    gbc.gridy=i; 
					    gbc.weightx=0.01; 
						gbc.weighty=0.01;
						gbc.insets= new Insets(30, 10, 0, 10);
					    add(campiMatrice[i][j], gbc);
				}	          
		      }
		}
		
		
		gbc.gridx=0;
		gbc.gridy++;
		gbc.weightx=1;
		gbc.weighty=1;
		gbc.gridwidth=10;
		gbc.anchor=GridBagConstraints.PAGE_START;
		//gbc.gridwidth=100;
		gbc.fill=GridBagConstraints.HORIZONTAL;
		add(labelDeterminante, gbc);
		
		}


	
}
