int totalSquareCount;
void userDefinedSetup()
{
  background(230, 220, 230);
//  thread("AIMethod");
  if (frame != null)
  {
    frame.setResizable(true);
  }
}

void gameScreenSetup()
{
  int spaceWidth = 40, squareWidth = 40;
  int Width = 0, Height = 0;
  int y = 100, x = 50;
  int yLimit = height - 50;
  int xLimit = width - 50;
  PImage ghost = loadImage("data/ghost.png");
  PShape person = loadShape("data/person.svg");
  int colCount = 0;
  int rowCount = 1;

  //Play Button
  /*****************/
  rectMode(CENTER);
  fill(239, 217, 97);
  rect(width/2, 40, 100, 70);
  fill(200, 65, 80);
  triangle(width/2 + 20, 40, width/2 - 10, 20, width/2 - 10, 60);
  /*****************/

  //Draw Squares
  rectMode(CORNER);
  while (y <= yLimit)
  {
    rowCount = width/80;
    fill(166, 99, 93);
    //    fill(116, 65, 80);
    rect(x, y, squareWidth, squareWidth);
    x += (squareWidth + spaceWidth);

    if (x > xLimit)
    {
      x = 50;
      y += spaceWidth + squareWidth;
      colCount ++;
    }
  }

  strokeWeight(2);
  fill(104, 184, 223);
  rect(50, 5, 75, 75);
  shape(person, 50, 5, 75, 75);

  if (AI)
  {
    image(ghost, width - 125, 5, 75, 75);
  }
  else
  {
    fill(181, 217, 84);
    rect(width - 125, 5, 75, 75);
    shape(person, width - 125, 5, 75, 75);
  }
  totalSquareCount = colCount * rowCount;
  rows = rowCount;
  columns = colCount;
}
