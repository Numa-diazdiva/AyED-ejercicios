package tp02.ejercicio3;

public class VerificadorBalance {

	private PilaGenerica<Character> pila;
	
	public VerificadorBalance() {
		pila = new PilaGenerica<Character>();
	}
	
	
	private boolean compararApertura(char car) {
		char[] apertura = "{[(".toCharArray();
		for(int i = 0; i < apertura.length; i++) {
			if(car == apertura[i]) {
				return true;
			}
		}
		return false;
	}
	
	private boolean compararCierre(char car) {
		char[] cierre = ")]}".toCharArray();
		for(int i = 0; i < cierre.length; i++) {
			if(car == cierre[i]) {
				return true;
			}
		}
		return false;
	}
	
	
	private boolean cumple(Character car, Character otro) {
		return (car == '}' & otro ==  '{') | (car == ']' & otro == '[') | (car == ')' & otro == '(');
	}
	
	// Precondición: la cadena no contiene caracteres extraños
	public boolean estaBalanceada(String cadena) {
		// ChequeoInicial
		if (cadena.length() == 0) {
			return true;
		} else if (this.compararCierre(cadena.charAt(0))){
			return false;
		}
		
		for(int i = 0; i < cadena.length(); i++) {
			Character caracter = cadena.charAt(i);
			if(this.compararApertura(caracter)) {
				System.out.println("--> apilo: " + caracter);
				this.pila.apilar(caracter);
			}
			else {
				// Arranca el de cierre
				// No me fijo si es de cierre porque no puede haber otra cosa
				Character otroCaracter = this.pila.desapilar();
				System.out.println("Comparo: " + otroCaracter + caracter);
				if (! cumple(caracter, otroCaracter)) {
					return false;
				}
			}
		}
		return true;
	}
	
	
	
	
	
}
