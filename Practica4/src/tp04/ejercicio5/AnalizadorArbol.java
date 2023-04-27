package tp04.ejercicio5;

import tp02.ejercicio2.ListaGenerica;
import tp04.ejercicio3.ColaGenerica;
import tp04.ejercicio4.ArbolGeneral;

public class AnalizadorArbol {

	/*
	 * Método iterativo para calcular el nivel con más retardo de envío promedio
	 * Se utiliza un recorrido por niveles
	 * */
	public int devolverMaximoPromedio(ArbolGeneral<AreaEmpresa> arbol) {
		
		int retardoTotalNivel = 0, retardoPromedioMax = 0, cantNodosNivel = 0;
		ArbolGeneral<AreaEmpresa> nodoActual;
		ColaGenerica<ArbolGeneral<AreaEmpresa>> cola = new ColaGenerica<ArbolGeneral<AreaEmpresa>>();
		cola.encolar(arbol);
		cola.encolar(null);
		
		while(!cola.esVacia()) {
			nodoActual = cola.desencolar();
			if(nodoActual != null) {
				// Proceso el nodo si no es una marca de fin de nivel
				cantNodosNivel++;
				System.out.print(cantNodosNivel);
				retardoTotalNivel += nodoActual.getDato().getDemoraTransmision(); 
				// Encolo los hijos
				if(nodoActual.tieneHijos()) {
					ListaGenerica<ArbolGeneral<AreaEmpresa>> hijos = nodoActual.getHijos();
					hijos.comenzar();
					while(!hijos.fin()) {
						cola.encolar(hijos.proximo());
					}
				}	
			} else {
				int retardoPromedioActual = retardoTotalNivel / cantNodosNivel;
				if(retardoPromedioActual > retardoPromedioMax) {
					retardoPromedioMax = retardoPromedioActual;
				}
				if(!cola.esVacia()) {
					cola.encolar(null);
					retardoTotalNivel = 0;
					cantNodosNivel = 0;
				}
			}
		}
		
		
		return retardoPromedioMax;
	}
	
	
	
	
	
	
}
