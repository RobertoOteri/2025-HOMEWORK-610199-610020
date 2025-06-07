package it.uniroma3.diadia.ambienti;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

import org.junit.jupiter.api.BeforeEach;

class TestStanze {


	private Stanza s1;
	private Stanza s2;
	private Attrezzo a1;
	private Attrezzo a2;
	@BeforeEach 
	public void setUp() {
		s1 = new Stanza("Studio");
		s2 = new Stanza("Biblioteca");
		a1 = new Attrezzo("spada", 5);
		a2 = new Attrezzo("spada", 5);
		s1.impostaStanzaAdiacente("nord", s2);
		s1.addAttrezzo(a1);
	}
	
	@Test
	public void testStanzaAdiacenteNord() {
		assertEquals(s2, s1.getStanzaAdiacente("nord"));
	}
	@Test
	public void testStanzaAdiacenteSudNull (){
		assertNull(s1.getStanzaAdiacente("sud"));
	}
	@Test
	public void testStanzaAdiacenteNordDiversa () {
		assertFalse(s1==s2.getStanzaAdiacente("sud"));
	}
	@Test
	public void testHasAttrezzoPresente () {
		assertTrue(s1.hasAttrezzo("spada"));
	}
	@Test
	public void testHasAttrezzoNonNull  () {
		assertNotNull(s1.hasAttrezzo("spada"));
	}
	@Test
	public void testHasAttrezzoAssente () {
		assertFalse(s1.hasAttrezzo("chiodo"));
	}
	@Test
	public void testGetAttrezzoEsistente() {
		assertEquals(a1, s1.getAttrezzo("spada"));	
	}
	@Test
	public void testGetAttrezzoInesistente () {
		assertNull(s2.getAttrezzo("spada"));	
	}
	@Test
	public void testGetAttrezzoStessoOggetto () {
		assertTrue(a1==s1.getAttrezzo("spada"));
	}
	@Test
	public void testGetDirezioniContieneNord () {
		assertEquals("nord", s1.getDirezioni().toArray()[0]);
	}
	@Test
	public void testGetDirezioniNonContieneSud1 () {
		assertFalse("sud"==s1.getDirezioni().toArray()[0]);
	}
	@Test
	public void testGetDirezioniNonVuoto2 () {
		assertNotNull(s2.getDirezioni());
	}
	@Test
	public void testRemoveAttrezzoTrue() {
		assertTrue(this.s1.removeAttrezzo(a1));
	}
	
	@Test
	public void testRemoveAttrezzoA2() {
		assertTrue(this.s1.removeAttrezzo(a2));
		
	}

}
