package environment;

import java.util.ArrayList;
//import java.util.Pair; // VSCode doesn't want me to do that

import util.Case;
import gameCommons.Game;

public class Lane {
	private Game game;
	private int ord;
	private int speed;
	//private ArrayList<Pair<Car, Integer>> cars;
	private ArrayList<Car> cars;
	private ArrayList<Integer> ticker;
	private boolean leftToRight;
	private double density;

	// Constructor
	public Lane(Game game, int ord, int speed, boolean ltr, double density) {
		this.game = game;
		this.ord = ord;
		this.speed = speed;
		this.leftToRight = ltr;
		this.cars = new ArrayList<>();
		this.density = density;
	}



	// Updating function
	public void update() {

		// TODO

		//Every step:
		// Increment the ticker for all cars, if it exceeds their speed then move them
		// Add all cars to the graphical interface
		// Try to add another car

	}



	// Methods
	//TODO: Add methods

	/*
	 * Fourni : mayAddCar(), getFirstCase() et getBeforeFirstCase() 
	 */

	/**
	 * If the first Case is empty,
	 * Maybe add a new car (with probability equal to density)
	 */
	private void randomlyAddCarIfPossible() {
		if (game.isCaseSafe(this.getFirstCase()) && game.isCaseSafe(this.getBeforeFirstCase())) {
			//If cases are empty, throw the dice and maybe put a car
			if (game.randomGen.nextDouble() < this.density) {
				//this.cars.add(new Pair<>(new Car(game, getBeforeFirstCase(), leftToRight), 0));
				this.cars.add(new Car(game, getBeforeFirstCase(), leftToRight));
				this.ticker.add(0);
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
