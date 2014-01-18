void showWelcomeScreen()
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

void showGameScreen()
{
  background(234, 232, 211);
  gameScreenSetup();
}
