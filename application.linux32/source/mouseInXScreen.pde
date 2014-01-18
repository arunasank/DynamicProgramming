void mouseInWelcomeScreen()
{
  if (mouseX > width/8 && mouseX < (width/8 + 136) && mouseY > height/2 && mouseY < (height/2 + 118))
  {
    AI = true;
    programState = gameScreen;
  }
  if (mouseX > width/2 && mouseX < (width/2 + 136) && mouseY > height/2 && mouseY < (height/2 + 118))
  {
    programState = gameScreen;
  }
}

void mouseInGameScreen()
{
  PShape person = loadShape("data/person.svg");

  if (mouseX > width/2 && mouseX < (width/2 + 100) && mouseY > 40 && mouseY < 110)
  {
    programState = playing;
    turn ='1';

    if (!AI)
    {
      strokeWeight(2);
      fill(255);
      rect(width - 125, 5, 75, 75);
      shape(person, width - 125, 5, 75, 75);
    }
    mouseInPlayingScreen(true);
  }
    
}

void mouseInPlayingScreen(boolean flag)
{
  int x = mouseX - 50, y = mouseY - 100;
  color redSquareColour = color(166, 99, 93);
  color yellowSquareColour = color(239, 217, 97);
  color blueSquareColour = color(104, 184, 223);
  color greenSquareColour = color(181, 217, 84);
  int j = 0;
  PShape star = loadShape("data/star.svg");

  if (flag)
  {
    frame.setResizable(false);
    flag = false;
  }

  if (perTurn <= 3 && get(mouseX, mouseY) == redSquareColour && mouseButton == LEFT && turn == '1' && totalSquareCount != 1)
  {
    recordSelectedSquare(floor(x/80), floor(y/80), 'L');
    fill(blueSquareColour);
    rect(floor(x/80)*80 + 50, floor(y/80)*80 + 100, 40, 40);
    perTurn ++;
  }
  else if (perTurn <= 3 && get(mouseX, mouseY) == redSquareColour && mouseButton == LEFT && turn == '2' && totalSquareCount != 1)
  {
    recordSelectedSquare(floor(x/80), floor(y/80), 'L');
    fill(greenSquareColour);
    rect(floor(x/80)*80 + 50, floor(y/80)*80 + 100, 40, 40);
    perTurn ++;
  }

  if (perTurn > 0 && turn == '1' && get(mouseX, mouseY) == blueSquareColour && mouseButton == RIGHT)
  {
    recordSelectedSquare(floor(x/80), floor(y/80), 'R');
    fill(redSquareColour);
    rect(floor(x/80)*80 + 50, floor(y/80)*80 + 100, 40, 40);
    perTurn --;
  }
  else if (perTurn > 0 && turn == '2' && get(mouseX, mouseY) == greenSquareColour && mouseButton == RIGHT)
  {
    recordSelectedSquare(floor(x/80), floor(y/80), 'R');
    fill(redSquareColour);
    rect(floor(x/80)*80 + 50, floor(y/80)*80 + 100, 40, 40);
    perTurn --;
  }

  if (turn == '1' && mouseX>width - 125 && mouseX < width - 50 && mouseY > 5 && mouseY < 80)
  {
    print("turn 1 " + perTurn);
    turn = '2';
    switchPlayer2();
  }
  j = 0;
  if (turn == '2' && mouseX > 50 && mouseX < 125 && mouseY > 5 && mouseY < 80)
  {
    print("turn 2 " + perTurn);
    turn = '1';
    switchPlayer1();
  }
    if (totalSquareCount == 1)
    {
      switch(turn)
      {
        case'1':
           shape(star, 78, 38, 20, 20);
          break;
        case '2':
          shape(star, width - 97, 38, 20, 20);
      }
    }
}
