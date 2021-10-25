package frog;

import gameCommons.Game;
import gameCommons.IFrog;
import util.Direction;
import util.Case;

public class Frog implements IFrog {
	
	private Game game;
	private Case caseFrog;
	private Direction direction;


	public Frog(Game game) {
		this.game=game;
		this.caseFrog=null;
		this.direction=null;
	}

	/**
	 * Donne la position actuelle de la grenouille
	 * @return
	 */
	public Case getPosition(){
		return this.caseFrog;
	}

	/**
	 * Donne la direction de la grenouille, c'est � dire de son dernier mouvement
	 * @return
	 */

	public Direction getDirection(){
		return this.direction;
	}

	/**
	 * D�place la grenouille dans la direction donn�e et teste la fin de partie
	 * @param key
	 */
	public void move(Direction key){
		this.direction=key;
	}


}
