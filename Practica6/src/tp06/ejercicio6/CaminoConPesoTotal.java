package tp06.ejercicio6;

import tp06.ejercicio3.Grafo;
import tp06.ejercicio3.ListaEnlazadaGenerica;
import tp06.ejercicio3.ListaGenerica;

public class CaminoConPesoTotal {

	private ListaGenerica<String> camino;
	private int pesoTotal;
	
	
	public CaminoConPesoTotal(ListaGenerica<String> camino, int pesoTotal) {
		this.camino = camino;
		this.pesoTotal = pesoTotal;
	}
	
	public CaminoConPesoTotal() {
		this.camino =  new ListaEnlazadaGenerica<String>();
		this.pesoTotal = 0;
	}
	
	
	public ListaGenerica<String> getCamino() {
		return this.camino;
	}
	
	public int pesoTotal() {
		return this.pesoTotal;
	}
	
	public boolean esVacio() {
		return this.camino.esVacia();
	}
	
	public void setCamino(ListaGenerica<String> camino) {
		this.camino = camino;
	}
	
	public void setTiempo(int tiempo) {
		this.pesoTotal = tiempo;
	}
	
}
