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
/**
 * BabbleController is the controller for the babble game
 * @author Anna Blood
 * @version CS6261
 */
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

    /**
	 * Initializes the babble controller
	 */
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
    
    /**
	 * Creates a tile rack with max tiles and clears the played word
	 */
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
    
    /**
	 * Handles Mouse Event for clicking a tile in the tile rack by moving it to the word rack
	 */
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
    
    /**
	 * Handles mouse event for clicking a tile in the word rack by moving it back to the tile Rack
	 */
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
    
    /**
	 * Handles mouse click for play button by verifying the played word is valid 
	 * and calling the updateScore method or sending an error message for invalid word
	 */
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

    /**
	 * Handles mouse click on reset button by clearing the played word and reseting the tileRack
	 */
    public void reset(MouseEvent event) {

    	for(Tile tile : this.playedWord.tiles()) {
    		this.tileRack.append(tile);
    	}

    	this.playedWord.clear();
    	this.gameTilesLV.setItems(this.tileRack.tiles());
		this.setRack();
    	this.wordLV.setItems(this.playedWord.tiles());
    	this.setWord();
    }
    
    /**
	 * Updates the score by adding the played word score to the total score
	 */
    private void updateScore() {
    	this.totalScore += this.playedWord.getScore();
    	this.score.set(this.totalScore);
    }
    
    /**
	 * Sets the cell factory of the tileRack
	 */
    private void setRack() {
    	this.gameTilesLV.setCellFactory(new Callback<ListView<Tile>, ListCell<Tile>>() {
    		@Override
    		public ListCell<Tile> call(ListView<Tile> tile) {
    			return new TileCell();
    		}
    	});
    }
    
    /**
	 * Sets the cell factory of the word rack
	 */
    private void setWord() {
    	this.wordLV.setCellFactory(new Callback<ListView<Tile>, ListCell<Tile>>() {
    		@Override
    		public ListCell<Tile> call(ListView<Tile> tile) {
    			return new TileCell();
    		}
    	});
    }
    
    /**
	 * updates the items in tile cells
	 */
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