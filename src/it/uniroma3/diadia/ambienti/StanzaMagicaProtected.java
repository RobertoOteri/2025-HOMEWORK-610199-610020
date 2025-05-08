package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Classe StanzaMagica - Classe che implementa una stanza magica con degli effetti speciali:
 * se nella stanza sono stati già depositati due oggetti a partire dal terzo ne invertirà il nome e gli
 * raddopierà il peso
 * 
 * @author docente di POO/ matricole "610199" - "610020"
 * @see Attrezzo
 * @version versione.B
 */
public class StanzaMagicaProtected extends StanzaProtected {

	final static private int SOGLIA_MAGICA_DEFAULT = 3;
	private int contatoreAttrezziPosati;
	private int sogliaMagica;
	
	public StanzaMagicaProtected (String nome) {
		this(nome, SOGLIA_MAGICA_DEFAULT);
	}
	
	public StanzaMagicaProtected (String nome, int soglia) {
		super(nome);
		this.contatoreAttrezziPosati = 0;
		this.sogliaMagica = soglia;
	}
	
	/**
	 * modifica un attrezzo appena posato nella stanza applicandoci l'effetto magico
	 * 
	 * 
	 * @param l'attrezzo su cui applicare l'effetto magico.
	 * @return l'indirizzo dell'attrezzo modificato.
	 */
	private Attrezzo modificaAttrezzo(Attrezzo attrezzo) {
		StringBuilder nomeInvertito;
		int pesoX2 = attrezzo.getPeso()* 2;
		nomeInvertito = new StringBuilder(attrezzo.getNome());
		nomeInvertito = nomeInvertito.reverse();
		attrezzo = new Attrezzo(nomeInvertito.toString(), pesoX2);
		return attrezzo;
	}
	
	@Override 
	/**
	 * Aggiunge un attrezzo nella stanza e aumenta il contatore degli attrezzi degli attrezzi 
	 * posati
	 * 
	 * @param attrezzo l'attrezzo da mettere nella stanza.
	 * @return true se riesce ad aggiungere l'attrezzo, false atrimenti.
	 */
	public boolean addAttrezzo(Attrezzo attrezzo) {
	if(this.contatoreAttrezziPosati>=this.sogliaMagica) {
		attrezzo = this.modificaAttrezzo(attrezzo);
	}
	boolean vero = super.addAttrezzo(attrezzo);
	if(vero) {
		this.contatoreAttrezziPosati++;
	}
	return vero;
	}
}
