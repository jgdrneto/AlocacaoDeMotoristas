package classes;

import classes.Algoritmo.CONSTANTES;

public class Viagem {
	private Motorista motorista;
	private Percurso percurso;
	private int custo;
	
	public Viagem(Motorista nMotorista, Percurso nPercurso) {
		this.motorista = nMotorista;
		this.percurso = nPercurso;
		
		//custoPercurso();
	}
	/*
	private void custoPercurso() {
		if(motorista.getDistPercorrida()+percurso.getDistancia() > CONSTANTES.DISTMAXTRABMOT.VALOR){
			this.custo = CONSTANTES.CUSTOKMNORMAL.VALOR *((CONSTANTES.DISTMAXTRABMOT.VALOR - motorista.getDistPercorrida()) +
						 CONSTANTES.CUSTOKMEXTRA.VALOR *(motorista.getDistPercorrida()+percurso.getDistancia() - CONSTANTES.DISTMAXTRABMOT.VALOR));
		}else{
			this.custo = CONSTANTES.CUSTOKMNORMAL.VALOR*percurso.getDistancia();
		}
	}
	*/
	public void setMotorista(Motorista motorista) {
		this.motorista = motorista;
	}

	public void setPercurso(Percurso percurso) {
		this.percurso = percurso;
	}

	public Motorista getMotorista() {
		return motorista;
	}

	public Percurso getPercurso() {
		return percurso;
	}
	
	public int getCusto(){
		return this.custo;
	}
}
