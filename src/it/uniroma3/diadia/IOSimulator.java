package it.uniroma3.diadia;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe che si occupa di salvare in degli array gli ingressi di Input e Output dell'intero
 * programma
 * 
 * @author docente di POO/ matricole "610199" - "610020"
 * @version versione.C
 */

public class IOSimulator implements IO {
	
	private List<String> righeDaLeggere;
	private List<String> messaggiProdotti;
	private int indiceRigaDaLeggere;
	private int indiceMostraMessaggio;

	
	public IOSimulator(List<String> righeDaLeggere) {
		this.righeDaLeggere = righeDaLeggere;
		this.messaggiProdotti = new ArrayList<String>();
		this.indiceRigaDaLeggere=0;
		this.indiceMostraMessaggio=0;
	}
	@Override
	/**
	 * Metodo che si occupa di salvare in un array una stringa mostrata a video
	 * 
	 * @param Stringa stampata da salvare
	 * 
	 */
	public void mostraMessaggio(String messaggio) {
		this.messaggiProdotti.add(messaggio);
	}

	@Override
	/**
	 * Metodo che si occupa di trovare nell'array il comando da eseguire 
	 * nel momento in cui viene chiamata
	 * 
	 * @return la Stringa di comando da eseguire
	 * 
	 */
	public String leggiRiga() {
		String rigaLetta = this.righeDaLeggere.get(this.indiceRigaDaLeggere);
		this.indiceRigaDaLeggere++;
		return rigaLetta;
	} 
	
	/**
	 * Metodo che si occupa di trovare nell'array la stringa contenente il messaggio da mostrare 
	 * nel momento in cui viene chiamata
	 * 
	 * @return la Stringa di messaggio da mostrare
	 * 
	 */
	public String messaggioCorrente() {
		String messaggio = this.messaggiProdotti.get(this.indiceMostraMessaggio);
		this.indiceMostraMessaggio++;
		return messaggio;
	}
	
	/**
	 * Metodo che si occupa di verificare se nell'array è presente un prossimo messaggio da mostrare
	 * 
	 * @return true se è presente un prossimo messaggio, false altrimenti
	 * 
	 */
	public boolean hasNextMessaggio() {
		return this.indiceMostraMessaggio < this.messaggiProdotti.size();
	}
	
	public List<String> getMessaggiProdotti() {
		return this.messaggiProdotti;
	}

}
