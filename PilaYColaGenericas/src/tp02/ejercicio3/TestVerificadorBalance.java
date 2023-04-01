package tp02.ejercicio3;

public class TestVerificadorBalance {

	public static void main(String args[]) {
		VerificadorBalance verificador = new VerificadorBalance();
		String stringIngresado = args[0];
		
		System.out.println("Su cadena: " + stringIngresado);
		if (verificador.estaBalanceada(stringIngresado)) {
			System.out.println("Cadena balanceada");
		} else {
			System.out.println("Cadena no balanceada");
		}
		
		stringIngresado = args[1];
		System.out.println("Su cadena: " + stringIngresado);
		if (verificador.estaBalanceada(stringIngresado)) {
			System.out.println("Cadena balanceada");
		} else {
			System.out.println("Cadena no balanceada");
		}
		
//		stringIngresado = args[1];
//		System.out.println("Su cadena: " + stringIngresado);
//		if (verificador.estaBalanceada(stringIngresado)) {
//			System.out.println("Cadena balanceada");
//		} else {
//			System.out.println("Cadena no balanceada");
//		}
//		
//		
//		
	}
	
	
}
