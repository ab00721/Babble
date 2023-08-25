package edu.westga.cs.babble.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestPlayedWordMatches {
	
	private PlayedWord playedWord;
	
	@BeforeEach
	public void setUp() {
		this.playedWord = new PlayedWord();
	}
	
	@Test
	public void hasTilesForAMultipleLetterWord() {	
		Tile tile1 = new Tile('R');
		Tile tile2 = new Tile('E');
		Tile tile3 = new Tile('D');
		
		Tile[] tilesToAdd = {tile1, tile2, tile3};
		for (Tile tile: tilesToAdd) {
			this.playedWord.append(tile);
		}

		assertTrue(this.playedWord.matches("RED"));
	}
	
	@Test
	public void hasTilesForASingleLetterWord() {	
		Tile tile1 = new Tile('A');
		this.playedWord.append(tile1);		

		assertTrue(this.playedWord.matches("A"));
	}
	
	@Test
	public void cannotMatchWordWhenTilesAreScrambled() {	
		Tile tile1 = new Tile('D');
		Tile tile2 = new Tile('R');
		Tile tile3 = new Tile('E');
		
		Tile[] tilesToAdd = {tile1, tile2, tile3};
		for (Tile tile: tilesToAdd) {
			this.playedWord.append(tile);
		}

		assertFalse(this.playedWord.matches("RED"));
	}
	
	@Test
	public void cannotMatchWordIfInsufficientTiles() {	
		Tile tile1 = new Tile('G');
		Tile tile2 = new Tile('R');
		Tile tile3 = new Tile('E');
		Tile tile4 = new Tile('N');
		
		Tile[] tilesToAdd = {tile1, tile2, tile3, tile4};
		for (Tile tile: tilesToAdd) {
			this.playedWord.append(tile);
		}

		assertFalse(this.playedWord.matches("GREEN"));
	}
	
	@Test
	public void canMatchWordWithDuplicateLetters() {	
		Tile tile1 = new Tile('G');
		Tile tile2 = new Tile('R');
		Tile tile3 = new Tile('E');
		Tile tile4 = new Tile('E');
		Tile tile5 = new Tile('N');
		
		Tile[] tilesToAdd = {tile1, tile2, tile3, tile4, tile5};
		for (Tile tile: tilesToAdd) {
			this.playedWord.append(tile);
		}

		assertTrue(this.playedWord.matches("GREEN"));
	}
	
	@Test
	public void nonEmptyWordShouldNotMatchEmptyText() {	
		Tile tile1 = new Tile('G');
		Tile tile2 = new Tile('R');
		Tile tile3 = new Tile('E');
		Tile tile4 = new Tile('E');
		Tile tile5 = new Tile('N');
		
		Tile[] tilesToAdd = {tile1, tile2, tile3, tile4, tile5};
		for (Tile tile: tilesToAdd) {
			this.playedWord.append(tile);
		}

		assertFalse(this.playedWord.matches(""));
	}
	
	@Test
	public void emptyWordShouldNotMatchEmptyText() {	
		assertTrue(this.playedWord.tiles().isEmpty());
		assertFalse(this.playedWord.matches(""));
	}
	
	@Test
	public void shouldNotAllowNull() {	
		assertThrows(IllegalArgumentException.class, () -> this.playedWord.matches(null));
	}

}
