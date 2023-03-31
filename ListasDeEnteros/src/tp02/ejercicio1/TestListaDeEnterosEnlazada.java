/*
Escriba una clase llamada TestListaDeEnterosEnlazada que reciba en su método main una
secuencia de números, los agregue a un objeto de tipo ListaDeEnterosEnlazada y luego imprima los
elementos de dicha lista.
*/
package tp02.ejercicio1;


public class TestListaDeEnterosEnlazada {

    public static void main(String[] args) {
        ListaDeEnterosEnlazada lista = new ListaDeEnterosEnlazada();
        
		for(int i = 0; i < args.length; i++) {
			try {
				lista.agregarFinal(Integer.parseInt(args[i]));
			}
			catch(NumberFormatException e) {
				System.out.print("Error en el parámetro");
			}
		}
        
		int i = 1;
		while(i <= lista.tamanio()) {
			System.out.print(" " + lista.elemento(i));
			i++;
		}

        lista.comenzar();
        while(! lista.fin()) {
            System.out.print(lista.proximo());        
        }
    }
}
