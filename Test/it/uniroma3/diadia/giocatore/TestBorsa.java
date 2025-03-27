package it.uniroma3.diadia.giocatore;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.uniroma3.diadia.attrezzi.Attrezzo;
class TestBorsa {

	private Borsa borsa1;
	private Borsa borsa2;
	private Attrezzo spada;
	private Attrezzo lancia;
	private Attrezzo chiodo;
	
	@BeforeEach
	public void setUp() {
		borsa1 = new Borsa(10);
		borsa2 = new Borsa(10);
		spada = new Attrezzo("spada", 3);
		lancia = new Attrezzo("lancia", 8);
		chiodo = new Attrezzo("chiodo", 1);
		borsa1.addAttrezzo(spada);
	}
	 @Test
	 public void aggiuntaAttrezzoOK() {
		 assertTrue(borsa1.addAttrezzo(chiodo));
	 }
	 
	 @Test
	 public void aggiuntaAttrezzoSuperaPeso2() {
		 assertFalse(borsa1.addAttrezzo(lancia));
	 }
	 
	 @Test
	 public void aggiuntaAttrezzoPossibile() {
		 assertTrue(borsa2.addAttrezzo(lancia));
	 }
	 
	 @Test
	 public void rimozioneAttrezzoNonPresente() {
		 assertNull(borsa1.removeAttrezzo("chiodo"));
	 }
	 
	 @Test
	 public void rimozioneAttrezzoPresente() {
		 assertEquals(spada, borsa1.removeAttrezzo("spada"));
	 }
	 
	 @Test
	 public void rimozioneDaBorsaVuota() {
		 assertNull(borsa2.removeAttrezzo("lancia")); 
	 }
	 
	 @Test
	 public void verificaAttrezzoNonPresente() {
		 assertFalse(borsa2.hasAttrezzo("lancia"));	 
	 }
	 
	 @Test
	 public void verificaAttrezzoPresente() {
		 assertTrue(borsa1.hasAttrezzo("spada"));	 
	 }
	 
	 @Test
	 public void verificaAttrezzoNonAggiunto() {
		 assertFalse(borsa1.hasAttrezzo("chiodo"));	 
	 }
	 
	 @Test
	 public void recuperoAttrezzoPresente() {
		 assertSame(spada, borsa1.getAttrezzo("spada"));
	 }
	 
	 @Test
	 public void  recuperoAttrezzoAssente() {
		 assertNull(borsa2.getAttrezzo("chiodo"));
	 }
	 
	 @Test
	 public void recuperoAttrezzoNonInserito() {
		 assertNull(borsa1.getAttrezzo("lancia"));
	 }


}
