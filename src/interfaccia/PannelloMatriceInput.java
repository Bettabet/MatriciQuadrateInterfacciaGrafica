package interfaccia;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class PannelloMatriceInput extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JTextField[][] campiMatrice;
	private JButton calcolaMatrice;
	private MatriceListener matriceListener;
	
	public PannelloMatriceInput(int dimensione) {
		
		setPreferredSize(new Dimension(190+(dimensione-2)*80,0));
		setLayout(new GridBagLayout());
		
		Border bordoInterno= BorderFactory.createTitledBorder("Inserisci matrice");
		Border bordoEsterno= BorderFactory.createEmptyBorder(0, 10, 10, 10);
		Border bordoFinale= BorderFactory.createCompoundBorder(bordoEsterno, bordoInterno);
		setBorder(bordoFinale);
		
		campiMatrice= new JTextField[dimensione][dimensione];
		for (int i=0; i<dimensione; i++) { 
			  for (int j=0; j<dimensione; j++) { 
				  campiMatrice[i][j]= new JTextField(5);
			  }
		}
		
		calcolaMatrice= new JButton("Calcola");
		
		//Layout
		GridBagConstraints gbc= new GridBagConstraints();
		
		
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
		
		gbc.gridx=0;
		gbc.gridy++;
		gbc.weightx=1;
		gbc.weighty=1;
		gbc.gridwidth=10;
		gbc.anchor=GridBagConstraints.PAGE_START;
		//gbc.gridwidth=100;
		gbc.fill=GridBagConstraints.HORIZONTAL;
		add(calcolaMatrice, gbc);
		
		
		//Bottone Calcola Matrice
		calcolaMatrice.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) throws NumberFormatException {
				if (matriceListener!=null) {
					double[][] entrateMatrice= new double[dimensione][dimensione];
					try {
						for (int i=0; i<dimensione; i++) {
							for (int j=0; j<dimensione; j++) {
								entrateMatrice[i][j]= Double.valueOf(campiMatrice[i][j].getText());
							}
						}
					}
					catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(PannelloMatriceInput.super.getParent(), "Input non corretto!", "Errore", JOptionPane.ERROR_MESSAGE);
						return ;
					}
					
					matriceListener.creaOutputMatrice(dimensione, entrateMatrice);
				}
			}
		});
	}
	
	public void setCreaOutputMatrice(MatriceListener matriceListener) {
		this.matriceListener=matriceListener;
	}

}
