package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.ConfigurazioniIniziali;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.attrezzi.ComparatorePerNome;
import it.uniroma3.diadia.attrezzi.ComparatorePerPeso;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Classe che gestisce la borsa del giocateore, gli attrezzi contenuti, il
 * numero di attrezzi presenti in essa e, il peso massimo che può contenere la
 * borsa
 *
 * @author docente di POO/ matricole "610199" - "610020"
 * @version versione.C
 */

public class Borsa{
	public final static int DEFAULT_PESO_MAX_BORSA = ConfigurazioniIniziali.getPesoMax();
	private Map<String, Attrezzo> attrezzi;
	private int pesoAttuale;
	private int pesoMax;

	/**
	 * Crea una borsa Imposta in automatico a 10 il peso massimo della borsa
	 */
	public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA);
		this.pesoAttuale=0;
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
		this.attrezzi = new HashMap<>();
		this.pesoAttuale=0;
	}

	/**
	 * Metodo che aggiunge un attrezzo alla borsa
	 *
	 * @param prende come parametro l'attrezzo da aggiungere
	 * @return true se l'attrezzo è stato corretamente aggiunto
	 * 
	 */
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (this.getPeso() + attrezzo.getPeso() > this.getPesoMax()) {
			return false;	
		}
		this.pesoAttuale += attrezzo.getPeso();
		this.attrezzi.put(attrezzo.getNome(), attrezzo);
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
	
	return this.attrezzi.get(nomeAttrezzo);
	}

	/**
	 * @return il peso totale degli attrezzi contenuti nella borsa
	 * 
	 */

	public int getPeso() {
		return this.pesoAttuale;
	}

	/**
	 * @return true se la borsa è vuota, false altrimenti
	 * 
	 */

	public boolean isEmpty() {
		return this.attrezzi.size()==0;
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
	 *        cercato
	 * @return l'indirizzo dell'attrezzo rimosso
	 */

	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		if(this.attrezzi.containsKey(nomeAttrezzo)) {
			this.pesoAttuale = pesoAttuale - this.attrezzi.get(nomeAttrezzo).getPeso();
		}
		return this.attrezzi.remove(nomeAttrezzo);
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
			s.append(this.getContenutoOrdinatoPerPeso().toString());
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
	
	public List<Attrezzo> getContenutoOrdinatoPerPeso(){
		List<Attrezzo> attrezzi = new ArrayList<>(this.attrezzi.values());
		ComparatorePerPeso comparatore = new ComparatorePerPeso();
		Collections.sort(attrezzi, comparatore);
		return attrezzi;
	}
	
	public SortedSet<Attrezzo> getSortedSetOrdinatoPerPeso(){
		ComparatorePerPeso comparatore = new ComparatorePerPeso();
		SortedSet<Attrezzo> attrezziOrdinati = new TreeSet<Attrezzo>(comparatore);
		attrezziOrdinati.addAll(this.attrezzi.values());
		return attrezziOrdinati;
	}
	
	public SortedSet<Attrezzo> getContenutoOrdinatoPerNome(){
		ComparatorePerNome comparatore = new ComparatorePerNome();
		SortedSet<Attrezzo> attrezzi = new TreeSet<Attrezzo>(comparatore);
		attrezzi.addAll(this.attrezzi.values());
		return attrezzi;	
	}
	
	public Map<Integer, Set<Attrezzo>> getContenutoRaggruppatoPerPeso(){
		Map<Integer, Set<Attrezzo>> mappa = new HashMap<>();
		for(Attrezzo attrezzo : this.attrezzi.values()) {
			Set<Attrezzo> attrezziPeso = mappa.get(attrezzo.getPeso());
			if(attrezziPeso == null) {
				attrezziPeso = new HashSet<>();
				mappa.put(attrezzo.getPeso(), attrezziPeso);
			}
			attrezziPeso.add(attrezzo);
		}
		return mappa;
	}

}
