package it.uniroma3.diadia.ambienti;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import it.uniroma3.diadia.ConfigurazioniIniziali;
import it.uniroma3.diadia.FormatoFileNonValidoException;

public class CaricatoreLabirinto {

	private static final String STANZE = ConfigurazioniIniziali.getNomiStanze();
	private static final String STANZA_INIZIALE = ConfigurazioniIniziali.getNomiStanzaIniziale();
	private static final String STANZA_VINCENTE = ConfigurazioniIniziali.getNomiStanzaVincente();
	private static final String ATTREZZI = ConfigurazioniIniziali.getNomiAttrezzi();
	private static final String USCITE = ConfigurazioniIniziali.getNomiUscite();
	private static final String MAGHI = ConfigurazioniIniziali.getNomiMaghi();
	private static final String STREGHE = ConfigurazioniIniziali.getNomiStreghe();
	private static final String CANI = ConfigurazioniIniziali.getNomiCani();
	private static final String MAGICHE = ConfigurazioniIniziali.getNomiStanzeMagiche();
	private static final String MAGICHE_SOGLIA = ConfigurazioniIniziali.getNomiStanzeMagicheSoglia();
	private static final String BLOCCATE = ConfigurazioniIniziali.getNomiStanzeBloccate();
	private static final String BUIE = ConfigurazioniIniziali.getNomiStanzeBuie();
	
	private String nomeFile;
	private Map<String, Stanza> stanze;
	private BufferedReader reader;
	private Labirinto.LabirintoBuilder builder;
	
	public CaricatoreLabirinto(String nomeFile, Labirinto labirinto) throws FileNotFoundException {
		this.nomeFile = nomeFile;
		reader = new BufferedReader(new FileReader(nomeFile));
		builder = new  Labirinto.LabirintoBuilder(labirinto);
	}
	
	public List<String> separaStringheAlleVirgoleEDuePunti(String riga){
		List<String> result = new LinkedList<>();
		Scanner scanner = new Scanner(riga);
		scanner.useDelimiter(":");
		if(scanner.hasNext()) {
			result.add(scanner.next().trim() + ":");
		}
		
		if(scanner.hasNext()) {
			String resto = scanner.next();
			Scanner sottoScanner = new Scanner(resto);
			sottoScanner.useDelimiter(",");
			while(sottoScanner.hasNext()) {
				result.add(sottoScanner.next().trim());
			}
		}
		return result;
	}
	
	public void carica() throws IOException, FormatoFileNonValidoException {
		try {
			String stringaLetta;
			while((stringaLetta = this.reader.readLine())!=null) {
				List<String> parole = this.separaStringheAlleVirgoleEDuePunti(stringaLetta);
				if(STANZE.equals(parole.get(0))) {
					this.leggiEImpostaStanze(parole);
				}else if(STANZA_INIZIALE.equals(parole.get(0))) {
					this.leggiEImpostaStanzaIniziale(parole);
				}else if(STANZA_VINCENTE.equals(parole.get(0))) {
					this.leggiEImpostaStanzaVincente(parole);
				}else if(ATTREZZI.equals(parole.get(0))) {
					this.leggiEImpostaAttrezzi(parole);
				}else if(USCITE.equals(parole.get(0))) {
					this.leggiEImpostaUscite(parole);
				}else if(MAGHI.equals(parole.get(0))) {
					this.leggiEImpostaMago(parole);
				}else if(STREGHE.equals(parole.get(0))) {
					this.leggiEImpostaStrega(parole);
				}else if(CANI.equals(parole.get(0))) {
					this.leggiEImpostaCane(parole);
				}else if(MAGICHE.equals(parole.get(0))) {
					this.leggiEImpostaStanzaMagica(parole);
				}else if(MAGICHE_SOGLIA.equals(parole.get(0))) {
					this.leggiEImpostaStanzaMagicaConSoglia(parole);
				}else if(BLOCCATE.equals(parole.get(0))) {
					this.leggiEImpostaStanzaBloccata(parole);
				}else if(BUIE.equals(parole.get(0))) {
					this.leggiEImpostaStanzaBuia(parole);
				}else {
					throw new FormatoFileNonValidoException("Formato file non valido");
				}
			}
		}finally{
				try {
					this.reader.close();
				}catch(IOException e) {
					e.printStackTrace();
				}
		}
	}
	
	public void leggiEImpostaStanze(List<String> parole) {
		for(int i=1; i<parole.size(); i++) {
			this.builder.addStanza(parole.get(i));
		}
	}
	
	public void leggiEImpostaStanzaIniziale(List<String> parole) {
		this.builder.addStanzaIniziale(parole.get(1));
	}
	
	public void leggiEImpostaStanzaVincente(List<String> parole) {
		this.builder.addStanzaVincente(parole.get(1));
	}
	
	public void leggiEImpostaAttrezzi(List<String> parole) {
		int contatore; 
			for(int i=1; i<parole.size(); i++) {
				contatore=0;
				String riga = parole.get(i);
				Scanner scannerDiParole = new Scanner(riga);
				String nomeAttrezzo = null;
				String nomeStanza = null;
				int peso = 0;
				while(scannerDiParole.hasNext()) {
					if(contatore == 0) {
						nomeAttrezzo = scannerDiParole.next();
					}else if(contatore == 1) {
						peso = Integer.parseInt(scannerDiParole.next());
					}else {
						nomeStanza = scannerDiParole.next().trim();
					}
					contatore++;
				}
			this.builder.addAttrezzo(nomeAttrezzo, peso, nomeStanza);
			}
	}
	
