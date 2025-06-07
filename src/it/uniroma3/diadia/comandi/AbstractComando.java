package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public abstract class AbstractComando implements Comando {

	private IO io;
	private String parametro;
	private String nome;

	public abstract void esegui(Partita partita);
	
	public void setIoConsole(IO io) {
		this.io = io;
	}
	
	public IO getIoConsole() {
		return this.io;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getParametro() {
		return this.parametro;
	}
	
	public void setParametro(String parametro) {
		this.parametro = parametro;
	}

}
