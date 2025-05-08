package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Classe che gestisce il labirinto della partita, controlla la stanza in cui si
 * trova il giocatore (stanza Corrente) e imposta la stanza Vincente
 *
 * @author docente di POO/ matricole "610199" - "610020"
 * @version versione.B
 */

public class Labirinto {

	private Stanza stanzaCorrente;
	private Stanza stanzaVincente;

	/**
	 * Crea un Labirinto
	 */
	public Labirinto() {
		/* crea gli attrezzi */
		Attrezzo lanterna = new Attrezzo("lanterna", 3);
		Attrezzo osso = new Attrezzo("osso", 1);

		/* crea stanze del labirinto */
		Stanza atrio = new Stanza("Atrio");
		Stanza aulaN11 = new Stanza("Aula N11");
		Stanza aulaN10 = new Stanza("Aula N10");
		Stanza laboratorio = new Stanza("Laboratorio Campus");
		Stanza biblioteca = new Stanza("Biblioteca");

		/* collega le stanze */
		atrio.impostaStanzaAdiacente("nord", biblioteca);
		atrio.impostaStanzaAdiacente("est", aulaN11);
		atrio.impostaStanzaAdiacente("sud", aulaN10);
		atrio.impostaStanzaAdiacente("ovest", laboratorio);
		aulaN11.impostaStanzaAdiacente("est", laboratorio);
		aulaN11.impostaStanzaAdiacente("ovest", atrio);
		aulaN10.impostaStanzaAdiacente("nord", atrio);
		aulaN10.impostaStanzaAdiacente("est", aulaN11);
		aulaN10.impostaStanzaAdiacente("ovest", laboratorio);
		laboratorio.impostaStanzaAdiacente("est", atrio);
		laboratorio.impostaStanzaAdiacente("ovest", aulaN11);
		biblioteca.impostaStanzaAdiacente("sud", atrio);

		/* pone gli attrezzi nelle stanze */
		aulaN10.addAttrezzo(lanterna);
		atrio.addAttrezzo(osso);

		// il gioco comincia nell'atrio
		this.stanzaCorrente = atrio;
		// il gioco finisce con la vittoria se si arriva in Biblioteca
		this.stanzaVincente = biblioteca;
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
}