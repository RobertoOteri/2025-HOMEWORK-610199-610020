package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.ConfigurazioniIniziali;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

/**
 * Classe che gestisce il comandoPrendi
 *
 * @author docente di POO/ matricole "610199" - "610020"
 * @version versione.C
 */
public class ComandoPrendi extends AbstractComando{
	
	private static final String nome = ConfigurazioniIniziali.getNomeComandoPrendi();
	
	
	public ComandoPrendi() {
		super.setNome(nome);
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
		if(!partita.getLabirinto().getStanzaCorrente().hasAttrezzo(super.getParametro())) {
			String returnString;
			returnString = "Non c'è " + super.getParametro() + " nella stanza in cui ti trovi al momento!!\n";
			super.getIoConsole().mostraMessaggio(returnString);
		}else {
			Attrezzo attrezzoDaPrendere = partita.getLabirinto().getStanzaCorrente().getAttrezzo(super.getParametro());
			if(partita.getGiocatore().GetBorsa().addAttrezzo(attrezzoDaPrendere) && 
					partita.getLabirinto().getStanzaCorrente().removeAttrezzo(attrezzoDaPrendere)) {
				super.getIoConsole().mostraMessaggio("Attrezzo aggiunto correttamente alla borsa!!\n");
			}else {
				super.getIoConsole().mostraMessaggio("Non è stato possibile aggiungere l'attrezzo alla borsa!!\n");
			}
		}
	}

}
