package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.ConfigurazioniIniziali;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

/**
 * Classe che gestisce il comando guarda
 *
 * @author docente di POO/ matricole "610199" - "610020"
 * @version versione.C
 */
public class ComandoGuarda extends AbstractComando{

	private static final String nome = ConfigurazioniIniziali.getNomeComandoGuarda();
	
	
	public ComandoGuarda() {
		super.setNome(nome);
	}
	@Override
	/**
	 * Metodo che stampa a video la descrizione della stanza e la descrizione del
	 * giocatore
	 *
	 * @param Partita
	 */
	public void esegui(Partita partita) {
		super.getIoConsole().mostraMessaggio(partita.getGiocatore().getDescrizione() + "\n\n" 
	                      + partita.getLabirinto().getStanzaCorrente().getDescrizione());
	}


}
