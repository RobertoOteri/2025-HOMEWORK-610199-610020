package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;

class TestPartita {

	private Partita partita1;
	private Partita partita2;
	private Partita partita3;
	private Labirinto labirinto;
	
	@BeforeEach
	public void setUp() {
		this.labirinto = new Labirinto.LabirintoBuilder()
				.addStanza("Cucina")
				.addStanza("Biblioteca")
				.addStanzaIniziale("Cucina")
				.addStanzaVincente("Biblioteca")
				.getLabirinto();
		partita1 = new Partita(this.labirinto);
		partita2 = new Partita(this.labirinto);
		partita3 = new Partita(this.labirinto);
		partita2.getGiocatore().setCfu(0);
	}

	@Test
	public void testSetFinita() {
		partita1.setFinita();
		assertTrue(partita1.isFinita());
	}

	@Test
	public void testIsFinitaIniziale() {
		assertFalse(partita1.isFinita());
	}
	
	@Test
	public void testIsFinitaCfuUguale0() {
		assertTrue(partita2.isFinita());
	}
	
	@Test
	public void testVinta() {
		partita3.getLabirinto().setStanzaCorrente(partita3.getLabirinto().getStanzaVincente());
		assertTrue(partita3.vinta());
	}
	
	@Test
	public void testVintaIniziale() {
		assertFalse(partita1.vinta());
	}
	
	@Test
	public void testVintaCfu0() {
		assertFalse(partita2.vinta());
	}
}
