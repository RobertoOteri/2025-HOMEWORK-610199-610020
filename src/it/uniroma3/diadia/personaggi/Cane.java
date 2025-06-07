package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Cane extends AbstractPersonaggio {

	private static final String DESCRIZIONE = "\n\nEntrando nella stanza noti immediatamente un cane "
											+ "\ndi taglia media che dorme al centro di questa...\n\n";
	
	private static final String PRESENTAZIONE = "\ngrrr woof woof bark\n";
	
	private static final String REGALO = "\nBau woof, il cane scodinzolando lascia cadere a terra"
			                           + "\nl'oggetto che teneva tra i denti per addentare il"
			                           + "\ncibo che gli hai lasciato\n\n";
	
	private static final String REGALO_FAIL = "\nIl cane non ha potuto lasciare a terra l'oggetto"
			                                + "\nche teneva tra i denti perchè nella stanza ci"
			                                + "\nsono già troppi oggetti, ma non sembra intenzionato"
			                                + "\na lasciarti riprendere il cibo che gli hai dato!\n";
	
	private String ciboPreferito;
	private Attrezzo regaloDelCane;
	
	public Cane(Attrezzo regaloDelCane, String ciboPreferito) {
		super("cane", DESCRIZIONE, PRESENTAZIONE, PRESENTAZIONE);
		this.ciboPreferito = ciboPreferito;
		this.regaloDelCane = regaloDelCane;
	}
	
	@Override
	public String agisci(Partita partita) {
		partita.getGiocatore().setCfu(partita.getGiocatore().getCfu()-1);
		return PRESENTAZIONE + "Il cane ci ha morso... hai perso 1 CFU\n";
	}
	
	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		String stringaDiRitorno = null;
		if(attrezzo.getNome().equals(this.ciboPreferito)) {
			if(partita.getLabirinto().getStanzaCorrente().addAttrezzo(this.regaloDelCane)) {
				stringaDiRitorno = REGALO;
			}else {
				stringaDiRitorno = REGALO_FAIL;
			}
		}else
		{
			partita.getGiocatore().setCfu(partita.getGiocatore().getCfu()-1);
			stringaDiRitorno = PRESENTAZIONE + "Il cane ci ha morso... hai perso 1 CFU\n";
		}
		return stringaDiRitorno;
	}
}
