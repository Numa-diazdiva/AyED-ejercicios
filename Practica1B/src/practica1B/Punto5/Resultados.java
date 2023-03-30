package practica1B.Punto5;

public class Resultados {

	private int min, max;
	private double promedio;
	
	public Resultados(int min, int max, double promedio) {
		this.min = min;
		this.max = max;
		this.promedio = promedio;
	}
	
	public Resultados() {}
	
	public int getMin() {
		return min;
	}
	
	public void setMin(int min) {
		this.min = min;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public void setPromedio(double promedio) {
		this.promedio = promedio;
	}

	public int getMax() {
		return max;
	}
	
	public double getPromedio() {
		return promedio;
	}
	
}
