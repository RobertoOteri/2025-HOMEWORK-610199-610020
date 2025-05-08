package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestStanzaBuia {


	private StanzaBuia stanzaBuia;
	private Attrezzo lanterna;

	
	public void assertContains(String stringaContenuta, String stringa) {
		assertTrue(stringa.contains(stringaContenuta));
	}
	@BeforeEach
	void setUp() throws Exception {
		this.lanterna = new Attrezzo("lanterna",1);
		this.stanzaBuia = new StanzaBuia("atrio","lanterna");
	}

	@Test
	void testStanzaSenzaLanterna() {
		assertEquals("\nQui c'è un buio pesto....", this.stanzaBuia.getDescrizione());
	}
	
	@Test
	void testStanzaConLanterna() {
		stanzaBuia.addAttrezzo(lanterna);
		assertContains(this.stanzaBuia.getNome(), this.stanzaBuia.getDescrizione());
	}


}
