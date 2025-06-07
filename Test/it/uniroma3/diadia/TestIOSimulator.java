package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Labirinto;

class TestIOSimulator {

	private List<String> comandiDaEseguireVai;
	private List<String> comandiDaEseguirePrendi;
	private List<String> comandiDaEseguirePosa;
	private List<String> comandiDaEseguirePrendiEPosa;
	private IOSimulator ioVai;
	private IOSimulator ioPrendi;
	private IOSimulator ioPosa;
	private IOSimulator ioPrendiEPosa;
	private DiaDia diadia;
	private Labirinto labirinto;
	
	@BeforeEach
	public void setUp() {
		this.comandiDaEseguireVai = new ArrayList<String>();
		this.comandiDaEseguirePosa = new ArrayList<String>();
		this.comandiDaEseguirePrendi = new ArrayList<String>();
		this.comandiDaEseguirePrendiEPosa = new ArrayList<String>();
		this.comandiDaEseguirePrendiEPosa.add("prendi osso");
		this.comandiDaEseguirePrendiEPosa.add("posa osso");
		this.comandiDaEseguirePrendiEPosa.add("fine");
		this.comandiDaEseguirePrendi.add("prendi osso");
		this.comandiDaEseguirePrendi.add("fine");
		this.comandiDaEseguirePosa.add("posa osso");
		this.comandiDaEseguirePosa.add("fine");
		this.comandiDaEseguireVai.add("vai sud");
		this.comandiDaEseguireVai.add("fine");
		this.labirinto = new Labirinto.LabirintoBuilder()
				.addStanza("Atrio")
				.addStanza("Biblioteca")
				.addStanzaIniziale("Atrio")
				.addAttrezzo("osso", 3, "Atrio")
				.addStanzaVincente("Biblioteca")
				.addStanza("Aula N10")
				.addAdiacenza("Atrio", "Aula N10", "sud")
				.addAdiacenza("Atrio", "Biblioteca", "nord")
				.getLabirinto();
		this.ioPrendiEPosa = creaSimulazionePartitaEGioca(comandiDaEseguirePrendiEPosa);
		this.ioPosa = creaSimulazionePartitaEGioca(comandiDaEseguirePosa);
		this.ioPrendi = creaSimulazionePartitaEGioca(comandiDaEseguirePrendi);
		this.ioVai = creaSimulazionePartitaEGioca(comandiDaEseguireVai);
	}
	
	public IOSimulator creaSimulazionePartitaEGioca(List<String> righeDaLeggere) {
		IOSimulator io = new IOSimulator(righeDaLeggere);
		new DiaDia(io, this.labirinto, 1).gioca();
		return io;
	}
	
	public static void assertContains (String expected, String interariga) {
		assertTrue(interariga.contains(expected));
	}
	
	
	@Test
	void testPartitaComandoPrendi() {
		assertTrue(this.ioPrendi.hasNextMessaggio());
		String messaggio1 = this.ioPrendi.messaggioCorrente();
		assertEquals(DiaDia.MESSAGGIO_BENVENUTO, messaggio1);
		assertTrue(this.ioPrendi.hasNextMessaggio());
		String messaggio2 = this.ioPrendi.messaggioCorrente();
		assertContains("LIVELLO", messaggio2);
		assertTrue(this.ioPrendi.hasNextMessaggio());
		String messaggio3 = this.ioPrendi.messaggioCorrente();
		assertContains("aggiunto", messaggio3);
		String messaggio4 = this.ioPrendi.messaggioCorrente();
		assertEquals("Grazie di aver giocato!", messaggio4);
		assertFalse(this.ioPrendi.hasNextMessaggio());	
	}
	
	@Test
	void testPartitaConComandoVai() {
		assertTrue(this.ioVai.hasNextMessaggio());
		String messaggio1 = this.ioVai.messaggioCorrente();
		assertEquals(DiaDia.MESSAGGIO_BENVENUTO, messaggio1);
		assertTrue(this.ioVai.hasNextMessaggio());
		String messaggio2 = this.ioVai.messaggioCorrente();
		assertContains("LIVELLO", messaggio2);
		assertTrue(this.ioVai.hasNextMessaggio());
		String messaggio3 = this.ioVai.messaggioCorrente();
		assertContains("Aula N10", messaggio3);
		assertTrue(this.ioVai.hasNextMessaggio());
		String messaggio4 = this.ioVai.messaggioCorrente();
		assertEquals("Grazie di aver giocato!", messaggio4);
		assertFalse(this.ioVai.hasNextMessaggio());	
	}
	
	@Test
	void testPartitaComandoPosa() {
		assertTrue(this.ioPosa.hasNextMessaggio());
		String messaggio1 = this.ioPosa.messaggioCorrente();
		assertEquals(DiaDia.MESSAGGIO_BENVENUTO, messaggio1);
		assertTrue(this.ioPosa.hasNextMessaggio());
		String messaggio2 = this.ioPosa.messaggioCorrente();
		assertContains("LIVELLO", messaggio2);
		assertTrue(this.ioPosa.hasNextMessaggio());
		String messaggio3 = this.ioPosa.messaggioCorrente();
		assertContains("Non hai oggetti", messaggio3);
		assertTrue(this.ioPosa.hasNextMessaggio());
		String messaggio4 = this.ioPosa.messaggioCorrente();
		assertEquals("Grazie di aver giocato!", messaggio4);
		assertFalse(this.ioPosa.hasNextMessaggio());	
	}
	
	@Test
	void testPartitaComandoPrendiEPosa() {
		assertTrue(this.ioPrendiEPosa.hasNextMessaggio());
		String messaggio1 = this.ioPrendiEPosa.messaggioCorrente();
		assertEquals(DiaDia.MESSAGGIO_BENVENUTO, messaggio1);
		assertTrue(this.ioPrendiEPosa.hasNextMessaggio());
		String messaggio2 = this.ioPrendiEPosa.messaggioCorrente();
		assertContains("LIVELLO", messaggio2);
		assertTrue(this.ioPrendiEPosa.hasNextMessaggio());
		String messaggio3 = this.ioPrendiEPosa.messaggioCorrente();
		assertContains("aggiunto", messaggio3);
		String messaggio4 = this.ioPrendiEPosa.messaggioCorrente();
		assertContains("Hai posato", messaggio4);
		assertTrue(this.ioPrendiEPosa.hasNextMessaggio());
		String messaggio5 = this.ioPrendiEPosa.messaggioCorrente();
		assertEquals("Grazie di aver giocato!", messaggio5);
		assertFalse(this.ioPrendiEPosa.hasNextMessaggio());
		
	}
	
}
