package classes;

/*
 *	Descrição: Classe que representa o Percurso
 */
public class Percurso {
	
	//Atributos
	private int distancia;
	
	/*
	 *	Descrição: Construtor da classe
	 */
	public Percurso(int nDistancia) {
		this.distancia = nDistancia;
	}
	
	/*
	 *	Descrição: Método para se obter externamente a distância do percurso
	 *
	 *	@return int : Distância do percurso
	 */
	public int getDistancia() {
		return distancia;
	}

}

