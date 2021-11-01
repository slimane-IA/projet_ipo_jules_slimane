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
		// ajouter la premiere ligne ( pour avoire 0 voiture , on met density a 0)
		this.lines.add(new Lane(this.game, 0, 0.0));
		// ajouter les lignes avec des voiture 
        for(int i = 1; i < game.height - 1; ++i) {
            this.lines.add(new Lane(this.game, i, this.game.defaultDensity));
        }
		// ajouter la derniere ligne ( pour avoire 0 voiture , on met density a 0)
        this.lines.add(new Lane(this.game, this.game.height - 1, 0.0));
    }
	
	/**
	 * Teste si une case est sure, c'est e dire que la grenouille peut s'y poser
	 * sans mourir
	 * 
	 * @param c
	 *            la case e tester
	 * @return vrai s'il n'y a pas danger
	 */
	public boolean isSafe(Case anyCase){
		return this.lines.get(anyCase.ord).isSafe(anyCase);
	}

	/**
	 * Teste si la case est une case d'arrivee
	 * 
	 * @param c
	 * @return vrai si la case est une case de victoire
	 */
	public boolean isWinningPosition(Case anyCase){
		if (anyCase.ord == this.game.height - 1)
			return true;
		return false;
	}

	/**
	 * Effectue une etape d'actualisation de l'environnement
	 */
	public void update(){
		for (Lane lane : this.lines)
			lane.update();
	}

}
