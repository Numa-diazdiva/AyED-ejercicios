package practica1B;

import java.util.Scanner;

public class Aplicacion1Bpunto2 {

	
	public static void main(String[] args) {
		// Soluciones sin usar clases de collections?
		int n;
		Scanner s = new Scanner(System.in);
		System.out.println("Ingrese números para calcular sus n múltiplos. Para salir ingrese un número menor a 1: ");
		n = s.nextInt();
		while(n > 0) {
			int[] multiplos = multiplosEnteros(n);
			for(int i = 0; i < n; i++) {
				System.out.println(multiplos[i]);
			}
			System.out.println("Ingrese números para calcular sus n múltiplos. Para salir ingrese un número menor a 1: ");
			n = s.nextInt();
		}
		s.close();
	}
	
	
	/*
	 * Devuelve un array de enteros con los primeros n múltiplos del
	 * número pasado por parámetro
	 * 
	 * @param n número de base para calcular múltiplos
	 * */
	public static int[] multiplosEnteros(int n) {
		int[] multiplos = new int[n];
		for(int i = 0; i < n; i++) {
			multiplos[i] = n * (i + 1);
		}
		return multiplos;
	}
	
}
