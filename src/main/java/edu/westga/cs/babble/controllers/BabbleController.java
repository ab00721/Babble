package edu.westga.cs.babble.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import static javax.swing.JOptionPane.showMessageDialog;

import edu.westga.cs.babble.model.EmptyTileBagException;
import edu.westga.cs.babble.model.PlayedWord;
import edu.westga.cs.babble.model.Tile;
import edu.westga.cs.babble.model.TileBag;
import edu.westga.cs.babble.model.TileNotInGroupException;
import edu.westga.cs.babble.model.TileRack;
import edu.westga.cs.babble.model.TileRackFullException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

public class BabbleController implements Initializable {

    @FXML
    private ListView<Tile> gameTiles;
    
    @FXML
    private ListView<Tile> word;

    @FXML
    private Button playButton;

    @FXML
    private Button resetButton;

    @FXML
    private TextField wordScore;
    
    private TileRack tileRack;
    private TileBag tileBag;
    private PlayedWord playedWord;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	this.tileRack = new TileRack();
    	this.tileBag = new TileBag();
    	this.playedWord = new PlayedWord();
    	this.createTileRack();
    }
    
    private void createTileRack() {
    	while (this.tileRack.getNumberOfTilesNeeded() > 0) {
    		try {
				this.tileRack.append(this.tileBag.drawTile());
			} catch (TileRackFullException e) {
				showMessageDialog(null, "Rack Full");
			} catch (EmptyTileBagException e) {
				showMessageDialog(null, "Game Over");
			}
    	}
    	this.gameTiles.setItems(this.tileRack.tiles());
    	this.setRack();
    }
    
    public void handleTileClick(MouseEvent event) {
    	Tile selectedTile = gameTiles.getSelectionModel().getSelectedItem();
    	if (selectedTile != null) {
    		try {
    			this.tileRack.remove(selectedTile);
    			this.playedWord.append(selectedTile);
    		} catch (TileRackFullException e) {
				showMessageDialog(null, "Rack Full");
			} catch (TileNotInGroupException e) {
				showMessageDialog(null, "Tile Not In Group");
			}
    	}
		this.gameTiles.setItems(this.tileRack.tiles());
    	this.setRack();
    	this.word.setItems(this.playedWord.tiles());
    	this.setWord();
    }
    
    public void handleWordClick(MouseEvent event) {
    	Tile selectedTile = word.getSelectionModel().getSelectedItem();
    	if (selectedTile != null) {
    		try {
    			this.playedWord.remove(selectedTile);
    			this.tileRack.append(selectedTile);
    		} catch (TileRackFullException e) {
				showMessageDialog(null, "Rack Full");
			} catch (TileNotInGroupException e) {
				showMessageDialog(null, "Tile Not In Group");
			}
    	}
		this.gameTiles.setItems(this.tileRack.tiles());
    	this.setRack();
		this.word.setItems(this.playedWord.tiles());
    	this.setWord();
    }
    
    public void play(MouseEvent event) {

    }

    public void reset(MouseEvent event) {
    	
    }
    
    private void setRack() {
    	this.gameTiles.setCellFactory(new Callback<ListView<Tile>, ListCell<Tile>>() {
    		@Override
    		public ListCell<Tile> call(ListView<Tile> tile) {
    			return new TileCell();
    		}
    	});
    }
    
    private void setWord() {
    	this.word.setCellFactory(new Callback<ListView<Tile>, ListCell<Tile>>() {
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
    			setText(String.valueOf(item.getLetter()));
    		}
    	}
    }
    
}