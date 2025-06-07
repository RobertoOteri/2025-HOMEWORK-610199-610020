package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

import java.util.ArrayList;
import java.util.List;

import it.uniroma3.diadia.ConfigurazioniIniziali;
import it.uniroma3.diadia.IO;

/**
 * Classe che consente all'utente di capire quali comandi può eseguire.
 *
 * @author docente di POO/ matricole "610199" - "610020"
 * @version versione.C
 */
public class ComandoAiuto extends AbstractComando{
	
	private static final String nome = ConfigurazioniIniziali.getNomeComandoAiuto();

	static  private List<String> elencoComandi;
	
	public ComandoAiuto() {
		
		super.setNome(nome);
		this.elencoComandi = new ArrayList<String>();
		for(Comandi c : Comandi.values()) {
			this.elencoComandi.add(c.name());
		}
	}
	
	@Override
	/**
	 * Metodo che stampa a video tutti i comandi che può eseguire l'utente
	 *
	 * @param Partita
	 */
	public void esegui(Partita partita) {
		String returnString  = "";
		for(int i=0; i<this.elencoComandi.size(); i++) {
			returnString = returnString + this.elencoComandi.get(i) +", ";
		}
		super.getIoConsole().mostraMessaggio(returnString);
	}
	
}
