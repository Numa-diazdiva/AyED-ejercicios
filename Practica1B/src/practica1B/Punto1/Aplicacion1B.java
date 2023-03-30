package practica1B.Punto1;

public class Aplicacion1B {
	
	public static void main(String[] args) {
		imprimirFor(2,10);
		System.out.println("--------------");
		imprimirWhile(5,11);
		System.out.println("--------------");
		imprimirSinEC(1, 10);
	}
	
	
	public static void imprimirFor(int a, int b) {
		for (int i = a; i <= b; i++) {
			System.out.println(i);
		}
	}
	
	public static void imprimirWhile(int a, int b) {
		while (a <= b) {
			System.out.println(a);
			a++;
		}
	}
	
	public static void imprimirSinEC(int a, int b) {
		if(a <= b) {
			System.out.println(a);
			a++;
			imprimirSinEC(a, b);
		}
	}

}
