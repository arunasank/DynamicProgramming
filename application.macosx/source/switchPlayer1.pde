void switchPlayer1()
{
  PShape person = loadShape("data/person.svg");
  PShape star = loadShape("data/star.svg");
  strokeWeight(2);
  fill(104, 184, 223);
  rect(50, 5, 75, 75);
  shape(person, 50, 5, 75, 75);


  if (!AI)
  {
    fill(255);
    rect(width - 125, 5, 75, 75);
    shape(person, width - 125, 5, 75, 75);
  }

  int j =0;
  while (j<3 && selectedSquare[0][j] != null && selectedSquare[1][j] != -1)
  {
    fill(255);
    rect(selectedSquare[0][j]*80 + 50, selectedSquare[1][j]*80 + 100, 40, 40);
    fill(181, 217, 84, 90);
    rect(selectedSquare[0][j]*80 + 50, selectedSquare[1][j]*80 + 100, 40, 40);
    selectedSquare[0][j] = -1;
    selectedSquare[1][j] = -1;
    j++;
  }
  perTurn  = 1;
}

void switchPlayer2()
{
  PShape star = loadShape("data/star.svg");
  int j = 0;
  PShape person = loadShape("data/person.svg");
  fill(255);
  rect(50, 5, 75, 75);
  shape(person, 50, 5, 75, 75);

if(!AI)
  {
    j = 0;
    strokeWeight(2);
    fill(181, 217, 84);
    rect(width - 125, 5, 75, 75);
    shape(person, width - 125, 5, 75, 75);
    try
    {
      while (j < 3 && selectedSquare[0][j] != null && selectedSquare[0][j] != -1)
      {
        fill(255);
        rect(selectedSquare[0][j]*80 + 50, selectedSquare[1][j]*80 + 100, 40, 40);
        fill(104, 184, 223, 90);
        rect(selectedSquare[0][j]*80 + 50, selectedSquare[1][j]*80 + 100, 40, 40);
        selectedSquare[0][j] = -1;
        selectedSquare[1][j] = -1;
        j++;
      }
      perTurn = 1;
    }
    catch(Exception e)
    {
      print(e);
    }
  }
  else
  {
    AIMethod();
  }
}
