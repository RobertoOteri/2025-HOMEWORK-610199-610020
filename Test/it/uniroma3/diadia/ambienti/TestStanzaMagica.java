package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestStanzaMagica {

	private StanzaMagica stanzaMagica;
	private Attrezzo piatto;
	private Attrezzo forchetta;
	private Attrezzo coltello;
	private Attrezzo bicchiere;
	
	@BeforeEach
	public void setUp() {
		this.stanzaMagica = new StanzaMagica("cucina");
		this.piatto = new Attrezzo("piatto", 3);
		this.forchetta = new Attrezzo("forchetta", 1);
		this.coltello = new Attrezzo("coltello", 1);
		this.stanzaMagica.addAttrezzo(forchetta);
		this.stanzaMagica.addAttrezzo(coltello);
		this.stanzaMagica.addAttrezzo(piatto);
	}
	
	@Test
	public void testForchettaInvariata() {
		assertEquals(this.forchetta.getNome(), this.stanzaMagica.getAttrezzi()[0].getNome());
		assertEquals(this.forchetta.getPeso(), this.stanzaMagica.getAttrezzi()[0].getPeso());
	}
	
	@Test
	public void testColtelloInvariato() {
		assertEquals(this.coltello.getNome(), this.stanzaMagica.getAttrezzi()[1].getNome());
		assertEquals(this.coltello.getPeso(), this.stanzaMagica.getAttrezzi()[1].getPeso());
	}
	
	@Test
	public void testEffettoMagico() {
		assertEquals("ottaip", this.stanzaMagica.getAttrezzi()[2].getNome());
		assertTrue(6==this.stanzaMagica.getAttrezzi()[2].getPeso());
	}
	
}
