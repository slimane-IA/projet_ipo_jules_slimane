package environment;

import java.util.ArrayList;
import java.util.HashMap;

import util.Case;
import gameCommons.Game;

public class Environment {
	protected Game game;
	protected ArrayList<Lane> lines = new ArrayList<Lane>();
	protected HashMap<Integer,Boolean> isRondin = new HashMap<Integer,Boolean>();
	protected int lowestLine = 0; //Lowest line handled
	protected int highestLine = 0; //Highest line handled
	protected int timerForRiver=0;

    public Environment(Game game) {
		this.game=game;
		
		this.update();
    }


	// getters:
	public HashMap<Integer,Boolean> getIsRondin(){
		return this.isRondin;
	}
	
	/**
	 * Checks if Case is safe, meaning the Frog can go there without dying
	 * 
	 * @param c the check Case
	 * @return whether there's no danger
	 */
	public boolean isSafe(Case anyCase){
		return this.lines.get(anyCase.ord - lowestLine).isSafe(anyCase);
	}

	/**
	 * Checks if a Case is a finishing one
	 * 
	 * @param c
	 * @return whether the Case is a winning one
	 */
	public boolean isWinningPosition(Case anyCase){
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
	public void update(){
		
		//Set lowestLine & highestLine
		int curHeight = this.game.getFrogCase().ord;

		//If lowestLine is too far below, remove lines
		while(this.lowestLine < curHeight-5) {
			this.lines.remove(0);
			this.lowestLine++;
		}

		//If highestLine is not far enough above, add lines
		while(this.highestLine < curHeight+this.game.height+5) {
			if(this.timerForRiver>0){
				this.lines.add(new Lane(this.game, this.highestLine, this.game.defaultDensity, 2));
				this.isRondin.put(this.highestLine,true);
				if(this.timerForRiver%2==0)
					this.lines.get(this.lines.size()-1).setLeftToRight(true);
				else	
				this.lines.get(this.lines.size()-1).setLeftToRight(false);
				this.timerForRiver--;
			}else{
				this.lines.add(new Lane(this.game, this.highestLine, 0.1, 1));
				this.isRondin.put(this.highestLine,false);
				this.timerForRiver--;		
			}
			//20% chance to get a river || >5 because we dont want to start with rivers directly
			if(this.timerForRiver<-4 && this.lines.size()>3 &&this.game.randomGen.nextBoolean()){
					this.timerForRiver=4;
					//this.game.getGraphic().add(new Element(new Case(0,0), Color.blue));
			}
			
			this.highestLine++;
		}

		for (Lane lane : this.lines)
			lane.update();
	}

	public ArrayList<Lane> getLines(){
		return this.lines;
	}

}
