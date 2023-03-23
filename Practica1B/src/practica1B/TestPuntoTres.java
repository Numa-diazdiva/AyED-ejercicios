package practica1B;

public class TestPuntoTres {
	// Ver de generalizar las clases profesor y alumno
	public static void main(String[] args) {
		Estudiante[] estudiantes = new Estudiante[2];
		Profesor[] profesores = new Profesor[3];
		
		estudiantes[0] = new Estudiante("Matías", "Gómez", 5, "mati_97@yahoo.com.ar", "Moreno 460");
		estudiantes[1] = new Estudiante("Dana", "Hubbard", 4, "dana_99@yahoo.com.ar", "Castelli 1963");

		profesores[0] = new Profesor("Elías", "Fredersen", "efredersen@gmail.com", "Grabado 3", "FdA");
		profesores[1] = new Profesor("Matilde", "Bellini", "bell@gmail.com", "Astronomía 4", "Astronomía");
		profesores[2] = new Profesor("Raquel", "Viejo", "rachel@gmail.com", "Anatomía 3", "Medicina");
		
		for(int i = 0; i < 2; i++) {
			System.out.println(estudiantes[i].tusDatos());
		}
		
		for(int i = 0; i < 3; i++) {
			System.out.println(profesores[i].tusDatos());
		}
		
	}
	
}
