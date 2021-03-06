package classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 *	Descrição: Classe responsável pela execução do algoritmo
 */
public class Algoritmo {
	
	/*
	 *	ENUM com constantes definidas para realizar o algoritmo
	 *	
	 *	@Const CUSTOKMNORMAL : Custo dado a cada KM realizado pelo motorista
	 *	@Const CUSTOKMEXTRA : Custo dado a cada KM realizado pelo motorista quando este excede o máximo de distância permitido
	 *	@Comst DISTMAXTRABMOT :	Distância máxima em KM que o motorista pode executar sem que seja contado como extra.
	 */
	public enum CONSTANTES{
		CUSTOKMNORMAL(1),
		CUSTOKMEXTRA(2),
		DISTMAXTRABMOT(90);
		
		public int VALOR;
		
		CONSTANTES(int nValor){
			this.VALOR=nValor;
		}
	}

	//Atributos
	public List<Percurso> percursos;		//Lista com os percursos a serem usados no algoritmo
	public List<Motorista> motoristas;		//Lista com os motoristas a serem usados no algoritmo
	
	private List<Integer> ls;				//Solução boa que será usada no algoritmo de backtraking
	
	private Scanner sc;						//Objeto para gerenciar a leitura do teclado
	/*
	 *	Descrição: Construtor da classe
	 */
	public Algoritmo(){
			
		//Iniciando atributos
		this.percursos = new ArrayList<Percurso>();
		this.motoristas = new ArrayList<Motorista>();
		this.sc =  new Scanner(System.in);
		
	}

	/*
	 *	Descrição: Método que inicia a execução do algoritmo
	 */
	public void start(){
		
		//Escolhendo execução personalizada ou execução de casos de testes
		switch(inicio()){
			case 1:	//Execução de casos de testes
				casosDeTestes();
			break;	
			case 2: //Execução personalizada
				definirValores();
			break;
		}
		
		executar();
		
		sc.close();
	}

	//Métodos com o caso de testes
	private void casosDeTestes() {
		
		int escolha=0;
			
		do{
			System.out.println("Escolha uma das opções abaixo:");
			System.out.println("1 - Teste 1");
			System.out.println("2 - Teste 2");
			System.out.println("3 - Teste 3");
			System.out.print("-> ");
					
			try {
				escolha = sc.nextInt();
			} catch (Exception e) {
				escolha=0;
			}
			
			//Aumentando espaçamento
			System.out.println("");
			
		}while(escolha<1 || escolha>3);	
				
		switch(escolha){
			case 1:
				//Instâncias do teste 1
				criarMot(3);
				criarPerEstatico(5);
			break;
			case 2:
				//Instâncias do teste 2
				criarMot(5);
				criarPerEstatico(10);
			break;
			case 3:
				criarMot(7);
				criarPerEstatico(9);
			break;	
		}
		
	}

	/*
	 *	Descrição: Método para execução principal do algoritmo
	 */
	private void executar() {
		
		//-------------------------------------------------------------------------
		
		//Área para execução principal do algoritmo
		
		//-------------------------------------------------------------------------
		System.out.println("Instâncias");
		System.out.println("--------------------------------------------");
		
		System.out.println("Quantidade de motoristas: "+ motoristas.size());
		System.out.println("Quantidade de percursos: "+ percursos.size());
		System.out.println(stringListPercursos());
		
		System.out.println("--------------------------------------------");
		
		System.out.println("Solução");
		
		System.out.println("--------------------------------------------");
		
		//Definindo tempo de execução
		
		//Tempo de inicio de execução da heurística
 		long inicio = System.currentTimeMillis();
		
		//Realizar método que retorna uma boa solução(nem sempre a melhor) para o problema.
		ls = custoQuaseMinimo(new ArrayList<Integer>());
		
		//Tempo total de execução da heurística
		long tempoExecucao = System.currentTimeMillis() - inicio;
		
		//Imprimindo uma solução boa para o problema com as instâncias informadas
		System.out.println("Solução Boa: " + ls.toString() + " Custo: " + calcularCusto(ls)) ;
		
		System.out.println("Tempo de execução em milisegundos: " + tempoExecucao);
		
		System.out.println("--------------------------------------------");
		
		//Tempo de inicio de execução da solução ótima
 		inicio = System.currentTimeMillis();
 		
		//Encontrando a melhor solução com backtraking com poldas por meio da ajuda da solução boa
		List<Integer> solucaoOtima = backtraking(new ArrayList<Integer>());
		
		//Tempo total de execução da solução ótima
		tempoExecucao = System.currentTimeMillis() - inicio;
		
		//Imprimindo uma solução ótima para o problema com as instâncias informadas
		System.out.println("Solução Ótima: " + solucaoOtima.toString() + " Custo: " + calcularCusto(solucaoOtima)) ;
		
		System.out.println("Tempo de execução em milisegundos: " + tempoExecucao);

		System.out.println("--------------------------------------------");
	}
	
