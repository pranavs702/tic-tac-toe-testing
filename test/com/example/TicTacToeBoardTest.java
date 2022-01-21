package com.example;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TicTacToeBoardTest {

  @Test
  public void testValidBoardNoWinner() {
    TicTacToeBoard board = new TicTacToeBoard("O...X.X..");
    assertEquals(Evaluation.NoWinner, board.evaluate());
  }

  @Test
  public void testInvalidBoardLargeDifferenceXO() {
    TicTacToeBoard board = new TicTacToeBoard("OOO.XOX..");
    assertEquals(Evaluation.UnreachableState, board.evaluate());
  }

  @Test
  public void testInvalidBoardBothWinners() {
    TicTacToeBoard board = new TicTacToeBoard("OOO.XOXXX");
    assertEquals(Evaluation.UnreachableState, board.evaluate());
  }

  @Test
  public void testValidBoardXWins() {
    TicTacToeBoard board = new TicTacToeBoard("O.O.O.XXX");
    assertEquals(Evaluation.Xwins, board.evaluate());
  }

  @Test
  public void testValidBoardOWins() {
    TicTacToeBoard board = new TicTacToeBoard("OOOXX....");
    assertEquals(Evaluation.Owins, board.evaluate());
  }

  @Test
  public void testValidBoardWinLeftDiagonal() {
    TicTacToeBoard board = new TicTacToeBoard("OO.XOX.XO");
    assertEquals(Evaluation.Owins, board.evaluate());
  }

  @Test
  public void testValidBoardWinRightDiagonal() {
    TicTacToeBoard board = new TicTacToeBoard("O.XOX.XOO");
    assertEquals(Evaluation.Xwins, board.evaluate());
  }

  @Test
  public void testValidBoardWinColumns() {
    TicTacToeBoard board = new TicTacToeBoard("O.XOX.OXO");
    assertEquals(Evaluation.Owins, board.evaluate());
    board = new TicTacToeBoard(".OX.O..OX");
    assertEquals(Evaluation.Owins, board.evaluate());
    board = new TicTacToeBoard(".XO.XO..O");
    assertEquals(Evaluation.Owins, board.evaluate());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidBoardLengthException(){
    TicTacToeBoard board = new TicTacToeBoard("O.XOX.OXO.");
    board.evaluate();
  }

  @Test
  public void testEvaluatingLowerCaseAndUpperCase(){

  }
}
