package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;

import it.uniroma3.diadia.Partita;

/**
 * Classe che gestisce il comando fine 
 *
 * @author docente di POO/ matricole "610199" - "610020"
 * @version versione.B
 */

public class ComandoFine implements Comando {

	private IO io;
	
	@Override
	public void setIoConsole(IO io) {
		this.io = io;
	}
	@Override
	public void setParametro(String parametro) {}
	
	@Override
	/**
	 * Metodo che stampa a video il messaggio di ringraziamento 
	 *
	 * @param Partita
	 */
	public void esegui(Partita partita) {
		partita.setFinita();
		this.io.mostraMessaggio("Grazie di aver giocato!");
	}
	
	@Override
	public String getNome() {
		return "fine";
	}
	
	@Override
	public String getParametro() {
		return "";
	}
	
}
