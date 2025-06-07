package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Classe StanzaBloccata - Classe che implementa una stanza con delle direzioni bloccate
 * a cui si può accedere solo se nella stanza che si trova nella direzione bloccata è
 * presente un attrezzo sbloccante
 * 
 * @author docente di POO/ matricole "610199" - "610020"
 * @see Attrezzo
 * @version versione.C
 */
public class StanzaBloccata extends Stanza {

	private String nomeDirezioneBloccata;
	private String nomeAttrezzoPerSbloccare;
	
	public StanzaBloccata (String nome, String nomeDirezioneBloccata, String nomeAttrezzoPerSbloccare) {
		super(nome);
		this.nomeDirezioneBloccata = nomeDirezioneBloccata;
		this.nomeAttrezzoPerSbloccare = nomeAttrezzoPerSbloccare;
	}
	
	@Override
	/**
	 * Se la direzione non è bloccata oppure è bloccata ma la stanza in data direzione
	 * contiene l'attrezzo sbloccante restituisce la stanza adiacente nella direzione
	 * specificata, altrimenti restituisce l'indirizzo della stanza di partenza.
	 * 
	 * @param direzione
	 */
	public Stanza getStanzaAdiacente (String direzione) {
		Stanza stanzaAdiacente = super.getStanzaAdiacente(direzione);
		if(!super.hasAttrezzo(this.nomeAttrezzoPerSbloccare) && this.nomeDirezioneBloccata.equals(direzione)) {
			return this;
		}
		return stanzaAdiacente;	
	}
	
	@Override
	/**
	 * Restituisce una rappresentazione stringa di questa stanza, stampadone la
	 * descrizione, le uscite, gli eventuali attrezzi contenuti e le eventuali stanze adiacenti
	 * bloccate indicando l'atttrezzo per sbloccarle
	 * 
	 * @return la rappresentazione stringa
	 */
	public String toString() {
		StringBuilder risultato = new StringBuilder();
		risultato.append(super.getNome());
		risultato.append("\nUscite\n");
		risultato.append(super.getDirezioni().toString());
		if(super.getDirezioni().contains(this.nomeDirezioneBloccata)) {
			risultato.append("\n" + this.nomeDirezioneBloccata + " è una direzione bloccata!!"
					+ "\nTrovare l'attrezzo: " + this.nomeAttrezzoPerSbloccare + " e posarlo per accedervi");
		}
		risultato.append("\nAttrezzi nella stanza: ");
		risultato.append(super.getAttrezzi().values().toString());
		return risultato.toString();
	}

	@Override
	/**
	 * Restituisce la descrizione della stanza.
	 * 
	 * @return la descrizione della stanza
	 */
	public String getDescrizione() {
		return this.toString();
	}
}
