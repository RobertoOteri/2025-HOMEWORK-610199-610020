package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.comandi.Comando;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestFabbricaDiComandiFisarmonica {

	private FabbricaDiComandiFisarmonica fabbricaDiComandi;
	private Comando comandoVai;
	private Comando comandoAiuto;
	private Comando comandoFine;
	private Comando comandoPrendi;
	private Comando comandoPosa;
	private Comando comandoGuarda;
	
	@BeforeEach
	public void setUp() {
		this.fabbricaDiComandi = new FabbricaDiComandiFisarmonica();
		comandoVai = this.fabbricaDiComandi.costruisciComando("vai", new IOConsole());
		comandoAiuto = this.fabbricaDiComandi.costruisciComando("aiuto", new IOConsole());
		comandoFine = this.fabbricaDiComandi.costruisciComando("fine", new IOConsole());
		comandoPrendi = this.fabbricaDiComandi.costruisciComando("prendi", new IOConsole());
		comandoPosa = this.fabbricaDiComandi.costruisciComando("posa", new IOConsole());
		comandoGuarda = this.fabbricaDiComandi.costruisciComando("guarda", new IOConsole());
		comandoVai.setParametro("nord");
		comandoPrendi.setParametro("spada");
		comandoPosa.setParametro("osso");
	
	}
	
	@Test
	void testVai() {
		assertEquals("vai", comandoVai.getNome());
		assertEquals("nord", comandoVai.getParametro());
	}
	
	@Test
	void testAiuto() {
		assertEquals("aiuto", comandoAiuto.getNome());
	}
	
	@Test
	void testFine() {
		assertEquals("fine", comandoFine.getNome());
	}
	
	@Test
	void testPrendi() {
		assertEquals("prendi", comandoPrendi.getNome());
		assertEquals("spada", comandoPrendi.getParametro());
	}
	
	@Test
	void testPosa() {
		assertEquals("posa", comandoPosa.getNome());
		assertEquals("osso", comandoPosa.getParametro());
	}
	
	@Test
	void testGuarda() {
		assertEquals("guarda", comandoGuarda.getNome());
	}
}
