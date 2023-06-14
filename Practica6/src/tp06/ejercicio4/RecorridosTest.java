package tp06.ejercicio4;

import org.junit.jupiter.api.BeforeEach;

import tp06.ejercicio3.Grafo;
import tp06.ejercicio3.GrafoImplListAdy;
import tp06.ejercicio3.Vertice;
import tp06.ejercicio3.VerticeImplListAdy;

public class RecorridosTest {

	private Grafo<String> grafo;
	private Recorridos<String> recorridos;
	private Vertice<String> v1,v2,v3,v4,v5,v6;
	
	@BeforeEach
	void setUp() {
		this.grafo = new GrafoImplListAdy<String>();
		this.recorridos = new Recorridos<String>();
		v1 = new VerticeImplListAdy<String>("Mariel");	
		v2 = new VerticeImplListAdy<String>("Ricardo");
		v3 = new VerticeImplListAdy<String>("Fedora");
		v4 = new VerticeImplListAdy<String>("Sixto");
		v5 = new VerticeImplListAdy<String>("Marcela");
		v6 = new VerticeImplListAdy<String>("Fredersen");
		this.grafo.agregarVertice(v1);
		this.grafo.agregarVertice(v2);
		this.grafo.agregarVertice(v3);
		this.grafo.agregarVertice(v4);
		this.grafo.agregarVertice(v5);
		this.grafo.agregarVertice(v6);
		
	}

	
	
}
