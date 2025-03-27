package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

class TestPartita {

	private Partita partita1;
	private Partita partita2;
	private Partita partita3;
	
	@BeforeEach
	public void setUp() {
		partita1 = new Partita();
		partita2 = new Partita();
		partita3 = new Partita();
		partita2.getGiocatore().setCfu(0);
		partita3.getLabirinto().setStanzaCorrente(partita3.getLabirinto().getStanzaVincente());
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
