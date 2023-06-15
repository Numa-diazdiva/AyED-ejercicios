package tp06.ejercicio5;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tp06.ejercicio3.ListaEnlazadaGenerica;
import tp06.ejercicio3.ListaGenerica;
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
		
		this.mapa.conectarCiudades(sanJuan, sanLuis, 5);
		this.mapa.conectarCiudades(sanJuan, mendoza, 15);
		
		this.mapa.conectarCiudades(sanLuis, mendoza, 7);
		
		this.mapa.conectarCiudades(mendoza, sanLuis, 7);
		this.mapa.conectarCiudades(mendoza, staRosa, 50);
		this.mapa.conectarCiudades(mendoza, lagoPuelo, 20);
	
		this.mapa.conectarCiudades(lagoPuelo, mendoza, 20);
		this.mapa.conectarCiudades(lagoPuelo, staRosa, 15);
		
		this.mapa.conectarCiudades(staRosa, lagoPuelo, 15);
		this.mapa.conectarCiudades(staRosa, bsAs, 20);
		
		this.mapa.conectarCiudades(bsAs, trenqueLauquen, 25);
		this.mapa.conectarCiudades(bsAs, laPlata, 2);
		
		this.mapa.conectarCiudades(trenqueLauquen, marDelPlata, 10);
		
		this.mapa.conectarCiudades(staRosa, trenqueLauquen, 7);
	}
	
	
	@Test
	void testCaminoEntreCiudades() {
		System.out.println("-------------------------------CAMINO ENTRE CIUDADES OK");
		System.out.println(this.mapa.devolverCamino("San Juan", "Trenque Lauquen"));
		assertEquals("San Juan -> San Luis -> Mendoza -> Santa Rosa -> Buenos Aires -> Trenque Lauquen",
						this.mapa.devolverCamino("San Juan", "Trenque Lauquen").toString());
	}
	
	@Test
	void testCaminoEvitandoCiudadOK() {
		System.out.println("-------------------------------CAMINO EVITANDO OK");
		ListaGenerica<String> ciudadesAEsquivar = new ListaEnlazadaGenerica<String>();
		ciudadesAEsquivar.agregarFinal(bsAs.dato());
		assertTrue(ciudadesAEsquivar.incluye(bsAs.dato()));
		assertEquals("San Juan -> San Luis -> Mendoza -> Santa Rosa -> Trenque Lauquen -> Mar del Plata", 
						this.mapa.devolverCaminoExceptuando("San Juan", "Mar del Plata", ciudadesAEsquivar).toString());
	}
	
	@Test
	void testCaminoEvitandoCiudadesOK() {
		System.out.println("-------------------------------CAMINO EVITANDO VARIAS CIUDADES OK");
		ListaGenerica<String> ciudadesAEsquivar = new ListaEnlazadaGenerica<String>();
		ciudadesAEsquivar.agregarFinal(bsAs.dato());
		ciudadesAEsquivar.agregarFinal(sanLuis.dato());
		assertEquals("San Juan -> Mendoza -> Santa Rosa -> Trenque Lauquen -> Mar del Plata",
						this.mapa.devolverCaminoExceptuando("San Juan", "Mar del Plata", ciudadesAEsquivar).toString());
	}
	
	@Test
	void testCaminoEvitandoCiudadesFail() {
		System.out.println("-------------------------------CAMINO EVITANDO VARIAS CIUDADES OK");
		ListaGenerica<String> ciudadesAEsquivar = new ListaEnlazadaGenerica<String>();
		ciudadesAEsquivar.agregarFinal(staRosa.dato());
		assertTrue(this.mapa.devolverCaminoExceptuando("San Juan", "Mar del Plata", ciudadesAEsquivar).esVacia());
	}
	
	@Test
	void testCaminoMasCorto() {
		System.out.println("-------------------------------CAMINO MAS CORTO ENTRE CIUDADES OK");
		System.out.println("Camino más corto: " + mapa.caminoMasCorto("San Juan", "Mar del Plata"));
		assertEquals(5, mapa.caminoMasCorto("San Juan", "Mar del Plata").tamanio());
	}
	
	@Test
	void testCaminoSinCargarCombustible() {
		System.out.println("-------------------------------CAMINO SIN CARGAR COMBUSTIBLE");
		System.out.println("Camino posible: " + mapa.caminoSinCargarCombustible("San Juan", "Mar del Plata", 70));
		
		assertEquals("San Juan -> San Luis -> Mendoza -> Lago Puelo -> Santa Rosa -> Trenque Lauquen -> Mar del Plata",
						mapa.caminoSinCargarCombustible("San Juan", "Mar del Plata", 70).toString());
		assertEquals("San Juan -> San Luis -> Mendoza -> Santa Rosa -> Trenque Lauquen -> Mar del Plata",
						mapa.caminoSinCargarCombustible("San Juan", "Mar del Plata", 100).toString());
		assertEquals("San Juan -> San Luis -> Mendoza -> Santa Rosa -> Buenos Aires -> Trenque Lauquen -> Mar del Plata",
						mapa.caminoSinCargarCombustible("San Juan", "Mar del Plata", 120).toString());
	}
	
	@Test
	void testCaminoCargandoCombustible() {
		System.out.println("-------------------------------CAMINO CARGANDO COMBUSTIBLE");
		assertEquals("San Juan -> San Luis -> Mendoza -> Santa Rosa -> Trenque Lauquen -> Mar del Plata",
						this.mapa.caminoConMenorCargaDeCombustible("San Juan", "Mar del Plata", 50).toString());
	}
	
	
	
}
