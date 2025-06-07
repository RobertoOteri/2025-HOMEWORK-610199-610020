package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.ConfigurazioniIniziali;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

/**
 * Classe che implementa il comando vai che permette al giocatore di spostarsi all'interno delle
 * stanza del labirinto.
 *
 * @author docente di POO/ matricole "610199" - "610020"
 * @version versione.C
 */
public class ComandoVai extends AbstractComando{
	
	private static final String nome = ConfigurazioniIniziali.getNomeComandoVai();
	
	public ComandoVai() {
		super.setNome(nome);
	}

	@Override
	/**
	 * Metodo che gestisce gli spostamenti del giocatore stampando a video un messaggio di errore
	 * se si cerca di andare in una direzione inesistente, la descrizione della stanza nella quale
	 * ci si è spostati altrimenti
	 *
	 * @param Partita
	 */
	public void esegui(Partita partita) {
		Stanza stanzaCorrente = partita.getLabirinto().getStanzaCorrente();
		Stanza prossimaStanza = null;
		if(super.getParametro()==null) {
			super.getIoConsole().mostraMessaggio("Dove vouoi andare?\n");
			return;
		}
		prossimaStanza = stanzaCorrente.getStanzaAdiacente(super.getParametro());
		if(prossimaStanza==null) {
			super.getIoConsole().mostraMessaggio("Non c'è nessuna stanza in questa direzione!!\n");
			return;
		}
		partita.getLabirinto().setStanzaCorrente(prossimaStanza);
		super.getIoConsole().mostraMessaggio(prossimaStanza.toString());
		partita.getGiocatore().setCfu(partita.getGiocatore().getCfu()-1);
	}
}
