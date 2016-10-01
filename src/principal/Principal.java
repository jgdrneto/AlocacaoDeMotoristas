package principal;

import java.util.ArrayList;
import java.util.List;

import classes.Algoritmo;

public class Principal {

	public static void main(String[] args) {
		
		Algoritmo algoritmo = new Algoritmo();
		
		/*
		List<Integer> i = new ArrayList<Integer>();
		
		i.add(0);
		
		System.out.println("Custo minimo:" + algoritmo.custoQuaseMinimo(i) + " Custo: "+ algoritmo.calcularCusto(algoritmo.custoQuaseMinimo(i)));
		*/
		
		algoritmo.start();
		
		//System.out.println(algoritmo.getMelhorSolucao().toString());
	
	}

}
