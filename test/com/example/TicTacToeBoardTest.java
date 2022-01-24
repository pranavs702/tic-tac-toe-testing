package com.example;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests the evaluate() function of TicTacToeBoard.java assuming that only square boards are valid.
 */
public class TicTacToeBoardTest {

  /**
   * Test if the correct result is returned when there is no winner but the board is valid.
   */
  @Test
  public void testValidBoardNoWinner() {
    TicTacToeBoard board = new TicTacToeBoard("O...X.X..");
    assertEquals(Evaluation.NoWinner, board.evaluate());
  }

  /**
   * Test if the correct result is returned when the board is invalid due to there being a
   * difference between the amount of x and o marks that is greater than one.
   */
  @Test
  public void testInvalidBoardLargeDifferenceXO() {
    TicTacToeBoard board = new TicTacToeBoard("OOO.XOX..");
    assertEquals(Evaluation.UnreachableState, board.evaluate());
  }

  /**
   * Test if the correct result is returned when the board is invalid due to both x and o side
   * winning with the board arrangement.
   */
  @Test
  public void testInvalidBoardBothWinners() {
    TicTacToeBoard board = new TicTacToeBoard("OOO.XOXXX");
    assertEquals(Evaluation.UnreachableState, board.evaluate());
  }

  /**
   * Test if the correct result is returned when the board is valid and x wins.
   */
  @Test
  public void testValidBoardXWins() {
    TicTacToeBoard board = new TicTacToeBoard("O.O.O.XXX");
    assertEquals(Evaluation.Xwins, board.evaluate());
  }

  /**
   * Test if the correct result is returned when the board is valid and o wins.
   */
  @Test
  public void testValidBoardOWins() {
    TicTacToeBoard board = new TicTacToeBoard("OOOXX....");
    assertEquals(Evaluation.Owins, board.evaluate());
  }

  /**
   * Test if the correct result is returned when some side wins through completing the diagonal
   * starting top left and ending bottom right.
   */
  @Test
  public void testValidBoardWinLeftDiagonal() {
    TicTacToeBoard board = new TicTacToeBoard("OO.XOX.XO");
    assertEquals(Evaluation.Owins, board.evaluate());
  }

  /**
   * Test if the correct result is returned when some side wins through completing the diagonal
   * starting top right and ending bottom left.
   */
  @Test
  public void testValidBoardWinRightDiagonal() {
    TicTacToeBoard board = new TicTacToeBoard("O.XOX.XOO");
    assertEquals(Evaluation.Xwins, board.evaluate());
  }

  /**
   * Test if the correct result is returned when some side wins by completing any column.
   */
  @Test
  public void testValidBoardWinColumns() {
    // first column
    TicTacToeBoard board = new TicTacToeBoard("O.XOX.OXO");
    assertEquals(Evaluation.Owins, board.evaluate());
    // second column
    board = new TicTacToeBoard(".OX.O..OX");
    assertEquals(Evaluation.Owins, board.evaluate());
    // third column
    board = new TicTacToeBoard(".XO.XO..O");
    assertEquals(Evaluation.Owins, board.evaluate());
  }

  /**
   * Test if the correct result is returned when some side wins by completing any row.
   */
  @Test
  public void testValidBoardWinRows() {
    // first row
    TicTacToeBoard board = new TicTacToeBoard("OOOXX.X..");
    assertEquals(Evaluation.Owins, board.evaluate());
    // second row
    board = new TicTacToeBoard(".XXOOO.XX");
    assertEquals(Evaluation.Owins, board.evaluate());
    // third row
    board = new TicTacToeBoard(".X.XX.OOO");
    assertEquals(Evaluation.Owins, board.evaluate());
  }

  /**
   * Test if the correct result is returned when the board length given implies that the board is
   * not square (which we assume it is).
   */
  // learned how to write this test from https://www.baeldung.com/junit-assert-exception
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidBoardLengthException() {
    TicTacToeBoard board = new TicTacToeBoard("O.XOX.OXO.");
    board.evaluate();
  }

  /**
   * Test if the correct result is returned when the string passed to TicTacToeBoard has both upper
   * and lowercase x and o.
   */
  @Test
  public void testEvaluatingLowerCaseAndUpperCase() {
    TicTacToeBoard board = new TicTacToeBoard("O.XoX.OxO");
    assertEquals(Evaluation.Owins, board.evaluate());
  }

  /**
   * Test if the correct result is returned when the string passed to TicTacToeBoard has random
   * characters as place holders rather than ".".
   */
  @Test
  public void testEvaluatingVariousEmptyPlaceholder() {
    TicTacToeBoard board = new TicTacToeBoard("OaXoX*OxO");
    assertEquals(Evaluation.Owins, board.evaluate());
  }
}
