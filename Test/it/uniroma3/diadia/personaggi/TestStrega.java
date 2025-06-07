package it.uniroma3.diadia.personaggi;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.ambienti.Labirinto;

class TestStrega {

	private Partita partita;
	private Strega strega;
	private Stanza stanzaCorrente;
	private Stanza stanzaConPochiAttrezzi;
	private Stanza stanzaConMoltiAttrezzi;

	@BeforeEach
	public void setUp() {
		stanzaCorrente = new Stanza("attuale");
		stanzaConPochiAttrezzi = new Stanza("vuota");
		stanzaConMoltiAttrezzi = new Stanza("piena");
		stanzaCorrente.impostaStanzaAdiacente("nord", stanzaConPochiAttrezzi);
		stanzaCorrente.impostaStanzaAdiacente("sud", stanzaConMoltiAttrezzi);
		stanzaConMoltiAttrezzi.addAttrezzo(new Attrezzo("martello", 1));
		stanzaConMoltiAttrezzi.addAttrezzo(new Attrezzo("chiave", 1));
		Labirinto labirinto = new Labirinto.LabirintoBuilder()
			.addStanza("attuale")
			.addStanzaIniziale("attuale")
			.getLabirinto();
		labirinto.setStanzaCorrente(stanzaCorrente);
		partita = new Partita(labirinto);
		labirinto.setStanzaCorrente(stanzaCorrente);
		strega = new Strega();
	}

	@Test
	public void testAgisciNonSalutataVaInStanzaConMenoAttrezzi() {
		strega.agisci(partita);
		assertEquals("vuota", partita.getLabirinto().getStanzaCorrente().getNome());
	}

	@Test
	public void testAgisciSalutataVaInStanzaConPiuAttrezzi() {
		strega.saluta();
		strega.agisci(partita);
		assertEquals("piena", partita.getLabirinto().getStanzaCorrente().getNome());
	}

	@Test
	public void testAgisciNessunaStanzaAdiacente() {
		Stanza stanzaIsolata = new Stanza("isolata");
		Labirinto labirinto = new Labirinto.LabirintoBuilder()
			.addStanzaIniziale("isolata")
			.getLabirinto();
		labirinto.setStanzaCorrente(stanzaIsolata);
		Partita partitaIsolata = new Partita(labirinto);

		String risultato = strega.agisci(partitaIsolata);
		assertTrue(risultato.contains("non sortisce alcun effetto"));
	}

	@Test
	public void testRiceviRegalo() {
		Attrezzo bastone = new Attrezzo("bastone", 2);
		String risposta = strega.riceviRegalo(bastone, partita);
		assertTrue(risposta.contains("HAHAHAH"));
	}
}
