package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

/**
 * Classe che gestisce il comandoPrendi
 *
 * @author docente di POO/ matricole "610199" - "610020"
 * @version versione.B
 */
public class ComandoPrendi implements Comando {
	
	private IO io;
	private String nomeAttrezzoDaPrendere;
	
	@Override
	public void setIoConsole(IO io) {
		this.io = io;
	}
	@Override
	public void setParametro(String parametro) {
		this.nomeAttrezzoDaPrendere = parametro;
	}
	
	@Override
	/**
	 * Metodo che stampa a video se l'oggetto è presente nella stanza e, se è
	 * presente, stampa il messaggio che è stato correttamente aggiunto nella borsa
	 * , altrimenti stampa che non è stato possibile aggiungerlo nella borsa
	 *
	 * @param Partita
	 */
	public void esegui(Partita partita) {
		if(!partita.getLabirinto().getStanzaCorrente().hasAttrezzo(this.nomeAttrezzoDaPrendere)) {
			String returnString;
			returnString = "Non c'è " + this.nomeAttrezzoDaPrendere + " nella stanza in cui ti trovi al momento!!\n";
			this.io.mostraMessaggio(returnString);
		}else {
			Attrezzo attrezzoDaPrendere = partita.getLabirinto().getStanzaCorrente().getAttrezzo(nomeAttrezzoDaPrendere);
			if(partita.getGiocatore().GetBorsa().addAttrezzo(attrezzoDaPrendere) && 
					partita.getLabirinto().getStanzaCorrente().removeAttrezzo(attrezzoDaPrendere)) {
				this.io.mostraMessaggio("Attrezzo aggiunto correttamente alla borsa!!\n");
			}else {
				this.io.mostraMessaggio("Non è stato possibile aggiungere l'attrezzo alla borsa!!\n");
			}
		}
	}
	
	@Override
	public String getNome() {
		return "prendi";
	}
	
	@Override
	public String getParametro() {
		return this.nomeAttrezzoDaPrendere;
	}
	

}