	/*
	 *	Descrição: Método para transformar lista de percursos em uma string
	 */
	private String stringListPercursos(){
		String s= "Percursos = { ";
		
		for(int i=0;i<percursos.size();i++){
			
			s+= "Percurso " + (i+1) + " : " + percursos.get(i).getDistancia() + "";
			
			if(i!=percursos.size()-1){
				s+=", ";
			}
			
		}
		
		s+=" }";
		
		return s;
		
	}
	
	/*
	 *	Descrição: Método para criar os percursos a serem usados no algoritmo
	 *	
	 *	@params int: Quantidade de motoristas a serem usados no algoritmo 
	 */
	private void criarPerEstatico(int quantPer) {
		
		//Lista com valores inteiros para serem as distâncias nos percursos
		List<Integer> listaInteiros  = listaIntPercursos();
		
		//Criando percursos de acordo com o valores das distâncias presente em listaInteiros
		for(int i=0;i<quantPer;i++){
			this.percursos.add(new Percurso(listaInteiros.get(i)));
		}
	}
	
	/*
	 *	Descrição: Método para criar os motoristas a serem usados no algoritmo
	 *	
	 *	@params int: Quantidade de motoristas a serem usados no algoritmo 
	 */
	private void criarMot(int quantM) {
		for(int i=0;i<quantM;i++){
			this.motoristas.add(new Motorista(i));
		}
		
	}
	
	/*
	 *	Descrição: Método usado para inicio da execução do algoritmo
	 */
	private int inicio(){
		//Variável que obterá a escolha definida pelo usuário
		int escolha=0;
	
		//Solicita ao usuário que escolha a opção, repetindo até que ele use uma resolução aceitável
		do{
			System.out.println("Software para encontra a melhor solução de alocação de motorista\n");
			System.out.println("Escolha uma das opções abaixo:");
			System.out.println("1 - Usar casos de testes feitos");
			System.out.println("2 - Usar casos de testes novos");
			System.out.print("-> ");
					
			try {
				escolha = sc.nextInt();
			} catch (Exception e) {
				escolha=0;
			}
			
			System.out.println("");
			
		}while(escolha<1 || escolha>2);	
			
		//Retorno da escolha feita pelo usuário
		return escolha;
	}
	
	/*
	 *	Descrição: Método usado para definir os valores da quantidade de motoristas e de percursos
	 */
	private void definirValores() {
		int escolha =0;

		//Definindo quantidade de motoristas
		do{
			System.out.println("Quantidade de motoristas(Número natural maior que 0):");
			System.out.print("-> ");
			
			try {
				escolha = sc.nextInt();
			} catch (Exception e) {
				escolha = 0;
			}
			
			//Aumentando espaçamento
			System.out.println("");
			
		}while(escolha<1 || escolha>=Integer.MAX_VALUE);	
		
		//Criando os motoristas e adicionando-os na lista de motorista da classe
		criarMot(escolha);
		
		escolha=0;
		//Definindo quantidade de percursos
		do{
			System.out.println("Quantidade de percursos (Número natural maior que 0):");
			System.out.print("-> ");
			
			try {
				escolha = sc.nextInt();
			} catch (Exception e) {
				escolha=0;
			}
			
			//Aumentando espaçamento
			System.out.println("");
		
		}while(escolha<1 || escolha>=Integer.MAX_VALUE);
				
		preencherPercurso(escolha);
		
	}
	/*
	 *	Descrição: Definir as distâncias de cada percurso
	 *	
	 *	@params int : Quantidade de percursos a serem usados
	 */
	private void preencherPercurso(int escolha) {
		
		int segEscolha=0;
		
		// 20  = Quantidade de percursos estáticos existentes
		if(escolha<=20){
			do{
				System.out.println("Usar valores para distâncias já existentes:");
				System.out.println("1 - Sim");
				System.out.println("2 - Não");
				System.out.print("-> ");
				
				try {
					segEscolha = sc.nextInt();
				} catch (Exception e) {
					segEscolha=0;
				}
			}while(segEscolha<1 || segEscolha>2);
			
			//Definindo ação de acordo com escolha do usuário
			switch(segEscolha){
				case 1:
					//Usa valores estáticos na definição das distâncias dos percursos
					criarPerEstatico(escolha);
				break;
				case 2:
					//Criar novos percursos
					criarPercusos(escolha);
				break;	
			}
			
		}else{
			//Criar novos percursos
			criarPercusos(escolha);
		}	
	}
		
