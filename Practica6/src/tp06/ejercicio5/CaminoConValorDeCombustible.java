package tp06.ejercicio5;

import tp06.ejercicio3.ListaGenerica;

public class CaminoConValorDeCombustible {
	
	private ListaGenerica<String> camino;
	private int combustibleRequerido;
	
	public CaminoConValorDeCombustible(ListaGenerica<String> camino, int combustibleRequerido) {
		this.camino = camino;
		this.combustibleRequerido = combustibleRequerido;
	}
	
	
	public int getCombustibleRequerido() {
		return this.combustibleRequerido;
	}
	
	public int cantidadDeRecargasSegunTanque(int capacidadTanque) {
		if(this.combustibleRequerido % capacidadTanque > 0) {
			return this.combustibleRequerido / capacidadTanque + 1;
		}
		return this.combustibleRequerido / capacidadTanque;
	}
	
	public ListaGenerica<String> getCamino() {
		return this.camino;
	}
	
}
