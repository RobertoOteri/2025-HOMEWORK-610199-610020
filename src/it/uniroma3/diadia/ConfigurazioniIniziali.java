package it.uniroma3.diadia;

import java.io.IOException;
import java.util.Properties;

public final class ConfigurazioniIniziali {
	
	private static final String DIADIA_PROPERTIES = "diadia.properties";
	private static final String PESO_MAX = "pesoMax";
	private static final String CFU = "cfu";
	private static final String NUMERO_LIVELLI = "numeroLivelli";
	private static final String NUMERO_MASSIMO_DIREZIONI = "numeroMassimoDirezioni";
	private static final String NUMERO_MASSIMO_ATTREZZI = "numeroMassimoAttrezzi";
	private static final String NUMERO_MASSIMO_PERSONAGGI = "numeroMassimoPersonaggi";
	private static final String SOGLIA_MAGICA_DEFAULT = "sogliaMagicaDefault";
	private static final String NOME_COMANDO_FINE = "nomeComandoFine";
	private static final String NOME_COMANDO_AIUTO = "nomeComandoAiuto";
	private static final String NOME_COMANDO_VAI = "nomeComandoVai";
	private static final String NOME_COMANDO_NON_VALIDO = "nomeComandoNonValido";
	private static final String NOME_COMANDO_PRENDI = "nomeComandoPrendi";
	private static final String NOME_COMANDO_POSA = "nomeComandoPosa";
	private static final String NOME_COMANDO_SALUTA = "nomeComandoSaluta";
	private static final String NOME_COMANDO_INTERAGISCI = "nomeComandoInteragisci";
	private static final String NOME_COMANDO_REGALA = "nomeComandoRegala";
	private static final String NOME_COMANDO_GUARDA = "nomeComandoGuarda";
	private static final String STANZE_MAGICHE = "magiche";
	private static final String STANZE_MAGICHE_SOGLIA = "magicheSoglia";
	private static final String STANZE_BUIE = "buie";
	private static final String STANZE_BLOCCATE = "bloccate";
	private static final String STANZE = "stanze";
	private static final String STANZA_INIZIALE = "inizio";
	private static final String STANZA_VINCENTE = "vincente";
	private static final String ATTREZZI = "attrezzi";
	private static final String USCITE = "uscite";
	private static final String MAGHI = "maghi";
	private static final String CANI = "cani";
	private static final String STREGHE = "streghe";


	private static Properties prop = null;
	
