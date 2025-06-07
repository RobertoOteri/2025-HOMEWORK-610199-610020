package it.uniroma3.diadia.personaggi;

import java.util.Map;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Strega extends AbstractPersonaggio {

	private final static String DESCRIZIONE = "\nEntrando nella stanza noti in un angolo una donna anziana"
			                                + "\ncon un grande naso bitorzoluto completamente coperta"
			                                + "\nda una veste viola, che sembra intenta a creare un intruglio"
			                                + "\ncon chissà quali ingredienti in un calderone...\n\n";
	
	private final static String PRESENTAZIONE = "\nSalve studente, io sono Grimilde la strega di roma3"
			                                  + "\navvicinati a me a tuo rischio e pericolo eheheh\n\n";
	
	private final static String PRESENTAZIONE_FAIL = "Già ci conosciamo mio caro studente ehehehe\n";
			
	private final static String SPOSTAMENTO = "Decidi di bere la pozione che ti viene porta dalla strega, "
			                                + "\nTi risvegli stordito in una nuova stanza....\n\n";
	
	private final static String RISATA = "\nHAHAHAHHAHAHAHA\n\n";
	public Strega() {
		super("strega", DESCRIZIONE, PRESENTAZIONE, PRESENTAZIONE_FAIL);
	}
	
	@Override
	public String agisci(Partita partita) {
		String direzioneMinima=null;
		String direzioneMassima=null;
		int minimo = 11;
		int massimo = -1;
		Stanza stanza = null;
		Map<String, Stanza> stanzeAdiacenti = partita.getLabirinto().getStanzaCorrente().getMapStanzeAdiacenti();
		if(stanzeAdiacenti == null || stanzeAdiacenti.isEmpty()) {
			return "Decidi di bere la pozione che ti viene donata dalla strega, ma questa non sortisce alcun effetto\n\n";
		}
		for(String s : stanzeAdiacenti.keySet()) {
			stanza = stanzeAdiacenti.get(s);
			if(stanza.getNumeroAttrezzi()<minimo) {
				minimo = stanza.getNumeroAttrezzi();
				direzioneMinima = s;
			}
			if(stanza.getNumeroAttrezzi()>massimo) {
				massimo = stanza.getNumeroAttrezzi();
				direzioneMassima = s;
			}
		}
		if(super.haSalutato()) {
			partita.getLabirinto().setStanzaCorrente(stanzeAdiacenti.get(direzioneMassima));
		}else {
			partita.getLabirinto().setStanzaCorrente(stanzeAdiacenti.get(direzioneMinima));
		}
		return SPOSTAMENTO;
	}
	
	@Override
	public String riceviRegalo (Attrezzo attrezzo, Partita partita) {
		return RISATA;
	}
} 
