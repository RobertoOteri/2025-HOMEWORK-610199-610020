package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class TestLabirintoBuilder {
	private Labirinto.LabirintoBuilder labirintoBuilder;
	private String nomeStanzaIniziale = "Atrio";
	private String nomeStanzaVincente = "Uscita";

	@Before
	public void setUp() throws Exception {
		labirintoBuilder = new Labirinto.LabirintoBuilder();
	}

	@Test
	public void testMonolocale() {
		Labirinto monolocale = labirintoBuilder
				.addStanza(nomeStanzaIniziale)
				.addStanza(nomeStanzaVincente)
				.addStanzaIniziale(this.nomeStanzaIniziale)
				.addStanzaVincente(this.nomeStanzaIniziale)
				.getLabirinto();
		assertEquals(this.nomeStanzaIniziale, monolocale.getStanzaCorrente().getNome());
		assertEquals(this.nomeStanzaIniziale, monolocale.getStanzaVincente().getNome());
	}
	
	@Test
	public void testMonolocaleConAttrezzo() {
		Labirinto monolocale = labirintoBuilder
				.addStanza(nomeStanzaIniziale)
				.addStanza(nomeStanzaVincente)
				.addStanzaIniziale(nomeStanzaIniziale).addAttrezzo("spada",1, this.nomeStanzaIniziale)
				.addStanzaVincente(nomeStanzaIniziale).addAttrezzo("spadina", 3, this.nomeStanzaIniziale)
				.getLabirinto();
		assertEquals(nomeStanzaIniziale,monolocale.getStanzaCorrente().getNome());
		assertEquals(nomeStanzaIniziale,monolocale.getStanzaVincente().getNome());
		assertEquals("spada",monolocale.getStanzaCorrente().getAttrezzo("spada").getNome());
		assertEquals("spadina",monolocale.getStanzaVincente().getAttrezzo("spadina").getNome());
	}
	
	@Test
	public void testMonolocaleConAttrezzoSingoloDuplicato() {
		Labirinto monolocale = labirintoBuilder
				.addStanza(nomeStanzaIniziale)
				.addStanza(nomeStanzaVincente)
				.addStanzaIniziale(nomeStanzaIniziale)
				.addAttrezzo("spada",1, this.nomeStanzaIniziale)
				.addAttrezzo("spada",1, this.nomeStanzaIniziale)
				.getLabirinto();
		int size = monolocale.getStanzaCorrente().getAttrezzi().size();
		assertTrue(size==1);
		assertEquals(Arrays.asList(new Attrezzo("spada",1)),monolocale.getStanzaCorrente().getAttrezziList());
	}
	
	@Test
	public void testBilocale() {
		Labirinto bilocale = labirintoBuilder
				.addStanza(nomeStanzaIniziale)
				.addStanza(nomeStanzaVincente)
				.addStanzaIniziale(nomeStanzaIniziale)
				.addStanzaVincente(nomeStanzaVincente)
				.addAdiacenza(nomeStanzaIniziale, nomeStanzaVincente, "nord")
				.addAdiacenza(nomeStanzaVincente, nomeStanzaIniziale, "sud")
				.getLabirinto();
		assertEquals(bilocale.getStanzaVincente(),bilocale.getStanzaCorrente().getStanzaAdiacente("nord"));
		assertEquals(Collections.singleton("nord"),bilocale.getStanzaCorrente().getDirezioni());
		assertEquals(Collections.singleton("sud"),bilocale.getStanzaVincente().getDirezioni());
	}
	
	@Test
	public void testTrilocale(){
		Labirinto trilocale = labirintoBuilder
				.addStanza(nomeStanzaIniziale)
				.addStanza(nomeStanzaVincente)
				.addStanzaIniziale(nomeStanzaIniziale).addAttrezzo("sedia", 1, this.nomeStanzaIniziale)
				.addStanza("biblioteca")
				.addAdiacenza(nomeStanzaIniziale, "biblioteca", "sud")
				.addAdiacenza("biblioteca", nomeStanzaIniziale, "nord")
				.addAttrezzo("libro antico", 5, "biblioteca")
				.addStanzaVincente(nomeStanzaVincente)
				.addAdiacenza("biblioteca", nomeStanzaVincente, "est")
				.addAdiacenza(nomeStanzaVincente,"biblioteca" , "ovest")
				.getLabirinto();	
		assertEquals(nomeStanzaIniziale, trilocale.getStanzaCorrente().getNome());
		assertEquals(nomeStanzaVincente, trilocale.getStanzaVincente().getNome());
		assertEquals("biblioteca",trilocale.getStanzaCorrente().getStanzaAdiacente("sud").getNome());
	}
	
	@Test
	public void testTrilocaleConStanzaDuplicata() {
				Labirinto trilocale = labirintoBuilder
				.addStanza(nomeStanzaIniziale)
				.addStanzaIniziale(nomeStanzaIniziale)
				.addStanza("stanza generica")
				.addStanza("stanza generica")
				.addAdiacenza(nomeStanzaIniziale, "stanza generica", "nord")
				.getLabirinto();
		assertTrue(labirintoBuilder.getListaStanze().size()<=2);
	}
	
	@Test
	public void testPiuDiQuattroAdiacenti() {
		Labirinto maze = labirintoBuilder
				.addStanza(nomeStanzaIniziale)
				.addStanza(nomeStanzaVincente)
				.addStanzaIniziale(nomeStanzaIniziale)
				.addStanza("stanza 1")
				.addStanza("stanza 2")
				.addStanza("stanza 3")
				.addStanza("stanza 4")
				.addStanza("stanza 5")
				.addAdiacenza(nomeStanzaIniziale, "stanza 1", "nord")
				.addAdiacenza(nomeStanzaIniziale, "stanza 2", "ovest")
				.addAdiacenza(nomeStanzaIniziale, "stanza 3", "sud")
				.addAdiacenza(nomeStanzaIniziale, "stanza 4", "est")
				.addAdiacenza(nomeStanzaIniziale, "stanza 5", "nord-est") // non dovrebbe essere aggiunta
				.getLabirinto();
				Stanza test = new Stanza("stanza 5");
		assertNull(maze.getStanzaCorrente().getStanzaAdiacente("nord-est"));
		assertTrue(maze.getStanzaCorrente().getMapStanzeAdiacenti().size()<=4);
		assertTrue(!maze.getStanzaCorrente().getMapStanzeAdiacenti().containsValue(test));
		Map<String,Stanza> mappa = new HashMap<>();
		mappa.put("nord", new Stanza("stanza 1"));
		mappa.put("ovest", new Stanza("stanza 2"));
		mappa.put("sud", new Stanza("stanza 3"));
		mappa.put("est", new Stanza("stanza 4"));
		assertEquals(mappa,maze.getStanzaCorrente().getMapStanzeAdiacenti());
	}
	
	@Test
	public void testImpostaStanzaInizialeCambiandola() {
		Labirinto maze = labirintoBuilder
				.addStanzaIniziale(this.nomeStanzaIniziale)
				.addStanza("nuova iniziale")
				.addStanzaIniziale("nuova iniziale")
				.getLabirinto();
		assertEquals("nuova iniziale",maze.getStanzaCorrente().getNome());
	}
	
	@Test
	public void testAggiuntaAttrezziAStanze_Iniziale() {
		String nomeAttrezzo = "attrezzo";
		int peso = 1;
		Labirinto maze = this.labirintoBuilder
				.addStanza(nomeStanzaIniziale)
				.addStanza(nomeStanzaVincente)
				.addStanzaIniziale(this.nomeStanzaIniziale)
				.addAttrezzo(nomeAttrezzo, peso, this.nomeStanzaIniziale)
				.getLabirinto();
		Attrezzo attrezzo = new Attrezzo(nomeAttrezzo,peso);
		assertEquals(attrezzo,maze.getStanzaCorrente().getAttrezzo(nomeAttrezzo));
	}
	
	@Test
	public void testAggiuntaAttrezziAStanze_AppenaAggiunte() {
		String nomeAttrezzo = "attrezzo";
		int peso = 1;
		String nomeStanza = "stanza 1";
		labirintoBuilder
				.addStanzaIniziale(nomeStanzaIniziale)
				.addStanza(nomeStanza)
				.addAttrezzo(nomeAttrezzo, peso, nomeStanza)
				.getLabirinto();
		assertTrue(this.labirintoBuilder.getListaStanze().get(nomeStanza).getAttrezziList().contains(new Attrezzo (nomeAttrezzo,peso)));
		assertEquals(new Attrezzo(nomeAttrezzo,peso),this.labirintoBuilder.getListaStanze().get(nomeStanza).getAttrezzo(nomeAttrezzo));
	}
	
	@Test
	public void testAggiuntaAttrezzoAStanze_AppenaAggiunteMultiple() {
		String nomeAttrezzo = "attrezzo";
		int peso = 1;
		String nomeStanza = "stanza 1";
		this.labirintoBuilder
				.addStanzaIniziale(nomeStanzaIniziale)
				.addStanza(nomeStanza)
				.addAttrezzo(nomeAttrezzo, peso, nomeStanza)
				.getLabirinto();
		Attrezzo attrezzo = new Attrezzo(nomeAttrezzo,peso);
		List<Attrezzo> attrezzi =  labirintoBuilder.getListaStanze().get(nomeStanza).getAttrezziList();
		assertEquals(attrezzo,attrezzi.get(attrezzi.indexOf(attrezzo)));
	}
	
	@Test
	public void testAggiuntaAttrezzoAStanze_MoltepliciAttrezziStessaStanza() {
		String nomeAttrezzo1 = "attrezzo 1";
		String nomeAttrezzo2 = "attrezzo 2";
		int peso1 = 1;
		int peso2 = 2;
		String nomeStanza1 = "Stanza 1";
		this.labirintoBuilder
		.addStanza(nomeStanza1)
		.addAttrezzo(nomeAttrezzo1, peso1, nomeStanza1)
		.addAttrezzo(nomeAttrezzo2, peso2, nomeStanza1);
		Map<String, Stanza> listaStanze = labirintoBuilder.getListaStanze();
		assertEquals(new Attrezzo(nomeAttrezzo2,peso2),listaStanze.get(nomeStanza1).getAttrezzo(nomeAttrezzo2));
		assertEquals(new Attrezzo(nomeAttrezzo1,peso1),listaStanze.get(nomeStanza1).getAttrezzo(nomeAttrezzo1));
	}
	
	
	@Test  //verifico che gli attrezzi vengano aggiunti all'ultima stanza aggiunta
	public void testAggiuntaAttrezzoAStanze_AttrezzoAggiuntoAllaSecondaStanza() {
		String nomeAttrezzo1 = "attrezzo 1";
		String nomeAttrezzo2 = "attrezzo 2";
		int peso1 = 1;
		int peso2 = 2;
		String nomeStanza1 = "Stanza 1";
		String nomeStanza2 = "Stanza 2";
		this.labirintoBuilder
		.addStanza(nomeStanza1)
		.addStanza(nomeStanza2)
		.addAttrezzo(nomeAttrezzo1, peso1, nomeStanza2)
		.addAttrezzo(nomeAttrezzo2, peso2, nomeStanza2);
		Map<String, Stanza> listaStanze = labirintoBuilder.getListaStanze();
		assertEquals(new Attrezzo(nomeAttrezzo1,peso1),listaStanze.get(nomeStanza2).getAttrezzo(nomeAttrezzo1));
		assertEquals(new Attrezzo(nomeAttrezzo2,peso2),listaStanze.get(nomeStanza2).getAttrezzo(nomeAttrezzo2));
	}
	
	@Test
	public void testAggiuntaAttrezzoAStanze_PrimoAttrezzoInUnaStanzaSecondoAttrezzoSecondaStanza() {
		String nomeAttrezzo1 = "attrezzo 1";
		String nomeAttrezzo2 = "attrezzo 2";
		int peso1 = 1;
		int peso2 = 2;
		String nomeStanza1 = "Stanza 1";
		String nomeStanza2 = "Stanza 2";
		this.labirintoBuilder
		.addStanza(nomeStanza1)
		.addAttrezzo(nomeAttrezzo1, peso1, nomeStanza1)
		.addStanza(nomeStanza2)
		.addAttrezzo(nomeAttrezzo2, peso2, nomeStanza2);
		Map<String, Stanza> listaStanze = labirintoBuilder.getListaStanze();
		assertEquals(new Attrezzo(nomeAttrezzo1,peso1),listaStanze.get(nomeStanza1).getAttrezzo(nomeAttrezzo1));
		assertEquals(new Attrezzo(nomeAttrezzo2,peso2),listaStanze.get(nomeStanza2).getAttrezzo(nomeAttrezzo2));
	}

	@Test
	public void testLabirintoConStanzaMagica_AggiuntaElementoOltreSogliaMagica() {
		String nomeAttrezzo1 = "attrezzo 1";
		String nomeAttrezzo2 = "attrezzo 2";
		String nomeAttrezzo2Inv = "2 ozzertta";
		int sogliaMagica = 1;
		int peso1 = 1;
		int peso2 = 2;
		int peso2_x2 = peso2*2;
		String nomeStanzaMagica = "Stanza Magica";
		this.labirintoBuilder
		.addStanzaMagicaConSoglia(nomeStanzaMagica, sogliaMagica)
		.addAttrezzo(nomeAttrezzo1, peso1, nomeStanzaMagica)
		.addAttrezzo(nomeAttrezzo2, peso2, nomeStanzaMagica);
		Map<String, Stanza> listaStanze = labirintoBuilder.getListaStanze();
		assertEquals(new Attrezzo(nomeAttrezzo2Inv,peso2_x2), listaStanze.get(nomeStanzaMagica).getAttrezzo(nomeAttrezzo2Inv));
		assertEquals(new Attrezzo(nomeAttrezzo1,peso1), listaStanze.get(nomeStanzaMagica).getAttrezzo(nomeAttrezzo1));
	}
	
	
	@Test
	public void testLabirintoConStanzaBloccata_ConPassepartout() {
		this.labirintoBuilder
		.addStanza(nomeStanzaIniziale)
		.addStanza(nomeStanzaVincente)
		.addStanzaIniziale(nomeStanzaIniziale)
		.addStanzaBloccata("stanza bloccata", "nord", "chiave").addAttrezzo("chiave", 1, "stanza bloccata")
		.addAdiacenza(nomeStanzaIniziale, "stanza bloccata", "nord")
		.addAdiacenza("stanza bloccata", nomeStanzaIniziale, "sud")
		.addStanzaVincente(nomeStanzaVincente)
		.addAdiacenza("stanza bloccata", nomeStanzaVincente, "nord")
		.addAdiacenza(nomeStanzaVincente, "stanza bloccata", "sud");
		Stanza stanzaVincente = new Stanza(nomeStanzaVincente);		//Asserisce che in presenza di passepartout, invocato il metodo getStanzaAdiacente(), la stanza bloccata ritorna la corretta adiacenza
		assertEquals(stanzaVincente, labirintoBuilder.getListaStanze().get("stanza bloccata").getStanzaAdiacente("nord"));	
	}
	
	@Test
	public void testLabirintoConStanzaBloccata_SenzaPassepartout() {
		this.labirintoBuilder
		.addStanza(nomeStanzaIniziale)
		.addStanza(nomeStanzaVincente)
		.addStanzaIniziale(nomeStanzaIniziale)
		.addStanzaBloccata("stanza bloccata", "nord", "chiave")
		.addAdiacenza(nomeStanzaIniziale, "stanza bloccata", "nord")
		.addAdiacenza("stanza bloccata", nomeStanzaIniziale, "sud")
		.addStanzaVincente(nomeStanzaVincente)
		.addAdiacenza("stanza bloccata", nomeStanzaVincente, "nord")
		.addAdiacenza(nomeStanzaVincente, "stanza bloccata", "sud");
		Stanza stanzaBloccata = new StanzaBloccata("stanza bloccata", "nord", "chiave");
		//Asserisce che in caso di mancanza di passepartout, invocato il metodo getStanzaAdiacente(), la stanza bloccata ritorna se stessa
		assertEquals(stanzaBloccata, labirintoBuilder.getListaStanze().get("stanza bloccata").getStanzaAdiacente("nord"));
	}
	
	@Test
	public void testLabirintoCompletoConTutteLeStanze() {
		
		Labirinto labirintoCompleto = this.labirintoBuilder
				.addStanza(nomeStanzaIniziale)
				.addStanza(nomeStanzaVincente)
				.addStanzaIniziale(nomeStanzaIniziale)
				.addStanzaVincente(nomeStanzaVincente)
				.addStanza("corridoio")
				.addAttrezzo("chiave", 1, "corridoio")
				.addAttrezzo("lanterna", 1, "corridoio")
				.addStanzaBloccata("corridoio bloccato","nord","chiave")
				.addStanzaMagicaConSoglia("stanza magica", 1)
				.addStanzaBuia("stanza buia","lanterna")
				.addStanza("Aula 1")
				.addAdiacenza(nomeStanzaIniziale, "corridoio", "nord")
				.addAdiacenza("corridoio", nomeStanzaIniziale, "sud")
				.addAdiacenza("corridoio", "corridoio bloccato", "nord")
				.addAdiacenza("corridoio bloccato", "corridoio", "sud")
				.addAdiacenza("corridoio bloccato", "Aula 1", "nord")
				.addAdiacenza("Aula 1", "corridoio bloccato", "sud")
				.addAdiacenza("Aula 1", nomeStanzaVincente,"nord")
				.addAdiacenza(nomeStanzaVincente, "Aula 1", "sud")
				.addAdiacenza("corridoio", "stanza magica", "est")
				.addAdiacenza("stanza magica", "corridoio", "ovest")
				.addAdiacenza("corridoio", "stanza buia", "ovest")
				.addAdiacenza("stanza buia", "corridoio", "est")
				.getLabirinto();
		assertEquals(nomeStanzaIniziale, labirintoCompleto.getStanzaCorrente().getNome());
		assertEquals(nomeStanzaVincente, labirintoCompleto.getStanzaVincente().getNome());
		Stanza corridoio = labirintoCompleto.getStanzaCorrente().getStanzaAdiacente("nord");
		assertEquals("corridoio",corridoio.getNome());
		assertTrue(corridoio.getDirezioni().containsAll(Arrays.asList("ovest","est","nord","sud")));
		Map<String,Stanza> mapAdiacenti = new HashMap<>();
		mapAdiacenti.put("nord",new Stanza("corridoio bloccato"));
		mapAdiacenti.put("sud",new Stanza(nomeStanzaIniziale));
		mapAdiacenti.put("est",new Stanza("stanza magica"));
		mapAdiacenti.put("ovest",new Stanza("stanza buia"));
		assertEquals(mapAdiacenti,corridoio.getMapStanzeAdiacenti());
		Attrezzo a1 = new Attrezzo("chiave",1);
		Attrezzo a2 = new Attrezzo("lanterna",1);
		assertEquals(Arrays.asList(a1,a2),corridoio.getAttrezziList());
	}
}