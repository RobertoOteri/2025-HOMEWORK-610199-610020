package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.ConfigurazioniIniziali;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoRegala extends AbstractComando{
	
	private final static String nome = ConfigurazioniIniziali.getNomeComandoRegala();
	
	public ComandoRegala() {
		super.setNome(nome);
	}
	
	@Override
	public void esegui(Partita partita) {
	   if(!partita.getGiocatore().GetBorsa().hasAttrezzo(super.getParametro())) {
		   super.getIoConsole().mostraMessaggio("\nNella borsa non è presente nessun oggetto con quel nome!!\n");
	   }else if(!partita.getLabirinto().getStanzaCorrente().hasPersonaggio()){ 
		   super.getIoConsole().mostraMessaggio("\nNon c'è nessuno a cui regalare qualcosa qui!!\n");
	   }else {
		   Attrezzo attrezzoRimosso = partita.getGiocatore().GetBorsa().removeAttrezzo(super.getParametro());
		   String stringa = partita.getLabirinto().getStanzaCorrente().getPersonaggio().riceviRegalo(attrezzoRimosso, partita);
		   super.getIoConsole().mostraMessaggio(stringa);
	   }
	}
}
