package it.uniroma3.diadia.giocatore;

/**
 * Classe che gestisce il giocatore della partita, gestisce i Cfu del giocatore,
 * imposta i Cfu iniziali e gestice la borsa del giocatore
 *
 * @author docente di POO/ matricole "610199" - "610020"
 * @version versione.A
 */
public class Giocatore {

	static final private int CFU_INIZIALI = 20;
	private int cfu = CFU_INIZIALI;
	private Borsa borsa;

	/**
	 * Crea un giocatore
	 * 
	 * Crea una Borsa
	 */
	public Giocatore() {
		this.borsa = new Borsa();
	}

	/**
	 * Restituisce i cfu posseduti dal giocatore.
	 * 
	 * @return il numero intero dei cfu posseduti dal giocatore.
	 */
	public int getCfu() {
		return this.cfu;
	}

	/**
	 * Imposta il numero di Cfu posseduti dal giocatore.
	 * 
	 * @param il numero interno dei nuovi Cfu posseduti dal giocatore.
	 */

	public void setCfu(int cfu) {
		this.cfu = cfu;
	}

	/**
	 * Restituisce la borsa del giocatore.
	 * 
	 * @return l'indirizzo della borsa.
	 */
	public Borsa GetBorsa() {
		return this.borsa;
	}

}
