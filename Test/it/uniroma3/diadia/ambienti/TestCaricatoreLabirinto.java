package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.FormatoFileNonValidoException;

class TestCaricatoreLabirinto {

	private final static String NOME_FILE = "resources/labirintoDiTest.txt";
	
	@Test
	public void testCarica() throws FormatoFileNonValidoException {

	Labirinto labiritno = new Labirinto(NOME_FILE);
	assertEquals("N10", labiritno.getStanzaCorrente().getNome());
	assertEquals("Biblioteca", labiritno.getStanzaVincente().getNome());
	assertEquals("osso", labiritno.getStanzaCorrente().getAttrezzo("osso").getNome());
	assertEquals(5, labiritno.getStanzaCorrente().getAttrezzo("osso").getPeso());
	}

}
