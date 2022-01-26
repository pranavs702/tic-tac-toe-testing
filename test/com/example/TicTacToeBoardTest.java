package com.example;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests if TicTacToeBoard evaluates strings correctly assuming that only square boards are valid.
 */
public class TicTacToeBoardTest {

  /**
   * Helper method to verify if passing the string input to the TicTacToeBoard class results in evaluate() returning the correct evaluation.
   *
   * @param input String input to the TicTacToeBoard class
   * @param correct Evaluation that the TicTacToeBoard class's evaluate() method should return
   */
  public void checkEquals(String input, Evaluation correct){
    TicTacToeBoard board = new TicTacToeBoard(input);
    assertEquals(correct, board.evaluate());
  }
  /**
   * Test if the correct result is returned when there is no winner but the board is valid.
   */
  @Test
  public void testValidBoardNoWinner() {
    checkEquals("O...X.X..", Evaluation.NoWinner);
  }

  /**
   * Test if the correct result is returned when the board is invalid due to there being a
   * difference between the amount of x and o marks that is greater than one.
   */
  @Test
  public void testInvalidBoardLargeDifferenceXO() {
    checkEquals("OOO.XOX..", Evaluation.UnreachableState);
  }

  /**
   * Test if the correct result is returned when the board is invalid due to both x and o side
   * winning with the board arrangement.
   */
  @Test
  public void testInvalidBoardBothWinners() {
    checkEquals("OOO.XOXXX", Evaluation.UnreachableState);
  }

  /**
   * Test if the correct result is returned when the board is valid and x wins.
   */
  @Test
  public void testValidBoardXWins() {
    checkEquals("O.O.O.XXX", Evaluation.Xwins);
  }

  /**
   * Test if the correct result is returned when the board is valid and o wins.
   */
  @Test
  public void testValidBoardOWins() {
    checkEquals("OOOXX....", Evaluation.Owins);
  }

  /**
   * Test if the correct result is returned when some side wins through completing the diagonal
   * starting top left and ending bottom right.
   */
  @Test
  public void testValidBoardWinLeftDiagonal() {
    checkEquals("OO.XOX.XO", Evaluation.Owins);
  }

  /**
   * Test if the correct result is returned when some side wins through completing the diagonal
   * starting top right and ending bottom left.
   */
  @Test
  public void testValidBoardWinRightDiagonal() {
    checkEquals("O.XOX.XOO", Evaluation.Xwins);
  }

  /**
   * Test if the correct result is returned when some side wins by completing any column.
   */
  @Test
  public void testValidBoardWinColumns() {
    // first column
    checkEquals("O.XOX.OXO", Evaluation.Owins);

    // second column
    checkEquals(".OX.O..OX", Evaluation.Owins);

    // third column
    checkEquals(".XO.XO..O", Evaluation.Owins);
  }

  /**
   * Test if the correct result is returned when some side wins by completing any row.
   */
  @Test
  public void testValidBoardWinRows() {
    // first row
    checkEquals("OOOXX.X..", Evaluation.Owins);

    // second row
    checkEquals(".XXOOO.XX", Evaluation.Owins);

    // third row
    checkEquals(".X.XX.OOO", Evaluation.Owins);
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
    checkEquals("O.XoX.OxO", Evaluation.Owins);
  }

  /**
   * Test if the correct result is returned when the string passed to TicTacToeBoard has random
   * characters as place holders rather than ".".
   */
  @Test
  public void testEvaluatingVariousEmptyPlaceholder() {
    checkEquals("OaXoX*OxO", Evaluation.Owins);
  }
}
