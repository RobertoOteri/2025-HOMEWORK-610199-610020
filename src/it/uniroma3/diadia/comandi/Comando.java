package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.IO;
import java.util.Scanner;
/**
 * Questa interfaccia modella un comando. Un comando consiste al piu' di due parole:
 * il nome del comando ed un parametro su cui si applica il comando. (Ad es.
 * alla riga digitata dall'utente "vai nord" corrisponde un comando di nome
 * "vai" e parametro "nord").
 *
 * @author docente di POO/ matricole "610199" - "610020"
 * @version versione.C
 */

public interface Comando {


	public void setIoConsole(IO io);
	public void setParametro(String parametro);
	public void esegui(Partita partita);
	public String getNome();
	public String getParametro();

}
