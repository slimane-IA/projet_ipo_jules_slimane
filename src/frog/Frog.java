package frog;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import gameCommons.Game;
import gameCommons.IFrog;
import util.Direction;
import util.ImageG;
import util.Case;

public class Frog implements IFrog {
	
	protected Game game;
	protected Case caseFrog;
	protected Direction direction;
	protected ImageG image ;



	public Frog(Game game) {
		this.game = game;
		this.caseFrog = new Case(Math.round(this.game.width/2), 0);
		this.direction = null;
		this.image = new ImageG("frog.png");
		setImage();
	}

	// setters: 
	public void setImage() {
		this.image= new ImageG("frog.png");
   }
   // getters: 
	public ArrayList<ImageG> getImage() {
		ArrayList<ImageG> res = new ArrayList<ImageG>();
		res.add(image);
		return res;
   }

	/**
	 * Gives the current position of the Frog
	 * @return Case
	 */
	public Case getPosition(){
		return this.caseFrog;
	}

	public void setPosition(Case anyCase ){
		this.caseFrog=anyCase;
	}


	/**
	 * Gives the direction of the Frog on its most recent move
	 * @return Direction
	 */
	public Direction getDirection(){
		return this.direction;
	}

	/**
	 * Puts isGameOn back to true in the game
	 */
	public void pressedEnter() {
		this.game.isGameOn = true;
	}

	/**
	 * Moves the Frog in the given direction and checks for game ending
	 * @param key given direction
	 */
	public void move(Direction key){
		
		// 1. move the Frog :
		switch(key) {
			case up:
				this.caseFrog = new Case(this.caseFrog.absc,this.caseFrog.ord+1); // or + case.size
				break;
			case down:
				if (this.caseFrog.ord-1 >= this.game.getMinLine())
					this.caseFrog = new Case(this.caseFrog.absc,this.caseFrog.ord-1);
				break;
			case left:
				if (this.caseFrog.absc-1>=0)
					this.caseFrog = new Case(this.caseFrog.absc-1,this.caseFrog.ord);
				break;
			case right:
				if (this.caseFrog.absc+1<this.game.width)
					this.caseFrog = new Case(this.caseFrog.absc+1,this.caseFrog.ord);
				break;
		}

		// 2. set new direction
		this.direction=key;

		// 3. check game ending
		this.game.update();

	}




}
