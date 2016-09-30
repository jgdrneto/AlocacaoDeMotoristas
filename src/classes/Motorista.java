package classes;

import classes.Algoritmo.CONSTANTES;

public class Motorista {
	
	private int indice;
	private int distPercorrida;
	
	public Motorista(int nIndice) {
		this.indice = nIndice;
		this.distPercorrida=0;
	}
	
	public int getDistPercorrida() {
		return distPercorrida;
	}

	public void setDistPercorrida(int distPercorrida) {
		this.distPercorrida = distPercorrida;
	}

	public int getIndice() {
		return indice;
	}
	
	public int getCusto(){
		if(this.distPercorrida > CONSTANTES.DISTMAXTRABMOT.VALOR){
			return CONSTANTES.CUSTOKMNORMAL.VALOR * CONSTANTES.DISTMAXTRABMOT.VALOR +
				   CONSTANTES.CUSTOKMEXTRA.VALOR *(this.distPercorrida-CONSTANTES.DISTMAXTRABMOT.VALOR);
		}else{
			return CONSTANTES.CUSTOKMNORMAL.VALOR*this.distPercorrida;
		}
	}
	
}
