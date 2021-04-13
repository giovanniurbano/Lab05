package it.polito.tdp.model;

import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.db.AnagrammaDAO;

public class Model {
	AnagrammaDAO dao;
	
	public Model() {
		dao = new AnagrammaDAO();
	}
	
	public List<List<String>> anagrammi(String parola) {
		List<String> corretti = new ArrayList<String>();
		List<String> errati = new ArrayList<String>();
		
		permuta("", parola, 0, corretti, errati); 
		
		List<List<String>> risultato = new ArrayList<>();
		risultato.add(corretti);
		risultato.add(errati);
		
		return risultato;
	}

	private void permuta(String parziale, String lettere, int livello, List<String> corretti, List<String> errati) {
		if(lettere.length() == 0) {
			if(dao.isCorrect(parziale) && !corretti.contains(parziale))
				corretti.add(parziale);
			if(!dao.isCorrect(parziale) && !errati.contains(parziale))
				errati.add(parziale);
		} 
		else {
			for(int pos=0; pos<lettere.length(); pos++) {
				
				char tentativo = lettere.charAt(pos); 
				
				String nuovaParziale = parziale + tentativo;
				String nuovaLettere = lettere.substring(0, pos) + lettere.substring(pos+1);
				
				if(dao.isRootCorrect(nuovaParziale))
					permuta(nuovaParziale, nuovaLettere, livello+1, corretti, errati);
				
			}
		}
	}
	
}
