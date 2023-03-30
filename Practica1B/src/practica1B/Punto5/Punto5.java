package practica1B.Punto5;

public class Punto5 {
	int min, max, promedio;

	public static void main(String[] args) {
		int[] arreglo = new int[10];
		
		Resultados resultados1, resultados2;
		
		for(int i = 0; i < 10; i++) {
			arreglo[i] = (int)( Math.random() * 100);
			System.out.println(arreglo[i]);
		}
	
		
		resultados1 = calcularPromedioMinMaxA(arreglo);
		resultados2 = new Resultados();
			
	}
	
	
	
	public static Resultados calcularPromedioMinMaxA(int[] valores) {
		int max, min, total;
		total = 0;
		max = -1;
		min = 32000;
		for (int i = 0; i < valores.length; i++) {
			total += valores[i];
			if (valores[i] > max) {
				max = valores[i];
			}
			if (valores[i] < min) {
				min = valores[i];
			}	
		}
		return new Resultados(min, max, total / valores.length);
	}
	
	
	public void calcularPromedioMinMaxB(int[] valores, Resultados resultados) {
		int max, min, total;
		total = 0;
		max = -1;
		min = 32000;
		for (int i = 0; i < valores.length; i++) {
			total += valores[i];
			if (valores[i] > max) {
				max = valores[i];
			}
			if (valores[i] < min) {
				min = valores[i];
			}	
		}
		resultados.setMin(min);
		resultados.setMax(max);
		resultados.setPromedio(total / valores.length);
	}
	
	public void calcularPromedioMinMaxC() {
		int total = 0;
		this.min = 0;
		
		
	}
	
	
}
