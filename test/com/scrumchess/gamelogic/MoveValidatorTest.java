package com.scrumchess.gamelogic;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class MoveValidatorTest {

	String startMove = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 1 1";


	@Test
	public void testSetMove() {
		MoveValidator moveValidator = MoveValidator.createWithFen("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 1 1");
		String move = "e2e4";
		assertTrue(moveValidator.setMove(move));
		moveValidator = MoveValidator.createWithFen("rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq - 0 1");
		assertTrue(!moveValidator.isWhiteTurn());
	}

	@Test
	public void testDoMove() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetFen() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsWhiteTurn() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsEndGame() {
		fail("Not yet implemented");
	}

}
