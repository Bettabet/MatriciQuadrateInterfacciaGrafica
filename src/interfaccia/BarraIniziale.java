package interfaccia;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class BarraIniziale extends JPanel implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	private JLabel labelMatrice;
	private JSpinner dimensioneMatrice;
	private JButton bottoneMatrice;
	private CreaInserimentoMatrice insert;
	
	public BarraIniziale() {
		
		setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
		//setLayout(null);
		
		
		labelMatrice= new JLabel("Dimensione Matrice: ");
		labelMatrice.setFont(new Font(labelMatrice.getFont().getName(), labelMatrice.getFont().getStyle(), 20));
		
		dimensioneMatrice= new JSpinner();
		SpinnerNumberModel modelloSpinner= new SpinnerNumberModel(2,2,9,1);
	    dimensioneMatrice.setModel(modelloSpinner);
	    dimensioneMatrice.setFont(new Font(dimensioneMatrice.getFont().getName(), dimensioneMatrice.getFont().getStyle(), 20));
	    
	    bottoneMatrice= new JButton("Crea Matrice");
	    bottoneMatrice.setFont(new Font(bottoneMatrice.getFont().getName(), bottoneMatrice.getFont().getStyle(), 17));
	    bottoneMatrice.addActionListener(this);
	    
	    add(labelMatrice);
		add(dimensioneMatrice);
		add(bottoneMatrice);
		
		/*
		 * bottoneMatrice.addActionListener(new ActionListener() {
		 * 
		 * @Override public void actionPerformed(ActionEvent arg0) { int dimensione=
		 * (int) dimensioneMatrice.getValue(); PannelloMatrice pannelloMatrice= new
		 * PannelloMatrice(dimensione); } });
		 */
		
	    
	}

	public void setCreaInserimentoMatrice(CreaInserimentoMatrice ins) {
		this.insert=ins;
	}
	
	@Override
	public void actionPerformed (ActionEvent e) {
		if (insert!=null) {
				int dimensione= (int) dimensioneMatrice.getValue();
				//System.out.println(dimensione);
				insert.creaMatrice(dimensione);
		}
	}
}
