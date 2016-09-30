package classes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Algoritmo {
	
	/*unidade de distância: KILOMETROS
	 *Unidade para moeda: REAIS
	 *Unidade para tempo: MINUTOS
	 */
	public enum CONSTANTES{
		NUMMOTORISTAS(3),
		CUSTOKMNORMAL(1),
		CUSTOKMEXTRA(2),
		DISTMAXTRABMOT(100);
		
		public int VALOR;
		
		CONSTANTES(int nValor){
			this.VALOR=nValor;
		}
	}

	//Atributos
	private Solucao melhorSolucao;
	
	List<Percurso> percursos;
	List<Motorista> motoristas;
	
	List<Integer> lMot = new ArrayList<Integer>();
	
	List<Solucao> ls = new ArrayList<Solucao>();
	
	public Algoritmo(){
		this.percursos = new ArrayList<Percurso>();
		this.motoristas = new ArrayList<Motorista>();
	}
	
	public void start(){	
		inicio();
	}
	
	public void inicio(){
		int escolha=0;
		
		do{
			System.out.println("Software para encontra a melhor solução de alocação de motorista");
			System.out.println("Escolha uma das opções abaixo:");
			System.out.println("1 - Usar instancias novas");
			System.out.println("2 - Usar instâncias feitas");
			System.out.print("->");
			try {
				escolha = System.in.read()-'0';
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}while(escolha<1 || escolha>2);	
		
		switch(escolha){
			case 2:
				iniciarValores();
			break;	
		}		
	}
	
	private void iniciarValores(){
		
		iniciarMotorista();
		
		iniciarPercursos();
		
		List<Integer> list = backtranking(lMot);
		
		System.out.println(list.toString());
	}
	
	private void iniciarMotorista() {
		
		for(int i=0;i<CONSTANTES.NUMMOTORISTAS.VALOR;i++){
			motoristas.add(new Motorista(i));
		}
		
	}

	private void iniciarPercursos() {
		
		//Iniciado 20 tipos de percurso
		
		this.percursos.add(new Percurso(40));
		this.percursos.add(new Percurso(60));
		this.percursos.add(new Percurso(80));
		this.percursos.add(new Percurso(70));
		this.percursos.add(new Percurso(20));
		/*
		this.pecursos.add(new Percurso(10, 60));
		this.pecursos.add(new Percurso(80, 50));
		this.pecursos.add(new Percurso(30, 30));
		this.pecursos.add(new Percurso(20, 50));
		this.pecursos.add(new Percurso(50, 20));
		
		this.pecursos.add(new Percurso(30, 80));
		this.pecursos.add(new Percurso(40, 10));
		this.pecursos.add(new Percurso(30, 50));
		this.pecursos.add(new Percurso(80, 80));
		this.pecursos.add(new Percurso(70, 10));
		
		this.pecursos.add(new Percurso(70, 40));
		this.pecursos.add(new Percurso(40, 70));
		this.pecursos.add(new Percurso(60, 70));
		this.pecursos.add(new Percurso(50, 20));
		this.pecursos.add(new Percurso(60, 30));
		*/
	}

public List<Integer> backtranking(List<Integer> lMot){
	
	if(lMot.size()==percursos.size()){		
		return new ArrayList<Integer>(lMot);
	}else{
			List<List<Integer>> lista = new ArrayList<List<Integer>>();

			for(int i=0;i<motoristas.size();i++){
				
				lMot.add(motoristas.get(i).getIndice());
				
				lista.add(backtranking(lMot));
				
				lMot.remove(lMot.size()-1);
			}
			
			System.out.println(lista.toString());
			
			List<Integer> listaRetorno = new ArrayList<Integer>();
			
			for(int i=0;i<lista.size();i++){
				
				System.out.println("Lista:" + lista.get(i).toString() + "Custo:" + calcularCusto(lista.get(i)));
				
				if(listaRetorno.isEmpty()){
					listaRetorno = new ArrayList<Integer>(lista.get(i));
				}else{
					if(calcularCusto(listaRetorno) > calcularCusto(lista.get(i))){
						listaRetorno.clear();
						listaRetorno = new ArrayList<Integer>(lista.get(i));
					}
				}
			}
			
			return new ArrayList<Integer>(listaRetorno);
		}	
	}	
		
	public Integer calcularCusto(List<Integer> lMot){

		Integer custo= new Integer(0);
		
		for(int i=0;i<lMot.size();i++){
			/*
			System.out.println("indice: "+ lMot.get(i));
			System.out.println("Motorista: " + motoristas.get(lMot.get(i)).getDistPercorrida());
			System.out.println("Percursos:" + percursos.get(i).getDistancia());
			*/
			motoristas.get(lMot.get(i)).setDistPercorrida(motoristas.get(lMot.get(i)).getDistPercorrida()+percursos.get(i).getDistancia());
		}
		
		for(Motorista mot : motoristas){
			custo+=mot.getCusto();
			mot.setDistPercorrida(0);
		}
		
		
		return new Integer(custo);
		
	}

	public Solucao getMelhorSolucao() {
		return melhorSolucao;
	}	
	
}
