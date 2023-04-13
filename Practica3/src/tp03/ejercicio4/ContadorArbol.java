package tp03.ejercicio4;

public class ContadorArbol {

	private RedBinariaLlena<Integer> arbol;
	
	public ContadorArbol() {
		this.arbol = new RedBinariaLlena<Integer>();
	}
	
	
	private void paresInOrden(RedBinariaLlena<Integer> arbol, ListaEnlazadaGenerica<Integer> enteros) {
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

	private void paresPostOrden(RedBinariaLlena<Integer> arbol, ListaEnlazadaGenerica<Integer> enteros) {
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
