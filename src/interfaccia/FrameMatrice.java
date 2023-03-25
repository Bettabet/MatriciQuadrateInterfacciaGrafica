package interfaccia;

import java.awt.BorderLayout;
import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.swing.JFrame;

import matriciQuadrate.MatriceQuadrata;

public class FrameMatrice extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private BarraIniziale barraIniziale;
	private PannelloMatriceInput pannelloMatrice;
	private PannelloMatriceOutput pannelloMatriceOutput;

	public FrameMatrice() {
		
		super("Matrice Quadrata");
		
		BorderLayout layout= new BorderLayout();
		
		setLayout(layout);
		
		barraIniziale = new BarraIniziale();
		
		add(barraIniziale,BorderLayout.PAGE_START);
		
		barraIniziale.setCreaInserimentoMatrice(new CreaInserimentoMatrice() {
			@Override
			public void creaMatrice(int dimensione) {
				if (pannelloMatriceOutput!=null) {
					FrameMatrice.this.remove(pannelloMatriceOutput);
					FrameMatrice.this.pack();
				}
				if (pannelloMatrice!=null) FrameMatrice.this.remove(pannelloMatrice);
				FrameMatrice.this.pack();
				pannelloMatrice= new PannelloMatriceInput(dimensione);
				add(pannelloMatrice, BorderLayout.LINE_START);
				FrameMatrice.this.pack();
				setSize(1700,900);
				if (pannelloMatrice!=null) {
					pannelloMatrice.setCreaOutputMatrice(new MatriceListener() {
						@Override
						public void creaOutputMatrice(int dimensione, double[][] entrateMatrice) {
							if (pannelloMatriceOutput!=null) {
								FrameMatrice.this.remove(pannelloMatriceOutput);
								FrameMatrice.this.pack();
							}
							MatriceQuadrata matriceQuadrata= new MatriceQuadrata(dimensione, entrateMatrice);
							Double determinante=matriceQuadrata.determinante();
							double[][] matriceInversa= matriceQuadrata.matriceInversa();
							if (matriceInversa!=null) {
								for (int i=0; i<dimensione; i++) {
									for (int j=0; j<dimensione; j++) {
										BigDecimal bd= new BigDecimal(matriceInversa[i][j]).setScale(5, RoundingMode.HALF_UP);
										matriceInversa[i][j]= bd.doubleValue();
									}
								}
							}
							pannelloMatriceOutput= new PannelloMatriceOutput(dimensione, determinante, matriceInversa);
							add(pannelloMatriceOutput, BorderLayout.CENTER);
							FrameMatrice.this.pack();
							setSize(1700,900);
							/*
							 * System.out.println("Matrice inserita: " +
							 * Arrays.deepToString(matriceQuadrata.getMatrix()));
							 * System.out.println("Il determinante della matrice e': " +determinante);
							 * double[][] matriceInversa= matriceQuadrata.matriceInversa(); if
							 * (matriceInversa==null) System.out.println("Matrice non invertibile"); else
							 * System.out.println("La matrice inversa è " +
							 * Arrays.deepToString(matriceInversa));
							 */
						}
					});
				}
			}
		});
		
		setSize(1700,900);
		
		setLocationRelativeTo(null);
		
		setVisible(true);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
}
