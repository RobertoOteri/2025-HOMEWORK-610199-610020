package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.giocatore.Borsa;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestComandoPrendi {

	private String nomeAttrezzoDaPrendere = "attrezzoDaPrendere";
	private Partita partita;
	private ComandoPrendi comandoPrendi;
	private String nomeAttrezzoNonPresente = "attrezzoNonPresente";
	
	@BeforeEach
	public void setUp() {
		this.comandoPrendi = new ComandoPrendi();
		this.comandoPrendi.setIoConsole(new IOConsole());
		this.partita = new Partita();
		Attrezzo nuovoAttrezzo = new Attrezzo(nomeAttrezzoDaPrendere, 1);
		this.partita.getLabirinto().getStanzaCorrente().addAttrezzo(nuovoAttrezzo);
		
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
