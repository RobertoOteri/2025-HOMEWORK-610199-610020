package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.ConfigurazioniIniziali;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Classe che gestisce il comando posa, che permette di posare un oggetto contenuto nella borsa
 * del giocatore all'interno di una stanza 
 *
 * @author docente di POO/ matricole "610199" - "610020"
 * @version versione.C
 */
public class ComandoPosa extends AbstractComando{

	private static final String nome = ConfigurazioniIniziali.getNomeComandoPosa();
	
	
	public ComandoPosa() {
		super.setNome(nome);
	}
	@Override
	/**
	 * se si può posare l'oggetto all'interno della stanza viene stampato a video un messaggio
	 * di corretto svolgimento del comando, altrimenti stampa un messaggio di errore spiegando
	 * il motivo dell'errore
	 *
	 * @param Partita
	 */
	public void esegui(Partita partita) {
		if(partita.getGiocatore().GetBorsa().isEmpty()) {
			super.getIoConsole().mostraMessaggio("Non hai oggetti nella borsa!!\n");
		}else if(partita.getLabirinto().getStanzaCorrente().getNumeroAttrezzi()==10) {
			super.getIoConsole().mostraMessaggio("La stanza contiene già troppi oggetti!!");
		}else if(!partita.getGiocatore().GetBorsa().hasAttrezzo(super.getParametro())) {
			String returnString;
			returnString = "All'interno della borsa non è stato trovato nessun " + super.getParametro();
			super.getIoConsole().mostraMessaggio(returnString);
		}else {
			Attrezzo attrezzoDaPosare = partita.getGiocatore().GetBorsa().getAttrezzo(super.getParametro());
			if (partita.getLabirinto().getStanzaCorrente().addAttrezzo(attrezzoDaPosare)) {
				String returnString;
				returnString = "Hai posato l'attrezzo "
				+ partita.getGiocatore().GetBorsa().removeAttrezzo(super.getParametro()).getNome()
				+" all'interno della stanza";
				super.getIoConsole().mostraMessaggio(returnString);
			}else {
				
				super.getIoConsole().mostraMessaggio("Non è stato possibile posare l'oggetto!!\n");
			}
		}
	}
}
