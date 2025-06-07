package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.comandi.Comando;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestFabbricaDiComandiFisarmonica {

	private FabbricaDiComandiFisarmonica fabbricaDiComandi;
	private AbstractComando comandoVai;
	private AbstractComando comandoAiuto;
	private AbstractComando comandoFine;
	private AbstractComando comandoPrendi;
	private AbstractComando comandoPosa;
	private AbstractComando comandoGuarda;
	private Scanner scanner;
	
	@BeforeEach
	public void setUp() {
		this.scanner = new Scanner(System.in);
		this.fabbricaDiComandi = new FabbricaDiComandiFisarmonica();
		comandoVai = this.fabbricaDiComandi.costruisciComando("vai", new IOConsole(scanner));
		comandoAiuto = this.fabbricaDiComandi.costruisciComando("aiuto", new IOConsole(scanner));
		comandoFine = this.fabbricaDiComandi.costruisciComando("fine", new IOConsole(scanner));
		comandoPrendi = this.fabbricaDiComandi.costruisciComando("prendi", new IOConsole(scanner));
		comandoPosa = this.fabbricaDiComandi.costruisciComando("posa", new IOConsole(scanner));
		comandoGuarda = this.fabbricaDiComandi.costruisciComando("guarda", new IOConsole(scanner));
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
