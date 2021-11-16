
package gameCommons;

import java.awt.Color;
import java.util.Random;

import util.Case;

import graphicalElements.Element;
import graphicalElements.IFroggerGraphics;
import java.util.concurrent.TimeUnit;

public class Game {

	public final Random randomGen = new Random();

	//Game characteristics
	public final int width;
	public final int height;
	public final int minSpeedInTimerLoops;
	public final double defaultDensity;
	public boolean isGameOn;
	public int timer;
	//today
	private int ticSeconds;

	//Linking used objects
	private IEnvironment environment;
	private IFrog frog;
	private IFroggerGraphics graphic;

	/**
	 * 
	 * @param graphic The graphical interface
	 * @param width in number of Case
	 * @param height in number of Case
	 * @param minSpeedInTimerLoop Minimum speed, in number of timer loops before move
	 * @param defaultDensity of cars for lanes
	 */
	public Game(IFroggerGraphics graphic, int width, int height, int minSpeedInTimerLoop, double defaultDensity) {
		super();
		this.graphic = graphic;
		this.width = width;
		this.height = height;
		this.minSpeedInTimerLoops = minSpeedInTimerLoop;
		this.defaultDensity = defaultDensity;
		this.isGameOn = true;
		this.timer=0;
		//today
		this.ticSeconds=3;
	}

	/**
	 * Links frog object to game
	 * @param frog
	 */
	public void setFrog(IFrog frog) {
		this.frog = frog;
	}

	public IFrog getFrog(){
		return this.frog;
	}

	/**
	 * Links environment object to game
	 * @param environment
	 */
	public void setEnvironment(IEnvironment environment) {
		this.environment = environment;
	}

	/**
	 * @return the graphical interface
	 */
	public IFroggerGraphics getGraphic() {
		return graphic;
	}

	/**
	 * @return whether the game is over
	 */
	public boolean testLose() {
		return !this.environment.isSafe(this.frog.getPosition());
	}

	/**
	 * @return whether the game is won
	 */
	public boolean testWin() {
		return this.environment.isWinningPosition(this.frog.getPosition());
	}

	/**
	 * @return whether specific Case is safe
	 */
	public boolean isCaseSafe(Case c) {
		return this.environment.isSafe(c);
	}

	/**
	 * @return Case of the game's Frog
	 */
	public Case getFrogCase() {
		return this.frog.getPosition();
	}

	/**
	 * @return Lowest line handled by the environment
	 */
	public int getMinLine() {
		return this.environment.getMinLine();
	}

	/**
	 * Updates the environment, displays the frog, and checks the endgame
	 */
	public void update() {
		//today
		// timer update():
		if(this.ticSeconds>8 && this.isGameOn){
			this.graphic.setTimerText("time (seconds):"+this.timer);
			this.graphic.displayTimer();
			this.timer++;
			this.ticSeconds=0;
		}
		;

		if(this.isGameOn) {
			this.graphic.clear();
			this.environment.update();
			this.graphic.add(new Element(frog.getPosition(), Color.GREEN));
			//today
			this.ticSeconds++;
		}
		if(this.testLose()) { //Not doing if/else is intentional
			this.graphic.endGameScreen("Game over! Your score: "+this.getFrogCase().ord);
			this.isGameOn = false;
			
		}
	}

}