package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

/**
 * Classe che gestisce il comando guarda
 *
 * @author docente di POO/ matricole "610199" - "610020"
 * @version versione.B
 */
public class ComandoGuarda implements Comando {

	private IO io;
	
	@Override
	public void setIoConsole(IO io) {
		this.io = io;
	}
	@Override
	public void setParametro(String parametro) {}
	
	@Override
	/**
	 * Metodo che stampa a video la descrizione della stanza e la descrizione del
	 * giocatore
	 *
	 * @param Partita
	 */
	public void esegui(Partita partita) {
		this.io.mostraMessaggio(partita.getGiocatore().getDescrizione() + "\n\n" 
	                      + partita.getLabirinto().getStanzaCorrente().getDescrizione());
	}
	
	@Override
	public String getNome() {
		return "guarda";
	}
	
	@Override
	public String getParametro() {
		return "";
	}

}
