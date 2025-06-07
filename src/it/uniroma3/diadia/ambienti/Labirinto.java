package it.uniroma3.diadia.ambienti;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import it.uniroma3.diadia.FormatoFileNonValidoException;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.Cane;
import it.uniroma3.diadia.personaggi.Mago;
import it.uniroma3.diadia.personaggi.Strega;

/**
 * Classe che gestisce il labirinto della partita, controlla la stanza in cui si
 * trova il giocatore (stanza Corrente) e imposta la stanza Vincente
 *
 * @author docente di POO/ matricole "610199" - "610020"
 * @version versione.C 
 */

public class Labirinto {

	public  LabirintoBuilder newBuilder() {
		return new LabirintoBuilder(this);
	}
	
	private Stanza stanzaCorrente;
	private Stanza stanzaVincente;

	
	public Labirinto() {
	}
	public Labirinto(String nomeFile) throws FormatoFileNonValidoException {
		try {
			CaricatoreLabirinto c = new CaricatoreLabirinto(nomeFile, this);
			c.carica();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Restituisce la stanza in cui si trova il giocatore in questo momento.
	 * 
	 * @return l'indirizzo della stanza corrente.
	 */
	public Stanza getStanzaCorrente() {
		return this.stanzaCorrente;
	}

	/**
	 * Restituisce la stanza Vincente.
	 * 
	 * @return l'indirizzo della stanza vincente.
	 */
	public Stanza getStanzaVincente() {
		return stanzaVincente;
	}

	/**
	 * Imposta la stanza in cui si trova il giocatore in questo momento.
	 * 
	 * @param l'indirizzo della nuova stanza in cui si trova il giocatore.
	 */
	public void setStanzaCorrente(Stanza stanzaCorrente) {
		this.stanzaCorrente = stanzaCorrente;
	}
	
	public void setStanzaVincente(Stanza stanzaVincente) {
		this.stanzaVincente = stanzaVincente;
	}
	
	public static class LabirintoBuilder {

		private Labirinto labirinto;
		private Map<String, Stanza> stanze;
		
		public LabirintoBuilder() {
			this.stanze = new HashMap<String, Stanza>();
			this.labirinto = new Labirinto();
		}
		
		public LabirintoBuilder(Labirinto labirinto) {
			this.stanze = new HashMap<String, Stanza>();
			this.labirinto = labirinto;
		}
		
		public LabirintoBuilder addCane (String nomeRegaloDelCane, int pesoRegalo, String ciboPreferito, String nomeStanza) {
			Attrezzo regaloDelCane = new Attrezzo(nomeRegaloDelCane, pesoRegalo);
			Cane cane = new Cane(regaloDelCane, ciboPreferito);
			this.getStanza(nomeStanza.trim()).addPersonaggio(cane);
			return this;
		}
		
		public LabirintoBuilder addMago (String nomeAttrezzo, int pesoAttrezzo, String nomeStanza) {
			Attrezzo attrezzoDelMago = new Attrezzo(nomeAttrezzo, pesoAttrezzo);
			Mago mago = new Mago(attrezzoDelMago);
			this.getStanza(nomeStanza.trim()).addPersonaggio(mago);
			return this;
		}
		
		public LabirintoBuilder addStrega(String nomeStanza) {
			Strega strega = new Strega();
			this.getStanza(nomeStanza.trim()).addPersonaggio(strega);
			return this;
		}
		
		public LabirintoBuilder addStanza (String nomeStanza) {
			Stanza stanza = new Stanza(nomeStanza);
			stanze.put(nomeStanza, stanza);
			return this;
		}
		
		public LabirintoBuilder addStanzaVincente(String nomeStanzaVincente) {
			Stanza stanzaVincente = this.stanze.get(nomeStanzaVincente);
			this.labirinto.setStanzaVincente(stanzaVincente);
			return this;	
		}
		
		public LabirintoBuilder addStanzaIniziale(String nomeStanzaIniziale) {
			Stanza stanzaIniziale = this.stanze.get(nomeStanzaIniziale);
			this.labirinto.setStanzaCorrente(stanzaIniziale);
			return this;
		}
		
		public LabirintoBuilder addStanzaBloccata(String nomeStanzaBloccata, String direzioneBloccata, String nomeAttrezzoPerSbloccare) {
			StanzaBloccata stanzaBloccata = new StanzaBloccata(nomeStanzaBloccata, direzioneBloccata, nomeAttrezzoPerSbloccare);
			this.stanze.put(nomeStanzaBloccata, stanzaBloccata);
			return this;
		}
		
		public LabirintoBuilder addstanzaMagica(String nomeStanzaMagica) {
			StanzaMagica stanzaMagica = new StanzaMagica(nomeStanzaMagica);
			this.stanze.put(nomeStanzaMagica, stanzaMagica);
			return this;
		}
		
		public LabirintoBuilder addStanzaMagicaConSoglia(String nomeStanzaMagica, int sogliaMagica) {
			StanzaMagica stanzaMagica = new StanzaMagica(nomeStanzaMagica, sogliaMagica);
			this.stanze.put(nomeStanzaMagica, stanzaMagica);
			return this;
		}
		
		public LabirintoBuilder addStanzaBuia(String nomeStanzaBuia, String nomeAttrezzoCheIllumina) {
			StanzaBuia stanzaBuia = new StanzaBuia(nomeStanzaBuia, nomeAttrezzoCheIllumina);
			this.stanze.put(nomeStanzaBuia, stanzaBuia);
			return this;
		}
		
		public LabirintoBuilder addAttrezzo(String nomeAttrezzo, int peso, String nomeStanza) {
			Attrezzo attrezzo = new Attrezzo(nomeAttrezzo, peso);
			Stanza stanza = this.getStanza(nomeStanza.trim());
			stanza.addAttrezzo(attrezzo);
			return this;
		}
		
		public LabirintoBuilder addAdiacenza(String nomeStanzaPartenza, String nomeStanzaAdiacente, String direzione) {
			Stanza stanzaPartenza = this.stanze.get(nomeStanzaPartenza.trim());
			Stanza stanzaAdiacente = this.stanze.get(nomeStanzaAdiacente.trim());
			stanzaPartenza.impostaStanzaAdiacente(direzione, stanzaAdiacente);
			return this;
		}
		
		public Stanza getStanza (String nomeStanza) {
			return this.stanze.get(nomeStanza);
		}
		public Labirinto getLabirinto() {
			return this.labirinto;
		}
		
		public Map<String, Stanza> getListaStanze(){
			return this.stanze;
		}
	}
}