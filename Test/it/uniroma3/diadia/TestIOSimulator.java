package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestIOSimulator {

	private String[] comandiDaEseguireVai = {"vai sud", "fine"};
	private String[] comandiDaEseguirePrendi = {"prendi osso", "fine"};
	private String[] comandiDaEseguirePosa = {"posa osso", "fine"};
	private String[] comandiDaEseguirePrendiEPosa = {"prendi osso", "posa osso", "fine"};
	private IOSimulator ioVai;
	private IOSimulator ioPrendi;
	private IOSimulator ioPosa;
	private IOSimulator ioPrendiEPosa;
	private DiaDia diadia;
	
	@BeforeEach
	public void setUp() {
		this.ioVai = creaSimulazionePartitaEGioca(comandiDaEseguireVai);
		this.ioPrendi = creaSimulazionePartitaEGioca(comandiDaEseguirePrendi);
		this.ioPosa = creaSimulazionePartitaEGioca(comandiDaEseguirePosa);
		this.ioPrendiEPosa = creaSimulazionePartitaEGioca(comandiDaEseguirePrendiEPosa);
	}
	
	public static IOSimulator creaSimulazionePartitaEGioca(String[] righeDaLeggere) {
		IOSimulator io = new IOSimulator(righeDaLeggere);
		new DiaDia(io).gioca();
		return io;
	}
	
	public static void assertContains (String expected, String interariga) {
		assertTrue(interariga.contains(expected));
	}
	@Test
	void testPartitaConComandoVai() {
		assertTrue(this.ioVai.hasNextMessaggio());
		String messaggio1 = this.ioVai.messaggioCorrente();
		assertEquals(DiaDia.MESSAGGIO_BENVENUTO, messaggio1);
		assertTrue(this.ioVai.hasNextMessaggio());
		String messaggio2 = this.ioVai.messaggioCorrente();
		assertContains("Aula N10", messaggio2);
		assertTrue(this.ioVai.hasNextMessaggio());
		String messaggio3 = this.ioVai.messaggioCorrente();
		assertEquals("Grazie di aver giocato!", messaggio3);
		assertFalse(this.ioVai.hasNextMessaggio());	
	}
	
	@Test
	void testPartitaComandoPrendi() {
		assertTrue(this.ioPrendi.hasNextMessaggio());
		String messaggio1 = this.ioPrendi.messaggioCorrente();
		assertEquals(DiaDia.MESSAGGIO_BENVENUTO, messaggio1);
		assertTrue(this.ioPrendi.hasNextMessaggio());
		String messaggio2 = this.ioPrendi.messaggioCorrente();
		assertContains("aggiunto", messaggio2);
		String messaggio3 = this.ioPrendi.messaggioCorrente();
		assertEquals("Grazie di aver giocato!", messaggio3);
		assertFalse(this.ioPrendi.hasNextMessaggio());	
	}
	
	@Test
	void testPartitaComandoPosa() {
		assertTrue(this.ioPosa.hasNextMessaggio());
		String messaggio1 = this.ioPosa.messaggioCorrente();
		assertEquals(DiaDia.MESSAGGIO_BENVENUTO, messaggio1);
		assertTrue(this.ioPosa.hasNextMessaggio());
		String messaggio2 = this.ioPosa.messaggioCorrente();
		assertEquals("Non hai oggetti nella borsa!!\n", messaggio2);
		String messaggio3 = this.ioPosa.messaggioCorrente();
		assertEquals("Grazie di aver giocato!", messaggio3);
		assertFalse(this.ioPosa.hasNextMessaggio());	
	}
	
	@Test
	void testPartitaComandoPrendiEPosa() {
		assertTrue(this.ioPrendiEPosa.hasNextMessaggio());
		String messaggio1 = this.ioPrendiEPosa.messaggioCorrente();
		assertEquals(DiaDia.MESSAGGIO_BENVENUTO, messaggio1);
		assertTrue(this.ioPrendiEPosa.hasNextMessaggio());
		String messaggio2 = this.ioPrendiEPosa.messaggioCorrente();
		assertContains("aggiunto", messaggio2);
		assertTrue(this.ioPrendiEPosa.hasNextMessaggio());
		String messaggio3 = this.ioPrendiEPosa.messaggioCorrente();
		assertContains("Hai posato l'attrezzo", messaggio3);
		String messaggio4 = this.ioPrendiEPosa.messaggioCorrente();
		assertEquals("Grazie di aver giocato!", messaggio4);
		assertFalse(this.ioPrendiEPosa.hasNextMessaggio());	
	}
	
}
