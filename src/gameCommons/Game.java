package gameCommons;

import java.util.Random;

import util.Case;
import frog.IFrog;
import graphicalElements.Element;
import graphicalElements.FroggerGraphic;
import environment.Environment;



public class Game {

	public final Random randomGen = new Random();

	//Game characteristics
	public final int width;
	public final int height;
	public final int minSpeedInTimerLoops;
	public final double defaultDensity;
	public boolean isGameOn;
	public float timer;
	private int tempo;

	//Linking used objects
	private Environment environment;
	private IFrog frog;
	private FroggerGraphic graphic;

	/**
	 * 
	 * @param graphic The graphical interface
	 * @param width in number of Case
	 * @param height in number of Case
	 * @param minSpeedInTimerLoop Minimum speed, in number of timer loops before move
	 * @param defaultDensity of cars for lanes
	 */
	public Game(FroggerGraphic graphic, int width, int height, int minSpeedInTimerLoop, double defaultDensity, int tempo) {
		super();
		this.graphic = graphic;
		this.width = width;
		this.height = height;
		this.minSpeedInTimerLoops = minSpeedInTimerLoop;
		this.defaultDensity = defaultDensity;
		this.tempo = tempo;
		this.isGameOn = true;
		this.timer=0;
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
	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}

	/**
	 * @return the graphical interface
	 */
	public FroggerGraphic getGraphic() {
		return graphic;
	}

	public Environment getEnvironment(){
		return this.environment;
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
		if(this.isGameOn){
			this.graphic.setTimerText("time:"+(float)Math.round(this.timer*10)/10+"s");
			this.graphic.displayTimer();
		}

		if(this.isGameOn) {
			this.graphic.clear();
			this.environment.update();
			this.graphic.add(new Element(frog.getPosition().absc, frog.getPosition().ord, "Frog"));

			//this.graphic.addLane(new Element(this.environment.getLanes().get(0).getOrd(), 0, Color.BLACK, this.environment.getLanes().get(0).getImage(), this.environment.getLanes().get(0).getLaneType()));
			this.timer += ((float)tempo/100)/8;
		}
		if(this.isGameOn && this.testLose()) { //Not doing if/else is intentional
			this.graphic.endGameScreen("Game over! Your score: "+this.getFrogCase().ord);
			
			this.isGameOn = false;


		}
	}

}