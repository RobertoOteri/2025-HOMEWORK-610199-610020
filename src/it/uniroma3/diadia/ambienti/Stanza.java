package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.ConfigurazioniIniziali;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.HashMap;

/**
 * Classe Stanza - una stanza in un gioco di ruolo. Una stanza e' un luogo
 * fisico nel gioco. E' collegata ad altre stanze attraverso delle uscite. Ogni
 * uscita e' associata ad una direzione.
 * 
 * @author docente di POO/ matricole "610199" - "610020"
 * @see Attrezzo
 * @version versione.C
 */

public class Stanza {

	static final private int NUMERO_MASSIMO_DIREZIONI = ConfigurazioniIniziali.getNumeroMassimoDirezioni();
	static final private int NUMERO_MASSIMO_ATTREZZI = ConfigurazioniIniziali.getNumeroMassimoAttrezzi();
	static final private int NUMERO_MASSIMO_PERSONAGGI = ConfigurazioniIniziali.getNumeroMassimoPersonaggi();

	private String nome;

	private Map<String, Attrezzo> attrezzi;

	private Map<String, Stanza> stanzeAdiacenti;
	
	private AbstractPersonaggio personaggio;
	
	/**
	 * Crea una stanza. Non ci sono stanze adiacenti, non ci sono attrezzi.
	 * 
	 * @param nome il nome della stanza
	 */
	public Stanza(String nome) {
		this.nome = nome;
		this.stanzeAdiacenti = new HashMap<String, Stanza>();
		this.attrezzi = new HashMap<>();
	}

	
	public boolean hasPersonaggio() {
		return this.personaggio!=null;
	}
	public AbstractPersonaggio getPersonaggio() {
		return this.personaggio;
	}
	
	public boolean addPersonaggio (AbstractPersonaggio personaggio) {
		boolean vero = false;
		if(this.personaggio==null) {
			this.personaggio=personaggio;
			vero = true;
		}
		return vero;
	}
	/**
	 * Imposta una stanza adiacente.
	 *
	 * @param direzione direzione in cui sara' posta la stanza adiacente.
	 * @param stanza    stanza adiacente nella direzione indicata dal primo
	 *                  parametro.
	 */
	public void impostaStanzaAdiacente(String direzione, Stanza stanza) {
		    if (this.stanzeAdiacenti.size() < 4) {
		        this.stanzeAdiacenti.put(direzione, stanza);
		    }
	}

	/**
	 * Restituisce la stanza adiacente nella direzione specificata
	 * 
	 * @param direzione
	 */
	public Stanza getStanzaAdiacente(String direzione) {
		return this.stanzeAdiacenti.get(direzione);
	}

	/**
	 * Restituisce la nome della stanza.
	 * 
	 * @return il nome della stanza
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * Restituisce la descrizione della stanza.
	 * 
	 * @return la descrizione della stanza
	 */
	public String getDescrizione() {
		return this.toString();
	}

	/**
	 * Restituisce la collezione di attrezzi presenti nella stanza.
	 * 
	 * @return la collezione di attrezzi nella stanza.
	 */
	public Map<String, Attrezzo> getAttrezzi() {
		return this.attrezzi;
	}

	
	public List<Attrezzo> getAttrezziList(){
		List<Attrezzo> lista = new ArrayList<Attrezzo>(this.attrezzi.values());
		return lista;
	}
	/**
	 * Restituisce il numero di attrezzi presenti nella stanza.
	 * 
	 * @return il numero di attrezzi presenti nella stanza.
	 */

	public int getNumeroAttrezzi() {
		return this.attrezzi.size();
	}

	public int getNumeroMassimoAttrezzi() {
		return this.NUMERO_MASSIMO_ATTREZZI;
	}

	/**
	 * Mette un attrezzo nella stanza.
	 * 
	 * @param attrezzo l'attrezzo da mettere nella stanza.
	 * @return true se riesce ad aggiungere l'attrezzo, false atrimenti.
	 */
	public boolean addAttrezzo(Attrezzo attrezzo) {
		boolean vero = false;
		if(this.attrezzi.size()<this.NUMERO_MASSIMO_ATTREZZI) {
			vero = true;
			this.attrezzi.put(attrezzo.getNome(), attrezzo);
		}
		return vero;
	}

	/**
	 * Restituisce una rappresentazione stringa di questa stanza, stampadone la
	 * descrizione, le uscite e gli eventuali attrezzi contenuti
	 * 
	 * @return la rappresentazione stringa
	 */
	public String toString() {
		StringBuilder risultato = new StringBuilder();
		risultato.append(this.nome);
		risultato.append("\nUscite: ");
		risultato.append(this.stanzeAdiacenti.keySet().toString());
		risultato.append("\nAttrezzi nella stanza: ");
		risultato.append(this.attrezzi.values().toString());
		if(this.personaggio!=null) {
			risultato.append(this.personaggio.getDescrizione());
		}
		return risultato.toString();
	}

	/**
	 * Controlla se un attrezzo esiste nella stanza (uguaglianza sul nome).
	 * 
	 * @return true se l'attrezzo esiste nella stanza, false altrimenti.
	 */
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.containsKey(nomeAttrezzo);	
	}

	/**
	 * Restituisce l'attrezzo nomeAttrezzo se presente nella stanza.
	 * 
	 * @param nomeAttrezzo
	 * @return l'attrezzo presente nella stanza. null se l'attrezzo non e' presente.
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.get(nomeAttrezzo);
	}

	/**
	 * Rimuove un attrezzo dalla stanza (ricerca in base al nome).
	 * 
	 * @param Attrezzo da rimuovere
	 * @return true se l'attrezzo e' stato rimosso, false altrimenti
	 */
	public boolean removeAttrezzo(Attrezzo attrezzo) {
		return this.attrezzi.remove(attrezzo.getNome()).equals(attrezzo);
	}

	/**
	 * Restituisce le direzioni percorribili dalla stanza.
	 * 
	 * @return un array di stringhe contenenti le direzioni percorribili dalla
	 *         stanza
	 */

	public Set<String> getDirezioni() {
		return this.stanzeAdiacenti.keySet();		
	}
	
	public Map<String, Stanza> getMapStanzeAdiacenti(){
		return this.stanzeAdiacenti;
	}
	
	@Override
	public boolean equals(Object o) {
		Stanza that = (Stanza) o;
		return that.getNome().equals(this.nome);
	}
	
	@Override
	public int hashCode() {
	    return this.nome.hashCode();
	}
}