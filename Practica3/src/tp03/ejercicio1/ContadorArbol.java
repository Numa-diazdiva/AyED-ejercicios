package tp03.ejercicio1;

public class ContadorArbol {

	private ArbolBinario<Integer> arbol;
	
	public ContadorArbol() {
		this.arbol = new ArbolBinario<Integer>();
	}
	
	
	private void paresInOrden(ArbolBinario<Integer> arbol, ListaEnlazadaGenerica<Integer> enteros) {
		if (arbol != null) {
			if(arbol.tieneHijoIzquierdo()) {
				paresInOrden(arbol.getHijoIzquierdo(), enteros);
			}
			if(arbol.getDato() % 2 == 0) {
				enteros.agregarFinal(arbol.getDato());
			}
			if(arbol.tieneHijoDerecho()) {
				paresInOrden(arbol.getHijoDerecho(), enteros);
			}
		}
	}
	
	/*
	 * Recorre el árbol binario de forma InOrden
	 * y devuelve una lista con aquellos nodos que sean pares
	 * */
	public ListaEnlazadaGenerica<Integer> numerosParesInOrden() {
		ListaEnlazadaGenerica<Integer> enteros = new ListaEnlazadaGenerica<Integer>();
		paresInOrden(this.arbol, enteros);
		return enteros;
	}

	private void paresPostOrden(ArbolBinario<Integer> arbol, ListaEnlazadaGenerica<Integer> enteros) {
		if (arbol != null) {
			if(arbol.tieneHijoIzquierdo()) {
				paresInOrden(arbol.getHijoIzquierdo(), enteros);
			}
			if(arbol.tieneHijoDerecho()) {
				paresInOrden(arbol.getHijoDerecho(), enteros);
			}
			if(arbol.getDato() % 2 == 0) {
				enteros.agregarFinal(arbol.getDato());
			}
		}
	}
	
	/*
	 * Recorre el árbol binario de forma PostOrden
	 * y devuelve una lista con aquellos nodos que sean pares
	 * */
	public ListaEnlazadaGenerica<Integer> numerosParesPostOrden() {
		ListaEnlazadaGenerica<Integer> enteros = new ListaEnlazadaGenerica<Integer>();
		paresPostOrden(this.arbol, enteros);
		return enteros;
	}

}
