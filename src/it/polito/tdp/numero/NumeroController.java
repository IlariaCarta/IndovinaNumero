package it.polito.tdp.numero;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.numero.model.NumeroModel;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class NumeroController {

	
	private NumeroModel model; //riferimento del modello creato nell'application

	
	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private HBox boxControllopartita;

	@FXML
	private TextField txtRimasti;
	// numero di tentativi rimasti ancora da provare

	@FXML
	private HBox boxControlloTentativi;

	@FXML
	private TextField txtTentativo;
	// tentativo inserito dall'utente

	@FXML
	private TextArea txtMessaggi;

	@FXML
	void handleNuovaPartita(ActionEvent event) {
		// Gestisce l'inizio di una nuova partita

		// Gestione dell'interfaccia
		boxControllopartita.setDisable(true);
		boxControlloTentativi.setDisable(false);
		txtMessaggi.clear();
		txtTentativo.clear();
		txtRimasti.setText(Integer.toString(model.getTMAX()));
		
		//comunico al modello di inizare una nuova partita
		model.newGame();

	}

	@FXML
	void handleProvaTentativo(ActionEvent event) {

		// Leggi il valore del tentativo
		String ts = txtTentativo.getText();

		// Controlla se è valido (il tipo di dato). Il modello richiede par di tipo giusto

		int tentativo ;
		try {
			tentativo = Integer.parseInt(ts);
		} catch (NumberFormatException e) {
			// la stringa inserita non è un numero valido
			txtMessaggi.appendText("Non è un numero valido\n");
			return ;
		}
		
		if(!model.tentativoValido(tentativo)) {
			txtMessaggi.appendText("Range non valido \n");
			return ;
		}
		
		int risultato = model.tentativo(tentativo);
		
		if(risultato == 0) {
			txtMessaggi.appendText("Complimenti, hai indovinato in "+model.getTentativiFatti()+" tentativi\n");
			boxControllopartita.setDisable(false);
			boxControlloTentativi.setDisable(true);		
			}
		else if (risultato < 0) {
			txtMessaggi.appendText("Tentativo troppo basso\n");
		}
			
			else {
					txtMessaggi.appendText("Tentativo troppo alto\n");
				
			}
			
		// Aggiornare interfaccia con n. tentativi rimasti
		txtRimasti.setText(Integer.toString(model.getTMAX()-model.getTentativiFatti()));
		
		if(!model.isInGioco()) {
			// la partita è finita!
			if(risultato!=0) {
				txtMessaggi.appendText("Hai perso!");
				txtMessaggi.appendText("\n il numero segreto era :"+model.getSegreto());
				boxControllopartita.setDisable(false);
				boxControlloTentativi.setDisable(true);
			}
		}
	}
	
	public NumeroModel getModel() {
		return model;
	}

	public void setModel(NumeroModel model) {
		this.model = model;
		txtRimasti.txtProperty().bind(Bindings.convert(model.tentativiFattiProperty()));
	}

	@FXML
	void initialize() {
		assert boxControllopartita != null : "fx:id=\"boxControllopartita\" was not injected: check your FXML file 'Numero.fxml'.";
		assert txtRimasti != null : "fx:id=\"txtRimasti\" was not injected: check your FXML file 'Numero.fxml'.";
		assert boxControlloTentativi != null : "fx:id=\"boxControlloTentativi\" was not injected: check your FXML file 'Numero.fxml'.";
		assert txtTentativo != null : "fx:id=\"txtTentativo\" was not injected: check your FXML file 'Numero.fxml'.";
		assert txtMessaggi != null : "fx:id=\"txtMessaggi\" was not injected: check your FXML file 'Numero.fxml'.";
		
		//Model = new Model(); questo metodo non va bene, xkè nel caso di + controllers bisognerebbe crearne uno identico per ogni controller
		// bisogna utilizzare la classe application, xkè è dfinita una sola volta.
	}
}
