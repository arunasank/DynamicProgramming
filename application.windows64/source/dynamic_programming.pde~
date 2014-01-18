    /*This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

	Author : Aruna Sankaranarayanan
	Email : aruna.evam@gmail.com

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.*/


import java.lang.Integer;
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
void setup()
{
  size(screenWidth(), screenHeight());
//  size(window.innerWidth, window.innerHeight);
  userDefinedSetup();
}

void draw()
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

void mousePressed()
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

  int screenWidth()
  {
    return (int)(displayWidth/2);
  }

  int screenHeight()
  {
    return (int)(displayHeight * 3/4);
  }
