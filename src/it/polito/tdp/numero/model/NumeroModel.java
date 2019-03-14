package it.polito.tdp.numero.model;

import java.security.InvalidParameterException;
import java.util.LinkedList;
import java.util.List;

public class NumeroModel {

	private final int NMAX = 100;
	private final int TMAX = 8;

	private List <Integer> tentativi;
	private int segreto;
	private int tentativiFatti;
	private boolean inGioco = false;
	
	public NumeroModel() {
		inGioco = false;
		tentativi = new LinkedList <Integer>();
	}
	
	/**
	 * Avvia nuova partita
	 */
	public void newGame() {
		inGioco = true;
		this.segreto = (int) (Math.random() * NMAX) + 1;
		this.tentativiFatti = 0;
		this.inGioco = true;
		this.tentativi = new LinkedList <Integer>();

	}
	
	public int tentativo (int t) {
		
		// controllo se la partita è in corso
		if(!inGioco) {
			throw new IllegalStateException("La partita è terminata");
		}
		
		//controllo se l'input è nel range corrretto
		if(!tentativoValido(t)) {
			throw new InvalidParameterException(String.format("Devi inserirre un numero"+"tra %d e %d", 1, NMAX));
		}
		
		
		//gestisci tentativo
		
		this.tentativiFatti++;
		this.tentativi.add(t);
		
		if(this.tentativiFatti== this.TMAX) {
			//la partita è finita perchè ho esaurito i tentativi
			this.inGioco = false;
		}
		if(t == this.segreto) {
			this.inGioco=false;
			return 0;
		}
		
		if(t> this.segreto) {
			return 1; // tentativo troppo alto
		}
		
		return -1; // tentativo troppo basso
		
	}
	
	public boolean tentativoValido(int t) {
		if(t<1 || t>NMAX) {
			return false;
		}
			else {
				if(this.tentativi.contains(t))
					return false;
				else
					return true;
			}
		
	}

	public int getSegreto() {
		return segreto;
	}

	public int getTentativiFatti() {
		return tentativiFatti;
	}

	public boolean isInGioco() {
		return inGioco;
	}

	public int getTMAX() {
		return TMAX;
	}
	
	
	
}
