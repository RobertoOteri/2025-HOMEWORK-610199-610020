package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Classe StanzaBloccata - Classe che implementa una stanza con delle direzioni bloccate
 * a cui si può accedere solo se nella stanza che si trova nella direzione bloccata è
 * presente un attrezzo sbloccante
 * 
 * @author docente di POO/ matricole "610199" - "610020"
 * @see Attrezzo
 * @version versione.B
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
		if(!stanzaAdiacente.hasAttrezzo(this.nomeAttrezzoPerSbloccare) && this.nomeDirezioneBloccata.equals(direzione)) {
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
		risultato.append("\nUscite: ");
		for (String direzione : super.getDirezioni()) {
			if (direzione != null) {
				risultato.append(" " + direzione);
				if(direzione.equals(this.nomeDirezioneBloccata)) {
					risultato.append("Stanza bloccata, si può accedere tramite: " + this.nomeAttrezzoPerSbloccare);
				}
			}
		}
		risultato.append("\nAttrezzi nella stanza: ");
		for (Attrezzo attrezzo : super.getAttrezzi()) {
			if (attrezzo != null) {
				risultato.append(attrezzo.toString() + " ");
			}
		}
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