	public void leggiEImpostaUscite(List<String> parole) {
		int contatore;
			for(int i=1; i<parole.size(); i++) {
				contatore = 0;
				String riga = parole.get(i);
				String nomeStanzaPartenza = null;
				String nomeStanzaArrivo = null;
				String direzione = null;
				Scanner scannerDiParole = new Scanner(riga);
				while(scannerDiParole.hasNext()) {
					String token = scannerDiParole.next();
					if(contatore == 0) {
						nomeStanzaPartenza = token;
					}else if(contatore == 1) {
						direzione =token ;
					}else {
						nomeStanzaArrivo = token;
					}
					contatore++;
				}
				this.builder.addAdiacenza(nomeStanzaPartenza, nomeStanzaArrivo, direzione);
			}
	}
	
	public void leggiEImpostaMago(List<String> parole) {
		int contatore;
			for(int i=1; i<parole.size(); i++) {
				contatore=0;
				Scanner scannerDiParole = new Scanner(parole.get(i));
				String nomeAttrezzo = null;
				String nomeStanza = null;
				int peso = 0;
				while(scannerDiParole.hasNext()) {
					if(contatore==0) {
						nomeAttrezzo = scannerDiParole.next();
					}else if(contatore==1) {
						peso = Integer.parseInt(scannerDiParole.next());
					}else {
						nomeStanza = scannerDiParole.next();
					}
					contatore++;
				}
				this.builder.addMago(nomeAttrezzo, peso, nomeStanza);
			}
	}
	
	public void leggiEImpostaStrega(List<String> parole) {	
		for(int i=1; i<parole.size(); i++) {
			this.builder.addStrega(parole.get(i));
		}
	}
	
	public void leggiEImpostaCane(List<String> parole) {
		int contatore;
			for(int i=1; i<parole.size(); i++) {
				contatore=0;
				Scanner scannerDiParole = new Scanner(parole.get(i));
				String nomeRegaloDelCane = null;
				int peso = 0;
				String ciboPreferito = null;
				String nomeStanza = null;
				while(scannerDiParole.hasNext()) {
					if(contatore==0) {
						nomeRegaloDelCane = scannerDiParole.next();
					}else if(contatore==1) {
						peso = Integer.parseInt(scannerDiParole.next());
					}else if(contatore == 2) {
						ciboPreferito = scannerDiParole.next();
					}else {
						nomeStanza = scannerDiParole.next();
					}
					contatore++;
				}
				this.builder.addCane(nomeRegaloDelCane, peso, ciboPreferito, nomeStanza);			
			}
	}
	
	public void leggiEImpostaStanzaBuia(List<String> parole) {
		int contatore;
		for(int i=1; i<parole.size(); i++) {
			contatore = 0;
			String nomeStanza = null;
			String nomeAttrezzoCheIllumina = null;
			Scanner scannerDiParole = new Scanner(parole.get(i));
			while(scannerDiParole.hasNext()) {
				if(contatore==0) {
					nomeStanza = scannerDiParole.next();
				}else {
					nomeAttrezzoCheIllumina = scannerDiParole.next();
				}
				contatore++;
			}
			this.builder.addStanzaBuia(nomeStanza, nomeAttrezzoCheIllumina);
		}
	}
	
	public void leggiEImpostaStanzaBloccata(List<String> parole) {
		int contatore;
		for(int i=1; i<parole.size(); i++) {
			contatore = 0;
			String nomeStanza = null;
			String nomeAttrezzoCheSblocca = null;
			String direzioneBloccata = null;
			Scanner scannerDiParole = new Scanner(parole.get(i));
			while(scannerDiParole.hasNext()) {
				if(contatore==0) {
					nomeStanza = scannerDiParole.next();
				}else if(contatore==1){
					direzioneBloccata = scannerDiParole.next();
				}else {
					nomeAttrezzoCheSblocca = scannerDiParole.next();
				}
				contatore++;
			}
			this.builder.addStanzaBloccata(nomeStanza, direzioneBloccata, nomeAttrezzoCheSblocca);
		}
	}
	
	public void leggiEImpostaStanzaMagica(List<String> parole) {
		for(int i=1; i<parole.size(); i++) {
			this.builder.addstanzaMagica(parole.get(i));
		}
	}
	
	public void leggiEImpostaStanzaMagicaConSoglia(List<String> parole) {
		int contatore;
		for(int i=1; i<parole.size(); i++) {
			contatore=0;
			String nomeStanza = null;
			int sogliaMagica = 0;
			Scanner scannerDiParole = new Scanner(parole.get(i));
			while(scannerDiParole.hasNext()) {
				if(contatore==0) {
					nomeStanza = scannerDiParole.next();
				}else {
					sogliaMagica = Integer.parseInt(scannerDiParole.next());
				}
				contatore++;
			}
			this.builder.addStanzaMagicaConSoglia(nomeStanza, sogliaMagica);
		}
	}
}
