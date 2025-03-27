package it.uniroma3.diadia;

import java.util.Scanner;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il metodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author docente di POO/ matricole "610199" - "610020" (da un'idea di Michael
 *         Kolling and David J. Barnes)
 * 
 * @version versione.A
 */

public class DiaDia {

	static final private String MESSAGGIO_BENVENUTO = ""
			+ "Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n"
			+ "Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"
			+ "I locali sono popolati da strani personaggi, " + "alcuni amici, altri... chissa!\n"
			+ "Ci sono attrezzi che potrebbero servirti nell'impresa:\n"
			+ "puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n"
			+ "o regalarli se pensi che possano ingraziarti qualcuno.\n\n"
			+ "Per conoscere le istruzioni usa il comando 'aiuto'.";

	static final private String[] elencoComandi = { "vai", "aiuto", "fine", "posa", "prendi" };

	private Partita partita;
	private IOConsole IOConsole;

	/**
	 * Crea un nuovo oggetto "DiaDia"
	 * 
	 * @param prende come parametro un oggetto "IOConsole" che gli servirà a gestire
	 *               gli Input e gli Output del programma
	 * 
	 */
	public DiaDia(IOConsole ioconsole) {
		this.IOConsole = ioconsole;
		// Crea una nuova partita
		this.partita = new Partita();
	}

	public static void main(String[] argc) {
		IOConsole ioconsole = new IOConsole();
		DiaDia gioco = new DiaDia(ioconsole);
		gioco.gioca();
	}

	/**
	 * Metodo che fa iniziare il gioco
	 *
	 * Stampa il messaggio di Benvenuto Gestisce le istruzioni dell'utente
	 */

	public void gioca() {
		String istruzione;
		this.IOConsole.mostraMessaggio(MESSAGGIO_BENVENUTO);
		do
			istruzione = this.IOConsole.leggiRiga();
		while (!processaIstruzione(istruzione));
	}

	/**
	 * Processa una istruzione
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false
	 *         altrimenti
	 */
	private boolean processaIstruzione(String istruzione) {
		Comando comandoDaEseguire = new Comando(istruzione);

		if (comandoDaEseguire.getNome().equals("fine")) {
			this.fine();
			return true;
		} else if (comandoDaEseguire.getNome().equals("vai")) {
			this.vai(comandoDaEseguire.getParametro());
		} else if (comandoDaEseguire.getNome().equals("aiuto")) {
			this.aiuto();
		} else if (comandoDaEseguire.getNome().equals("prendi")) {
			this.prendi(comandoDaEseguire.getParametro());
		} else if (comandoDaEseguire.getNome().equals("posa")) {
			this.posa(comandoDaEseguire.getParametro());
		} else {
			this.IOConsole.mostraMessaggio("Comando sconosciuto");
		}
		if (this.partita.vinta()) {
			this.IOConsole.mostraMessaggio("Hai vinto!");
			return true;
		} else {
			return false;
		}
	}

	// implementazioni dei comandi dell'utente:

	/**
	 * Stampa informazioni di aiuto.
	 */
	private void aiuto() {
		String returnString = "";
		for (int i = 0; i < elencoComandi.length; i++) {
			returnString = returnString + elencoComandi[i] + " ";
		}
		this.IOConsole.mostraMessaggio(returnString);
	}

	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra e ne stampa il
	 * nome, altrimenti stampa un messaggio di errore stampa anche il contenuto
	 * della borsa del giocatore
	 */
	private void vai(String direzione) {
		if (direzione == null)
			this.IOConsole.mostraMessaggio("Dove vuoi andare ?");
		Stanza prossimaStanza = null;
		prossimaStanza = this.partita.getLabirinto().getStanzaCorrente().getStanzaAdiacente(direzione);
		if (prossimaStanza == null)
			this.IOConsole.mostraMessaggio("Direzione inesistente");
		else {
			this.partita.getLabirinto().setStanzaCorrente(prossimaStanza);
			int cfu = this.partita.getGiocatore().getCfu();
			this.partita.getGiocatore().setCfu(cfu--);
		}
		this.IOConsole.mostraMessaggio(this.partita.getLabirinto().getStanzaCorrente().getDescrizione());
		this.IOConsole.mostraMessaggio(this.partita.getGiocatore().GetBorsa().getDescrizione());
	}
	// Metodo per prendere un attrezzo da una stanza:

	/**
	 * Stampa informazioni sull'esito dell'azione di prendere un attrezzo da una
	 * stanza.
	 */

	private void prendi(String nomeAttrezzo) {
		if (!this.partita.getLabirinto().getStanzaCorrente().hasAttrezzo(nomeAttrezzo)) {
			String returnString;
			returnString = "Non c'è " + nomeAttrezzo + " nella stanza in cui ti trovi al momento!!";
			this.IOConsole.mostraMessaggio(returnString);
		} else {
			Attrezzo attrezzodaprendere = this.partita.getLabirinto().getStanzaCorrente().getAttrezzo(nomeAttrezzo);
			;
			if (this.partita.getGiocatore().GetBorsa().addAttrezzo(attrezzodaprendere)
					&& this.partita.getLabirinto().getStanzaCorrente().removeAttrezzo(attrezzodaprendere)) {
				this.IOConsole.mostraMessaggio("Attrezzo aggiunto correttamente nella borsa");
			} else {
				this.IOConsole.mostraMessaggio("Non è stato possibile aggiungere l'attrezzo nella borsa!!");
			}
		}
	}

	// Metodo per posare un attrezzo in una stanza:

	/**
	 * Stampa informazioni sull'esito dell'azione di posare un attrezzo in una
	 * stanza.
	 */

	private void posa(String nomeAttrezzo) {
		if (this.partita.getGiocatore().GetBorsa().isEmpty()) {
			this.IOConsole.mostraMessaggio("Non hai oggetti nella borsa!!");
		} else if (this.partita.getLabirinto().getStanzaCorrente().getNumeroAttrezzi() == 10) {
			this.IOConsole.mostraMessaggio("La stanza contiene già troppi oggetti!!");
		} else if (!this.partita.getGiocatore().GetBorsa().hasAttrezzo(nomeAttrezzo)) {
			String returnString;
			returnString = "All'interno della borsa non è stato trovato nessun " + nomeAttrezzo;
			this.IOConsole.mostraMessaggio(returnString);
		} else {
			Attrezzo attrezzodaposare = this.partita.getGiocatore().GetBorsa().getAttrezzo(nomeAttrezzo);
			if (this.partita.getLabirinto().getStanzaCorrente().addAttrezzo(attrezzodaposare)) {
				String returnString;
				returnString = "Hai posato l'attrezzo "
						+ this.partita.getGiocatore().GetBorsa().removeAttrezzo(nomeAttrezzo).getNome()
						+ " all'interno della stanza";
				this.IOConsole.mostraMessaggio(returnString);
			} else {
				this.IOConsole.mostraMessaggio("Non è stato possibile posare l'oggetto!!");
			}
		}
	}

	/**
	 * Comando "Fine".
	 */
	private void fine() {
		this.IOConsole.mostraMessaggio("Grazie di aver giocato!"); // si desidera smettere
	}

}
