package environment;

import java.util.ArrayList;
//import java.util.Pair; // VSCode doesn't want me to do that

import util.Case;
import gameCommons.Game;

public class Lane {
	private Game game;
	private int ord;
	private int speed;
	private int ticker;
	private ArrayList<Car> cars;
	private boolean leftToRight;
	private double density;

	// Constructor
	public Lane(Game game, int ord, int speed, boolean ltr, double density) {
		this.game = game;
		this.ord = ord;
		this.speed = speed;
		this.ticker = 0;
		this.cars = new ArrayList<>();
		this.leftToRight = ltr;
		this.density = density;
	}



	// Updating function
	public void update() {

		// TODO

		//Every step:
		// Increment the ticker for all cars, if it exceeds their speed then move them
		this.ticker++;
		if(ticker > speed) {
			for(Car c : this.cars) {
				c.moveOne();
			}
			ticker = 0;
		}

		// Add all cars to the graphical interface
		for(Car c : this.cars) {
			c.addToGraphics();
		}
		// Try to add another car
		randomlyAddCarIfPossible();

	}



	// Methods
	//TODO: Add methods

	/*
	 * Fourni : mayAddCar() [renamed to randomlyAddCarIfPossible()], getFirstCase() et getBeforeFirstCase() 
	 */

	/**
	 * If the first Case is empty,
	 * Maybe add a new car (with probability equal to density)
	 */
	private void randomlyAddCarIfPossible() {
		if (game.isCaseSafe(this.getFirstCase()) && game.isCaseSafe(this.getBeforeFirstCase())) {
			//If cases are empty, throw the dice and maybe put a car
			if (game.randomGen.nextDouble() < this.density) {
				this.cars.add(new Car(game, getBeforeFirstCase(), leftToRight));
			}
		}
	}

	private Case getFirstCase() {
		if (leftToRight) {
			return new Case(0, ord);
		} else
			return new Case(game.width - 1, ord);
	}

	private Case getBeforeFirstCase() {
		if (leftToRight) {
			return new Case(-1, ord);
		} else
			return new Case(game.width, ord);
	}

}
