package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.IO;

/**
 * Classe che consente all'utente di capire quali comandi può eseguire.
 *
 * @author docente di POO/ matricole "610199" - "610020"
 * @version versione.B
 */
public class ComandoAiuto implements Comando{
	
	private IO io;

	static final private String[] elencoComandi = { "vai", "aiuto", "fine", "posa", "prendi", "guarda" };
	
	
	@Override
	public void setIoConsole(IO io) {
		this.io = io;
	}
	@Override
	public void setParametro(String parametro) {}
	
	@Override
	/**
	 * Metodo che stampa a video tutti i comandi che può eseguire l'utente
	 *
	 * @param Partita
	 */
	public void esegui(Partita partita) {
		String returnString  = "";
		for(int i=0; i<this.elencoComandi.length; i++) {
			returnString = returnString + elencoComandi[i] +", ";
		}
		this.io.mostraMessaggio(returnString);
	}
	
	@Override
	public String getNome() {
		return "aiuto";
	}
	
	@Override
	public String getParametro() {
		return "";
	}
	
}
