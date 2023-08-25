package edu.westga.cs.babble.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestTileGroupAppend {
	
	private TileGroup tileGroup;
	
	@BeforeEach
	public void setUp() {
		this.tileGroup = new TestTileGroup();
	}
	
	@Test
	public void shouldNotAllowNull() {
		assertThrows(IllegalArgumentException.class, () -> this.tileGroup.append(null));
	}
	
	@Test
	public void emptyGroupShouldBeEmpty() {
		assertEquals(this.tileGroup.tiles().isEmpty(), this.tileGroup.getHand() == "");
	}
	
	@Test
	public void shouldHaveOneTileInTileGroup() {
		Tile tile1 = new Tile('A');
		this.tileGroup.append(tile1);
		assertEquals(1, this.tileGroup.tiles().size());
		assertTrue(this.tileGroup.tiles().contains(tile1));
	}
	
	@Test
	public void shouldHaveManyTilesInTileGroup() {
		Tile tile1 = new Tile('A');
		Tile tile2 = new Tile('B');
		Tile tile3 = new Tile('C');
		Tile tile4 = new Tile('D');
		this.tileGroup.append(tile1);
		this.tileGroup.append(tile2);
		this.tileGroup.append(tile3);
		this.tileGroup.append(tile4);
		assertEquals(4, this.tileGroup.tiles().size());
		assertEquals("ABCD", this.tileGroup.getHand());
	}

	@Test
	public void shouldHaveManyTilesIncludingDuplicatesInTileGroup() {
		Tile tile1 = new Tile('A');
		Tile tile2 = new Tile('B');
		Tile tile3 = new Tile('C');
		Tile tile4 = new Tile('C');
		this.tileGroup.append(tile1);
		this.tileGroup.append(tile2);
		this.tileGroup.append(tile3);
		this.tileGroup.append(tile4);
		assertEquals(4, this.tileGroup.tiles().size());
		assertEquals("ABCC", this.tileGroup.getHand());
	}
	
	@Test
	public void canNotAddSameTileTwice() {
		Tile tile1 = new Tile('A');
		this.tileGroup.append(tile1);
		assertThrows(IllegalArgumentException.class, () -> this.tileGroup.append(tile1));
	}
}
