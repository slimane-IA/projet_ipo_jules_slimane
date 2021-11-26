package environment;

import java.util.ArrayList;
//import java.awt.Color;

import util.Case;
//import util.Direction;
import util.ImageG;
import gameCommons.Game;

public class Lane {
	protected Game game;
	protected int ord;
	protected int speed;
	protected ArrayList<Car> cars = new ArrayList<>();
	protected boolean leftToRight;
	protected double density;
	protected int waitToMove;
	protected int laneType; //Status: 0 for empty, 1 for road lane, 2 for river
	protected ImageG image ;

	public Lane(Game game, int ord, double density, int status) {
		this.game = game;
		this.ord = ord;
		this.speed = this.game.randomGen.nextInt(this.game.minSpeedInTimerLoops)+1;
		this.leftToRight= this.game.randomGen.nextBoolean();
		this.density = density;
		this.waitToMove=this.speed;
		this.laneType = status;
		//setimage:
		if (this.laneType == 1) {
			this.image=new ImageG("road.jpg");
		} else if (this.laneType == 2) {
			this.image= new ImageG("water.jpg");
		}
	}

	// setters
	public void setLeftToRight(boolean leftToRight) {
		this.leftToRight=leftToRight;
	}



	public void update() {

		// Toutes les voitures se deplacent d'une case au bout d'un nombre "tic
		// d'horloge" egal e leur vitesse
		// Notez que cette methode est appelee e chaque tic d'horloge

		// Les voitures doivent etre ajoutes a l interface graphique meme quand
		// elle ne bougent pas

		// A chaque tic d'horloge, une voiture peut etre ajoutee
		// waitToMove est le temps qu'on attend pour que le voiture move 
		this.waitToMove--;
		this.pauseAllCars();

		if (this.waitToMove<0) {
			this.startMovingAllCars();
			this.mayAddCar();
			this.waitToMove=this.speed;
		}
		
	}

	public void startMovingAllCars() {
		for (Car car : this.cars) {
			car.move();
			//move the frog is rondin and car on it
			if (car.getFrogOnIt() ) {
				if ( (this.game.getFrog().getPosition().absc-1>=0) && (this.game.getFrog().getPosition().absc+1<this.game.width) )
					this.game.getFrog().setPosition(car.getLeftPosition());
			}
		}
		// update the table
		this.cleanCarTable();
	}

	// pause according to speed
	public void pauseAllCars() {
		for (Car car : this.cars)
			car.pauseCar();
		// update the table
	}

	// update the table so that the cars already used won't be in the list
	public void cleanCarTable() {
		for (int i=0 ; i<this.cars.size(); i++) {
			if (this.cars.get(i).getLeftToRight()) {
				if (this.cars.get(i).getLeftPosition().absc>=this.game.width)
					this.cars.remove(this.cars.get(i));
			}else{
				if (this.cars.get(i).getLeftPosition().absc+this.cars.get(i).getLength()<=0)
					this.cars.remove(this.cars.get(i));
			}
		}
	}


	/**
	 * @param anyCase
	 * @return whether given Case is safe for a Frog
	 */
	public boolean isSafe(Case anyCase) {
		if (this.laneType == 1) { //If lane is a road lane
			for (Car car : this.cars) {
				if (car.occupyCase(anyCase))
					return false;
			}
			return true;
		} else if(this.laneType == 2) { //If lane is a river
			for (Car car : this.cars) {
				if (car.occupyCase(anyCase))
					return true;
			}
			return false;
		} else {return true;} //If lane is empty
	}



	/*
	 * Fourni : mayAddCar(), getFirstCase() et getBeforeFirstCase() 
	 */

	/**
	 * Ajoute une voiture au debut de la voie avec probabilite egale e la
	 * densite, si la premiere case de la voie est vide
	 */
	private void mayAddCar() {
		if(((this.laneType == 1) && isSafe(getFirstCase()) && isSafe(getBeforeFirstCase()))||((this.laneType == 2) && !isSafe(getFirstCase()) && !isSafe(getBeforeFirstCase()))) {
			if (game.randomGen.nextDouble() < density) {
				cars.add(new Car(game, getBeforeFirstCase(), this.leftToRight, this.laneType));
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

	public int getOrd() {
		return this.ord;
	}
	
	public ArrayList<ImageG> getImage() {
		ArrayList<ImageG> res = new ArrayList<ImageG>();
		res.add(image);
		return res;
	}

   	/**
	* @return status of the Lane
    */
	public int getLaneType() {
		return this.laneType;
	} 
}
