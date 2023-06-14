package tp06.ejercicio5;

import tp06.ejercicio3.Arista;
import tp06.ejercicio3.Grafo;
import tp06.ejercicio3.GrafoImplListAdy;
import tp06.ejercicio3.ListaEnlazadaGenerica;
import tp06.ejercicio3.ListaGenerica;
import tp06.ejercicio3.Vertice;
import tp06.ejercicio3.VerticeImplListAdy;

public class Mapa {

	private Grafo<String> mapaCiudades;
	
	public Mapa() {
		this.mapaCiudades = new GrafoImplListAdy<String>();
	}
	
	
	
	public Vertice<String> buscarVertice(String dato) {
		Vertice<String> aux;
		ListaGenerica<Vertice<String>> vertices = this.mapaCiudades.listaDeVertices();
		vertices.comenzar();
		while(!vertices.fin()) {
			aux = vertices.proximo();
			if(aux.dato() == dato) {
				return aux;
			}
		}
		return null;
	}
	
	public int posVertice(String dato) {
		Vertice<String> aux;
		int contador = 1;
		ListaGenerica<Vertice<String>> vertices = this.mapaCiudades.listaDeVertices();
		vertices.comenzar();
		while(!vertices.fin()) {
			aux = vertices.proximo();
			if(aux.dato() == dato) {
				return contador;
			}
			contador++;
		}
		return -1;
	}
	
	/*
	 * Retorna la lista de ciudades que se deben atravesar para ir de ciudad1 a ciudad2 en caso que se pueda
	 * llegar, si no retorna la lista vacía. (Sin tener en cuenta el combustible).
	 * */
	
	public ListaGenerica<String> devolverCamino(String ciudad1, String ciudad2) {
		boolean[] visitados = new boolean[this.mapaCiudades.listaDeVertices().tamanio() + 1];
		ListaGenerica<String> camino = new ListaEnlazadaGenerica<String>();
		int posicionInicial = this.posVertice(ciudad1);
		this.dfsCaminoEntreCiudades(posicionInicial, visitados, camino, ciudad2);		
		return camino;
	}
	


	
	private boolean dfsCaminoEntreCiudades(int posVertice, boolean[] visitados, ListaGenerica<String> camino, String destino) {
		System.out.println("-----------");
		
		visitados[posVertice] = true;
		Vertice<String> vertice = this.mapaCiudades.vertice(posVertice);
		camino.agregarFinal(vertice.dato());
		boolean llegue = false;
		
		System.out.println("Estoy en: " + vertice.dato());
		System.out.println("Camino: " + camino);
		System.out.println("* tamanio del camino: " + camino.tamanio());
		
		if(vertice.dato() != destino) {
			ListaGenerica<Arista<String>> aristas = this.mapaCiudades.listaDeAdyacentes(vertice);
			aristas.comenzar();
			
			while(!aristas.fin() & !llegue) {
				int verticeDestino = aristas.proximo().verticeDestino().getPosicion();
				System.out.println("Destino próximo: " + this.mapaCiudades.vertice(verticeDestino).dato());
				if(!visitados[verticeDestino]) {
					llegue = dfsCaminoEntreCiudades(verticeDestino, visitados, camino, destino);
				}
			}
		} else {
			llegue = true;
		}
		
		if(!llegue) {
			// Error en .eliminar() 
			// camino.eliminar(vertice.dato());
			camino.eliminarEn(camino.tamanio());
			System.out.println("Vuelvo de: " + vertice.dato());
			System.out.println("----------------");
		}
		return llegue;
	}
	
	public void agregarCiudad(String ciudad) {
		this.mapaCiudades.agregarVertice(new VerticeImplListAdy<String>(ciudad));
	}
	
	public void agregarCiudad(Vertice<String> ciudad) {
		this.mapaCiudades.agregarVertice(ciudad);
	}
	
	
	public void conectarCiudades(Vertice<String> ciudad1, Vertice<String> ciudad2) {
		this.mapaCiudades.conectar(ciudad1, ciudad2);
	}
		
	
}
