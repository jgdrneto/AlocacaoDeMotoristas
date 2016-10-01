package classes;

import classes.Algoritmo.CONSTANTES;

/*
 *	Classe que representa os motoristas
 */
public class Motorista {
	
	//Atributos
	private int indice;
	private int distPercorrida;
	
	/*
	 *	Descrição: Construtor da classe
	 *	
	 *	@params int : Indice do motoristas
	 */
	public Motorista(int nIndice) {
		this.indice = nIndice;
		this.distPercorrida=0;
	}
	
	/*
	 *	Descrição: Método para ter acesso a distância percorrida pelo motorista
	 *	
	 *	@return int : Distância percorrida pelo motorista
	 */
	public int getDistPercorrida() {
		return distPercorrida;
	}
	
	/*
	 *	Descrição: Método para mudar a distância percorrida pelo motorista
	 *	
	 *	@params int : Nova distância percorrida pelo motorista
	 */
	public void setDistPercorrida(int distPercorrida) {
		this.distPercorrida = distPercorrida;
	}
	
	/*
	 *	Descrição: Método que retorna o índice do motorista
	 *
	 *	@return int : Indice do motorista
	 */
	public int getIndice() {
		return indice;
	}
	
	/*
	 *	Descrição: Método que defini o custo para o motorista
	 *
	 *	@return int : Custo total do motorista 
	 */
	public int getCusto(){
		if(this.distPercorrida > CONSTANTES.DISTMAXTRABMOT.VALOR){
			return CONSTANTES.CUSTOKMNORMAL.VALOR * CONSTANTES.DISTMAXTRABMOT.VALOR +
				   CONSTANTES.CUSTOKMEXTRA.VALOR *(this.distPercorrida-CONSTANTES.DISTMAXTRABMOT.VALOR);
		}else{
			return CONSTANTES.CUSTOKMNORMAL.VALOR*this.distPercorrida;
		}
	}
	
}
