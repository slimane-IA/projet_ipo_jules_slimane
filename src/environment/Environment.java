package environment;

import java.util.ArrayList;

import util.Case;
import gameCommons.Game;
import gameCommons.IEnvironment;

public class Environment implements IEnvironment {
	private Game game;
	private ArrayList<Lane> lines = new ArrayList<Lane>();;

    public Environment(Game game) {
		this.game=game;
		//Add the first line (density is 0 as there are no cars)
		this.lines.add(new Lane(this.game, 0, 0.0));
		//Add lines with cars
        for(int i = 1; i < game.height - 1; ++i) {
            this.lines.add(new Lane(this.game, i, this.game.defaultDensity));
        }
		//Add the last line (density is 0 as there are no cars)
        this.lines.add(new Lane(this.game, this.game.height - 1, 0.0));
    }
	
	/**
	 * Checks if Case is safe, meaning the Frog can go there without dying
	 * 
	 * @param c the check Case
	 * @return true if there's no danger
	 */
	public boolean isSafe(Case anyCase){
		return this.lines.get(anyCase.ord).isSafe(anyCase);
	}

	/**
	 * Checks if a Case is a finishing one
	 * 
	 * @param c
	 * @return true is the Case is a winning one
	 */
	public boolean isWinningPosition(Case anyCase){
		if (anyCase.ord == this.game.height - 1)
			return true;
		return false;
	}

	/**
	 * Updates the environment once
	 */
	public void update(){
		for (Lane lane : this.lines)
			lane.update();
	}

}
