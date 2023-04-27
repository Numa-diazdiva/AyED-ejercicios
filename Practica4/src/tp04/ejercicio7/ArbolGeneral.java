package tp04.ejercicio7;

import tp02.ejercicio2.ListaEnlazadaGenerica;
import tp02.ejercicio2.ListaGenerica;
import tp04.ejercicio3.ColaGenerica;

public class ArbolGeneral<T> {

	private T dato;

	private ListaGenerica<ArbolGeneral<T>> hijos = new ListaEnlazadaGenerica<ArbolGeneral<T>>();

	public T getDato() {
		return dato;
	}

	public void setDato(T dato) {
		this.dato = dato;
	}

	public void setHijos(ListaGenerica<ArbolGeneral<T>> hijos) {
		if (hijos==null)
			this.hijos = new ListaEnlazadaGenerica<ArbolGeneral<T>>();
		else
			this.hijos = hijos;
	}

	public ArbolGeneral(T dato) {
		this.dato = dato;
	}

	public ArbolGeneral(T dato, ListaGenerica<ArbolGeneral<T>> hijos) {
		this(dato);
		if (hijos==null)
			this.hijos = new ListaEnlazadaGenerica<ArbolGeneral<T>>();
		else
			this.hijos = hijos;
	}

	public ListaGenerica<ArbolGeneral<T>> getHijos() {
		return this.hijos;
	}

	public void agregarHijo(ArbolGeneral<T> unHijo) {

		this.getHijos().agregarFinal(unHijo);
	}

	public boolean esHoja() {

		return !this.tieneHijos();
	}
	
	public boolean tieneHijos() {
		return !this.hijos.esVacia();
	}
	
	public boolean esVacio() {
		return this.dato == null && !this.tieneHijos();
	}

	

	public void eliminarHijo(ArbolGeneral<T> hijo) {
		if (this.tieneHijos()) {
			ListaGenerica<ArbolGeneral<T>> hijos = this.getHijos();
			if (hijos.incluye(hijo)) 
				hijos.eliminar(hijo);
		}
	}
	
	public ListaEnlazadaGenerica<T> preOrden() {
		return null;
	}
	
	public Integer altura() {
		int altura = 0;
		if(this.tieneHijos()) {
			recorridoAltura(altura);
		}
		return altura;
	}
	
	/*
	 * Está bien, pero otra solución más recursiva, con el cálculo a la vuelta puede ser más clara.
	 * */
	public Integer recorridoAltura(int alturaPrevia) {
		int altura = alturaPrevia, alturaMax = 0;
		if(! this.esVacio()) {
			altura++;
			if(!this.esHoja()) {
				ListaGenerica<ArbolGeneral<T>> hijos = this.getHijos();
				hijos.comenzar();
				while(!hijos.fin()) {
					int alturaSubArbol = hijos.proximo().recorridoAltura(altura); 
					if(alturaSubArbol > alturaMax) {
						alturaMax = alturaSubArbol;
					}
				}
			}
		}
		return altura + alturaMax;
	}
	
	/*
	 * Solución "a la vuelta"
	 * */
	public Integer recorridoAlturaVolviendo() {
		int alturaMax = 0;
		if(this.esHoja()) {
			return 0;
		} else {
			ListaGenerica<ArbolGeneral<T>> hijos = this.getHijos();
			hijos.comenzar();
			while(!hijos.fin()) {
				int alturaSubArbol = hijos.proximo().recorridoAlturaVolviendo(); 
				if(alturaSubArbol > alturaMax) {
					alturaMax = alturaSubArbol;
				}
			}
		}
		return alturaMax + 1;
	}
	
	/*
	 * -->>> corregir si está bien
	 * */
	public Integer nivel(T dato) {
		int nivel = 0;
		ColaGenerica<ArbolGeneral<T>> cola = new ColaGenerica<ArbolGeneral<T>>();
		cola.encolar(this);
		cola.encolar(null);
		while(!cola.esVacia()) {
			ArbolGeneral<T> actual = cola.desencolar();
			if (actual != null) {
				if(actual.getDato().equals(dato)) {
					return nivel;
				}
				if(actual.tieneHijos()) {
					ListaGenerica<ArbolGeneral<T>> hijos = actual.getHijos();
					hijos.comenzar();
					while(!hijos.fin()) {
						cola.encolar(hijos.proximo());
					}
				}
			} else {
				if(!cola.esVacia()) {
					nivel++;
					cola.encolar(null);
				}
			}
		}
		return -1;
	}	

	/*
	 * Calculo el ancho con un recorrido por niveles
	 * no pide devolver el nivel
	 * ---> checar
	 * */
	public Integer ancho() {
		int maximaCantDeNodosPorNivel = 0, cantActual = 0;
		ColaGenerica<ArbolGeneral<T>> cola = new ColaGenerica<ArbolGeneral<T>>();
		cola.encolar(this);
		cola.encolar(null);
		
		while(!cola.esVacia()) {
			ArbolGeneral<T> actual = cola.desencolar();
			if(actual != null) {
				cantActual++;
				if(actual.tieneHijos()) {
					ListaGenerica<ArbolGeneral<T>> hijos = actual.getHijos();
					hijos.comenzar();
					while(!hijos.fin()) {
						cola.encolar(hijos.proximo());
					}
				}
			} else {
				if(cantActual > maximaCantDeNodosPorNivel) {
					maximaCantDeNodosPorNivel = cantActual;
				}
				if(!cola.esVacia()) {
					cantActual = 0;
					cola.encolar(null);
				}
			}
		}
		
		return maximaCantDeNodosPorNivel;
	}

	/*
	 * Ejercicio 6
	 * */
	public boolean esAncestro(ArbolGeneral<T> a, ArbolGeneral<T> b) {	
		return a.buscar(b);
	}
	
	public boolean buscar(ArbolGeneral<T> nodo) {
		if(this.esHoja()) {
			if (this.equals(nodo)) {
				return true;
			}
		} else {
			this.hijos.comenzar();
			while(!hijos.fin()) {
				if(hijos.proximo().buscar(nodo)) {
					return true;
				}
			}
		}
		return false;
	}
	
	/*
	 * Ejercicio 7
	 * Suponiendo que es un nodo de red de agua
	 * */
	public double minimoCaudal(double caudal) {
	
		if(this.esHoja()) {
			// Caso base: devuelve su propio caudal (arista que le llega)
			return caudal;
		} else {
			// Sino, calculo el caudal de las aristas para mandar a los hijos y me quedo con el mínimo que devuelvan entre ellos recursivamente
			double caudalMin = caudal; // o Double.MAX_VALUE ? 
			double caudalEnviado = caudal / this.hijos.tamanio();
			this.hijos.comenzar();
			while(!hijos.fin()) {
				double valorActual = hijos.proximo().minimoCaudal(caudalEnviado);
				if(valorActual < caudalMin) {
					caudalMin = valorActual;
				}
			}
			return caudalMin;
		}
	}
	
}