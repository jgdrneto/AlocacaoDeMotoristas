package classes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
 *	Descrição: Classe responsável pela execução do algoritmo
 */
public class Algoritmo {
	
	/*
	 *	ENUM com constantes definidas para realizar o algoritmo
	 *	
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

	
	private List<Percurso> percursos;
	private List<Motorista> motoristas;
	
	private int quantidadeMot;
	private int quantidadePer;
	
	private List<Integer> ls;
	
	public Algoritmo(){
		this.percursos = new ArrayList<Percurso>();
		this.motoristas = new ArrayList<Motorista>();	
	}

	
	public void start(){
		
		//Escolhendo execução personalizada ou execução de casos de testes
		switch(inicio()){
			case 1:	//Execução de casos de testes
				casosTestes();
			break;	
			case 2: //Execução personalizada
				iniciarValores();
			break;
		}
	}
	
	private void casosTestes() {
		//Definição da quantidade de motoristas e percursos para a execução do caso de testes
		this.quantidadeMot=3;	//Quantidade de motoristas
		this.quantidadePer=10;	//Quantidade de percursos
		System.out.println("Caso de testes 1");
		System.out.println("--------------------------------------------");
		
		System.out.println("Quantidade de motoristas: "+ this.quantidadeMot);
		System.out.println("Quantidade de percursos: "+ this.quantidadeMot);
		System.out.println("Distância de cada percurso (KM):");
		for(int i=0;i<this.quantidadePer;i++){
			
		}
	}


	public int inicio(){
		int escolha=0;
		
		do{
			System.out.println("Software para encontra a melhor solução de alocação de motorista");
			System.out.println("Escolha uma das opções abaixo:");
			System.out.println("1 - Usar casos de testes novos");
			System.out.println("2 - Usar casos de testes feitos");
			System.out.print("->");
			try {
				escolha = System.in.read()-'0';
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}while(escolha<1 || escolha>2);	
		
		return escolha;
	}
	
	private void definirValores() {
		int escolha =0;
		do{
			System.out.println("Quantidade de motoristas(Número natural maior que 0):");
			System.out.print("->");
			try {
				escolha = System.in.read()-'0';
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}while(escolha<1 || escolha>=Integer.MAX_VALUE);	
		
		for(int i=0;i<escolha;i++){
			motoristas.add(new Motorista(i));
		}
		
		escolha=0;
		
		do{
			System.out.println("Quantidade de percursos(Número natural maior que 0):");
			System.out.print("->");
			try {
				escolha = System.in.read()-'0';
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}while(escolha<1 || escolha>=Integer.MAX_VALUE);
		
		preencherPercurso(escolha);
		
	}


	private void preencherPercurso(int escolha) {
		// TODO Auto-generated method stub
		
	}


	private void iniciarValores(){
		
		iniciarMotorista();
		
		iniciarPercursos();
		
		gerarUmaBoaSolucao();
		
		List<Integer> list = backtraking(new ArrayList<Integer>());
		
		System.out.println("Melhor solucao real: "+ list.toString() + "Custo: " + calcularCusto(list));
	}
	
	private void gerarUmaBoaSolucao() {
		
		ls = custoQuaseMinimo(new ArrayList<Integer>());
		
		System.out.println("Boa solução:" + ls.toString() +  " Custo: "+ calcularCusto(ls));
		
		
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
		
		
		this.percursos.add(new Percurso(10));
		this.percursos.add(new Percurso(80));
		this.percursos.add(new Percurso(30));
		this.percursos.add(new Percurso(20));
		this.percursos.add(new Percurso(50));
		
		this.percursos.add(new Percurso(30));
		this.percursos.add(new Percurso(40));
		this.percursos.add(new Percurso(30));
		this.percursos.add(new Percurso(80));
		this.percursos.add(new Percurso(70));
		
		this.percursos.add(new Percurso(70));
		this.percursos.add(new Percurso(40));
		this.percursos.add(new Percurso(60));
		this.percursos.add(new Percurso(50));
		this.percursos.add(new Percurso(60));
		
	}

public List<Integer> backtraking(List<Integer> lMot){
	
	if(lMot.size()==percursos.size()){		
		return new ArrayList<Integer>(lMot);
	}else{
			List<List<Integer>> lista = new ArrayList<List<Integer>>();

			for(int i=0;i<motoristas.size();i++){
				
				lMot.add(motoristas.get(i).getIndice());
				
				if(calcularCusto(lMot)<calcularCusto(ls)){
					lista.add(backtraking(lMot));				
				}else{
					lista.add(ls);
				}
					
				lMot.remove(lMot.size()-1);
			}
			
			List<Integer> listaRetorno = new ArrayList<Integer>();
			
			for(int i=0;i<lista.size();i++){
				
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
	
	public List<Integer> custoQuaseMinimo(List<Integer> m){

		List<Integer> lM = new ArrayList<Integer>(m);
			
		List<Double> precedencia;
		
		while(lM.size()!=percursos.size()){
		
			precedencia = denotarPrecedencia(adicionarCustosMotoristas(lM), lM.size());
		
			lM.add(encontrarIndiceComMenorValor(precedencia));
			
		}
		
		return lM;
	}
	
	private int encontrarIndiceComMenorValor(List<Double> precedencia) {
		double valor=Double.MAX_VALUE;
		int indice=-1;
		
		for(int i=0;i<precedencia.size();i++){
			if(precedencia.get(i)<valor){
				valor = precedencia.get(i);
				indice = i;
			}
		}
		
		return indice;
	}

	private List<Double> denotarPrecedencia(List<Motorista> motoristas, int p) {
		List<Double> precedencia = new ArrayList<Double>();
		
		for(Motorista m : motoristas){
			/*
			System.out.println("Ja percorrido:" + m.getDistPercorrida());
			System.out.println("Percurso:" + percursos.get(p).getDistancia());
			*/
			if(m.getDistPercorrida()+percursos.get(p).getDistancia()==CONSTANTES.DISTMAXTRABMOT.VALOR){
				precedencia.add(0.0);
			}else{
				precedencia.add((double)(m.getDistPercorrida()+percursos.get(p).getDistancia())/CONSTANTES.DISTMAXTRABMOT.VALOR);
			}
			
			m.setDistPercorrida(0);
		}
			
		//System.out.println("Precedencia:" + precedencia.toString());
		
		return new ArrayList<Double>(precedencia);
	}

	private List<Motorista> adicionarCustosMotoristas(List<Integer> lMot){
		
		List<Motorista> motoristasLim = new ArrayList<Motorista>(this.motoristas);
		
		for(int i=0;i<lMot.size();i++){
			/*
			System.out.println("indice: "+ lMot.get(i));
			System.out.println("Motorista: " + motoristas.get(lMot.get(i)).getDistPercorrida());
			System.out.println("Percursos:" + percursos.get(i).getDistancia());
			*/
			motoristasLim.get(lMot.get(i)).setDistPercorrida(motoristasLim.get(lMot.get(i)).getDistPercorrida()+percursos.get(i).getDistancia());
		}
			
		return new ArrayList<Motorista>(motoristasLim);
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
}
