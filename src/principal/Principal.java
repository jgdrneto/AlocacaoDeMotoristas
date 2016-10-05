package principal;

import classes.Algoritmo;

/*
 *	Descrição: Classe principal do projeto
 */
public class Principal {
	
	/*
	 *	Descrição: Método de inicio do programa
	 *
	 *	@params String[] : Vetor com os argumentos presentes no terminal na hora da execução
	 */
	public static void main(String[] args) {
		
		//Criando objeto algoritmo
		Algoritmo algoritmo = new Algoritmo();
		
		//Iniciando algoritmo
		algoritmo.start();
		
	}

}
