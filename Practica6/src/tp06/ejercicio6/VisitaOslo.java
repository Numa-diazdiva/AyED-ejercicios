package tp06.ejercicio6;

import tp06.ejercicio3.Arista;
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
	
	public VisitaOslo(Grafo<String> mapa) {
		this.mapaOslo = mapa;
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
		CaminoConPesoTotal caminoApropiado = new CaminoConPesoTotal();
		ListaGenerica<String> caminoAux = new ListaEnlazadaGenerica<String>();
		boolean[] visitados = new boolean[lugares.listaDeVertices().tamanio() + 1];
		int verticeInicial = this.buscarSitio("Ayuntamiento", lugares);
		
		if(verticeInicial != -1) {
			this.busquedaCaminoApropiadoDFS(lugares, verticeInicial, destino, maxTiempo, lugaresRestringidos, caminoAux, caminoApropiado, visitados, 0);
		}

		return caminoApropiado.getCamino();
	}
	
	
	/*
	 * Retorna el primer camino que encuentra
	 * */
	private boolean busquedaCaminoApropiadoDFS(Grafo<String> lugares, int verticeActual, String destino, int maxTiempo, ListaGenerica<String> lugaresRestringidos, ListaGenerica<String> camino, CaminoConPesoTotal caminoApropiado, boolean[] visitados, int tiempoActual) {
		
		Vertice<String> vertice = lugares.vertice(verticeActual);
		camino.agregarFinal(vertice.dato());
		visitados[verticeActual] = true;
		boolean llegue = false;
		
		if(vertice.dato() != destino) {
			ListaGenerica<Arista<String>> aristas = lugares.listaDeAdyacentes(vertice);
			
			aristas.comenzar();
			while(!aristas.fin() && !llegue) {
				Arista<String> aristaActual = aristas.proximo();
				Vertice<String> verticeDestino = aristaActual.verticeDestino();
				if(!visitados[verticeDestino.getPosicion()] && !lugaresRestringidos.incluye(verticeDestino.dato()) && (tiempoActual + aristaActual.peso() <= maxTiempo) ) {
					llegue = this.busquedaCaminoApropiadoDFS(lugares, verticeDestino.getPosicion(), destino, maxTiempo, lugaresRestringidos, camino, caminoApropiado, visitados, tiempoActual + aristaActual.peso());
				}
			}
		} else {
			caminoApropiado.setCamino(camino.clonar());
			caminoApropiado.setTiempo(tiempoActual);
			llegue = true;
		}
		
		visitados[verticeActual] = false;
		camino.eliminarEn(camino.tamanio());
		return llegue;
	}
	
	/*
	 * Éste método evalúa varios caminos para quedarse con el menor
	 * */
	private void busquedaCaminoMinimoApropiadoDFS(Grafo<String> lugares, int verticeActual, String destino, int maxTiempo, ListaGenerica<String> lugaresRestringidos, ListaGenerica<String> camino, CaminoConPesoTotal caminoApropiado, boolean[] visitados, int tiempoActual) {
		
		Vertice<String> vertice = lugares.vertice(verticeActual);
		camino.agregarFinal(vertice.dato());
		visitados[verticeActual] = true;

		if(vertice.dato() != destino) {
			ListaGenerica<Arista<String>> aristas = lugares.listaDeAdyacentes(vertice);
			
			aristas.comenzar();
			while(!aristas.fin()) {
				Arista<String> aristaActual = aristas.proximo();
				Vertice<String> verticeDestino = aristaActual.verticeDestino();
				if(!visitados[verticeDestino.getPosicion()] && !lugaresRestringidos.incluye(verticeDestino.dato()) && (tiempoActual + aristaActual.peso() <= maxTiempo) ) {
					this.busquedaCaminoMinimoApropiadoDFS(lugares, verticeDestino.getPosicion(), destino, maxTiempo, lugaresRestringidos, camino, caminoApropiado, visitados, tiempoActual + aristaActual.peso());
				}
			}
		} else {
			if(caminoApropiado.esVacio()) {
				caminoApropiado.setCamino(camino.clonar());
				caminoApropiado.setTiempo(tiempoActual);
			} else {
				if(caminoApropiado.pesoTotal() > tiempoActual) {
					caminoApropiado.setCamino(camino.clonar());
					caminoApropiado.setTiempo(tiempoActual);
				}
			}
		}
		
		visitados[verticeActual] = false;
		camino.eliminarEn(camino.tamanio());
	}
}
