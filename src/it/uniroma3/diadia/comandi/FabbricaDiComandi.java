package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;

/**
 * Questa interfaccia modella un comando. Un comando consiste al piu' di due parole:
 * il nome del comando ed un parametro su cui si applica il comando. (Ad es.
 * alla riga digitata dall'utente "vai nord" corrisponde un comando di nome
 * "vai" e parametro "nord").
 *
 * @author docente di POO/ matricole "610199" - "610020"
 * @version versione.B
 */
public interface FabbricaDiComandi {

	public Comando costruisciComando(String istruzione, IO io);
}
