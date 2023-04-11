package tp03.ejercicio1;

public class ColaGenerica<T> {

	private ListaEnlazadaGenerica<T> datos;
	
	public ColaGenerica(){
		this.datos = new ListaEnlazadaGenerica<T>();
	}
	
	public void encolar(T elemento) {
		this.datos.agregarFinal(elemento);
	}
	
	public T desencolar() {
		T elemento = this.datos.elemento(0);
		if (elemento != null) {
			this.datos.eliminarEn(0);
		}
		return elemento;
	}
	
	public T tope() {
		T elemento = null;
		this.datos.comenzar();
		while(! this.datos.fin()) {
			elemento = this.datos.proximo();
		}
		return elemento;
	}
	
	public boolean esVacia() {
		return this.datos.esVacia();
	}
	
}
