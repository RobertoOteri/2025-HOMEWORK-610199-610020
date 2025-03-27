package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestLabirinto {

	private Labirinto labirinto;
	private Stanza stanza;
	
	@BeforeEach
	public void setUp() {
		labirinto = new Labirinto();
		stanza = new Stanza("Laboratorio Campus");
		labirinto.setStanzaCorrente(stanza);
	}
	
	@Test
	public void testStanzaVincenteNonNulla() {
		assertNotNull(labirinto.getStanzaVincente());
	}
	
	@Test
	public void testNomeStanzaVincente() {
		assertEquals(labirinto.getStanzaVincente().getNome(), "Biblioteca");
	}
	
	@Test
	public void  testStanzaVincenteNonAulaN11() {
		assertFalse("Aula N11" == labirinto.getStanzaVincente().getNome());
	}
	
	@Test
	public void testNomeStanzaCorrente() {
		assertEquals("Laboratorio Campus", labirinto.getStanzaCorrente().getNome());
	}
	
	@Test
	public void testStanzaCorrenteCorretta() {
		assertTrue(stanza==labirinto.getStanzaCorrente());
	}
	
	@Test
	public void testStanzaAdiacenteNordNulla() {
		assertNull(labirinto.getStanzaCorrente().getStanzaAdiacente("nord"));
	}
}
