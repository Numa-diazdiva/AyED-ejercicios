package tp02.ejercicio1;

public class TestListaDeEnterosConArreglos {

	/*
	    Escriba una clase llamada TestListaDeEnterosConArreglos que reciba en su método main
		una secuencia de números, los agregue a un objeto de tipo ListaDeEnterosConArreglos y luego
		imprima los elementos de dicha lista.
	 */
	
	public static void main(String args[]) {
		ListaDeEnterosConArreglos lista = new ListaDeEnterosConArreglos();
		
		lista.comenzar();
		
		for(int i = 0; i < args.length; i++) {
			try {
				lista.agregarFinal(Integer.parseInt(args[i]));
			}
			catch(NumberFormatException e) {
				System.out.print("Error en el parámetro");
			}
		}
		
		// Pensaba hacerlo con el próximo() pero no actualiza el actual
		
		int i = 1;
		while(i <= lista.tamanio()) {
			System.out.print(" " + lista.elemento(i));
			i++;
		}
		
		
	}
	
}
