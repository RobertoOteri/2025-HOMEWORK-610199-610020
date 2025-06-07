package it.uniroma3.diadia.comandi;

import java.util.Scanner;
import it.uniroma3.diadia.IO;

/**
 * Questa classe modella un comando. Un comando consiste al piu' di due parole:
 * il nome del comando ed un parametro su cui si applica il comando. (Ad es.
 * alla riga digitata dall'utente "vai nord" corrisponde un comando di nome
 * "vai" e parametro "nord").
 *
 * @author docente di POO/ matricole "610199" - "610020"
 * @version versione.C
 */
public class FabbricaDiComandiFisarmonica implements FabbricaDiComandi {
	
	@Override
	/**
	 * Metodo che crea un comando a seconda dell'istruzione digitata dall'utente in input e passata
	 * al metodo come parametro
	 *
	 * @param La Stringa di istruzione scritta dall'utente in input, la console di I/O
	 * @return il comando creato
	 */
	public AbstractComando costruisciComando(String istruzione, IO io) {
		Scanner scannerDiParole = new Scanner(istruzione);
		String nomeComando = null;
		String parametro = null;
		AbstractComando comando = null;
		if(scannerDiParole.hasNext()) {
			nomeComando = scannerDiParole.next();
		}
		if(scannerDiParole.hasNext()) {
			parametro = scannerDiParole.next();
		} 
		
		if(nomeComando == null) {
			comando = new ComandoNonValido();
		}else if(nomeComando.equals("vai")) {
			comando = new ComandoVai();
		}else if(nomeComando.equals("prendi")) {
			comando = new ComandoPrendi();
		}else if(nomeComando.equals("posa")) {
			comando = new ComandoPosa();
		}else if(nomeComando.equals("aiuto")) {
			comando = new ComandoAiuto();
		}else if(nomeComando.equals("fine")) {
			comando = new ComandoFine();
		}else if(nomeComando.equals("guarda")) {
			comando = new ComandoGuarda(); 
		}else {
			comando = new ComandoNonValido();
		}
		comando.setParametro(parametro);
		comando.setIoConsole(io);
		return comando;
	}

}
