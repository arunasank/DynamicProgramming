void AIMethod() //<>//
{
  PShape star = loadShape("data/star.svg");
//  println("In AI Method: turn " + turn);
  int rowSquareNumber, colSquareNumber;
  color redSquareColour = color(166, 99, 93);
  int AISquareCount = 0;
  int tempTotalCount;
  color greenSquareColour = color(181, 217, 84);
  int j = 0;
  int i = 55;
  try
  {
    while (j < (perTurn - 1) && selectedSquare[0][j] != null && selectedSquare[0][j] != -1)
    {
      fill(255);
      rect(selectedSquare[0][j]*80 + 50, selectedSquare[1][j]*80 + 100, 40, 40);
      fill(104, 184, 223, 90);
      rect(selectedSquare[0][j]*80 + 50, selectedSquare[1][j]*80 + 100, 40, 40);
      selectedSquare[0][j] = -1;
      selectedSquare[1][j] = -1;
      j++;
    }

//    println("Finished 1st while loop");
  }
  catch(Exception e) { 
//    println(e);
  }

  if (totalSquareCount % 4 == 1)
  {
    AISquareCount = (int)random(2) + 1;
//    println("In Not favourable group. AISquareCount =" + AISquareCount);
  }
  else
  {
    println(perTurn);
    tempTotalCount = totalSquareCount;
    while(tempTotalCount % 4 !=1)
    {
      AISquareCount += 1;
      tempTotalCount -= 1;
    }
  }
  try
  {
    while (AISquareCount > 0)
    {
      rowSquareNumber = (int)random(rows);
      colSquareNumber = (int)random(columns);
//      println("rows " + rows +" columns " + columns);
//      println("rowSquareNumber " + rowSquareNumber + "colsquareNumber " + colSquareNumber);
      while (get ((rowSquareNumber * 80 + 70), (colSquareNumber * 80 + 120)) != redSquareColour)
      {
//        println("totalSquare Count " + totalSquareCount + "rowSquareNumber " + (rowSquareNumber) + "colSquareNumber "+ (colSquareNumber));
        rowSquareNumber = (int)random(rows);
        colSquareNumber = (int)random(columns);
      }
      if (get ((rowSquareNumber * 80 + 20 + 50), (colSquareNumber * 80 + 20 + 100)) == redSquareColour)
      {
        recordSelectedSquare(rowSquareNumber, colSquareNumber, 'L');
        fill(greenSquareColour);
        rect((rowSquareNumber*80 + 50), (colSquareNumber*80 + 100), 40, 40);
        startTime = millis();
        bool = true;
        redraw();
        AISquareCount --;
      }
    }
    
    if(totalSquareCount == 1)
    {
      shape(star, width - 97, 38, 20, 20);
      totalSquareCount = 0;
    }
    
    turn = '1';
    switchPlayer1();
  }
  catch(Exception e) { 
//    println(e);
  }
}
