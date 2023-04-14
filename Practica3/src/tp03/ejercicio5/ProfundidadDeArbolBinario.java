package tp03.ejercicio5;

import tp03.ejercicio2.ArbolBinario;
import tp03.ejercicio2.ColaGenerica;

public class ProfundidadDeArbolBinario {

	private ArbolBinario<Integer> arbol;
	
	public ProfundidadDeArbolBinario() {
		this.arbol = new ArbolBinario<Integer>();
	}
	
	
	/*
	 * Entiendo que son todos los del nivel de profundidad n
	 * */
	public int sumaElementosProfundidad(int profundidad) {
		int suma = 0;
		int nivelActual = 0;
		ColaGenerica<ArbolBinario<Integer>> cola = new ColaGenerica<ArbolBinario<Integer>>();
		ArbolBinario<Integer> a = this.arbol;
		ArbolBinario<Integer> aux;
		cola.encolar(a);
		cola.encolar(null);
		while(! cola.esVacia() && nivelActual <= profundidad) {
			aux = cola.desencolar();
			if (aux != null) {
				if (nivelActual == profundidad) {
					suma += aux.getDato();
				} else {
					// Acá podemos evitar encolar nodos sin sentido con este else
					// chequeado en clase
					if(aux.tieneHijoIzquierdo()) {
						cola.encolar(aux.getHijoIzquierdo());
					}
					if(aux.tieneHijoDerecho()) {
						cola.encolar(aux.getHijoDerecho());
					}
				}
			} else {
				if(!cola.esVacia()) {
					nivelActual++;
					cola.encolar(null);
				}
			}
		}
		return suma;
	}
}
