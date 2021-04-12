package it.polito.tdp.model;

import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.db.AnagrammaDAO;

public class Model {
	AnagrammaDAO dao;
	private List<String> corretti;
	private List<String> errati;
	
	public Model() {
		dao = new AnagrammaDAO();
	}
	
	public List<List<String>> anagrammi(String parola) {
		List<String> corretti = new ArrayList<String>();
		List<String> errati = new ArrayList<String>();
		permuta("", parola, 0, corretti, errati); // LANCIA la ricorsione
		//rimuovo dalla lista parole che non esistono nel dizionario
		List<List<String>> risultato = new ArrayList<>();
		risultato.add(corretti);
		risultato.add(errati);
		return risultato;
	}
	
	// livello = lunghezza della soluzione parziale
	// livello iniziale = 0
	// parziale = stringa che contiene l'anagramma incompleto in fase di costruzione
	// lettere = le lettere della parola iniziale che ancora non abbiamo utilizzato
	//           (=== il sottoproblema che dobbiamo risolvere)
	
	private void permuta(String parziale, String lettere, int livello, List<String> corretti, List<String> errati) {
		if(lettere.length() == 0) { // caso terminale 
			// la soluzione parziale è anche una soluzione completa
			//System.out.println(parziale);
			//if(parziale è ancora parola valida)
			if(dao.isCorrect(parziale))
				corretti.add(parziale);
			else
				errati.add(parziale);
		} 
		else {
			// fai ricorsione
			// sottoproblema == una lettera (un singolo carattere) di 'lettere'
			for(int pos=0; pos<lettere.length(); pos++) {
				
				char tentativo = lettere.charAt(pos); 
				
				String nuovaParziale = parziale + tentativo;
				String nuovaLettere = lettere.substring(0, pos) + lettere.substring(pos+1); // toglie il carattere pos da lettere
				
				//if(nuovaParziale è PREFISSO valido di almeno una parola nel dizionario)
				if(dao.isRootCorrect(nuovaParziale))
					permuta(nuovaParziale, nuovaLettere, livello+1, corretti, errati);
				
				// Backtracking (NON SERVE)
				// rimetti a posto 'parziale'
				// rimetti a posto 'lettere'
				
			}
		}
	}
	
}
