package edu.westga.cs.babble.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestTileRackAppend {
	
	private TileRack tileRack;
	
	@BeforeEach
	public void setUp() {
		this.tileRack = new TileRack();
	}
	
	@Test
	public void shouldNotAppendToFullRack() {
		Tile tile1 = new Tile('A');
		Tile tile2 = new Tile('B');
		Tile tile3 = new Tile('C');
		Tile tile4 = new Tile('D');
		Tile tile5 = new Tile('E');
		Tile tile6 = new Tile('F');
		Tile tile7 = new Tile('G');
		Tile tile8 = new Tile('H');
		
		Tile[] tilesToAdd = {tile1, tile2, tile3, tile4, tile5, tile6, tile7};
		for (Tile tile: tilesToAdd) {
			this.tileRack.append(tile);
		}
		
		assertEquals(7, this.tileRack.tiles().size());
		assertEquals("ABCDEFG", this.tileRack.getHand());
		assertThrows(TileRackFullException.class, () -> this.tileRack.append(tile8));
	}

}
