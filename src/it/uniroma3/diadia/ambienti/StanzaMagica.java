package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.ConfigurazioniIniziali;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Classe StanzaMagica - Classe che implementa una stanza magica con degli effetti speciali:
 * se nella stanza sono stati già depositati due oggetti a partire dal terzo ne invertirà il nome e gli
 * raddopierà il peso
 * 
 * @author docente di POO/ matricole "610199" - "610020"
 * @see Attrezzo
 * @version versione.C
 */
public class StanzaMagica extends Stanza {
	
	final static private int SOGLIA_MAGICA_DEFAULT = ConfigurazioniIniziali.getSogliaMagicaDefault();
	private int contatoreAttrezziPosati;
	private int sogliaMagica;
	
	public StanzaMagica (String nome) {
		this(nome, SOGLIA_MAGICA_DEFAULT);
	}
	
	public StanzaMagica (String nome, int soglia) {
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
	
	@Override
	public String toString() {
		StringBuilder risultato = new StringBuilder();
		risultato.append(super.getNome());
		risultato.append("\nUscite: ");
		risultato.append(super.getMapStanzeAdiacenti().keySet().toString());
		risultato.append("\nAttrezzi nella stanza: ");
		risultato.append(super.getAttrezzi().values().toString());
		if(super.getPersonaggio()!=null) {
			risultato.append(super.getPersonaggio().getDescrizione());
		}
		risultato.append("\nQuesta stanza emana una strana aura.......\n");
		return risultato.toString();
	}
}
