package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.giocatore.Borsa;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestComandoPrendi {

	private String nomeAttrezzoDaPrendere = "attrezzoDaPrendere";
	private Partita partita;
	private ComandoPrendi comandoPrendi;
	private String nomeAttrezzoNonPresente = "attrezzoNonPresente";
	private Labirinto labirinto;
	private Scanner scanner;
	
	@BeforeEach
	public void setUp() {
		scanner = new Scanner(System.in);
		labirinto = new Labirinto.LabirintoBuilder()
				.addStanza("cucina")
				.addStanzaIniziale("cucina")
				.addAttrezzo(nomeAttrezzoDaPrendere, 1, "cucina")
				.getLabirinto();
		this.comandoPrendi = new ComandoPrendi();
		this.comandoPrendi.setIoConsole(new IOConsole(scanner));
		this.partita = new Partita(labirinto);

		
	}
	@Test
	public void testEseguiAttrezzoPresente() {
		this.comandoPrendi.setParametro(nomeAttrezzoDaPrendere);
		this.comandoPrendi.esegui(partita);
		assertTrue(partita.getGiocatore().GetBorsa().hasAttrezzo(nomeAttrezzoDaPrendere));
		assertFalse(partita.getLabirinto().getStanzaCorrente().hasAttrezzo(nomeAttrezzoDaPrendere));
	}
	
	@Test
	public void testEseguiAttrezzoNonPresente() {
		this.comandoPrendi.setParametro(nomeAttrezzoNonPresente);
		this.comandoPrendi.esegui(partita);
		assertFalse(partita.getGiocatore().GetBorsa().hasAttrezzo(nomeAttrezzoNonPresente));
		assertFalse(partita.getGiocatore().GetBorsa().hasAttrezzo(nomeAttrezzoDaPrendere));
		assertFalse(partita.getLabirinto().getStanzaCorrente().hasAttrezzo(nomeAttrezzoNonPresente));
	}
	
	@Test
	public void testEseguiBorsaPiena() {
		Borsa borsa = partita.getGiocatore().GetBorsa();
		borsa.addAttrezzo(new Attrezzo("attrezzoPesante", borsa.getPesoMax()));
		this.comandoPrendi.setParametro(nomeAttrezzoDaPrendere);
		this.comandoPrendi.esegui(partita);
		assertFalse(partita.getGiocatore().GetBorsa().hasAttrezzo(nomeAttrezzoDaPrendere));
		assertTrue(partita.getLabirinto().getStanzaCorrente().hasAttrezzo(nomeAttrezzoDaPrendere));
	}
}
