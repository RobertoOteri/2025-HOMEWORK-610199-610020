package it.uniroma3.diadia;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.FabbricaDiComandiRiflessiva;
import it.uniroma3.diadia.comandi.FabbricaDiComandiFisarmonica;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.AbstractComando;
/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il metodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author docente di POO/ matricole "610199" - "610020" (da un'idea di Michael
 *         Kolling and David J. Barnes)
 * 
 * @version versione.C
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

	
	private  static final String FILE_LIVELLI = "resources/labirinto";

	private static final int NUMERO_LIVELLI = ConfigurazioniIniziali.getNumeroLivelli();
	private static int livello=1;
	private Partita partita;
	private IO io;

	/**
	 * Crea un nuovo oggetto "DiaDia"
	 * 
	 * @param prende come parametro un oggetto "IOConsole" che gli servirà a gestire
	 *               gli Input e gli Output del programma
	 * 
	 */
	public DiaDia(IO io, Labirinto labirinto, int livello) {
		this.io = io;
		// Crea una nuova partita
		this.partita = new Partita(labirinto);
		this.livello = livello;
	}

	public static void main(String[] argc) throws FormatoFileNonValidoException {
		try(Scanner scanner = new Scanner(System.in)) {
			IO io = new IOConsole(scanner);
			boolean vero = true;
		
			while(livello<=NUMERO_LIVELLI && vero) {
				Labirinto labirinto = new Labirinto(FILE_LIVELLI + livello + ".txt");
				DiaDia gioco = new DiaDia(io, labirinto, livello);
				gioco.gioca();
				if(gioco.partita.vinta()) {
					livello++;
				}else
				{
					vero = false;
				}
			}
		}
	}

	/**
	 * Metodo che fa iniziare il gioco
	 *
	 * Stampa il messaggio di Benvenuto Gestisce le istruzioni dell'utente
	 */

	public void gioca(){
		String istruzione;
		if(this.livello==1) {
			this.io.mostraMessaggio(MESSAGGIO_BENVENUTO);
		}
		this.io.mostraMessaggio("\n\nLIVELLO: " + this.livello +"\n");
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
		AbstractComando comandoDaEseguire;
		FabbricaDiComandiRiflessiva factory =  new FabbricaDiComandiRiflessiva();		
		comandoDaEseguire = factory.costruisciComando(istruzione, this.io);
		comandoDaEseguire.esegui(this.partita);
		if(this.partita.vinta() && this.livello !=3) {
			this.io.mostraMessaggio("Hai superato il livello!!\n\n");
		}else if(this.partita.vinta() && this.livello == 3) {
			this.io.mostraMessaggio("Hai Vinto!!");
		}
		if(!this.partita.giocatoreIsVivo()) {
			this.io.mostraMessaggio("Hai esaurito i CFU.....");
		}
		return this.partita.isFinita();
	}

}