	/*
	 *	Descrição: Método para determinar novas instâncias
	 *
	 * 	@params int : Quantidade de percursos
	 */
	private void criarPercusos(int escolha) {
		
		int distancia=0;
		
		for(int i=0;i<escolha;i++){
			
			do{
				System.out.print("Defina a distância do percurso " + i + ": " );
				try {
					distancia = sc.nextInt();
				} catch (Exception e) {
					escolha=0;
				}
			}while(distancia<1 || distancia >=Integer.MAX_VALUE);

			percursos.add(new Percurso(distancia));
		}
	}

	/*
	 *	Descrição: Método com valores estáticos de percurso
	 *	
	 *	@return List<Integer> : Lista com os valores estáticos para usar nos percursos
	 */
	private List<Integer> listaIntPercursos() {
		
		List<Integer> list = new ArrayList<Integer>();
		 
		list.add(40);
		list.add(60);
		list.add(80);
		list.add(70);
		list.add(20);
		
		list.add(10);
		list.add(80);
		list.add(30);
		list.add(20);
		list.add(50);
		
		list.add(30);
		list.add(40);
		list.add(30);
		list.add(80);
		list.add(70);
		
		list.add(70);
		list.add(40);
		list.add(60);
		list.add(50);
		list.add(60);
		
		return list;
		
	}


	/*
	 *	Descrição: Algoritmo de backtraking para encontrar a solução ótima da alocação de motoristas
	 *
	 *	@params List<Integer> : Lista com os indices dos motoristas
	 *
	 *	@return List<Integer> : Lista com uma solução para o problema de alocação de motorista
	 */
	private List<Integer> backtraking(List<Integer> lMot){

		 //Se eu tiver todos os percursos com motoristas
		if(lMot.size()==percursos.size()){
			//Retornando uma solução
			return new ArrayList<Integer>(lMot);
		}else{
			//Lista para guarda todas as soluções dos nós filhos
			List<List<Integer>> lista = new ArrayList<List<Integer>>();
			
			//Buscando todas as opções possiveis de motoristas para encontrar a melhor solução
			for(int i=0;i<motoristas.size();i++){
				
				//Adiciando motorista a lista de solução
				lMot.add(motoristas.get(i).getIndice());
				
				//Verificando se o limite minimo da lista atual tem menor ou igual a boa solução calculada anteriormente
				if(calcularCusto(custoQuaseMinimo(lMot))<=calcularCusto(ls)){
					
					lista.add(backtraking(lMot));				
				}else{

					//Se a solução encontrada com a lista atual é igual que a solução da heurística, ela que será retornada (polda da árvore)
					lista.add(custoQuaseMinimo(lMot));
				}
				
				//Excluindo item da lista
				lMot.remove(lMot.size()-1);
			}
			
			//Aqui seŕá verificado qual das soluções obtidas nos nós filhos será passada para o nó pai
			
			List<Integer> listaRetorno = new ArrayList<Integer>();
				
			//Verificando qual das lista obtidas nos nós filhos tem menor custo
			for(int i=0;i<lista.size();i++){
				
				if(listaRetorno.isEmpty()){
					listaRetorno = new ArrayList<Integer>(lista.get(i));
				}else{
					if(calcularCusto(listaRetorno) > calcularCusto(lista.get(i))){
						listaRetorno = new ArrayList<Integer>(lista.get(i));
					}
				}
			}
			
			//Retornando para o nó pai a lista de indices dos motoristas com menor custo
			return new ArrayList<Integer>(listaRetorno);
		}	
	}	
	
	/*
	 *	Descrição: Algoritmo para encontrar uma solução muito boa para o problema
	 *	
	 *	@params List<Integer> : Lista na qual precisamos complementar com motoristas
	 *
	 *	@return List<Integer> : Lista com uma boa solução para o problema
	 */
	private List<Integer> custoQuaseMinimo(List<Integer> m){
		
		//Lista que será retornada com a solução
		List<Integer> lM = new ArrayList<Integer>(m);
		
		//Lista de precedência(	Quanto menor o valor, maior a precedência)
		List<Double> precedencia;
		
		//Enquanto eu não obter uma solução 
		while(lM.size()!=percursos.size()){
			
			//Verifica a precedência dos candidatos a realizar o percurso
			precedencia = denotarPrecedencia(adicionarDistanciasMotoristas(lM), lM.size());
			
			//Adiciona o candidato de maior precedência para realizar o percurso.
			lM.add(encontrarIndiceComMenorValor(precedencia));
			
		}
		
		return lM;
	}
	
