package gameCommons;

import util.Case;
import util.Direction;

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
	
	/**
	 * Moves the Frog in the given direction and checks for game ending
	 * @param key given direction
	 */
	public void move(Direction key);

}
