package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Classe Stanza - una stanza in un gioco di ruolo. Una stanza e' un luogo
 * fisico nel gioco. E' collegata ad altre stanze attraverso delle uscite. Ogni
 * uscita e' associata ad una direzione.
 * 
 * @author docente di POO/ matricole "610199" - "610020"
 * @see Attrezzo
 * @version versione.C
 */

public class StanzaProtected {
	static final protected int NUMERO_MASSIMO_DIREZIONI = 4;
	static final protected int NUMERO_MASSIMO_ATTREZZI = 10;

	protected String nome;

	protected Attrezzo[] attrezzi;
	protected  int numeroAttrezzi;

	protected Stanza[] stanzeAdiacenti;
	protected int numeroStanzeAdiacenti;

	protected String[] direzioni;

	/**
	 * Crea una stanza. Non ci sono stanze adiacenti, non ci sono attrezzi.
	 * 
	 * @param nome il nome della stanza
	 */
	public StanzaProtected(String nome) {
		this.nome = nome;
		this.numeroStanzeAdiacenti = 0;
		this.numeroAttrezzi = 0;
		this.direzioni = new String[NUMERO_MASSIMO_DIREZIONI];
		this.stanzeAdiacenti = new Stanza[NUMERO_MASSIMO_DIREZIONI];
		this.attrezzi = new Attrezzo[NUMERO_MASSIMO_ATTREZZI];
	}

	/**
	 * Imposta una stanza adiacente.
	 *
	 * @param direzione direzione in cui sara' posta la stanza adiacente.
	 * @param stanza    stanza adiacente nella direzione indicata dal primo
	 *                  parametro.
	 */
	public void impostaStanzaAdiacente(String direzione, Stanza stanza) {
		boolean aggiornato = false;
		for (int i = 0; i < this.direzioni.length; i++)
			if (direzione.equals(this.direzioni[i])) {
				this.stanzeAdiacenti[i] = stanza;
				aggiornato = true;
			}
		if (!aggiornato)
			if (this.numeroStanzeAdiacenti < NUMERO_MASSIMO_DIREZIONI) {
				this.direzioni[numeroStanzeAdiacenti] = direzione;
				this.stanzeAdiacenti[numeroStanzeAdiacenti] = stanza;
				this.numeroStanzeAdiacenti++;
			}
	}

	/**
	 * Restituisce la stanza adiacente nella direzione specificata
	 * 
	 * @param direzione
	 */
	public Stanza getStanzaAdiacente(String direzione) {
		Stanza stanza = null;
		for (int i = 0; i < this.numeroStanzeAdiacenti; i++)
			if (this.direzioni[i].equals(direzione))
				stanza = this.stanzeAdiacenti[i];
		return stanza;
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
	public Attrezzo[] getAttrezzi() {
		return this.attrezzi;
	}

	/**
	 * Restituisce il numero di attrezzi presenti nella stanza.
	 * 
	 * @return il numero di attrezzi presenti nella stanza.
	 */

	public int getNumeroAttrezzi() {
		return this.numeroAttrezzi;
	}
	
	public void setNumeroAttrezzi(int numeroAttrezzi) {
		this.numeroAttrezzi = numeroAttrezzi;
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
		if (this.numeroAttrezzi < NUMERO_MASSIMO_ATTREZZI) {
			this.attrezzi[numeroAttrezzi] = attrezzo;
			this.numeroAttrezzi++;
			return true;
		} else {
			return false;
		}
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
		for (String direzione : this.direzioni)
			if (direzione != null)
				risultato.append(" " + direzione);
		risultato.append("\nAttrezzi nella stanza: ");
		for (Attrezzo attrezzo : this.attrezzi) {
			if (attrezzo != null) {
				risultato.append(attrezzo.toString() + " ");
			}
		}
		return risultato.toString();
	}

	/**
	 * Controlla se un attrezzo esiste nella stanza (uguaglianza sul nome).
	 * 
	 * @return true se l'attrezzo esiste nella stanza, false altrimenti.
	 */
	public boolean hasAttrezzo(String nomeAttrezzo) {
		boolean trovato;
		trovato = false;
		for (int i = 0; i < this.numeroAttrezzi && trovato == false; i++) {
			if (attrezzi[i].getNome().equals(nomeAttrezzo))
				trovato = true;
		}
		return trovato;
	}

	/**
	 * Restituisce l'attrezzo nomeAttrezzo se presente nella stanza.
	 * 
	 * @param nomeAttrezzo
	 * @return l'attrezzo presente nella stanza. null se l'attrezzo non e' presente.
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		Attrezzo attrezzoCercato;
		attrezzoCercato = null;
		for (int i = 0; i < this.numeroAttrezzi && attrezzoCercato == null; i++) {
			if (attrezzi[i].getNome().equals(nomeAttrezzo))
				attrezzoCercato = attrezzi[i];
		}
		return attrezzoCercato;
	}

	/**
	 * Rimuove un attrezzo dalla stanza (ricerca in base al nome).
	 * 
	 * @param nomeAttrezzo
	 * @return true se l'attrezzo e' stato rimosso, false altrimenti
	 */
	public boolean removeAttrezzo(Attrezzo attrezzo) {
		boolean trovato = false;
		for (int i = 0; i < this.numeroAttrezzi && trovato == false; i++) {
			if (this.attrezzi[i].getNome().equals(attrezzo.getNome())) {
				trovato = true;
				for (int j = i; j < this.numeroAttrezzi - 1; j++) {
					this.attrezzi[j] = this.attrezzi[j + 1];
				}
				this.attrezzi[this.numeroAttrezzi - 1] = null;
				this.numeroAttrezzi--;
			}
		}
		return trovato;
	}

	/**
	 * Restituisce le direzioni percorribili dalla stanza.
	 * 
	 * @return un array di stringhe contenenti le direzioni percorribili dalla
	 *         stanza
	 */

	public String[] getDirezioni() {
		if (this.direzioni == null || this.numeroStanzeAdiacenti == 0) {
			return new String[0];
		}
		String[] direzioni = new String[this.numeroStanzeAdiacenti];
		for (int i = 0; i < this.numeroStanzeAdiacenti; i++) {
			direzioni[i] = this.direzioni[i];
		}
		return direzioni;

	}
	
}
