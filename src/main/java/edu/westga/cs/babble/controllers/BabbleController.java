package edu.westga.cs.babble.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import edu.westga.cs.babble.model.EmptyTileBagException;
import edu.westga.cs.babble.model.PlayedWord;
import edu.westga.cs.babble.model.Tile;
import edu.westga.cs.babble.model.TileBag;
import edu.westga.cs.babble.model.TileNotInGroupException;
import edu.westga.cs.babble.model.TileRack;
import edu.westga.cs.babble.model.TileRackFullException;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

public class BabbleController implements Initializable {

    @FXML
    private ListView<Tile> gameTilesLV;
    
    @FXML
    private ListView<Tile> wordLV;

    @FXML
    private Button playButton;

    @FXML
    private Button resetButton;

    @FXML
    private TextField scoreTF;
    
    private TileRack tileRack;
    private TileBag tileBag;
    private PlayedWord playedWord;
    private SimpleIntegerProperty score;
    private int totalScore;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	this.tileRack = new TileRack();
    	this.tileBag = new TileBag();
    	this.playedWord = new PlayedWord();
    	this.score = new SimpleIntegerProperty(0);
    	this.totalScore = 0;
    	this.scoreTF.textProperty().bind(this.score.asString());
    	this.createTileRack();
    }
    
    private void createTileRack() {
    	while (this.tileRack.getNumberOfTilesNeeded() > 0) {
    		try {
				this.tileRack.append(this.tileBag.drawTile());
			} catch (TileRackFullException e) {
				return;
			} catch (EmptyTileBagException e) {
				return;
			}
    	}
    	this.gameTilesLV.setItems(this.tileRack.tiles());
    	this.setRack();
    	this.playedWord.clear();
    }
    
    public void handleTileClick(MouseEvent event) {
    	Tile selectedTile = gameTilesLV.getSelectionModel().getSelectedItem();
    	if (selectedTile != null) {
    		try {
    			this.tileRack.remove(selectedTile);
    			this.playedWord.append(selectedTile);
    		} catch (TileRackFullException e) {
				return;
			} catch (TileNotInGroupException e) {
				return;
			}
    	}
		this.gameTilesLV.setItems(this.tileRack.tiles());
    	this.setRack();
    	this.wordLV.setItems(this.playedWord.tiles());
    	this.setWord();
    }
    
    public void handleWordClick(MouseEvent event) {
    	Tile selectedTile = wordLV.getSelectionModel().getSelectedItem();
    	if (selectedTile != null) {
    		try {
    			this.playedWord.remove(selectedTile);
    			this.tileRack.append(selectedTile);
    		} catch (TileRackFullException e) {
				return;
			} catch (TileNotInGroupException e) {
				return;
			}
    	}
		this.gameTilesLV.setItems(this.tileRack.tiles());
    	this.setRack();
		this.wordLV.setItems(this.playedWord.tiles());
    	this.setWord();
    }
    
    public void play(MouseEvent event) {
    	WordDictionary dictionary = new WordDictionary();
    	if (dictionary.isValidWord(this.playedWord.getHand())) {
    		this.updateScore();
        	this.createTileRack();
    	} else {
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("Babble Message");
    		alert.setHeaderText("Message:");
    		alert.setContentText(this.playedWord.getHand() + " is not a valid word.");
    		alert.showAndWait();
    	}
    }

    public void reset(MouseEvent event) {
    	for(Tile tile : this.playedWord.tiles()) {
    		this.tileRack.append(tile);
    	}
		this.gameTilesLV.setItems(this.tileRack.tiles());
    	this.setRack();
    	this.playedWord.clear();
    	this.setWord();
    }
    
    private void updateScore() {
    	this.totalScore += this.playedWord.getScore();
    	this.score.set(this.totalScore);
    }
    
    private void setRack() {
    	this.gameTilesLV.setCellFactory(new Callback<ListView<Tile>, ListCell<Tile>>() {
    		@Override
    		public ListCell<Tile> call(ListView<Tile> tile) {
    			return new TileCell();
    		}
    	});
    }
    
    private void setWord() {
    	this.wordLV.setCellFactory(new Callback<ListView<Tile>, ListCell<Tile>>() {
    		@Override
    		public ListCell<Tile> call(ListView<Tile> tile) {
    			return new TileCell();
    		}
    	});
    }
    
    static class TileCell extends ListCell<Tile> {
    	@Override
    	public void updateItem(Tile item, boolean empty) {
    		super.updateItem(item, empty);
    		if (item != null) {
    			super.setText(String.valueOf(item.getLetter()));
    			super.setAccessibleText(String.valueOf(item.getLetter()));
    		}
    	}
    }
    
}