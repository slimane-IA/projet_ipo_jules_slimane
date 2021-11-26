package gameCommons;

import java.util.ArrayList;
import java.util.HashMap;

import environment.Lane;
import util.Case;

public interface IEnvironment {

	// getters
	public HashMap<Integer,Boolean> getIsRondin();

	/**
	 * Checks if Case is safe, meaning the Frog can go there without dying
	 * 
	 * @param c the check Case
	 * @return true if there's no danger
	 */
	public boolean isSafe(Case c);

	/**
	 * Checks if a Case is a finishing one
	 * 
	 * @param c
	 * @return true is the Case is a winning one
	 */
	public boolean isWinningPosition(Case c);

	/**
	 * @return Lowest handled line
	 */
	public int getMinLine();

	/**
	 * Updates the environment once
	 */
	public void update();

	public ArrayList<Lane> getLines();

}
