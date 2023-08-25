package edu.westga.cs.babble.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestTileRackGetNumberOfTilesNeeded {
private TileRack tileRack;
	
	@BeforeEach
	public void setUp() {
		this.tileRack = new TileRack();
	}
	
	@Test
	public void emptyTileRackShouldNeedMaxSizeNumberOfTiles() {	
		assertTrue(this.tileRack.tiles().isEmpty());
		assertEquals(TileRack.MAX_SIZE, this.tileRack.getNumberOfTilesNeeded());
	}
	
	@Test
	public void tileRackWithOneTileShouldNeedMaxSizeMinusOneTiles() {
		Tile tile1 = new Tile('A');
		this.tileRack.append(tile1);
		
		assertEquals(1, this.tileRack.tiles().size());
		assertEquals("A", this.tileRack.getHand());
		assertEquals(TileRack.MAX_SIZE - 1, this.tileRack.getNumberOfTilesNeeded());
	}
	
	@Test
	public void tileRackWithSeveralTilesShouldNeedSomeTiles() {
		Tile tile1 = new Tile('A');
		Tile tile2 = new Tile('B');
		Tile tile3 = new Tile('C');
		Tile tile4 = new Tile('D');
		Tile tile5 = new Tile('E');
		
		Tile[] tilesToAdd = {tile1, tile2, tile3, tile4, tile5};
		for (Tile tile: tilesToAdd) {
			this.tileRack.append(tile);
		}
		
		assertEquals(5, this.tileRack.tiles().size());
		assertEquals("ABCDE", this.tileRack.getHand());
		assertEquals(TileRack.MAX_SIZE - 5, this.tileRack.getNumberOfTilesNeeded());
	}
	
	@Test
	public void fullRackNeedsZeroTiles() {
		Tile tile1 = new Tile('A');
		Tile tile2 = new Tile('B');
		Tile tile3 = new Tile('C');
		Tile tile4 = new Tile('D');
		Tile tile5 = new Tile('E');
		Tile tile6 = new Tile('F');
		Tile tile7 = new Tile('G');
		
		Tile[] tilesToAdd = {tile1, tile2, tile3, tile4, tile5, tile6, tile7};
		for (Tile tile: tilesToAdd) {
			this.tileRack.append(tile);
		}
		
		assertEquals(TileRack.MAX_SIZE, this.tileRack.tiles().size());
		assertEquals("ABCDEFG", this.tileRack.getHand());
		assertEquals(0, this.tileRack.getNumberOfTilesNeeded());
	}
}
