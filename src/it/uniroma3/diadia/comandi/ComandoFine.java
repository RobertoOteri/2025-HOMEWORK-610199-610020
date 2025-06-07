package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.ConfigurazioniIniziali;
import it.uniroma3.diadia.IO;

import it.uniroma3.diadia.Partita;

/**
 * Classe che gestisce il comando fine 
 *
 * @author docente di POO/ matricole "610199" - "610020"
 * @version versione.C
 */

public class ComandoFine extends AbstractComando{

	
	private static final String nome = ConfigurazioniIniziali.getNomeComandoFine();
	
	public ComandoFine() {
		super.setNome(nome);
	}
	@Override
	/**
	 * Metodo che stampa a video il messaggio di ringraziamento 
	 *
	 * @param Partita
	 */
	public void esegui(Partita partita) {
		partita.setFinita();
		super.getIoConsole().mostraMessaggio("Grazie di aver giocato!");
	}
	
}
