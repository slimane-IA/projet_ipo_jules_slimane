package frog;

import java.util.ArrayList;

import util.Case;
import util.Direction;
import util.ImageG;

public interface IFrog {
	
	/**
	 * Gives the current position of the Frog
	 * @return Case
	 */
	public Case getPosition();
	
	/**
	 * Gives the direction of the Frog on its most recent move
	 * @return Direction
	 */
	public Direction getDirection();
	

	/**
	 * Puts isGameOn back to true in the game
	 */
	public void pressedEnter();

	public ArrayList<ImageG> getImage();
	public void setImage(); 
	
	/**
	 * Moves the Frog in the given direction and checks for game ending
	 * @param key given direction
	 */
	public void move(Direction key);

    public void setPosition(Case leftPosition);




}
