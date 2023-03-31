package tp02.ejercicio1;

public class TestSucesionDeEnteros {

	public static void main(String args[]) {
		SucesionDeEnteros sucesion = new SucesionDeEnteros();
		int n = Integer.parseInt(args[0]);
		ListaDeEnteros l = sucesion.calcularSucesion(n);
		
		l.comenzar();
		while(! l.fin()) {
			System.out.print(" " + l.proximo());
		}
		
	}
	
}
