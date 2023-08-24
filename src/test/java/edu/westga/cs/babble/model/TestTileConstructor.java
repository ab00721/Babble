package edu.westga.cs.babble.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class TestTileConstructor {
	
	@Test
	public void shouldNotAllowNonLetters() {
		assertThrows(IllegalArgumentException.class, () -> new Tile('3'));
	}
	
	@Test
	public void shouldCreateOnePointTiles() {
		String onePointLetters = "EAIONRTLSUeaionrtlsu";
		for (char letter : onePointLetters.toCharArray()) {
			Tile tile = new Tile(letter);
			assertEquals(1, tile.getPointValue());
		}
	}
	
	@Test
	public void shouldCreateTwoPointTiles() {
		String twoPointLetters = "DGdg";
		for (char letter : twoPointLetters.toCharArray()) {
			Tile tile = new Tile(letter);
			assertEquals(2, tile.getPointValue());
		}
	}
	
	@Test
	public void shouldCreateThreePointTiles() {
		String threePointLetters = "BCMPbcmp";
		for (char letter : threePointLetters.toCharArray()) {
			Tile tile = new Tile(letter);
			assertEquals(3, tile.getPointValue());
		}
	}
	
	@Test
	public void shouldCreateFourPointTiles() {
		String fourPointLetters = "FHVWYfhvwy";
		for (char letter : fourPointLetters.toCharArray()) {
			Tile tile = new Tile(letter);
			assertEquals(4, tile.getPointValue());
		}
	}
	
	@Test
	public void shouldCreateFivePointTiles() {
		String fivePointLetters = "Kk";
		for (char letter : fivePointLetters.toCharArray()) {
			Tile tile = new Tile(letter);
			assertEquals(5, tile.getPointValue());
		}
	}
	
	@Test
	public void shouldCreateEightPointTiles() {
		String eightPointLetters = "JXjx";
		for (char letter : eightPointLetters.toCharArray()) {
			Tile tile = new Tile(letter);
			assertEquals(8, tile.getPointValue());
		}
	}
	
	@Test
	public void shouldCreateTenPointTiles() {
		String tenPointLetters = "QZqz";
		for (char letter : tenPointLetters.toCharArray()) {
			Tile tile = new Tile(letter);
			assertEquals(10, tile.getPointValue());
		}
	}

}
