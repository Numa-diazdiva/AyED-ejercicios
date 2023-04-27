package tp04.ejercicio5;

import tp04.ejercicio4.ArbolGeneral;

public class AnalizadorArbolTest {

	private static ArbolGeneral<AreaEmpresa> arbol, nodo2, nodo3, nodo4, nodo5, nodo6, nodo7, nodo8, nodo9, nodo10, nodo11, nodo12, nodo13;
	
	public static void main(String[] args) {
		devolverMaximoPromedioTest();
	}
	
	
	public static void inicializarArbol() {
		arbol = new ArbolGeneral<AreaEmpresa>(new AreaEmpresa("Nivel 0", 14));
		nodo2 = new ArbolGeneral<AreaEmpresa>(new AreaEmpresa("Nivel 1-1", 13));
		nodo3 = new ArbolGeneral<AreaEmpresa>(new AreaEmpresa("Nivel 1-2", 25));
		nodo4 = new ArbolGeneral<AreaEmpresa>(new AreaEmpresa("Nivel 1-3", 10));
		arbol.agregarHijo(nodo2); arbol.agregarHijo(nodo3); arbol.agregarHijo(nodo4);
		
		nodo5 = new ArbolGeneral<AreaEmpresa>(new AreaEmpresa("Nivel 2-1", 4));
		nodo6 = new ArbolGeneral<AreaEmpresa>(new AreaEmpresa("Nivel 2-2", 7));
		nodo7 = new ArbolGeneral<AreaEmpresa>(new AreaEmpresa("Nivel 2-3", 5));
		nodo2.agregarHijo(nodo5); nodo2.agregarHijo(nodo6); nodo2.agregarHijo(nodo7);
		
		nodo8 = new ArbolGeneral<AreaEmpresa>(new AreaEmpresa("Nivel 2-4", 6));
		nodo9 = new ArbolGeneral<AreaEmpresa>(new AreaEmpresa("Nivel 2-5", 10));
		nodo10 = new ArbolGeneral<AreaEmpresa>(new AreaEmpresa("Nivel 2-6", 18));
		nodo3.agregarHijo(nodo8); nodo3.agregarHijo(nodo9); nodo3.agregarHijo(nodo10);	
		
		nodo11 = new ArbolGeneral<AreaEmpresa>(new AreaEmpresa("Nivel 2-7", 9));
		nodo12 = new ArbolGeneral<AreaEmpresa>(new AreaEmpresa("Nivel 2-8", 12));
		nodo13 = new ArbolGeneral<AreaEmpresa>(new AreaEmpresa("Nivel 2-9", 19));
		nodo4.agregarHijo(nodo11); nodo4.agregarHijo(nodo12); nodo4.agregarHijo(nodo13);	
	}
	/*
	 * Chequear que algo pasa en el desencolado inicial que parece ser null
	 * */
	public static void devolverMaximoPromedioTest() {
		inicializarArbol();
		System.out.print(arbol.getDato().getNombre());
		AnalizadorArbol analizador = new AnalizadorArbol();
		int resultado = analizador.devolverMaximoPromedio(arbol);
		if(resultado == 15) {
			System.out.print("Resultado correcto");
		} else {
			System.out.print("Resultado incorrecto");
		}
	}
	
}
