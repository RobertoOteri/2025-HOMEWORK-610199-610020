package it.uniroma3.diadia.giocatore;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestGiocatore {

	private Giocatore giocatore1;
	private Giocatore giocatore2;
	
	@BeforeEach
	public void setUp() {
		giocatore1 = new Giocatore();
		giocatore2 = new Giocatore();
		giocatore2.setCfu(10);
	}

    @Test
    public void testCfuIniziali() {
        assertEquals(20,giocatore1.getCfu());
    }
    @Test
    public void testCfuInizialiConAssertTrue() {
        assertTrue(20 == giocatore1.getCfu());
    }
    @Test
    public void testCfuInizialiConAssertFalse() {
        assertFalse(20 != giocatore1.getCfu());
    }

    @Test
    public void testBorsaNonNulla() {
        assertNotNull(giocatore1.GetBorsa());
    }

    @Test
    public void testSetCfuAggiornatoConAssertEquals() {
        assertEquals(10, giocatore2.getCfu());
    }
    
    @Test
    public void testSetCfuConAssertTrue() {
        assertTrue(giocatore2.getCfu() != giocatore1.getCfu());
    }
    
    @Test
    public void testSetCfuConAssertFalse() {
        assertFalse(10 != giocatore2.getCfu());
    }
}
