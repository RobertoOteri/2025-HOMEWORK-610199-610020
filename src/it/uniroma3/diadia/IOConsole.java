package it.uniroma3.diadia;

import java.util.Scanner;

/**
 * Classe che si occupa di gestire gli ingressi di Input e Output dell'intero
 * programma
 * 
 * @author docente di POO/ matricole "610199" - "610020"
 * @version versione.B
 */

public class IOConsole implements IO {

	@Override
	/**
	 * Metodo che si occupa di stampare a video una stringa
	 * 
	 * @param Stringa da stampare
	 * 
	 */
	public void mostraMessaggio(String msg) {
		System.out.println(msg);
	}
	
	@Override
	/**
	 * Metodo che si occupa di scannerizzare un comando inserito dall'utente
	 * 
	 * @return la Stringa di comando inserita dall'utente
	 * 
	 */
	public String leggiRiga() {
		Scanner scannerDiLinee = new Scanner(System.in);
		String riga = scannerDiLinee.nextLine();
		return riga;
	}
}
