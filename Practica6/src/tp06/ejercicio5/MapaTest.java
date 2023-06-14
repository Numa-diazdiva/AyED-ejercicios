package tp06.ejercicio5;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tp06.ejercicio3.Vertice;
import tp06.ejercicio3.VerticeImplListAdy;

public class MapaTest {
	private Mapa mapa;
	private Vertice<String> sanLuis, sanJuan, mendoza, bsAs, laPlata, staRosa, lagoPuelo, trenqueLauquen, marDelPlata;
	
	
	@BeforeEach
	void setUp() {
		this.mapa = new Mapa();
		this.sanLuis = new VerticeImplListAdy<String>("San Luis");
		this.sanJuan = new VerticeImplListAdy<String>("San Juan");
		this.mendoza = new VerticeImplListAdy<String>("Mendoza");
		this.bsAs = new VerticeImplListAdy<String>("Buenos Aires");
		this.laPlata = new VerticeImplListAdy<String>("La Plata");
		this.staRosa = new VerticeImplListAdy<String>("Santa Rosa");
		this.lagoPuelo = new VerticeImplListAdy<String>("Lago Puelo");
		this.trenqueLauquen = new VerticeImplListAdy<String>("Trenque Lauquen");
		this.marDelPlata = new VerticeImplListAdy<String>("Mar del Plata");
		
		this.mapa.agregarCiudad(this.sanLuis);
		this.mapa.agregarCiudad(this.sanJuan);
		this.mapa.agregarCiudad(this.mendoza);
		this.mapa.agregarCiudad(this.bsAs);
		this.mapa.agregarCiudad(this.laPlata);
		this.mapa.agregarCiudad(this.staRosa);
		this.mapa.agregarCiudad(this.lagoPuelo);
		this.mapa.agregarCiudad(this.trenqueLauquen);
		this.mapa.agregarCiudad(this.marDelPlata);
		
		this.mapa.conectarCiudades(sanJuan, sanLuis);
		this.mapa.conectarCiudades(sanJuan, mendoza);
		
		this.mapa.conectarCiudades(sanLuis, mendoza);
		
		this.mapa.conectarCiudades(mendoza, sanLuis);
		this.mapa.conectarCiudades(mendoza, staRosa);
		this.mapa.conectarCiudades(mendoza, lagoPuelo);
	
		this.mapa.conectarCiudades(lagoPuelo, mendoza);
		this.mapa.conectarCiudades(lagoPuelo, staRosa);
		
		this.mapa.conectarCiudades(staRosa, lagoPuelo);
		this.mapa.conectarCiudades(staRosa, bsAs);
		
		this.mapa.conectarCiudades(bsAs, trenqueLauquen);
		this.mapa.conectarCiudades(bsAs, laPlata);
		
		this.mapa.conectarCiudades(trenqueLauquen, marDelPlata);
	}
	
	
	@Test
	void testCaminoEntreCiudades() {
		System.out.println(this.mapa.devolverCamino("San Juan", "Trenque Lauquen"));
		assertEquals("San Juan -> San Luis -> Mendoza -> Santa Rosa -> Buenos Aires -> Trenque Lauquen",
						this.mapa.devolverCamino("San Juan", "Trenque Lauquen").toString());
	}
	
}
