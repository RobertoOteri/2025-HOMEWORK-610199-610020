package it.uniroma3.diadia.comandi;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

public class TestComandoPosa {
	
	private static final String ATTREZZO_DA_POSARE = "AttrezzoDaPosare";
	private ComandoPosa comandoPosa;
	private Partita partita;
	private Labirinto labirinto;
	private Scanner scanner;

	@Before
	public void setUp() throws Exception {
		scanner = new Scanner(System.in);
		labirinto = new Labirinto.LabirintoBuilder()
				.addStanza("Cucina")
				.addStanzaIniziale("Cucina")
				.getLabirinto();
		this.comandoPosa = new ComandoPosa();
		this.comandoPosa.setIoConsole(new IOConsole(scanner));
		this.partita = new Partita(labirinto);
		Borsa borsa = partita.getGiocatore().GetBorsa();
		Attrezzo attrezzoNuovo = new Attrezzo(ATTREZZO_DA_POSARE, 1);
		borsa.addAttrezzo(attrezzoNuovo);
	}

	@Test
	public void testEseguiAttrezzoPresente() {
		this.comandoPosa.setParametro(ATTREZZO_DA_POSARE);
		this.comandoPosa.esegui(partita);
		assertTrue(partita.getLabirinto().getStanzaCorrente().hasAttrezzo(ATTREZZO_DA_POSARE));
		assertFalse(partita.getGiocatore().GetBorsa().hasAttrezzo(ATTREZZO_DA_POSARE));
	}
	
	@Test
	public void testEseguiAttrezzoNonPresente() {
		String nonPresente = "attrezzoNonPresente";
		this.comandoPosa.setParametro(nonPresente);
		this.comandoPosa.esegui(partita);
		assertFalse(partita.getLabirinto().getStanzaCorrente().hasAttrezzo(nonPresente));
		assertFalse(partita.getLabirinto().getStanzaCorrente().hasAttrezzo(ATTREZZO_DA_POSARE));
		assertTrue(partita.getGiocatore().GetBorsa().hasAttrezzo(ATTREZZO_DA_POSARE));
	}
	
	@Test
	public void testEseguiStanzaPiena() {
		Stanza stanzaCorrente = partita.getLabirinto().getStanzaCorrente();
		for (int i = 0; i < stanzaCorrente.getNumeroMassimoAttrezzi(); i++) {
			stanzaCorrente.addAttrezzo(new Attrezzo("attrezzo"+i, 1));
		}
		
		this.comandoPosa.setParametro(ATTREZZO_DA_POSARE);
		this.comandoPosa.esegui(partita);
		assertFalse(stanzaCorrente.hasAttrezzo(ATTREZZO_DA_POSARE));
		assertTrue(partita.getGiocatore().GetBorsa().hasAttrezzo(ATTREZZO_DA_POSARE));
	}
	
}
