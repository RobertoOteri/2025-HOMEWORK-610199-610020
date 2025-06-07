package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.ConfigurazioniIniziali;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

/**
 * Classe che gestisce il comandoNonValido
 *
 * @author docente di POO/ matricole "610199" - "610020"
 * @version versione.C
 */
public class ComandoNonValido extends AbstractComando{
	
	private static final String nome = ConfigurazioniIniziali.getNomeComandoNonValido();
	
	public ComandoNonValido() {
		super.setNome(nome);
	}
	@Override
	/**
	 * Metodo che stampa a video "Il comando non è valido" quando l'utente isserice
	 * un comando che non è presente tra quelli elencati
	 *
	 * @param Partita
	 */
	public void esegui(Partita partita) {
		super.getIoConsole().mostraMessaggio("Il comando inserito non è valido!!\n");
	}
}
