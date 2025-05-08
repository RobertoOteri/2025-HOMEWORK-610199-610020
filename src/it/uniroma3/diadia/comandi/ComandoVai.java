package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

/**
 * Classe che implementa il comando vai che permette al giocatore di spostarsi all'interno delle
 * stanza del labirinto.
 *
 * @author docente di POO/ matricole "610199" - "610020"
 * @version versione.B
 */
public class ComandoVai implements Comando {
	
	private IO io;
	private String direzione;
	
	@Override
	public void setIoConsole(IO io) {
		this.io = io;
	}
    @Override
    public void setParametro(String parametro) {
    	this.direzione=parametro;
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
		if(this.direzione==null) {
			this.io.mostraMessaggio("Dove vouoi andare?\n");
			return;
		}
		prossimaStanza = stanzaCorrente.getStanzaAdiacente(this.direzione);
		if(prossimaStanza==null) {
			this.io.mostraMessaggio("Non c'è nessuna stanza in questa direzione!!\n");
			return;
		}
		partita.getLabirinto().setStanzaCorrente(prossimaStanza);
		this.io.mostraMessaggio(prossimaStanza.toString());
		partita.getGiocatore().setCfu(partita.getGiocatore().getCfu()-1);
	}
	@Override
	public String getNome() {
		return "vai";
	}
	@Override
	public String getParametro() {
		return this.direzione;
	}
}
