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

	//const
	int LENGTH = 2;

	

	//Constructor(s)
	public Car(Game game, Case leftPosition, boolean leftToRight) {
		this.game=game;
		this.leftPosition=leftPosition;
		this.leftToRight=leftToRight;
		this.length=LENGTH;
    }



	/**
	 * Moves car 1 spot to the right or left (depending on leftToRight)
	 */
	public void moveOne() {
		this.leftPosition = this.leftPosition.getNextCase(this.leftToRight);
	}
	
	//TODO : add methods
	

	


    /* Fourni : addToGraphics() permettant d'ajouter un element graphique correspondant a la voiture*/
	public void addToGraphics() { //Replaced with public function
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
