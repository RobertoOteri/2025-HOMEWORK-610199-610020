package it.uniroma3.diadia.attrezzi;

import it.uniroma3.diadia.ambienti.Stanza;

/**
 * Una semplice classe che modella un attrezzo. Gli attrezzi possono trovarsi
 * all'interno delle stanze del labirinto. Ogni attrezzo ha un nome ed un peso.
 *
 * @author docente di POO/ matricole "610199" - "610020"
 * @see Stanza
 * @version versione.C
 */
public class Attrezzo implements Comparable<Attrezzo>{

	private String nome;
	private int peso;

	/**
	 * Crea un attrezzo
	 * 
	 * @param nome il nome che identifica l'attrezzo
	 * @param peso il peso dell'attrezzo
	 */
	public Attrezzo(String nome, int peso) {
		this.peso = peso;
		this.nome = nome;
	}

	/**
	 * Restituisce il nome identificatore dell'attrezzo
	 * 
	 * @return il nome identificatore dell'attrezzo
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * Restituisce il peso dell'attrezzo
	 * 
	 * @return il peso dell'attrezzo
	 */
	public int getPeso() {
		return this.peso;
	}

	/**
	 * Restituisce una rappresentazione stringa di questo attrezzo
	 * 
	 * @return la rappresentazione stringa
	 */
	public String toString() {
		return this.getNome() + " (" + this.getPeso() + "kg)";
	}
	
	public void setPeso(int peso) {
		this.peso = peso;
	}
	@Override
	public boolean equals(Object o) {
		Attrezzo that = (Attrezzo) o;
		return that.getNome().equals(this.nome) && that.getPeso() == this.peso;
	}

	@Override
	public int compareTo(Attrezzo o) {
		int ritorno = this.peso - o.getPeso();
		if(ritorno==0) {
			ritorno = this.nome.compareTo(o.getNome());
		}
		return ritorno;
	}
	


}