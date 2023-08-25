package edu.westga.cs.babble.model;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestPlayedWordClear {
	
	private PlayedWord playedWord;
	
	@BeforeEach
	public void setUp() {
		this.playedWord = new PlayedWord();
	}
	
	@Test
	public void shouldClearEmptyWord() {	
		assertTrue(this.playedWord.tiles().isEmpty());
		this.playedWord.clear();
		assertTrue(this.playedWord.tiles().isEmpty());		
	}
	
	@Test
	public void shouldClearWordWithOneTile() {	
		Tile tile1 = new Tile('A');
		this.playedWord.append(tile1);

		assertTrue(this.playedWord.matches("A"));
		this.playedWord.clear();
		assertTrue(this.playedWord.tiles().isEmpty());	
	}
	
	@Test
	public void shouldClearWordWithManyTiles() {	
		Tile tile1 = new Tile('R');
		Tile tile2 = new Tile('E');
		Tile tile3 = new Tile('D');
		
		Tile[] tilesToAdd = {tile1, tile2, tile3};
		for (Tile tile: tilesToAdd) {
			this.playedWord.append(tile);
		}

		assertTrue(this.playedWord.matches("RED"));
		this.playedWord.clear();
		assertTrue(this.playedWord.tiles().isEmpty());	
	}

}
