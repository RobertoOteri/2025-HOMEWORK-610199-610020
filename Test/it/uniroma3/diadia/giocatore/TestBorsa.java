package it.uniroma3.diadia.giocatore;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;

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
	 
	  @Test
	    public void testGetContenutoOrdinatoPerPeso() {
	        Borsa borsa = new Borsa(20);
	        Attrezzo a1 = new Attrezzo("pala", 5);
	        Attrezzo a2 = new Attrezzo("martello", 3);
	        Attrezzo a3 = new Attrezzo("cacciavite", 2);
	        borsa.addAttrezzo(a1);
	        borsa.addAttrezzo(a2);
	        borsa.addAttrezzo(a3);

	        List<Attrezzo> ordinati = borsa.getContenutoOrdinatoPerPeso();

	        assertEquals(3, ordinati.size());
	        assertEquals(a3, ordinati.get(0)); 
	        assertEquals(a2, ordinati.get(1)); 
	        assertEquals(a1, ordinati.get(2)); 
	    }

	    @Test
	    public void testGetSortedSetOrdinatoPerPeso() {
	        Borsa borsa = new Borsa(20);
	        Attrezzo a1 = new Attrezzo("pala", 5);
	        Attrezzo a2 = new Attrezzo("martello", 3);
	        Attrezzo a3 = new Attrezzo("cacciavite", 2);
	        borsa.addAttrezzo(a1);
	        borsa.addAttrezzo(a2);
	        borsa.addAttrezzo(a3);

	        SortedSet<Attrezzo> ordinati = borsa.getSortedSetOrdinatoPerPeso();

	        assertEquals(3, ordinati.size());
	        Attrezzo[] array = ordinati.toArray(new Attrezzo[0]);
	        assertEquals(a3, array[0]);
	        assertEquals(a2, array[1]);
	        assertEquals(a1, array[2]);
	    }

	    @Test
	    public void testGetContenutoOrdinatoPerNome() {
	        Borsa borsa = new Borsa(20);
	        Attrezzo a1 = new Attrezzo("pala", 5);
	        Attrezzo a2 = new Attrezzo("martello", 3);
	        Attrezzo a3 = new Attrezzo("cacciavite", 2);
	        borsa.addAttrezzo(a1);
	        borsa.addAttrezzo(a2);
	        borsa.addAttrezzo(a3);

	        SortedSet<Attrezzo> ordinati = borsa.getContenutoOrdinatoPerNome();

	        assertEquals(3, ordinati.size());
	        Attrezzo[] array = ordinati.toArray(new Attrezzo[0]);
	        assertEquals(a3, array[0]);
	        assertEquals(a2, array[1]);
	        assertEquals(a1, array[2]);
	    }

	    @Test
	    public void testGetContenutoRaggruppatoPerPeso() {
	        Borsa borsa = new Borsa(20);
	        Attrezzo a1 = new Attrezzo("pala", 5);
	        Attrezzo a2 = new Attrezzo("martello", 3);
	        Attrezzo a3 = new Attrezzo("cacciavite", 3);
	        borsa.addAttrezzo(a1);
	        borsa.addAttrezzo(a2);
	        borsa.addAttrezzo(a3);

	        Map<Integer, Set<Attrezzo>> raggruppati = borsa.getContenutoRaggruppatoPerPeso();

	        assertEquals(2, raggruppati.size());
	        assertTrue(raggruppati.containsKey(5));
	        assertTrue(raggruppati.containsKey(3));

	        Set<Attrezzo> peso5 = raggruppati.get(5);
	        Set<Attrezzo> peso3 = raggruppati.get(3);

	        assertEquals(1, peso5.size());
	        assertTrue(peso5.contains(a1));

	        assertEquals(2, peso3.size());
	        assertTrue(peso3.contains(a2));
	        assertTrue(peso3.contains(a3));
	    }
	
}
