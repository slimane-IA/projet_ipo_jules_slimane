package environment;

import java.awt.Color;

import util.Case;
import gameCommons.Game;
import graphicalElements.Element;

public class Car {
	private Game game;
	private Case leftPosition;
	private boolean leftToRight;
	private int length;
	private final Color colorLtR = Color.BLACK;
	private final Color colorRtL = Color.BLUE;

	public Car (Game game, Case leftPosition, boolean leftToRight){
		this.game = game;
		this.leftPosition = leftPosition;
		this.leftToRight = leftToRight;
		this.length= this.game.randomGen.nextInt(3);
	}

	//getters:
	public  Case getLeftPosition(){
		return this.leftPosition;
	}

	public  boolean getLeftToRight(){
		return this.leftToRight;
	}

	public  int getLength(){
		return this.length;
	}
	
	// move qui comme move pour frog mais acev directtion left or right 
	public void move(){
		if(leftToRight){
			this.leftPosition = new Case(this.leftPosition.absc+1,this.leftPosition.ord);
		}else{
			this.leftPosition = new Case(this.leftPosition.absc-1,this.leftPosition.ord);
		}

		// mise-a-jour graphic
		this.addToGraphics();
	}

	// pause car
	public void pauseCar(){
		this.addToGraphics();
	}

	// prend une case en paramettre renvois 
	// true si la voiture occupe cette case 
	// false sinon
	public boolean occupyCase(Case anyCase){
		// for a car to occupy a case, it has to be in the same line (.ord) AND it has to occupy either by its first case or the othes case according to it's length 
		if (anyCase.ord==this.leftPosition.ord && anyCase.absc >= this.leftPosition.absc && anyCase.absc<(this.leftPosition.absc+this.length))
			return true;
		return false;
	}

	
	
	/* Fourni : addToGraphics() permettant d'ajouter un element graphique correspondant a la voiture*/
	private void addToGraphics() {
		for (int i = 0; i < length; i++) {
			Color color = colorRtL;
			if (this.leftToRight){
				color = colorLtR;
			}
			game.getGraphic()
					.add(new Element(leftPosition.absc + i, leftPosition.ord, color));
		}
	}

}
