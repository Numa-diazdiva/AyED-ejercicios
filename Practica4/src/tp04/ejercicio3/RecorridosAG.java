package tp04.ejercicio3;

import tp02.ejercicio2.ListaEnlazadaGenerica;
import tp02.ejercicio2.ListaGenerica;
import tp04.ejercicio1.ArbolGeneral;

public class RecorridosAG {

	ArbolGeneral<Integer> arbol;
	
	public RecorridosAG(Integer num) {
		this.arbol = new ArbolGeneral<Integer>(num);
		
	}
	
	public ListaGenerica<Integer> numerosImparesMayoresQuePreOrden (ArbolGeneral<Integer> a, Integer n) {
		ListaGenerica<Integer> imparesMayores = new ListaEnlazadaGenerica<Integer>();
		this.recorridoPreOrdenImpares(a, n, imparesMayores);
		return imparesMayores;
	}
	
	public void recorridoPreOrdenImpares(ArbolGeneral<Integer> a, Integer n, ListaGenerica<Integer> l) {
		if(! a.esVacio()) {
			if ((a.getDato() % 2 != 0) && (a.getDato() > n)) {
				l.agregarFinal(a.getDato());
			}
			
			if(! a.esHoja()) {
				ListaGenerica<ArbolGeneral<Integer>> hijos = a.getHijos();
				hijos.comenzar();
				while(!hijos.fin()) {
					recorridoPreOrdenImpares(hijos.proximo(), n, l);
				}
			}
		}
	}
	
	
	public ListaGenerica<Integer> numerosImparesMayoresQueInOrden (ArbolGeneral<Integer> a, Integer n) {
		ListaGenerica<Integer> imparesMayores = new ListaEnlazadaGenerica<Integer>();
		this.recorridoInOrdenImpares(a, n, imparesMayores);
		return imparesMayores;	
	}
	
	public void recorridoInOrdenImpares(ArbolGeneral<Integer> a, Integer n, ListaGenerica<Integer> l) {
		if(! a.esVacio()) {
			
			if(! a.esHoja()) {
				ListaGenerica<ArbolGeneral<Integer>> hijos = a.getHijos();
				hijos.comenzar();
				recorridoInOrdenImpares(hijos.proximo(), n, l);
				if (esImparMayorQue(a.getDato(), n)) {
					l.agregarFinal(a.getDato());
				}
				while(!hijos.fin()) {
					recorridoInOrdenImpares(hijos.proximo(), n, l);
				}
			} else {
				if (esImparMayorQue(a.getDato(), n)) {
					l.agregarFinal(a.getDato());
				}
			}
		}
	}
	
	
	public ListaGenerica<Integer> numerosImparesMayoresQuePostOrden (ArbolGeneral<Integer> a, Integer n) {
		ListaGenerica<Integer> imparesMayores = new ListaEnlazadaGenerica<Integer>();
		this.recorridoPostOrdenImpares(a, n, imparesMayores);
		return imparesMayores;
	}
	
	public void recorridoPostOrdenImpares(ArbolGeneral<Integer> a, Integer n, ListaGenerica<Integer> l) {
		if (!a.esVacio()) {
			if (! a.esHoja()) {
				ListaGenerica<ArbolGeneral<Integer>> hijos = a.getHijos();
				hijos.comenzar();
				while(!hijos.fin()) {
					recorridoPostOrdenImpares(hijos.proximo(), n, l);
				}
			}
			if(this.esImparMayorQue(a.getDato(), n)) {
				l.agregarFinal(a.getDato());
			}
		}
	}
	
	
	public ListaGenerica<Integer> numerosImparesMayoresQuePorNiveles(ArbolGeneral<Integer> a, Integer n) {
		ListaGenerica<Integer> imparesMayores = new ListaEnlazadaGenerica<Integer>();
		ColaGenerica<ArbolGeneral<Integer>> cola = new ColaGenerica<ArbolGeneral<Integer>>();
		cola.encolar(a);
		
		while(!cola.esVacia()) {
			ArbolGeneral<Integer> nodoActual = cola.desencolar();
			if (!nodoActual.esVacio()) {
				if(esImparMayorQue(nodoActual.getDato(), n)) {
					imparesMayores.agregarFinal(nodoActual.getDato());
				}
				if (nodoActual.tieneHijos()) {
					ListaGenerica<ArbolGeneral<Integer>> hijos = a.getHijos();
					hijos.comenzar();
					while(!hijos.fin()) {
						cola.encolar(hijos.proximo());
					}
				}				
			}
		}
		return imparesMayores;
	}
	
	public boolean esImparMayorQue(Integer n1, Integer n2) {
		return ((n1 % 2) != 0) && n1 > 2;
	}	
	
}