	/*
	 *	Descrição: Algoritmo para encontrar o indice do motorista com o menor valor (Maior precedência)
	 *
	 *	@params List<Integer> : Lista com as precedências de cada motorista
	 *
	 *	@return int : Indice do motorista que tem maior precedência (menor valor na lista de entrada)
	 */
	private int encontrarIndiceComMenorValor(List<Double> precedencia) {
		
		//Iniciando variáveis
		double valor=Double.MAX_VALUE;
		int indice=-1;
		
		//Encontrando o indice de maior precedência
		for(int i=0;i<precedencia.size();i++){
			if(precedencia.get(i)<valor){
				valor = precedencia.get(i);
				indice = i;
			}
		}
		
		return indice;
	}
	
	/*
	 *	Descrição:
	 *
	 *	@params List<Motorista> : Lista de motorista para que possam ser realizadas as respectivas precedências na alocação do percurso
	 *	@params int : Percurso a ser definido o melhor motorista a fazê-lo
	 *
	 *	@return List<Double> : As precedências de cada motorista a realizar o percurso
	 */
	private List<Double> denotarPrecedencia(List<Motorista> motoristas, int p) {
		
		//Lista com as precedencias de cada motorista para receber o percurso
		List<Double> precedencia = new ArrayList<Double>();
		
		//Verifica para cada motorista
		for(Motorista m : motoristas){

			//Se a distancia do percurso + a distância já feita pelo motorista dê igual a quantidade de distância máxima sem custos extras, esse motorista tem precedência em relação aos outros
			if(m.getDistPercorrida()+percursos.get(p).getDistancia() == CONSTANTES.DISTMAXTRABMOT.VALOR){
				precedencia.add(0.0);
			}else{
				//Valores significa o quanto falta/excede para o motorista percorrer a distância máxima 
				precedencia.add((double)(m.getDistPercorrida()+percursos.get(p).getDistancia())/CONSTANTES.DISTMAXTRABMOT.VALOR);
			}
			
			m.setDistPercorrida(0);
		}
			
		//Retornando precedencia (Neste caso, maior precedencia é definida pelo menor valor)
		return new ArrayList<Double>(precedencia);
	}
	
	
	/*
	 *	Descrição : Adiciona as distâncias aos motoristas de acordo com a lista passada por parâmetro
	 *
	 *	@params List<Integer> : Lista com a sequência de motorista na mesma ordem dos percursos
	 *
	 *	@return Lista<Motorista> Lista com os motoristas com as suas respectivas distâncias modifcadas
	 */
	private List<Motorista> adicionarDistanciasMotoristas(List<Integer> lMot){
		
		List<Motorista> motoristasLim = new ArrayList<Motorista>(this.motoristas);
		
		for(int i=0;i<lMot.size();i++){
			//Adicionando distância percorrida ao motorista que tiver o mesmo índice que o presente no lMot.get(i)
			motoristasLim.get(lMot.get(i)).setDistPercorrida(motoristasLim.get(lMot.get(i)).getDistPercorrida()+percursos.get(i).getDistancia());
		}
			
		return new ArrayList<Motorista>(motoristasLim);
	}
	
	/*
	 *	Descrição : Calcula o custo para uma lista de motoristas 
	 *
	 *	@params List<Integer> : Lista com a sequência de motorista na mesma ordem dos percursos
	 */
	private Integer calcularCusto(List<Integer> lMot){
		
		//Variável que terá o custo
		Integer custo= new Integer(0);
		
		//Conseguindo o custo de cada motorista
		for(int i=0;i<lMot.size();i++){
			//Adicionando distância percorrida ao motorista que tiver o mesmo índice que o presente no lMot.get(i)
			motoristas.get(lMot.get(i)).setDistPercorrida(motoristas.get(lMot.get(i)).getDistPercorrida()+percursos.get(i).getDistancia());
		}
		
		//Somando os valores percorridos do motoristas e excluindo os valores individuais de cada (para que possam ser usados futuramente)
		for(Motorista mot : motoristas){
			custo+=mot.getCusto();
			mot.setDistPercorrida(0);
		}
		
		//Retornando custo total dos motoristas
		return new Integer(custo);
		
	}
}
