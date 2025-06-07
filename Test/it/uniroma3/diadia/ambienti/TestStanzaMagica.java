package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class TestStanzaMagica {

    private StanzaMagica stanzaMagica;
    private final int soglia = 2; // per test controllabili

    @Before
    public void setUp() {
        stanzaMagica = new StanzaMagica("Stanza Magica", soglia);
    }

    @Test
    public void testAddAttrezziPrimaDellaSoglia_nonModificati() {
        Attrezzo a1 = new Attrezzo("libro", 1);
        Attrezzo a2 = new Attrezzo("penna", 2);

        assertTrue(stanzaMagica.addAttrezzo(a1));
        assertTrue(stanzaMagica.addAttrezzo(a2));

        assertNotNull(stanzaMagica.getAttrezzo("libro"));
        assertNotNull(stanzaMagica.getAttrezzo("penna"));
        assertEquals(1, stanzaMagica.getAttrezzo("libro").getPeso());
    }

    @Test
    public void testAddAttrezzoSuperataSoglia_attrezzoModificato() {
        stanzaMagica.addAttrezzo(new Attrezzo("uno", 1));
        stanzaMagica.addAttrezzo(new Attrezzo("due", 1));
        stanzaMagica.addAttrezzo(new Attrezzo("tre", 2)); // il terzo -> modifica

        Attrezzo attrezzoModificato = stanzaMagica.getAttrezzo("ert"); // "tre" invertito

        assertNotNull(attrezzoModificato);
        assertEquals("ert", attrezzoModificato.getNome());
        assertEquals(4, attrezzoModificato.getPeso()); // peso 2 * 2
    }


    @Test
    public void testToStringContieneAuraMagica() {
        String descrizione = stanzaMagica.toString();
        assertTrue(descrizione.contains("strana aura"));
    }

    @Test
    public void testContatoreAttrezziAumentaSoloSeAggiunto() {
        Attrezzo attrezzo = new Attrezzo("zaino", 1);

        assertTrue(stanzaMagica.addAttrezzo(attrezzo));
        assertTrue(stanzaMagica.getAttrezzo("zaino") != null);

    }
}
