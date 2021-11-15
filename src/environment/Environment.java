package environment;

import java.util.ArrayList;

import util.Case;
import gameCommons.Game;
import gameCommons.IEnvironment;

public class Environment implements IEnvironment {
	private Game game;
	private ArrayList<Lane> lines = new ArrayList<Lane>();
	private int lowestLine = 0; //Lowest line handled
	private int highestLine = 0; //Highest line handled

    public Environment(Game game) {
		this.game=game;
		
		this.update();
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
			//20% chance to get a river || >5 because we dont want to start with rivers directly
			if(this.lines.size()>5 && this.game.randomGen.nextBoolean()){//&& ((this.game.randomGen.nextInt(5) == 0) ? true : false)){
				//for(int i=0 ; i<4; i++){
					this.lines.add(new Lane(this.game, this.highestLine, this.game.defaultDensity,true));
				//}
			}else{
				//for(int i=0 ; i<4; i++){
					this.lines.add(new Lane(this.game, this.highestLine, this.game.defaultDensity,false));
				//}			
			}
			
			this.highestLine++;
		}

		for (Lane lane : this.lines)
			lane.update();
	}

}
