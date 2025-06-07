package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestLabirinto {

	private Labirinto labirinto;
	private Stanza stanza;
	private Stanza stanzaVincente;
	
	@BeforeEach
	public void setUp() {
		labirinto = new Labirinto();
		stanza = new Stanza("Laboratorio Campus");
		stanzaVincente = new Stanza("Biblioteca");
		labirinto.setStanzaCorrente(stanza);
		labirinto.setStanzaVincente(stanzaVincente);
	}
	
	@Test
	public void testStanzaVincenteNonNull() {
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
