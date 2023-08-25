package edu.westga.cs.babble.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestPlayedWordGetScore {
	private PlayedWord playedWord;
	
	@BeforeEach
	public void setUp() {
		this.playedWord = new PlayedWord();
	}
	
	@Test
	public void emptyWordShouldHaveScoreOfZero() {	
		assertTrue(this.playedWord.tiles().isEmpty());
		assertEquals(0, this.playedWord.getScore());
	}
	
	@Test
	public void scoreAOneTileWord() {	
		Tile tile1 = new Tile('A');
		this.playedWord.append(tile1);
		assertEquals("A", this.playedWord.getHand());
		assertEquals(1, this.playedWord.getScore());
	}
	
	@Test
	public void scoreAWordWithMultipleDifferingTiles() {	
		Tile tile1 = new Tile('T');
		Tile tile2 = new Tile('I');
		Tile tile3 = new Tile('L');
		Tile tile4 = new Tile('E');
		
		Tile[] tilesToAdd = {tile1, tile2, tile3, tile4};
		for (Tile tile: tilesToAdd) {
			this.playedWord.append(tile);
		}

		assertEquals("TILE", this.playedWord.getHand());
		assertEquals(4, this.playedWord.getScore());
	}
	
	@Test
	public void scoreAWordContainingDuplicateTiles() {	
		Tile tile1 = new Tile('A');
		Tile tile2 = new Tile('P');
		Tile tile3 = new Tile('P');
		Tile tile4 = new Tile('L');		
		Tile tile5 = new Tile('E');
		
		Tile[] tilesToAdd = {tile1, tile2, tile3, tile4, tile5};
		for (Tile tile: tilesToAdd) {
			this.playedWord.append(tile);
		}

		assertEquals("APPLE", this.playedWord.getHand());
		assertEquals(9, this.playedWord.getScore());
	}
}
