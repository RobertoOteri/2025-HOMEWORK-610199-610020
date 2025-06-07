package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public abstract class AbstractPersonaggio {

	private String nome;
	private String descrizione;
	private String presentazione;
	private String presentazione_fail;
	private boolean salutato;
	
	
	public AbstractPersonaggio(String nome, String descrizione, String presentazione, String presentazione_fail) {
		this.nome=nome;
		this.descrizione=descrizione;
		this.presentazione = presentazione;
		this.presentazione_fail = presentazione_fail;
		salutato = false;
	}
	public String getNome() {
		return this.nome;
	}
	 public String getDescrizione() {
		 return this.descrizione;
	}
	public boolean haSalutato() {
		return this.salutato;
	}
	public String getPresentazione() {
		return this.presentazione;
	}
	
	public String saluta() {
		String stringaDiRitorno = null;
		if(!this.salutato) {
			stringaDiRitorno = this.presentazione;
			this.salutato = true;
		}else {
			stringaDiRitorno = this.presentazione_fail;
		}
		return stringaDiRitorno;
	}

	public abstract String agisci(Partita partita);
	public abstract String riceviRegalo(Attrezzo attrezzo, Partita partita);
	
}
