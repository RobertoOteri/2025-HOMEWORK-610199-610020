package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Giocatore;

/**
 * Questa classe modella una partita del gioco
 *
 * @author docente di POO/ matricole "610199" - "610020"
 * @see Stanza
 * @version versione.C
 */

public class Partita {

	private Labirinto labirinto;
	private boolean finita;
	private Giocatore giocatore;
	

	/**
	 * Crea una nuova partita
	 * 
	 */
	public Partita(Labirinto labirinto) {
		/* inizializza il labirinto */
		this.labirinto = labirinto;
		this.finita = false;
		/* crea il Giocatore */
		this.giocatore = new Giocatore();
	}

	/**
	 * Restituisce il labirinto della partita
	 * 
	 * @return l'indirizzo del labirinto
	 */
	public Labirinto getLabirinto() {
		return this.labirinto;
	}

	/**
	 * Restituisce il Giocatore
	 * 
	 * @return l'indirizzo del giocatore.
	 */
	public Giocatore getGiocatore() {
		return this.giocatore;
	}

	/**
	 * Restituisce vero se e solo se la partita e' stata vinta
	 * 
	 * @return vero se partita vinta
	 */
	public boolean vinta() {
		return this.labirinto.getStanzaCorrente() == this.labirinto.getStanzaVincente();
	}

	/**
	 * Restituisce vero se e solo se la partita e' finita
	 * 
	 * @return vero se partita finita
	 */
	public boolean isFinita() {
		return finita || vinta() || (this.giocatore.getCfu() == 0);
	}

	/**
	 * Imposta la partita come finita
	 *
	 */
	public void setFinita() {
		this.finita = true;
	}
	
	public boolean giocatoreIsVivo() {
		return this.giocatore.getCfu()!=0;
	}
	
	public void setLabirinto(Labirinto labirinto) {
		this.labirinto = labirinto;
	}
}
