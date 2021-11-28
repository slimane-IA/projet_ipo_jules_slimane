package environment;

import java.awt.Color;
import java.util.ArrayList;
//import javax.swing.*;

//import javax.swing.DefaultBoundedRangeModel;

//import java.awt.image.BufferedImage;
import util.Case;
//import util.Direction;
import util.ImageG;
import gameCommons.Game;
import graphicalElements.Element;
//import java.awt.Image;
//import util.ImageG;


public class Car {
	protected Game game;
	protected Case leftPosition;
	protected boolean leftToRight;
	protected int length;
	protected Color color = Color.pink;//new Color(186, 140, 99); // by default to rondin color
	protected int carType; //1 for regular car, 2 for rondin
	protected ArrayList<String> props;
	protected boolean hasBackGround;
	protected boolean frogOnIt=false;


	protected ArrayList<ImageG> image ;



	public Car (Game game, Case leftPosition, boolean leftToRight, int carType) {
		this.game = game;
		this.leftPosition = leftPosition;
		this.leftToRight = leftToRight;
		this.carType = carType;

		this.length = (this.carType == 1) ? (this.game.randomGen.nextInt(4)) : (this.game.randomGen.nextInt(4)+3);
		this.props = new ArrayList<>();

		this.hasBackGround = false;
		this.image = new ArrayList<ImageG>();
		setImage();
	}



	//Getters
	
	public boolean hasBackGround() {
		return this.hasBackGround;
	}
	public int carType() {return this.carType;}

	public Case getLeftPosition() {return this.leftPosition;}

	public boolean getLeftToRight() {return this.leftToRight;}

	public int getLength() {return this.length;}

	public boolean getFrogOnIt() {return this.frogOnIt;}

	public ArrayList<String> getProps() {return this.props;}

	public String getProp(int i) {return this.props.get(i);}



	//Setters

	public void setImage() {
		 if (this.carType == 1) {
			for ( int i=0; i<this.length;i++) {
				if (leftToRight) {
					this.image.add(new ImageG("c"+this.length+"l"+i+".png"));
					this.props.add("c"+this.length+"l"+i);
					
				} else {
					this.image.add(new ImageG("c"+this.length+"r"+i+".png"));
					this.props.add("c"+this.length+"r"+i);
				}
			}
		} else { //Considering there's only two car types, no need to check if it's == 2
		for ( int i=0; i<this.length;i++) {
				this.image.add(new ImageG("rondin.png"));			
				this.props.add("rondin");
			}
		}
	}



	/**
	 * Moves the car
	 */
	public void move() {

		//Check if car is on it
		this.frogOnIt = (this.carType == 1) && this.occupyCase(this.game.getFrogCase());
		
		this.leftPosition = new Case(this.leftPosition.absc + (leftToRight ? 1 : -1), this.leftPosition.ord);

		//Update graphics
		this.addToGraphics();
	}

	/**
	 * Pause car
	 */
	public void pauseCar() {
		this.addToGraphics();
	}

	/**
	 * @param anyCase
	 * @return whether the car occupies this case
	 */
	public boolean occupyCase(Case anyCase) {
		// for a car to occupy a case, it has to be in the same line (.ord) AND it has to occupy either by its first case or the othes case according to it's length 
		if (anyCase.ord==this.leftPosition.ord && anyCase.absc >= this.leftPosition.absc && anyCase.absc<(this.leftPosition.absc+this.length))
			return true;
		return false;
	}
	
	/* Fourni : addToGraphics() permettant d'ajouter un element graphique correspondant a la voiture*/
	private void addToGraphics() {
		game.getGraphic().add(new Element(leftPosition.absc, leftPosition.ord, this.props));
	}

}
