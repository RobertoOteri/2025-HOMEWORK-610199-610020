package it.uniroma3.diadia.ambienti;

/**
 * Classe StanzaBuia - una stanza in un gioco di ruolo. E' possibile vedere cosa
 * c'è all'interno della stanza buia solo se si posa all'interno di essa un
 * oggetto che permette di illuminare la stanza. E' collegata ad altre stanze
 * attraverso delle uscite. Ogni uscita e' associata ad una direzione.
 * 
 * @author docente di POO/ matricole "610199" - "610020"
 * @see Attrezzo
 * @version versione.B
 */

public class StanzaBuia extends Stanza {

	private String nomeAttrezzoCheIllumina;
	
	public StanzaBuia (String nome, String nomeAttrezzoCheIllumina){
	super(nome);
	this.nomeAttrezzoCheIllumina = nomeAttrezzoCheIllumina;
	
}
	@Override 
	/**
	 * Restituisce una rappresentazione stringa di questa stanza, solo se la stanza contiene
	 * un oggetto che consente di illuminarla, stampadone la
	 * descrizione, le uscite e gli eventuali attrezzi contenuti. Se all'interno della stanza
	 * non è presente l'attrezzo che la illumina stampa la stringa: "Qui c'è un buio pesto...."
	 * 
	 * @return la rappresentazione stringa
	 */
	public String toString () {
		String returnString;
		if(!super.hasAttrezzo(this.nomeAttrezzoCheIllumina)) {
			returnString = "\nQui c'è un buio pesto....";
		}else{
			returnString = super.toString();
		}
		return returnString;
	}
	@Override
	/**
	 * Restituisce la descrizione della stanza buia.
	 * 
	 * @return la descrizione della stanza
	 */
	public String getDescrizione () {
		return this.toString();
	}
}
