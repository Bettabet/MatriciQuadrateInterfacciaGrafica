package matriciQuadrate;

public class MatriceQuadrata{

	private int dimension;
	private double[][] entrateMatrice;
	
	public MatriceQuadrata (int dimension, double[][] matrix) {
		this.dimension=dimension;
		this.entrateMatrice=matrix;
	}

	public double[][] getMatrix() {
		return entrateMatrice;
	}

	public double determinante() {
		int numeroScambi=0;
		double[][] copiedMatrix= new double[dimension][dimension];
		copiaMatriceOriginale(copiedMatrix);
		for (int i=0; i<dimension; i++) {
			if (copiedMatrix[i][i]==0) {
				boolean scambioRiuscito= scambioRighe(i,copiedMatrix);
				if (!scambioRiuscito) return 0.0;
				numeroScambi++;
			}
			riduzioneAScala(i, copiedMatrix, 1);
		}
		double determinante = calcoloDeterminante(copiedMatrix);
		if (numeroScambi%2==0)
		return determinante;
		else return -determinante;
	}
	
	private void copiaMatriceOriginale(double[][] matrixWithIdentity) {
		for (int i=0; i<dimension; i++) {
			for (int j=0; j<dimension; j++) {
				matrixWithIdentity[i][j]=this.entrateMatrice[i][j];
			}
		}
	}
	
	private boolean scambioRighe(int numeroRiga, double[][] copiedMatrix) {
		double[] riga= copiedMatrix[numeroRiga];
		int numeroRigaDaScambiare= trovaRiga(numeroRiga, copiedMatrix);
		if (numeroRigaDaScambiare!=0) {
			copiedMatrix[numeroRiga]=copiedMatrix[numeroRigaDaScambiare];
			copiedMatrix[numeroRigaDaScambiare]=riga;
			return true;
		}
			return false;
	}
	
	private int trovaRiga(int numeroRiga, double[][] copiedMatrix) {
		int RigaDaCercare= numeroRiga+1;
		while(RigaDaCercare<dimension) {
			if (copiedMatrix[RigaDaCercare][numeroRiga]!=0.0)
				return RigaDaCercare;
			RigaDaCercare++;
		}
		return 0;
	}
	
	private void riduzioneAScala(int i, double[][] copiedMatrix, int dim) {
		for (int j=i+1; j<dimension; j++) {
			double CostanteDiRapporto= - copiedMatrix[j][i]/copiedMatrix[i][i];
			for (int z=i; z<dim*dimension; z++) {
				copiedMatrix[j][z]+= CostanteDiRapporto*copiedMatrix[i][z];
			}
		}
	}

	private double calcoloDeterminante(double[][]copiedMatrix) {
		double determinante=1.0;
		for (int i=0; i<dimension; i++) {
			determinante*=copiedMatrix[i][i];
		}
		return determinante;
	}

	
	
	
	
	
	public double[][] matriceInversa() {
		double[][] matrixWithIdentity= new double[dimension][dimension*2];
		costruzioneMatriceConIdentità(matrixWithIdentity);
		for (int i=0; i<dimension; i++) {
			if (matrixWithIdentity[i][i]==0) {
				boolean scambioRiuscito= scambioRighe(i,matrixWithIdentity);
				if (!scambioRiuscito) return null;
			}
			riduzioneAScala(i,matrixWithIdentity, 2);
		}
		riduzioneAScalaSecondoPassaggio(matrixWithIdentity);
		calcoloInversa(matrixWithIdentity);
		return copiaMatriceInversa(matrixWithIdentity);
	}
	
	private void costruzioneMatriceConIdentità(double[][] matrixWithIdentity) {
		copiaMatriceOriginale(matrixWithIdentity);
		for (int i=0; i<dimension; i++) {
			for (int j=dimension; j<2*dimension; j++) {
				if (i+dimension==j) matrixWithIdentity[i][j]=1.0;
				else matrixWithIdentity[i][j]=0.0;
			}
		}
	}
	
	private void riduzioneAScalaSecondoPassaggio(double[][] matrixWithIdentity) {
		for (int i=dimension-2; i>=0; i--) {
			for (int j=i; j>=0; j--) {
				double CostanteDiRapporto= - matrixWithIdentity[j][i+1]/matrixWithIdentity[i+1][i+1];
				for (int z=i+1; z<2*dimension; z++) {
					matrixWithIdentity[j][z]+= CostanteDiRapporto*matrixWithIdentity[i+1][z];
				}
			}
		}
	}
	
	private void calcoloInversa(double[][] matrixWithIdentity) {
		for (int i=0; i<dimension; i++) {
			double fattoreDiCorrezione= 1.0/matrixWithIdentity[i][i];
			matrixWithIdentity[i][i]*=fattoreDiCorrezione;
			for (int j=dimension; j<2*dimension; j++) {
				matrixWithIdentity[i][j]*=fattoreDiCorrezione;
			}
		}
	}
	
	private double[][] copiaMatriceInversa(double[][] matrixWithIdentity) {
		double[][] matriceInversa=new double[dimension][dimension];
		for (int i=0; i<dimension; i++) {
			for (int j=dimension; j<2*dimension; j++) {
				matriceInversa[i][j-dimension]=matrixWithIdentity[i][j];
			}
		}
		return matriceInversa;
	}
}
