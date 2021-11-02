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
		this.game = game;
		this.caseFrog = new Case(Math.round(this.game.width/2), 0);
		this.direction = null;
	}

	/**
	 * Donne la position actuelle de la grenouille
	 * @return
	 */
	public Case getPosition(){
		return this.caseFrog;
	}

	/**
	 * Donne la direction de la grenouille, c'est e dire de son dernier mouvement
	 * @return
	 */

	public Direction getDirection(){
		return this.direction;
	}

	/**
	 * Deplace la grenouille dans la direction donnee et teste la fin de partie
	 * @param key
	 */
	public void move(Direction key){
		// 1. deplacer la grenouille :
		
		switch(key) {
			case up:
				if (this.caseFrog.ord+1<this.game.height)
					this.caseFrog = new Case(this.caseFrog.absc,this.caseFrog.ord+1); // or + case.size
				break;
			case down:
				if (this.caseFrog.ord-1>=0)
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

		// 2. set last direct
		this.direction=key;

		// 3. tester la fin de la partie
		this.game.update();

	}


}
