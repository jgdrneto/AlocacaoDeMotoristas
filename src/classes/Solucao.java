package classes;

import java.util.List;

public class Solucao {
	private List<Integer> lista;
	private int custo;
	
	public Solucao(List<Integer> nLista, int nCusto) {
		this.lista = nLista;
		this.custo = nCusto;
	}

	public List<Integer> getLista() {
		return lista;
	}

	public void setLista(List<Integer> lista) {
		this.lista = lista;
	}

	public int getCusto() {
		return custo;
	}

	public void setCusto(int custo) {
		this.custo = new Integer(custo);
	}
	
	@Override
	public String toString(){
		return "Arranjo: " + lista.toString() + " Custo: "+ this.custo;
	}
}
