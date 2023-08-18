package edu.westga.cs.babble.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import static javax.swing.JOptionPane.showMessageDialog;

import edu.westga.cs.babble.model.EmptyTileBagException;
import edu.westga.cs.babble.model.Tile;
import edu.westga.cs.babble.model.TileBag;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	this.tileRack = new TileRack();
    	this.tileBag = new TileBag();
    	this.createTileRack();
    }
    
    private void createTileRack() {
    	while (this.tileRack.getNumberOfTilesNeeded() > 0) {
    		try {
				this.tileRack.append(tileBag.drawTile());
			} catch (TileRackFullException e) {
				showMessageDialog(null, "Rack Full");
			} catch (EmptyTileBagException e) {
				showMessageDialog(null, "Game Over");
			}
    	}
    	this.gameTiles.setItems(this.tileRack.tiles());
    	this.gameTiles.setCellFactory(new Callback<ListView<Tile>, ListCell<Tile>>() {
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
    
    @FXML
    void play(MouseEvent event) {

    }

    @FXML
    void reset(MouseEvent event) {

    }
    
}