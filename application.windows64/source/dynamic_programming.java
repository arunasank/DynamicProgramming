import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.lang.Integer; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class dynamic_programming extends PApplet {


final int welcomeScreen = 1;
final int gameScreen = 2;
final int playing = 3;
final int player1 = 1;
final int player2 = 2;
int rows, columns;
boolean bool;
int startTime;

int playerState = 1;
int perTurn = 1;
char turn;
boolean AI;
Integer [][] selectedSquare = new Integer[2][3];
int programState = 1;
public void setup()
{
  size(screenWidth(), screenHeight());
//  size(window.innerWidth, window.innerHeight);
  userDefinedSetup();
}

public void draw()
{
  if (width <=380)
  {
    if (height <= 330)
      frame.setSize(390, 350);
    else
      frame.setSize(390, height);
  }
  if (height <= 330)
  {
    //    Width = (width <= 380) ? 390 : width;
    if (width <= 380)
      frame.setSize(390, 350);
    else
      frame.setSize(width, 350);
  }
  switch(programState)
  {
  case welcomeScreen :
    showWelcomeScreen();
    break;
  case gameScreen:
    showGameScreen();
    break;
  }

}

public void mousePressed()
{
  switch(programState)
  {
    case welcomeScreen:
      mouseInWelcomeScreen();
      break;
    case gameScreen:
      mouseInGameScreen();
      break;
    case playing:
//      println("mouse X " + mouseX + "mouseY " + mouseY);
      mouseInPlayingScreen(false);
      break;
      
    }  
  }

  public int screenWidth()
  {
    return (int)(displayWidth/2);
  }

  public int screenHeight()
  {
    return (int)(displayHeight * 3/4);
  }
public void AIMethod() //<>//
{
  PShape star = loadShape("data/star.svg");
//  println("In AI Method: turn " + turn);
  int rowSquareNumber, colSquareNumber;
  int redSquareColour = color(166, 99, 93);
  int AISquareCount = 0;
  int tempTotalCount;
  int greenSquareColour = color(181, 217, 84);
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
int totalSquareCount;
public void userDefinedSetup()
{
  background(230, 220, 230);
//  thread("AIMethod");
  if (frame != null)
  {
    frame.setResizable(true);
  }
}

public void gameScreenSetup()
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
public void mouseInWelcomeScreen()
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

public void mouseInGameScreen()
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

public void mouseInPlayingScreen(boolean flag)
{
  int x = mouseX - 50, y = mouseY - 100;
  int redSquareColour = color(166, 99, 93);
  int yellowSquareColour = color(239, 217, 97);
  int blueSquareColour = color(104, 184, 223);
  int greenSquareColour = color(181, 217, 84);
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
public void recordSelectedSquare(int squareColumn, int squareRow, int whichMouseButton)
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
public void showWelcomeScreen()
{
  PFont introFont = loadFont("MOC.vlw");
  PImage ghost  = loadImage("data/ghost.png");
  PShape person = loadShape("data/person.svg");
  textFont(introFont, 40);
  fill(0);
  text("Whom do you want to play against?", width/10, height/3);

  image(ghost, width/5, height/2, 118, 118);
  strokeWeight(2);
  fill(246, 230, 230);
  rect(width/2, height/2, 118, 118);
  shape(person, width/2, height/2, 118, 118);
}

public void showGameScreen()
{
  background(234, 232, 211);
  gameScreenSetup();
}
public void switchPlayer1()
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

public void switchPlayer2()
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

  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "dynamic_programming" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
