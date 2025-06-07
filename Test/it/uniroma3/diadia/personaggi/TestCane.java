package it.uniroma3.diadia.personaggi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Giocatore;
import it.uniroma3.diadia.ambienti.Labirinto;

class TestCane {

    private Partita partita;
    private Cane cane;
    private Attrezzo cibo;
    private Attrezzo regalo;
    private Stanza stanzaCorrente;

    @BeforeEach
    public void setUp() {
        cibo = new Attrezzo("osso", 1);
        regalo = new Attrezzo("palla", 2);
        stanzaCorrente = new Stanza("Atrio");
        Labirinto labirinto = new Labirinto.LabirintoBuilder()
        	.addStanza("Atrio")
            .addStanzaIniziale("Atrio")
            .getLabirinto();
        labirinto.setStanzaCorrente(stanzaCorrente);
        partita = new Partita(labirinto);
        partita.getGiocatore().setCfu(10);;
        cane = new Cane(regalo, "osso");
    }

    @Test
    public void testAgisciDiminuisceCfu() {
        int cfuIniziali = partita.getGiocatore().getCfu();
        cane.agisci(partita);
        assertEquals(cfuIniziali - 1, partita.getGiocatore().getCfu());
    }

    @Test
    public void testRiceviRegaloConCiboPreferitoRegalaAttrezzo() {
        boolean regaloPresentePrima = stanzaCorrente.hasAttrezzo(regalo.getNome());
        assertFalse(regaloPresentePrima);
        String risultato = cane.riceviRegalo(cibo, partita);
        assertTrue(stanzaCorrente.hasAttrezzo(regalo.getNome()));
        assertEquals(10, partita.getGiocatore().getCfu());
    }

    @Test
    public void testRiceviRegaloConCiboNonPreferitoDiminuisceCfu() {
        Attrezzo ciboSbagliato = new Attrezzo("carota", 1);
        int cfuIniziali = partita.getGiocatore().getCfu();
        String risultato = cane.riceviRegalo(ciboSbagliato, partita);
        assertEquals(cfuIniziali - 1, partita.getGiocatore().getCfu());
        assertFalse(stanzaCorrente.hasAttrezzo(regalo.getNome()));
    }

    @Test
    public void testRiceviRegaloConCiboPreferitoMaStanzaPienaNonRegalaAttrezzo() {
        for(int i = 0; i < 10; i++) {
            stanzaCorrente.addAttrezzo(new Attrezzo("attrezzo" + i, 1));
        }
        int cfuIniziali = partita.getGiocatore().getCfu();
        String risultato = cane.riceviRegalo(cibo, partita);
        assertFalse(stanzaCorrente.hasAttrezzo(regalo.getNome()));
        assertEquals(cfuIniziali, partita.getGiocatore().getCfu());
    }
}
