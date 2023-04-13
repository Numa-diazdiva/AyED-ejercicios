package tp03.ejercicio2;

public class ArbolBinario<T> {
	private T dato;
	private ArbolBinario<T> hijoIzquierdo;   
	private ArbolBinario<T> hijoDerecho; 

	
	public ArbolBinario() {
		super();
	}

	public ArbolBinario(T dato) {
		this.dato = dato;
	}

	/*
	 * getters y setters
	 * 
	 */
	public T getDato() {
		return dato;
	}

	public void setDato(T dato) {
		this.dato = dato;
	}
	
	/**
	 * Preguntar antes de invocar si tieneHijoIzquierdo()
	 * @return
	 */
	public ArbolBinario<T> getHijoIzquierdo() {
		return this.hijoIzquierdo;
	}

	public ArbolBinario<T> getHijoDerecho() {
		return this.hijoDerecho;

	}

	public void agregarHijoIzquierdo(ArbolBinario<T> hijo) {
		this.hijoIzquierdo = hijo;
	}

	public void agregarHijoDerecho(ArbolBinario<T> hijo) {
		this.hijoDerecho = hijo;
	}

	public void eliminarHijoIzquierdo() {
		this.hijoIzquierdo = null;
	}

	public void eliminarHijoDerecho() {
		this.hijoDerecho = null;
	}

	public boolean esVacio() {
		return this.getDato() == null && !this.tieneHijoIzquierdo() && !this.tieneHijoDerecho();
	}

	public boolean esHoja() {
		return (!this.tieneHijoIzquierdo() && !this.tieneHijoDerecho());

	}

	@Override
	public String toString() {
		return this.getDato().toString();
	}

	 
	public boolean tieneHijoIzquierdo() {
		return this.hijoIzquierdo!=null;
	}

	 
	public boolean tieneHijoDerecho() {
		return this.hijoDerecho!=null;
	}

	
	/*
	 * Evalúa es hoja. Si es así devuelve 1.
	 * Si no es hoja, pide a sus hijos -los que tenga- que cuenten sus hojas y las suma para devolverlas.
	 * */
	public int contarHojas() {
		int hojas = 0;
		if (this.esHoja()) {
			return 1;
		} else {
			if(this.tieneHijoIzquierdo()) {
				hojas += this.getHijoIzquierdo().contarHojas();
			}
			if(this.tieneHijoDerecho()) {
				hojas += this.getHijoDerecho().contarHojas();
			}
		}
		return hojas;
	}
	
	/*
	 * Si es hoja no hace nada y solo de devuelve así mismo -no tiene nada que reordenar-
	 * Si no es hoja envía el mensaje de espejo a ls hijos que tenga (se invierte desde las hojas a la raíz)
	 * Una vez que envió los mensajes, hace swap entre las referencias de los nodos.
	 * */
    public ArbolBinario<T> espejo() {
    	if(!this.esHoja()) {
    		if (this.tieneHijoIzquierdo()) {
    			this.getHijoIzquierdo().espejo();
    		}
    		if (this.tieneHijoDerecho()) {
    			this.getHijoDerecho().espejo();
    		}
    		// Swap
    		ArbolBinario<T> aux = this.hijoIzquierdo;
    		this.hijoIzquierdo = this.hijoDerecho;
    		this.hijoDerecho = aux;
    	}
		return this;
	}


    
    
	public void entreNiveles(int n, int m){
		ColaGenerica<ArbolBinario<T>> cola = new ColaGenerica<ArbolBinario<T>>();
		cola.encolar(this);
		cola.encolar(new ArbolBinario<T>()); // lo dejo vacío
		int nivelActual = 0;
		while(!cola.esVacia() && nivelActual <= m) {
			ArbolBinario<T> actual = cola.desencolar();
			if(!actual.esVacio()) {
				if( nivelActual >= n) {
					// Proceso sólo si corresponde al rango de niveles deseado
					// Sino únicamente avanzo en niveles.
					System.out.print(actual.getDato());
				}
				if (actual.tieneHijoIzquierdo()) {
					cola.encolar(actual.getHijoIzquierdo());
				}
				if (actual.tieneHijoDerecho()) {
					cola.encolar(actual.getHijoDerecho());
				}
			} else {
				if (!cola.esVacia()) {
					cola.encolar(new ArbolBinario<T>());
					nivelActual ++;
				}
			}
			
		}
	
	}

	
	// Por leer mal la consigna tenés este método
	/*
	 * Evalúa si tiene hijo izquierdo. Si lo tiene, lo cuenta y recursivamente le pregunta cuántos hijos tiene para luego sumarlos.
	 * Hace lo mismo con el hijo derecho y finalmente devuelve la totalidad de nodos.
	 * */
	public int contarNodos() {
		int hijos = 0;
		if (this.tieneHijoIzquierdo()) {
			hijos += 1;
			hijos += this.getHijoIzquierdo().contarNodos();
		}
		if (this.tieneHijoDerecho()) {
			hijos += this.getHijoDerecho().contarNodos();
			hijos += 1;
		}
		return hijos;
	}
	
	

}
