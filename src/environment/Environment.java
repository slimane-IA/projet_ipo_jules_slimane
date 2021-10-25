package environment;

import java.util.ArrayList;

import util.Case;
import gameCommons.Game;
import gameCommons.IEnvironment;

public class Environment implements IEnvironment {

	@Override
	public boolean isSafe(Case c) {
		// if the case is empty, so there is no obstacl on it, thus we can say that the case is safe
		return c.empty;
		// TODO: test it
	}

	@Override
	public boolean isWinningPosition(Case c) {
		return c.isLastLine;
		// TODO: test it or suggest more
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
		
	//TODO

}
