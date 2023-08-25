package edu.westga.cs.babble.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestTileGroupRemove {
	
private TileGroup tileGroup;
	
	@BeforeEach
	public void setUp() {
		this.tileGroup = new TestTileGroup();
	}
	
	@Test
	public void shouldNotAllowNull() {
		assertThrows(IllegalArgumentException.class, () -> this.tileGroup.remove(null));
	}
	
	@Test
	public void canNotRemoveFromEmptyTileGroup() {
		Tile tile1 = new Tile('A');
		assertTrue(this.tileGroup.tiles().isEmpty());
		assertThrows(TileNotInGroupException.class, () -> this.tileGroup.remove(tile1));
	}
	
	@Test
	public void canNotRemoveTileNotInTileGroup() {
		Tile tile1 = new Tile('A');
		Tile tile2 = new Tile('B');
		this.tileGroup.append(tile1);
		assertEquals("A", this.tileGroup.getHand());
		assertTrue(!this.tileGroup.tiles().contains(tile2));
		assertThrows(TileNotInGroupException.class, () -> this.tileGroup.remove(tile2));
	}
	
	@Test
	public void canRemoveOnlyTileInTileGroup() throws TileNotInGroupException {
		Tile tile1 = new Tile('A');
		this.tileGroup.append(tile1);
		assertEquals("A", this.tileGroup.getHand());
		assertTrue(!this.tileGroup.tiles().isEmpty());
		this.tileGroup.remove(tile1);
		assertEquals("", this.tileGroup.getHand());
		assertTrue(this.tileGroup.tiles().isEmpty());
	}
	
	@Test
	public void canRemoveFirstOfManyTilesFromTileGroup() throws TileNotInGroupException {
		Tile tile1 = new Tile('A');
		Tile tile2 = new Tile('B');
		Tile tile3 = new Tile('C');
		Tile tile4 = new Tile('D');
		this.tileGroup.append(tile1);
		this.tileGroup.append(tile2);
		this.tileGroup.append(tile3);
		this.tileGroup.append(tile4);
		assertEquals("ABCD", this.tileGroup.getHand());
		this.tileGroup.remove(tile1);
		assertEquals("BCD", this.tileGroup.getHand());
	}
	
	@Test
	public void canRemoveLastOfManyTilesFromTileGroup() throws TileNotInGroupException {
		Tile tile1 = new Tile('A');
		Tile tile2 = new Tile('B');
		Tile tile3 = new Tile('C');
		Tile tile4 = new Tile('D');
		this.tileGroup.append(tile1);
		this.tileGroup.append(tile2);
		this.tileGroup.append(tile3);
		this.tileGroup.append(tile4);
		assertEquals("ABCD", this.tileGroup.getHand());
		this.tileGroup.remove(tile4);
		assertEquals("ABC", this.tileGroup.getHand());
	}
	
	@Test
	public void canRemoveMiddleOfManyTilesFromTileGroup() throws TileNotInGroupException {
		Tile tile1 = new Tile('A');
		Tile tile2 = new Tile('B');
		Tile tile3 = new Tile('C');
		Tile tile4 = new Tile('D');
		this.tileGroup.append(tile1);
		this.tileGroup.append(tile2);
		this.tileGroup.append(tile3);
		this.tileGroup.append(tile4);
		assertEquals("ABCD", this.tileGroup.getHand());
		this.tileGroup.remove(tile3);
		assertEquals("ABD", this.tileGroup.getHand());
	}
	
	@Test
	public void canRemoveMultipleTilesFromTileGroup() throws TileNotInGroupException {
		Tile tile1 = new Tile('A');
		Tile tile2 = new Tile('B');
		Tile tile3 = new Tile('C');
		Tile tile4 = new Tile('D');
		this.tileGroup.append(tile1);
		this.tileGroup.append(tile2);
		this.tileGroup.append(tile3);
		this.tileGroup.append(tile4);
		assertEquals("ABCD", this.tileGroup.getHand());
		this.tileGroup.remove(tile2);
		this.tileGroup.remove(tile3);
		assertEquals("AD", this.tileGroup.getHand());
	}
	
	@Test
	public void doesNotRemoveDuplicateTilesFromTileGroup() throws TileNotInGroupException {
		Tile tile1 = new Tile('A');
		Tile tile2 = new Tile('B');
		Tile tile3 = new Tile('C');
		Tile tile4 = new Tile('C');
		this.tileGroup.append(tile1);
		this.tileGroup.append(tile2);
		this.tileGroup.append(tile3);
		this.tileGroup.append(tile4);
		assertEquals("ABCC", this.tileGroup.getHand());
		this.tileGroup.remove(tile3);
		assertEquals("ABC", this.tileGroup.getHand());
		assertTrue(this.tileGroup.tiles().contains(tile1) && this.tileGroup.tiles().contains(tile2) && !this.tileGroup.tiles().contains(tile3) && this.tileGroup.tiles().contains(tile4));
	}
}
