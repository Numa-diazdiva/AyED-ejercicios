package tp06.ejercicio6;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tp06.ejercicio3.Arista;
import tp06.ejercicio3.Grafo;
import tp06.ejercicio3.GrafoImplListAdy;
import tp06.ejercicio3.ListaEnlazadaGenerica;
import tp06.ejercicio3.ListaGenerica;
import tp06.ejercicio3.Vertice;
import tp06.ejercicio3.VerticeImplListAdy;

public class VisitaOsloTest {

	private Grafo<String> mapaOslo;
	private Vertice<String> ayuntamiento, parqueBotanico, museoMunch, elTigre, laOpera, fortaleza, palacioReal, akker, galeriaNac, parqueVigeland, homenkollen, folkMuseum, museoFram, museoBarco, museoVikingo;
	private VisitaOslo visitaOslo;
	
	
	@BeforeEach
	void setUp() {
		this.mapaOslo = new GrafoImplListAdy<String>();
		this.ayuntamiento = new VerticeImplListAdy<String>("Ayuntamiento");
		this.parqueBotanico = new VerticeImplListAdy<String>("Parque Botánico");
		this.museoMunch = new VerticeImplListAdy<String>("Museo Munch");
		this.elTigre = new VerticeImplListAdy<String>("El Tigre");
		this.laOpera = new VerticeImplListAdy<String>("La Opera");
		this.fortaleza = new VerticeImplListAdy<String>("Fortaleza Akershus");
		this.galeriaNac = new VerticeImplListAdy<String>("Galería Nacional");
		this.palacioReal = new VerticeImplListAdy<String>("Palacio Real");
		this.akker = new VerticeImplListAdy<String>("Akker Brigge");
		this.homenkollen = new VerticeImplListAdy<String>("Holmenkollen");
		this.parqueVigeland = new VerticeImplListAdy<String>("Parque Vigeland");
		this.folkMuseum = new VerticeImplListAdy<String>("FolkMuseum");
		this.museoVikingo = new VerticeImplListAdy<String>("Museo Vikingo");
		this.museoFram = new VerticeImplListAdy<String>("Museo Fram");
		this.museoBarco = new VerticeImplListAdy<String>("Museo del Barco Polar");
		
		this.mapaOslo.agregarVertice(ayuntamiento);
		this.mapaOslo.agregarVertice(parqueBotanico);
		this.mapaOslo.agregarVertice(museoMunch);
		this.mapaOslo.agregarVertice(elTigre);
		this.mapaOslo.agregarVertice(laOpera);
		this.mapaOslo.agregarVertice(fortaleza);
		this.mapaOslo.agregarVertice(galeriaNac);
		this.mapaOslo.agregarVertice(palacioReal);
		this.mapaOslo.agregarVertice(akker);
		this.mapaOslo.agregarVertice(homenkollen);
		this.mapaOslo.agregarVertice(parqueVigeland);
		this.mapaOslo.agregarVertice(folkMuseum);
		this.mapaOslo.agregarVertice(museoVikingo);
		this.mapaOslo.agregarVertice(museoFram);
		this.mapaOslo.agregarVertice(museoBarco);
		
		
		this.mapaOslo.conectar(ayuntamiento, parqueBotanico, 10);
		this.mapaOslo.conectar(parqueBotanico, ayuntamiento, 10);
		this.mapaOslo.conectar(ayuntamiento, elTigre, 15);
		this.mapaOslo.conectar(elTigre, ayuntamiento, 15);
		this.mapaOslo.conectar(ayuntamiento, akker, 20);
		this.mapaOslo.conectar(akker, ayuntamiento, 20);
		this.mapaOslo.conectar(ayuntamiento, palacioReal, 5);
		this.mapaOslo.conectar(palacioReal, ayuntamiento, 5);
		
		this.mapaOslo.conectar(elTigre, laOpera, 5);
		this.mapaOslo.conectar(laOpera, elTigre, 5);
		this.mapaOslo.conectar(elTigre, museoMunch, 15);
		this.mapaOslo.conectar(museoMunch, elTigre, 15);
		
		this.mapaOslo.conectar(laOpera, fortaleza, 10);
		this.mapaOslo.conectar(fortaleza, laOpera, 10);
		
		this.mapaOslo.conectar(museoMunch, parqueBotanico, 1);
		this.mapaOslo.conectar(parqueBotanico, museoMunch, 1);
		
		this.mapaOslo.conectar(parqueBotanico, galeriaNac, 15);
		this.mapaOslo.conectar(galeriaNac, parqueBotanico, 15);
		
		this.mapaOslo.conectar(galeriaNac, parqueVigeland, 10);
		this.mapaOslo.conectar(parqueVigeland, galeriaNac, 10);
		
		this.mapaOslo.conectar(parqueVigeland, homenkollen, 30);
		this.mapaOslo.conectar(homenkollen, parqueVigeland, 30);
		this.mapaOslo.conectar(parqueVigeland, folkMuseum, 20);
		this.mapaOslo.conectar(folkMuseum, parqueVigeland, 20);
		
		this.mapaOslo.conectar(folkMuseum, palacioReal, 5);
		this.mapaOslo.conectar(palacioReal, folkMuseum, 5);
		this.mapaOslo.conectar(folkMuseum, akker, 30);
		this.mapaOslo.conectar(akker, folkMuseum, 30);
		this.mapaOslo.conectar(folkMuseum, museoFram, 5);
		this.mapaOslo.conectar(museoFram, folkMuseum, 5);
		
		this.mapaOslo.conectar(museoFram, museoBarco, 5);
		this.mapaOslo.conectar(museoBarco, museoFram, 5);
		
		this.mapaOslo.conectar(museoBarco, museoVikingo, 5);
		this.mapaOslo.conectar(museoVikingo, museoBarco, 5);
		
		this.mapaOslo.conectar(museoVikingo, akker, 30);
		this.mapaOslo.conectar(akker, museoVikingo, 30);
		
		visitaOslo = new VisitaOslo();
	}
	
	
	@Test
	void testPaseoEnBici() {
		ListaGenerica<String> restringidos = new ListaEnlazadaGenerica<String>();
		restringidos.agregarFinal(akker.dato());
		restringidos.agregarFinal(palacioReal.dato());
		ListaGenerica<String> camino = visitaOslo.paseoEnBici(mapaOslo, "Museo Vikingo", 120, restringidos);
		System.out.println(camino);
		System.out.println("Camino longitud" + camino.tamanio());
		
	}
	
}
