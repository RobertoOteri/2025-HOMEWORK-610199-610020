package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Classe che gestisce la borsa del giocateore, gli attrezzi contenuti, il
 * numero di attrezzi presenti in essa e, il peso massimo che può contenere la
 * borsa
 *
 * @author docente di POO/ matricole "610199" - "610020"
 * @version versione.B
 */

public class Borsa {
	public final static int DEFAULT_PESO_MAX_BORSA = 10;
	private Attrezzo[] attrezzi;
	private int numeroAttrezzi;
	private int pesoMax;

	/**
	 * Crea una borsa Imposta in automatico a 10 il peso massimo della borsa
	 */
	public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA);
	}

	/**
	 * Crea una borsa
	 * 
	 * @param peso massimo che può contenere la borsa
	 * 
	 *             imposta a 0 il numero degli attrezzi imposta il peso massimo che
	 *             può contenere la borsa uguale a quello che viene passato come
	 *             parametro imposta a 10 il numero massimo degli attrezzi
	 *             contenibili nella borsa
	 */
	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.attrezzi = new Attrezzo[10]; // speriamo bastino...
		this.numeroAttrezzi = 0;
	}

	/**
	 * Metodo che aggiunge un attrezzo alla borsa
	 *
	 * @param prende come parametro l'attrezzo da aggiungere
	 * @return true se l'attrezzo è stato corretamente aggiunto
	 * 
	 */
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (this.getPeso() + attrezzo.getPeso() > this.getPesoMax())
			return false;
		if (this.numeroAttrezzi == 10)
			return false;
		this.attrezzi[this.numeroAttrezzi] = attrezzo;
		this.numeroAttrezzi++;
		return true;
	}

	/**
	 * @return il peso massimo della borsa
	 * 
	 */
	public int getPesoMax() {
		return pesoMax;
	}

	/**
	 * Metodo che dato il nome di un attrezzo contenuto nella borsa restuisce
	 * l'indirizzo di un attrezzo con lo stesso nome contenuto nella borsa
	 * 
	 * @param La stringa contenente il nome dell'attrezzo richiesto
	 * @return L'attrezzo cercato
	 * 
	 */

	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		Attrezzo a = null;
		for (int i = 0; i < this.numeroAttrezzi; i++)
			if (this.attrezzi[i].getNome().equals(nomeAttrezzo))
				a = attrezzi[i];

		return a;
	}

	/**
	 * @return il peso totale degli attrezzi contenuti nella borsa
	 * 
	 */

	public int getPeso() {
		int peso = 0;
		for (int i = 0; i < this.numeroAttrezzi; i++)
			peso += this.attrezzi[i].getPeso();

		return peso;
	}

	/**
	 * @return true se la borsa è vuota, false altrimenti
	 * 
	 */

	public boolean isEmpty() {
		return this.numeroAttrezzi == 0;
	}

	/**
	 * Metodo che verifica se nella borsa vi è un attrezzo con un dato nome
	 * 
	 * @param Prende come parametro una stringa contenente il nome dell'attrezzo
	 *               cercato
	 * @return true se la borsa contiene almeno un attrezzo con il nome ricevuto in
	 *         parametro, false altrimenti
	 */

	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.getAttrezzo(nomeAttrezzo) != null;
	}

	/**
	 * Metodo che rimuove un oggetto dalla borsa
	 * 
	 * @param Prende come parametro una stringa contenente il nome dell'attrezzo
	 *               cercato
	 * @return l'indirizzo dell'attrezzo rimosso
	 */

	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		Attrezzo a = null;
		boolean trovato = false;
		for (int i = 0; i < this.numeroAttrezzi && trovato == false; i++) {
			if (this.attrezzi[i].getNome().equals(nomeAttrezzo)) {
				a = this.attrezzi[i];
				trovato = true;
				for (int j = i; j < this.numeroAttrezzi - 1; j++) {
					this.attrezzi[j] = this.attrezzi[j + 1];
				}
				this.attrezzi[numeroAttrezzi - 1] = null;
				numeroAttrezzi = numeroAttrezzi - 1;
			}
		}
		return a;
	}

	/**
	 * Restituisce una rappresentazione stringa della borsa, stampadone il peso
	 * totale degli oggetti contenuti, il peso massimo che può contenere la borsa e
	 * gli eventuali attrezzi contenuti
	 * 
	 * @return la rappresentazione stringa
	 */
	public String toString() {
		StringBuilder s = new StringBuilder();
		if (!this.isEmpty()) {
			s.append("Contenuto borsa (" + this.getPeso() + "kg/" + this.getPesoMax() + "kg): ");
			for (int i = 0; i < this.numeroAttrezzi; i++)
				s.append(attrezzi[i].toString() + " ");
		} else
			s.append("Borsa vuota");
		return s.toString();
	}

	/**
	 * Restituisce la descrizione della borsa.
	 * 
	 * @return la descrizione della borsa.
	 */
	public String getDescrizione() {
		return this.toString();
	}
}
