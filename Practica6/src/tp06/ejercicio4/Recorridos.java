package tp06.ejercicio4;

import tp06.ejercicio3.Arista;
import tp06.ejercicio3.ColaGenerica;
import tp06.ejercicio3.Grafo;
import tp06.ejercicio3.ListaEnlazadaGenerica;
import tp06.ejercicio3.ListaGenerica;
import tp06.ejercicio3.Vertice;

public class Recorridos<T> {

	
	public ListaGenerica<T> dfs(Grafo<T> grafo) {
		ListaGenerica<T> recorrido = new ListaEnlazadaGenerica<T>();
		
		boolean[] visitados = new boolean[grafo.listaDeVertices().tamanio() + 1];
		
		for(int i = 1; i <= grafo.listaDeVertices().tamanio(); i++) {
			if(!visitados[i]) {
				this.dfs(recorrido, grafo.vertice(i), visitados, grafo);
			}
		}
		
		return recorrido;
	}
	
	private void dfs(ListaGenerica<T> recorrido, Vertice<T> vertice, boolean[] visitados, Grafo<T> grafo) {
		visitados[vertice.getPosicion()] = true;
		recorrido.agregarFinal(vertice.dato());
		ListaGenerica<Arista<T>> adyacentes = grafo.listaDeAdyacentes(vertice);
		adyacentes.comenzar();
		while(!adyacentes.fin()) {
			Arista<T> actual = adyacentes.proximo();
			
			if(!visitados[actual.verticeDestino().getPosicion()]) {
				this.dfs(recorrido, grafo.vertice(actual.verticeDestino().getPosicion()), visitados, grafo);
			}
			
			
		}
	}
	
	
	public ListaGenerica<T> bfs(Grafo<T> grafo) {
		ListaGenerica<T> recorrido = new ListaEnlazadaGenerica<T>();
		boolean[] visitados = new boolean[grafo.listaDeVertices().tamanio() + 1];
		
		for(int i = 1; i <= grafo.listaDeVertices().tamanio(); i++) {
			if(!visitados[i]) {
				this.bfs(grafo, i, visitados, recorrido);
			}
		}
		
		return recorrido;
	}
	
	private void bfs(Grafo<T> grafo, int pos, boolean[] visitados, ListaGenerica<T> recorrido) {
		ColaGenerica<Vertice<T>> cola = new ColaGenerica<Vertice<T>>();
		
		cola.encolar(grafo.vertice(pos));
		visitados[pos] = true;
		
		while(!cola.esVacia()) {
			Vertice<T> actual = cola.desencolar();
			recorrido.agregarFinal(actual.dato());
			
			ListaGenerica<Arista<T>> adyacentes = grafo.listaDeAdyacentes(actual);
			adyacentes.comenzar();
			while(!adyacentes.fin()) {
				Arista<T> aristaActual = adyacentes.proximo();
				if (!visitados[aristaActual.verticeDestino().getPosicion()]) {
					visitados[aristaActual.verticeDestino().getPosicion()] = true;
					cola.encolar(aristaActual.verticeDestino());
				}
			}	
			
		}
		
	} 
	
	
}
