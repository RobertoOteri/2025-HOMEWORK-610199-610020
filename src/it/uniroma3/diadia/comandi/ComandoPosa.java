package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Classe che gestisce il comando posa, che permette di posare un oggetto contenuto nella borsa
 * del giocatore all'interno di una stanza 
 *
 * @author docente di POO/ matricole "610199" - "610020"
 * @version versione.B
 */
public class ComandoPosa implements Comando {

	private IO io;
	private String nomeAttrezzoDaPosare;
	
	@Override
	public void setIoConsole(IO io) {
		this.io = io;
	}	
	@Override
	public void setParametro(String parametro) {
		this.nomeAttrezzoDaPosare = parametro;
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
			this.io.mostraMessaggio("Non hai oggetti nella borsa!!\n");
		}else if(partita.getLabirinto().getStanzaCorrente().getNumeroAttrezzi()==10) {
			this.io.mostraMessaggio("La stanza contiene già troppi oggetti!!");
		}else if(!partita.getGiocatore().GetBorsa().hasAttrezzo(nomeAttrezzoDaPosare)) {
			String returnString;
			returnString = "All'interno della borsa non è stato trovato nessun " + this.nomeAttrezzoDaPosare;
			this.io.mostraMessaggio(returnString);
		}else {
			Attrezzo attrezzoDaPosare = partita.getGiocatore().GetBorsa().getAttrezzo(nomeAttrezzoDaPosare);
			if (partita.getLabirinto().getStanzaCorrente().addAttrezzo(attrezzoDaPosare)) {
				String returnString;
				returnString = "Hai posato l'attrezzo "
				+ partita.getGiocatore().GetBorsa().removeAttrezzo(this.nomeAttrezzoDaPosare).getNome()
				+" all'interno della stanza";
				this.io.mostraMessaggio(returnString);
			}else {
				
				this.io.mostraMessaggio("Non è stato possibile posare l'oggetto!!\n");
			}
		}
	}
	
	@Override
	public String getNome() {
		return "posa";
	}
	
	@Override
	public String getParametro() {
		return this.nomeAttrezzoDaPosare;
	}
}
