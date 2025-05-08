package it.uniroma3.diadia.comandi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.DiaDia;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

public class TestComandoVai {
	
	
	private static final String NOME_STANZA_PARTENZA = "Partenza";
	private static final String NORD = "nord";
	private Partita partita;
	private Comando comandoVai;
	private Stanza partenza;
	

	@Before
	public void setUp() throws Exception {
		this.comandoVai = new ComandoVai();
		this.comandoVai.setIoConsole(new IOConsole());
		this.partita = new Partita();
		this.partenza = new Stanza(NOME_STANZA_PARTENZA);
		this.partita.getLabirinto().setStanzaCorrente(this.partenza);
	}

	@Test
	public void testVaiStanzaNonPresente() {
		this.comandoVai.setParametro(NORD);
		this.comandoVai.esegui(this.partita);
		assertEquals(NOME_STANZA_PARTENZA, this.partita.getLabirinto().getStanzaCorrente().getNome());
	}
	
	@Test
	public void testVaiStanzaPresente() {
		Stanza destinazione = new Stanza("Destinazione");
		this.partenza.impostaStanzaAdiacente(NORD, destinazione);
		this.comandoVai.setParametro(NORD);
		this.comandoVai.esegui(partita);
		assertEquals("Destinazione", this.partita.getLabirinto().getStanzaCorrente().getNome());
	}
	
	@Test
	public void testVaiStanzaPresenteInDirezioneSbagliata() {
		Stanza destinazione = new Stanza("Destinazione");
		this.partenza.impostaStanzaAdiacente("sud", destinazione);
		this.comandoVai.setParametro(NORD);
		this.comandoVai.esegui(partita);
		assertEquals(NOME_STANZA_PARTENZA, this.partita.getLabirinto().getStanzaCorrente().getNome());
	}
	
	
	@Test
	public void testCfueGiocatore() {
		Stanza stanzaAdiacente = new Stanza("Adiacente");
		this.partita.getLabirinto().getStanzaCorrente().impostaStanzaAdiacente(this.NORD,stanzaAdiacente);
		this.comandoVai.setParametro(NORD);
		this.comandoVai.esegui(partita);
		assertEquals(19,this.partita.getGiocatore().getCfu());
	}

}