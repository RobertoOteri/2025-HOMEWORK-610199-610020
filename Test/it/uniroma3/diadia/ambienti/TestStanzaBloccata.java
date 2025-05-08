package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestStanzaBloccata {


	
	private StanzaBloccata stanzabloccata;
	private Stanza stanzaNord;
	private Stanza stanzaSud;
	private Attrezzo passpartout;
	
	@BeforeEach
	public void setUp() {
		this.stanzabloccata = new StanzaBloccata("atrio", "nord", "passpartout");
		this.stanzaNord = new Stanza("cucina");
		this.stanzaSud = new Stanza("studio");
		this.passpartout = new Attrezzo("passpartout", 1);
		this.stanzabloccata.impostaStanzaAdiacente("nord", stanzaNord);
		this.stanzabloccata.impostaStanzaAdiacente("sud", stanzaSud);
	}
	
	@Test
	public void testStanzaSud() {
		assertEquals(this.stanzaSud.getNome(), this.stanzabloccata.getStanzaAdiacente("sud").getNome());
	}
	
	@Test
	public void testStanzaNordSenzaPasspartout() {
		assertEquals(this.stanzabloccata.getNome(), this.stanzabloccata.getStanzaAdiacente("nord").getNome());
	}
	
	@Test
	public void testStanzaNordConPasspartout() {
		this.stanzaNord.addAttrezzo(passpartout);
		assertEquals(this.stanzaNord.getNome(), this.stanzabloccata.getStanzaAdiacente("nord").getNome());
	}
	

}
