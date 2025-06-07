package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Mago extends AbstractPersonaggio {

	private static final String DESCRIZIONE = "\n\nAll'interno della Stanza riesci ad intravedere un'"
			                                  + "anziana \npersona dalla lunga barba bianca con indosso"
			                                  + "\ncon vesti a dir poco stravaganti.....\n\n"; 
	
	private static final String PRESENTAZIONE = "\nCiao, io sono Merlino mago della sede di ingegneria\n"
			                                  + "ti aiuterò a raggiungere il tuo obbiettivo \ndonandoti un magico"
			                                  + "oggetto!!\n\n";
	
	private static final String PRESENTAZIONE_FAIL = "\nCi siamo già presentati\n";
	
	private static final String MAGIA = "\nAttreverso una potente magia dimezzerò il "
			                          + "peso di questo oggetto!!\n";
	
	private static final String MAGIA_FAIL = "\nMi dispiace, ma non posso usare la mia magia su "
			                               + "\nquesto oggetto perchè la stanza è gia troppo piena,"
			                               + "\nte lo rimetto in borsa invariato!!\n\n";
	
	private static final String REGALO = "\nEcco a te un utilissimo oggetto!!\n";
	private static final String SCUSE = "\nMi dispiace, ma non ho più nulla da donarti\n\n";
	
	private Attrezzo attrezzoDaDonare;
	
	public Mago(Attrezzo attrezzoDaDonare){
		super("mago", DESCRIZIONE, PRESENTAZIONE, PRESENTAZIONE_FAIL);
		this.attrezzoDaDonare = attrezzoDaDonare;
	}
	
	public void setAttrezzoDaDonare(Attrezzo attrezzo) {
		this.attrezzoDaDonare = attrezzo;
	}
	
	@Override
	public String agisci(Partita partita) {
		String messaggio;
		if(this.attrezzoDaDonare!=null) {
			messaggio = REGALO + "Lo strano mago ha lasciato a terra un " + this.attrezzoDaDonare.getNome() + "\n\n";
			partita.getLabirinto().getStanzaCorrente().addAttrezzo(attrezzoDaDonare);
		}else {
			messaggio = SCUSE;
		}
		return messaggio;
	}
	
	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		String stringaDiRitorno = null;
		attrezzo.setPeso(attrezzo.getPeso()/2);
		if(partita.getLabirinto().getStanzaCorrente().addAttrezzo(attrezzo)) {
			stringaDiRitorno = MAGIA;
		}else {
			attrezzo.setPeso(attrezzo.getPeso()*2);
			partita.getGiocatore().GetBorsa().addAttrezzo(attrezzo);
			stringaDiRitorno = MAGIA_FAIL;
		}
		return stringaDiRitorno;
	}
}
