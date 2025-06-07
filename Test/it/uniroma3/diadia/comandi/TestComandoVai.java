package it.uniroma3.diadia.comandi;

import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertTrue;

import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.DiaDia;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.ambienti.Labirinto;

public class TestComandoVai {
	
	
	private static final String NOME_STANZA_PARTENZA = "Partenza";
	private static final String NORD = "nord";
	private Partita partita;
	private Comando comandoVai;
	private Stanza partenza;
	private Labirinto labirinto;
	private Scanner scanner;
	

	@Before
	public void setUp() throws Exception {
		scanner = new Scanner(System.in);
		this.labirinto = new Labirinto.LabirintoBuilder()
				.addStanza(NOME_STANZA_PARTENZA)
				.addStanzaIniziale(NOME_STANZA_PARTENZA)
				.addStanza("Destinazione")
				.addAdiacenza(NOME_STANZA_PARTENZA, "Destinazione", NORD)
				.getLabirinto();
		this.comandoVai = new ComandoVai();
		this.comandoVai.setIoConsole(new IOConsole(scanner));
		this.partita = new Partita(labirinto);
	}

	@Test
	public void testVaiStanzaNonPresente() {
		this.comandoVai.setParametro("sud");
		this.comandoVai.esegui(this.partita);
		assertEquals(NOME_STANZA_PARTENZA, this.partita.getLabirinto().getStanzaCorrente().getNome());
	}
	
	@Test
	public void testVaiStanzaPresente() {
		this.comandoVai.setParametro(NORD);
		this.comandoVai.esegui(partita);
		assertEquals("Destinazione", this.partita.getLabirinto().getStanzaCorrente().getNome());
	}
	
	@Test
	public void testVaiStanzaPresenteInDirezioneSbagliata() {
		this.comandoVai.setParametro("sud");
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