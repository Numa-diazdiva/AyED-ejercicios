package tp06.ejercicio6;

import tp06.ejercicio3.Grafo;
import tp06.ejercicio3.GrafoImplListAdy;
import tp06.ejercicio3.ListaEnlazadaGenerica;
import tp06.ejercicio3.ListaGenerica;
import tp06.ejercicio3.Vertice;

public class VisitaOslo {

	private Grafo<String> mapaOslo;
	
	public VisitaOslo() {
		this.mapaOslo = new GrafoImplListAdy<String>();
	}
	
	
	private int buscarSitio(String sitio, Grafo<String> lugares) {
		ListaGenerica<Vertice<String>> vertices = lugares.listaDeVertices();
		vertices.comenzar();
		while(!vertices.fin()) {
			Vertice<String> actual = vertices.proximo();
			if(actual.dato() == sitio) {
				return actual.getPosicion();
			}
			
		}
		return -1;
	}
	
	
	ListaGenerica<String> paseoEnBici(Grafo<String> lugares, String destino, int maxTiempo, ListaGenerica<String> lugaresRestringidos) {
		ListaGenerica<String> caminoApropiado;
		
		ListaGenerica<ListaEnlazadaGenerica<String>> caminosPosibles = new ListaEnlazadaGenerica<ListaEnlazadaGenerica<String>>();
		
		int verticeInicial = this.buscarSitio(destino, lugares);
		
		
		return caminoApropiado;
	}
	
	
	
}
