package edu.westga.cs.babble.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestTileBagDrawTile {
	
	private TileBag tileBag;
	
	@BeforeEach
	public void setUp() {
		this.tileBag = new TileBag();
	}
	
	@Test
	public void canDrawAllTiles() throws EmptyTileBagException {
		for (int i = 0; i < 98; i++) {
			this.tileBag.drawTile();
		}
		assertTrue(this.tileBag.isEmpty());
	}
	
	@Test
	public void canNotDrawTooManyTiles() throws EmptyTileBagException {
		
		for (int i = 0; i < 98; i++) {
			this.tileBag.drawTile();
		}
		assertTrue(this.tileBag.isEmpty());
		assertThrows(EmptyTileBagException.class, () -> this.tileBag.drawTile());
	}
	
	@Test
	public void hasProperTileDistribution() throws EmptyTileBagException {
		char[] letters = {
				'E','A','I','O','N','R','T',
				'L','S','U','D','G',
				'B','C','M','P','F',
				'H','V','W','Y',
				'K','J','X','Q','Z'
		};
		
		int[] expected = {
				12,9,9,8,6,6,6,
				4,4,4,4,3,
				2,2,2,2,2,
				2,2,2,2,
				1,1,1,1,1
		};
		
		for (int i = 0; i < letters.length; i++) {
			this.tileBag = new TileBag();
			assertEquals(expected[i], this.countTiles(letters[i]));
		}
	}
	
	private int countTiles(char letter) throws EmptyTileBagException {
		int count = 0;
		while (!this.tileBag.isEmpty()) {
			if (this.tileBag.drawTile().getLetter() == letter) {
				count++;
			}
		}
		return count;
	}

}
