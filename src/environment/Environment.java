package environment;

import java.util.ArrayList;
//import java.util.HashMap;

import util.Case;
import gameCommons.Game;

public class Environment {
	protected Game game;
	protected ArrayList<Lane> lanes = new ArrayList<Lane>();

	protected final int linesBelow = 5;
	protected int lowestLine = -linesBelow; //Lowest line handled
	protected int highestLine = lowestLine; //Highest line handled

	protected int curBlocType = 0; //Specifies the type of the current bloc
	protected int curBlocRem = 1+linesBelow; //Specifies the amount of lanes remaining to be created for the current bloc
	protected boolean curLaneLTR = false; //Specifies whether the current lane is ltr or rtl (reset with each new bloc)

    public Environment(Game game) {
		this.game=game;
		
		this.update();
    }
	
	/**
	 * Checks if Case is safe, meaning the Frog can go there without dying
	 * 
	 * @param anyCase
	 * @return whether there's no danger
	 */
	public boolean isSafe(Case anyCase) {
		return this.lanes.get(anyCase.ord - lowestLine).isSafe(anyCase);
	}

	/**
	 * Checks if a Case is a finishing one
	 * 
	 * @param anyCase
	 * @return whether the Case is a winning one
	 */
	public boolean isWinningPosition(Case anyCase) {
		if (anyCase.ord == this.game.height - 1)
			return true;
		return false;
	}

	/**
	 * @return Lowest line handled
	 */
	public int getMinLine() {
		return this.lowestLine;
	}

	/**
	 * Updates the environment once
	 */
	public void update() {
		
		//Set lowestLine & highestLine
		int curHeight = this.game.getFrogCase().ord;

		//System.out.println(curHeight+", "+this.lowestLine+", "+this.highestLine+", "+this.lanes.size());

		//If lowestLine is too far below, remove lines
		while(this.lowestLine < curHeight-this.linesBelow) {
			this.lanes.remove(0);
			this.lowestLine++;
		}

		//If highestLine is not far enough above, add lines
		while(this.highestLine < curHeight+this.game.height+5) {

			//Make new bloc if needed
			if(this.curBlocRem <= 0) {

				if(this.curBlocType == 0) { //If currently on a 0 bloc, create a new lane or road bloc
					this.curBlocType = this.game.randomGen.nextInt(2)+1;
					//this.curBlocRem = this.game.randomGen.nextInt(6)+1;
					this.curBlocRem = (this.curBlocType == 1) ? this.game.randomGen.nextInt(6)+1 : 1; //One lane if river, between 1 and 6 if road

				} else { //Else (currently on a non-0 bloc), create a new empty or different bloc

					//50-50 chance of going to a 0-type or to go to the opposite type directly
					int nextType = (3-this.curBlocType);
					this.curBlocType = (this.game.randomGen.nextBoolean()) ? 0 : nextType;
					
					//Bloc length 1 if empty lane, else between 1 and 6
					//this.curBlocRem = (this.curBlocType == 0) ? 1 : this.game.randomGen.nextInt(4)+3;
					this.curBlocRem = (this.curBlocType == 1) ? this.game.randomGen.nextInt(4)+3 : 1; //One lane if river or empty
					
					
					//Init the new ltr
					this.curLaneLTR = this.game.randomGen.nextBoolean();
				}
			}
			
			//Add new line
			this.lanes.add(new Lane(this.game, this.highestLine, this.game.defaultDensity, this.curBlocType, this.curLaneLTR));
			this.curLaneLTR = !this.curLaneLTR;
			this.curBlocRem--;
			
			this.highestLine++;
		}

		for (Lane lane : this.lanes) {
			lane.update();
		}
	}

	public ArrayList<Lane> getLanes() {
		return this.lanes;
	}

	public int getAmountOfLanes() {
		return (this.highestLine-this.lowestLine);
	}

	public int getLowestLine() {
		return this.lowestLine;
	}

	public int getLinesBelow() {
		return this.linesBelow;
	}

}
