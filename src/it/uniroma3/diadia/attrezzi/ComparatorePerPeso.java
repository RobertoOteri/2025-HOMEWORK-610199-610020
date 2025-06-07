package it.uniroma3.diadia.attrezzi;

import java.util.Comparator;

public class ComparatorePerPeso implements Comparator<Attrezzo> {

	@Override
	public int compare(Attrezzo o1, Attrezzo o2) {
		int ritorno = o1.getPeso() - o2.getPeso();
		if(ritorno == 0) {
			ritorno = o1.getNome().compareTo(o2.getNome());
		}
		return ritorno;
	}	
}