	private static void caricaCondizioniIniziali() {
		prop = new Properties();
		try {
			prop.load(ConfigurazioniIniziali.class.getClassLoader().getResourceAsStream(DIADIA_PROPERTIES));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static int getPesoMax() {
		if(prop==null) {
			caricaCondizioniIniziali();
		}
		return Integer.parseInt(prop.getProperty(PESO_MAX));
	}
	
	public static int getCFU() {
		if(prop==null) {
			caricaCondizioniIniziali();
		}
		return Integer.parseInt(prop.getProperty(CFU));
	}
	
	public static int getNumeroLivelli() {
		if(prop==null) {
			caricaCondizioniIniziali();
		}
		return Integer.parseInt(prop.getProperty(NUMERO_LIVELLI));
	}
	public static int getNumeroMassimoDirezioni() {
		if(prop==null) {
			caricaCondizioniIniziali();
		}
		return Integer.parseInt(prop.getProperty(NUMERO_MASSIMO_DIREZIONI));
	}
	
	public static int getNumeroMassimoAttrezzi() {
		if(prop==null) {
			caricaCondizioniIniziali();
		}
		return Integer.parseInt(prop.getProperty(NUMERO_MASSIMO_ATTREZZI));
	}
	
	public static int getNumeroMassimoPersonaggi() {
		if(prop==null) {
			caricaCondizioniIniziali();
		}
		return Integer.parseInt(prop.getProperty(NUMERO_MASSIMO_PERSONAGGI));
	}
	
	public static int getSogliaMagicaDefault() {
		if(prop==null) {
			caricaCondizioniIniziali();
		}
		return Integer.parseInt(prop.getProperty(SOGLIA_MAGICA_DEFAULT));
	}
	
	public static String getNomeComandoAiuto() {
		if(prop==null) {
			caricaCondizioniIniziali();
		}
		return prop.getProperty(NOME_COMANDO_AIUTO);
	}
	
	public static String getNomeComandoFine() {
		if(prop==null) {
			caricaCondizioniIniziali();
		}
		return prop.getProperty(NOME_COMANDO_FINE);
	}
	
	public static String getNomeComandoVai() {
		if(prop==null) {
			caricaCondizioniIniziali();
		}
		return prop.getProperty(NOME_COMANDO_VAI);
	}
	
	public static String getNomeComandoPrendi() {
		if(prop==null) {
			caricaCondizioniIniziali();
		}
		return prop.getProperty(NOME_COMANDO_PRENDI);
	}
	
	public static String getNomeComandoPosa() {
		if(prop==null) {
			caricaCondizioniIniziali();
		}
		return prop.getProperty(NOME_COMANDO_POSA);
	}
	
	public static String getNomeComandoSaluta() {
		if(prop==null) {
			caricaCondizioniIniziali();
		}
		return prop.getProperty(NOME_COMANDO_SALUTA);
	}
	
	public static String getNomeComandoInteragisci() {
		if(prop==null) {
			caricaCondizioniIniziali();
		}
		return prop.getProperty(NOME_COMANDO_INTERAGISCI);
	}
	
	public static String getNomeComandoNonValido() {
		if(prop==null) {
			caricaCondizioniIniziali();
		}
		return prop.getProperty(NOME_COMANDO_NON_VALIDO);
	}
	
	public static String getNomeComandoGuarda() {
		if(prop==null) {
			caricaCondizioniIniziali();
		}
		return prop.getProperty(NOME_COMANDO_GUARDA);
	}
	
	public static String getNomeComandoRegala() {
		if(prop==null) {
			caricaCondizioniIniziali();
		}
		return prop.getProperty(NOME_COMANDO_REGALA);
	}
	
	public static String getNomiStanzeMagiche() {
		if(prop==null) {
			caricaCondizioniIniziali();
		}
		return prop.getProperty(STANZE_MAGICHE);
	}
	

	public static String getNomiStanzeMagicheSoglia() {
		if(prop==null) {
			caricaCondizioniIniziali();
		}
		return prop.getProperty(STANZE_MAGICHE_SOGLIA);
	}
	
	public static String getNomiStanzeBloccate() {
		if(prop==null) {
			caricaCondizioniIniziali();
		}
		return prop.getProperty(STANZE_BLOCCATE);
	}
	
	public static String getNomiStanzeBuie() {
		if(prop==null) {
			caricaCondizioniIniziali();
		}
		return prop.getProperty(STANZE_BUIE);
	}
	
	public static String getNomiStanze() {
		if(prop==null) {
			caricaCondizioniIniziali();
		}
		return prop.getProperty(STANZE);
	}
	
	public static String getNomiStanzaIniziale() {
		if(prop==null) {
			caricaCondizioniIniziali();
		}
		return prop.getProperty(STANZA_INIZIALE);
	}
	
	public static String getNomiStanzaVincente() {
		if(prop==null) {
			caricaCondizioniIniziali();
		}
		return prop.getProperty(STANZA_VINCENTE);
	}
	
	public static String getNomiAttrezzi() {
		if(prop==null) {
			caricaCondizioniIniziali();
		}
		return prop.getProperty(ATTREZZI);
	}
	
	public static String getNomiUscite() {
		if(prop==null) {
			caricaCondizioniIniziali();
		}
		return prop.getProperty(USCITE);
	}
	
	public static String getNomiMaghi() {
		if(prop==null) {
			caricaCondizioniIniziali();
		}
		return prop.getProperty(MAGHI);
	}
	
	public static String getNomiCani() {
		if(prop==null) {
			caricaCondizioniIniziali();
		}
		return prop.getProperty(CANI);
	}
	
	public static String getNomiStreghe() {
		if(prop==null) {
			caricaCondizioniIniziali();
		}
		return prop.getProperty(STREGHE);
	}
}
