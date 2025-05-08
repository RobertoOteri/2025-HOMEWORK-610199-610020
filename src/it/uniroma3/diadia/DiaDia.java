package it.uniroma3.diadia;

import java.util.Scanner;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.FabbricaDiComandiFisarmonica;
import it.uniroma3.diadia.comandi.Comando;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il metodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author docente di POO/ matricole "610199" - "610020" (da un'idea di Michael
 *         Kolling and David J. Barnes)
 * 
 * @version versione.B
 */

public class DiaDia {

	static final public String MESSAGGIO_BENVENUTO = ""
			+ "Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n"
			+ "Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"
			+ "I locali sono popolati da strani personaggi, " + "alcuni amici, altri... chissa!\n"
			+ "Ci sono attrezzi che potrebbero servirti nell'impresa:\n"
			+ "puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n"
			+ "o regalarli se pensi che possano ingraziarti qualcuno.\n\n"
			+ "Per conoscere le istruzioni usa il comando 'aiuto'.";


	private Partita partita;
	private IO io;

	/**
	 * Crea un nuovo oggetto "DiaDia"
	 * 
	 * @param prende come parametro un oggetto "IOConsole" che gli servirà a gestire
	 *               gli Input e gli Output del programma
	 * 
	 */
	public DiaDia(IO io) {
		this.io = io;
		// Crea una nuova partita
		this.partita = new Partita();
	}

	public static void main(String[] argc) {
		IO io = new IOConsole();
		DiaDia gioco = new DiaDia(io);
		gioco.gioca();
	}

	/**
	 * Metodo che fa iniziare il gioco
	 *
	 * Stampa il messaggio di Benvenuto Gestisce le istruzioni dell'utente
	 */

	public void gioca() {
		String istruzione;
		
		this.io.mostraMessaggio(MESSAGGIO_BENVENUTO);
		do
			istruzione = this.io.leggiRiga();
		while (!processaIstruzione(istruzione));
	}

	/**
	 * Processa una istruzione5
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false
	 *         altrimenti
	 */
	private boolean processaIstruzione(String istruzione) {
		Comando comandoDaEseguire;
		FabbricaDiComandiFisarmonica factory = new FabbricaDiComandiFisarmonica();
		
		comandoDaEseguire = factory.costruisciComando(istruzione, this.io);
		comandoDaEseguire.esegui(this.partita);
		if(this.partita.vinta()) {
			io.mostraMessaggio("Hai vinto!!");
		}if(!this.partita.giocatoreIsVivo()) {
			io.mostraMessaggio("Hai esaurito i CFU.....");
		}
		return this.partita.isFinita();
	}

}