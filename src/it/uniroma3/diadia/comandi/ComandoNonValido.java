package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

/**
 * Classe che gestisce il comandoNonValido
 *
 * @author docente di POO/ matricole "610199" - "610020"
 * @version versione.B
 */
public class ComandoNonValido implements Comando{

	private IO io;
	
	@Override
	public void setIoConsole(IO io) {
		this.io = io;
	}
	@Override
	public void setParametro(String parametro) {}
	
	@Override
	/**
	 * Metodo che stampa a video "Il comando non è valido" quando l'utente isserice
	 * un comando che non è presente tra quelli elencati
	 *
	 * @param Partita
	 */
	public void esegui(Partita partita) {
		this.io.mostraMessaggio("Il comando inserito non è valido!!\n");
	}

	@Override
	public String getNome() {
		return "";
	}
	
	@Override
	public String getParametro() {
		return "";
	}
}
