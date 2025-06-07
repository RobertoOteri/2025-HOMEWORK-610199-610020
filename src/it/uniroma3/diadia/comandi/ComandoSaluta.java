package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.ConfigurazioniIniziali;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class ComandoSaluta extends AbstractComando {
	
	private final static String nome = ConfigurazioniIniziali.getNomeComandoSaluta();

	private final static String MESSAGGIO_FAIL = "Non c'è nessuno da salutare qui....\n";
	
	
	public ComandoSaluta() {
		super.setNome(nome);
	}
	
	@Override
	public void esegui(Partita partita) {
		AbstractPersonaggio personaggio = partita.getLabirinto().getStanzaCorrente().getPersonaggio();
		if(personaggio!=null) {
			super.getIoConsole().mostraMessaggio(personaggio.saluta());	
		}else
		{
			super.getIoConsole().mostraMessaggio(MESSAGGIO_FAIL);
		}
	}
}
