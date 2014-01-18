void recordSelectedSquare(int squareColumn, int squareRow, int whichMouseButton)
{
  int i=0, j = 0;
  switch(whichMouseButton)
  {
  case 'L':
    while (true)
    {
      if ((selectedSquare[0][j] == null || selectedSquare[0][j] == -1) && j < 3)
      {
        selectedSquare[0][j] = squareColumn;
        selectedSquare[1][j] = squareRow;
        j++;
        totalSquareCount -= 1;
        break;
      }
      else if (j < 2)
      {
        j++;
      }
      else
      {
        break;
      }
    }
    break;
  case 'R':
    for (i=0;i<3;i++)
    {
      if (selectedSquare[0][i] != null && selectedSquare[1][i] != null && selectedSquare[0][i] == squareColumn && selectedSquare[1][i] == squareRow)
      {
        selectedSquare[0][i] = -1;
        selectedSquare[1][i] = -1;
        totalSquareCount += 1;
      }
    }
    break;
  }

//  println(totalSquareCount);
}
