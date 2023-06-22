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
			// Agrego quitar de visitados
			camino.eliminarEn(camino.tamanio());
			System.out.println("Vuelvo de: " + vertice.dato());
			System.out.println("----------------");
		}
		return llegue;
	}
	
	
	
	/*
	 * Retorna la lista de ciudades que
	 * forman un camino desde ciudad1 a ciudad2, sin pasar por las ciudades que están contenidas en la lista
	 * ciudades pasada por parámetro, si no existe camino retorna la lista vacía. (Sin tener en cuenta el
	 * combustible).
	 * */
	public ListaGenerica<String> devolverCaminoExceptuando(String origen, String destino, ListaGenerica<String> ciudades) {
		ListaGenerica<String> camino = new ListaEnlazadaGenerica<String>();
		boolean[] visitados = new boolean[this.mapaCiudades.listaDeVertices().tamanio() + 1];
		this.dfsCaminoEntreCiudadesExceptuando(this.posVertice(origen), visitados, camino, destino, ciudades);
		return camino;
	}
	
	
	private boolean dfsCaminoEntreCiudadesExceptuando(int posVertice, boolean[] visitados, ListaGenerica<String> camino, String destino, ListaGenerica<String> ciudadesAEsquivar) {
		
		System.out.println("-----------");
		
		visitados[posVertice] = true;
		Vertice<String> vertice = this.mapaCiudades.vertice(posVertice);
		boolean llegue = false;
		camino.agregarFinal(vertice.dato());
	
		System.out.println("Estoy en: " + vertice.dato());
		System.out.println("Camino: " + camino);
		System.out.println("* tamanio del camino: " + camino.tamanio());
		
		if(vertice.dato() != destino) {
			ListaGenerica<Arista<String>> aristas = this.mapaCiudades.listaDeAdyacentes(vertice);
			aristas.comenzar();
			
			while(!aristas.fin() & !llegue) {
				int verticeDestino = aristas.proximo().verticeDestino().getPosicion();
				System.out.println("Destino próximo: " + this.mapaCiudades.vertice(verticeDestino).dato());
				// Si tengo que esquivar la ciudad no hago nada, mejor controlar ésto acá (no ir a la ciudad si no tengo que ir)
				if(!visitados[verticeDestino] && !ciudadesAEsquivar.incluye(this.mapaCiudades.vertice(verticeDestino).dato()) ) {
					llegue = dfsCaminoEntreCiudadesExceptuando(verticeDestino, visitados, camino, destino, ciudadesAEsquivar);
				}
			}
		} else {
			llegue = true;
		}
		
		if(!llegue) {
			// Agregar desmarca de visitados
			visitados[posVertice] = false;
			camino.eliminarEn(camino.tamanio());
			System.out.println("Vuelvo de: " + vertice.dato());
			System.out.println("----------------");
		}
		
		
		return llegue;

	}
	
	
	
	
	/*
	 * Retorna la lista de ciudades que forman el camino más corto para llegar de ciudad1 a ciudad2, si no
	 * existe camino retorna la lista vacía. (Las rutas poseen la distancia). (Sin tener en cuenta el
	 * combustible).
	 * */
	public ListaGenerica<String> caminoMasCorto(String origen, String destino) {
		ListaGenerica<ListaEnlazadaGenerica<String>> caminos = new ListaEnlazadaGenerica<ListaEnlazadaGenerica<String>>();
		ListaEnlazadaGenerica<String> caminoMenor, camino;
		boolean[] visitados = new boolean[this.mapaCiudades.listaDeVertices().tamanio() + 1];
		
		dfsCaminosEntreCiudades(this.posVertice(origen), destino, new ListaEnlazadaGenerica<String>(), caminos, visitados);
		
		caminos.comenzar();
		caminoMenor = caminos.proximo();
		System.out.println(caminoMenor);
		while(!caminos.fin()) {
			camino = caminos.proximo();
			System.out.println(camino);
			if(camino.tamanio() < caminoMenor.tamanio()) {
				caminoMenor = camino;
			}
		}
		return caminoMenor;
	}
	
	
	private void dfsCaminosEntreCiudades(int posVertice, String destino, ListaEnlazadaGenerica<String> caminoActual, ListaGenerica<ListaEnlazadaGenerica<String>> caminos, boolean[] visitados) {
		visitados[posVertice] = true;
		Vertice<String> vertice = this.mapaCiudades.vertice(posVertice);
		caminoActual.agregarFinal(vertice.dato());
				
		if(vertice.dato() != destino) {
			ListaGenerica<Arista<String>> aristas = this.mapaCiudades.listaDeAdyacentes(vertice);
			aristas.comenzar();
			while(!aristas.fin()) {
				int verticeDestino = aristas.proximo().verticeDestino().getPosicion();
				// Si en el camino que vengo haciendo no pasé, me voy por ese vértice
				if(!visitados[verticeDestino]) {
					 this.dfsCaminosEntreCiudades(verticeDestino, destino, caminoActual, caminos, visitados);
				}
			}
		} else {
			// Agrego el camino actual en caso de llegar a la ciudad
			// Acá comparo con camino actual y me quedo el min, chequeo es vaci{ia al principio
			caminos.agregarFinal((ListaEnlazadaGenerica<String>) caminoActual.clonar());
		}
	
		// Backtracking
		visitados[posVertice] = false;
		caminoActual.eliminarEn(caminoActual.tamanio());
	}
	
	
	public ListaGenerica<String> caminoSinCargarCombustible(String ciudad1, String ciudad2, int tanqueAuto) {
		ListaGenerica<String> camino = new ListaEnlazadaGenerica<String>();
		boolean[] visitados = new boolean[this.mapaCiudades.listaDeVertices().tamanio() + 1];
		this.dfsConCombustibleSinCargar(this.posVertice(ciudad1), ciudad2, tanqueAuto, visitados, camino);
		return camino;
	}
	
	
	private boolean dfsConCombustibleSinCargar(int posVertice, String destino, int tanqueAuto, boolean[] visitados, ListaGenerica<String> camino) {
		
		visitados[posVertice] = true;
		Vertice<String> vertice = this.mapaCiudades.vertice(posVertice);
		camino.agregarFinal(vertice.dato());
		boolean llegue = false;
		System.out.println(camino + " | Nafta: " + tanqueAuto);
		if(vertice.dato() != destino) {
			ListaGenerica<Arista<String>> aristas = this.mapaCiudades.listaDeAdyacentes(vertice);
			aristas.comenzar();
			
			while(!aristas.fin() & !llegue) {
				Arista<String> ruta = aristas.proximo();
				int verticeDestino = ruta.verticeDestino().getPosicion();
				if(!visitados[verticeDestino] && (tanqueAuto - ruta.peso() > 0)) {
					llegue = dfsConCombustibleSinCargar(verticeDestino, destino, tanqueAuto - ruta.peso(), visitados, camino);
				}
			}
		} else {
			llegue = true;
		}
		
		if(!llegue) {
			camino.eliminarEn(camino.tamanio());
			visitados[posVertice] = false;
			System.out.println("Vuelvo de " + vertice.dato());
		}
		return llegue;
	}
	
	/*
	 * Retorna la lista de ciudades que forman un camino para
	 * llegar de ciudad1 a ciudad2 teniendo en cuenta que el auto debe cargar la menor cantidad de veces. El
	 * auto no se debe quedar sin combustible en medio de una ruta, además puede completar su tanque al
	 * llegar a cualquier ciudad. Si no existe camino retorna la lista vacía.
	 * */
	ListaGenerica<String> caminoConMenorCargaDeCombustible (String ciudad1, String ciudad2, int tanqueAuto) {
		ListaGenerica<String> camino = new ListaEnlazadaGenerica<String>();
		boolean[] visitados = new boolean[this.mapaCiudades.listaDeVertices().tamanio() + 1];
		ListaGenerica<CaminoConValorDeCombustible> caminos = new ListaEnlazadaGenerica<CaminoConValorDeCombustible>();
		this.dfsCargandoCombustible(this.posVertice(ciudad1), ciudad2, visitados, 0, camino, caminos);
		
		caminos.comenzar();
		CaminoConValorDeCombustible caminoMin = caminos.proximo();
		System.out.println(caminoMin.getCamino() + " Cargas: " + caminoMin.cantidadDeRecargasSegunTanque(tanqueAuto));
		while(!caminos.fin()) {
			
			CaminoConValorDeCombustible caminoActual = caminos.proximo();
			System.out.println(caminoActual.getCamino() + " Cargas: " + caminoActual.cantidadDeRecargasSegunTanque(tanqueAuto));
			if(caminoActual.cantidadDeRecargasSegunTanque(tanqueAuto) < caminoMin.cantidadDeRecargasSegunTanque(tanqueAuto)) {
				caminoMin = caminoActual;
			}
		}
		return caminoMin.getCamino();
	}
	
	private void dfsCargandoCombustible(int posVertice, String destino, boolean[] visitados, int combustibleConsumido, ListaGenerica<String> camino, ListaGenerica<CaminoConValorDeCombustible> caminos) {
		visitados[posVertice] = true;
		Vertice<String> vertice = this.mapaCiudades.vertice(posVertice);
		camino.agregarFinal(vertice.dato());
		
		if(destino != vertice.dato()) {
			ListaGenerica<Arista<String>> aristas = this.mapaCiudades.listaDeAdyacentes(vertice);
			aristas.comenzar();
			while(!aristas.fin()) {
				Arista<String> aristaActual = aristas.proximo();
				if(!visitados[aristaActual.verticeDestino().getPosicion()]) {
					this.dfsCargandoCombustible(aristaActual.verticeDestino().getPosicion(),
												destino,
												visitados,
												combustibleConsumido + aristaActual.peso(),
												camino,
												caminos);
			
				}
			}
				
		} else {
			// también podría manejar el conteo de combustible y las ciudades en el CaminoConValorDeCombustible y clonarlo cuando llego
			caminos.agregarFinal(new CaminoConValorDeCombustible(camino.clonar(), combustibleConsumido));
		}
		
		// Backtracking
		visitados[posVertice] = false;
		camino.eliminarEn(camino.tamanio());
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
	
	public void conectarCiudades(Vertice<String> ciudad1, Vertice<String> ciudad2, int consumo) {
		this.mapaCiudades.conectar(ciudad1, ciudad2, consumo);
	}


}
