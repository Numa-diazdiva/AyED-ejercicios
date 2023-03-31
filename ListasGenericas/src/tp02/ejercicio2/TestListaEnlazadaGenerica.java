package tp02.ejercicio2;


public class TestListaEnlazadaGenerica {

	public static void main(String[] args) {
		Estudiante e1, e2, e3, e4;
		
		e1 = new Estudiante("Matías", "Gómez", 5, "mati_97@yahoo.com.ar", "Moreno 460");
		e2 = new Estudiante("Dana", "Hubbard", 4, "dana_99@yahoo.com.ar", "Castelli 1963");
		e3 = new Estudiante("Pablito", "Aoe", 7, "pabroaoe291@gmail.com", "63 145");
		e4 = new Estudiante("Katrina", "Valdivia", 3, "kat9@gmail.com", "44 456");
		
		ListaGenerica<Estudiante> lista = new ListaEnlazadaGenerica<Estudiante>();
		
		lista.agregarInicio(e1);
		lista.agregarInicio(e2);
		lista.agregarEn(e3, 2);
		lista.agregarFinal(e4);
		
		lista.comenzar();
		
		while(! lista.fin()) {
			System.out.println(lista.proximo().tusDatos());
		}
		
		Estudiante[] nuevosEstudiantes = new Estudiante[3];
		nuevosEstudiantes[0] = new Estudiante("Jefferson", "Brown", 3, "jeffB9@gmail.com", "12 100");
		nuevosEstudiantes[1] = new Estudiante("Daniela", "Muckwinson", 3, "danB9@gmail.com", "12 100");
		nuevosEstudiantes[2] = new Estudiante("Quimey", "Huang", 3, "qj@gmail.com", "12 100");

		lista.agregar(nuevosEstudiantes);

		System.out.println("------------------------------------");
		lista.comenzar();
		
		while(! lista.fin()) {
			System.out.println(lista.proximo().tusDatos());
		}
		
		
		
	}
	
	
}
