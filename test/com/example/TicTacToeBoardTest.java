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
  public void testInvalidBoardLargeDifferenceXO(){
    TicTacToeBoard board = new TicTacToeBoard("OOO.XOX..");
    assertEquals(Evaluation.UnreachableState, board.evaluate());
  }

  @Test
  public void testInvalidBoardBothWinners(){
    TicTacToeBoard board = new TicTacToeBoard("OOO.XOXXX");
    assertEquals(Evaluation.UnreachableState, board.evaluate());
  }

  @Test
  public void testValidBoardXWins(){
    TicTacToeBoard board = new TicTacToeBoard("O.O.O.XXX");
    assertEquals(Evaluation.Xwins, board.evaluate());
  }

  @Test
  public void testValidBoardOWins(){
    TicTacToeBoard board = new TicTacToeBoard("OOOXX....");
    assertEquals(Evaluation.Owins, board.evaluate());
  }
}
