package tp03.ejercicio4;

public class RedBinariaLlena<T> {
	private T dato;
	private RedBinariaLlena<T> hijoIzquierdo;   
	private RedBinariaLlena<T> hijoDerecho; 
	private int tiempoRetardo = 12;
	
	public RedBinariaLlena() {
		super();
	}

	public RedBinariaLlena(T dato) {
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
	public RedBinariaLlena<T> getHijoIzquierdo() {
		return this.hijoIzquierdo;
	}

	public RedBinariaLlena<T> getHijoDerecho() {
		return this.hijoDerecho;

	}

	public void agregarHijoIzquierdo(RedBinariaLlena<T> hijo) {
		this.hijoIzquierdo = hijo;
	}

	public void agregarHijoDerecho(RedBinariaLlena<T> hijo) {
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
    public RedBinariaLlena<T> espejo() {
    	if(!this.esHoja()) {
    		if (this.tieneHijoIzquierdo()) {
    			this.getHijoIzquierdo().espejo();
    		}
    		if (this.tieneHijoDerecho()) {
    			this.getHijoDerecho().espejo();
    		}
    		// Swap
    		RedBinariaLlena<T> aux = this.hijoIzquierdo;
    		this.hijoIzquierdo = this.hijoDerecho;
    		this.hijoDerecho = aux;
    	}
		return this;
	}


    
    
	public void entreNiveles(int n, int m){
		ColaGenerica<RedBinariaLlena<T>> cola = new ColaGenerica<RedBinariaLlena<T>>();
		cola.encolar(this);
		cola.encolar(new RedBinariaLlena<T>()); // lo dejo vacío
		int nivelActual = 0;
		while(!cola.esVacia() && nivelActual <= m) {
			RedBinariaLlena<T> actual = cola.desencolar();
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
					cola.encolar(new RedBinariaLlena<T>());
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
	
	
	/*
	 * Creo que en este caso puedo efectuar un recorrido en profundidad preOrden, puesto
	 * que se asocia con la idea de camino. En éste caso, el mayor retardo es el camino
	 * que tiene mayor tiempo de retardo.
	 * En este caso no guardo el camino porque no se solicita.
	 * */
	public int mayorRetardoReenvio() {
		int retardo;
		if (this.esHoja()) {
			return 0;
		} else {
			retardo = this.tiempoRetardo;
			// Capaz al ser un árbol lleno, acá puedo agregar un if para ver que tengan hijos
			int retardoIzquierdo = this.getHijoIzquierdo().mayorRetardoReenvio();
			int retardoDerecho = this.getHijoDerecho().mayorRetardoReenvio();
			if (retardoIzquierdo > retardoDerecho) {
				retardo += retardoIzquierdo;
			} else {
				retardo += retardoDerecho;
			}
		}
		return retardo;
	}
	
	
	
	
	

}
