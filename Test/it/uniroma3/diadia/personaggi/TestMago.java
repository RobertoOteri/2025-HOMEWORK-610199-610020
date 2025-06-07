package it.uniroma3.diadia.personaggi;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.giocatore.Giocatore;

class TestMago {

	private Partita partita;
	private Mago mago;
	private Attrezzo dono;
	private Attrezzo oggettoDaModificare;
	private Stanza stanza;

	@BeforeEach
	public void setUp() {
		dono = new Attrezzo("bacchetta", 2);
		oggettoDaModificare = new Attrezzo("pietra", 4);
		stanza = new Stanza("torre");
		Labirinto labirinto = new Labirinto.LabirintoBuilder()
				.addStanza("torre")
				.addStanzaIniziale("torre")
				.getLabirinto();
		labirinto.setStanzaCorrente(stanza);
		partita = new Partita(labirinto);
		mago = new Mago(dono);
	}

	@Test
	public void testAgisciAggiungeAttrezzoQuandoDisponibile() {
		assertFalse(stanza.hasAttrezzo(dono.getNome()));
		mago.agisci(partita);
		assertTrue(stanza.hasAttrezzo(dono.getNome()));
	}

	@Test
	public void testAgisciSenzaAttrezzoDaDonare() {
		mago.setAttrezzoDaDonare(null);
		mago.agisci(partita);
		assertFalse(stanza.hasAttrezzo("null"));
	}

	@Test
	public void testRiceviRegaloDimezzaPesoEAggiungeInStanza() {
		int pesoIniziale = oggettoDaModificare.getPeso();
		mago.riceviRegalo(oggettoDaModificare, partita);
		assertEquals(pesoIniziale / 2, stanza.getAttrezzo("pietra").getPeso());
	}

	@Test
	public void testRiceviRegaloStanzaPienaNonModificaAttrezzoFinale() {
		for (int i = 0; i < 10; i++) {
			stanza.addAttrezzo(new Attrezzo("oggetto" + i, 1));
		}
		Giocatore giocatore = partita.getGiocatore();
		mago.riceviRegalo(oggettoDaModificare, partita);
		Attrezzo recuperato = giocatore.GetBorsa().getAttrezzo("pietra");
		assertNotNull(recuperato);
		assertEquals(4, recuperato.getPeso());
	}
}
