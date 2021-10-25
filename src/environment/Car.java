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

	//constat
	int LENGTH = 2;

	//TODO Constructeur(s)

		
	public Car(Game game, Case leftPosition, boolean leftToRight) {
		this.game=game;
		this.leftPosition=leftPosition;
		this.leftToRight=leftToRight;
		this.length=LENGTH;
    }
	
	
	//TODO : ajout de methodes

	


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
