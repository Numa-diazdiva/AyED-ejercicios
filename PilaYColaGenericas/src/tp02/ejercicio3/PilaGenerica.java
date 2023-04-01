package tp02.ejercicio3;

public class PilaGenerica<T> {

	private ListaEnlazadaGenerica<T> datos;
	
	public PilaGenerica() {
		this.datos = new ListaEnlazadaGenerica<T>();
	}
	
	public void apilar(T elemento) {
		this.datos.agregarFinal(elemento);		
	}
	
	public T desapilar() {
		T elemento = this.tope();
		this.datos.eliminar(elemento);
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
