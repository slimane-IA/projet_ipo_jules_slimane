package environment;

import java.util.ArrayList;
import java.awt.Color;

import util.Case;
import gameCommons.Game;

public class Lane {
	private Game game;
	private int ord;
	private int speed;
	private ArrayList<Car> cars = new ArrayList<>();
	private boolean leftToRight;
	private double density;
	private int waitToMove;
	//private final Color color;
	private boolean isRondin;

	public Lane(Game game, int ord, double density, boolean isRondin){
		this.game = game;
		this.ord = ord;
		this.speed = this.game.randomGen.nextInt(this.game.minSpeedInTimerLoops)+1;
		this.leftToRight= this.game.randomGen.nextBoolean();
		this.density = density;
		this.waitToMove=this.speed;
		this.isRondin = isRondin;
		// if(isRondin){
		// 	this.color=Color.CYAN;
		// }else{
		// 	this.color=Color.white;
		// }
		

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

		if (this.waitToMove<0){
			this.startMovingAllCars();
			this.mayAddCar();
			this.waitToMove=this.speed;
		}
		
	}

	public void startMovingAllCars(){
		for (Car car : this.cars)
			car.move();
		// update the table
		this.cleanCarTable();
	}

	// pause according to speed
	public void pauseAllCars(){
		for (Car car : this.cars)
			car.pauseCar();
		// update the table
	}

	// update the table so that the cars already used won't be in the list
	public void cleanCarTable(){
		for (int i=0 ; i<this.cars.size(); i++){
			if (this.cars.get(i).getLeftToRight()){
				if (this.cars.get(i).getLeftPosition().absc>=this.game.width)
					this.cars.remove(this.cars.get(i));
			}else{
				if (this.cars.get(i).getLeftPosition().absc+this.cars.get(i).getLength()<=0)
					this.cars.remove(this.cars.get(i));
			}
		}
	}


	// is safe : regarder si un case est safe en parcourant toute les voiture dans la lignes 
	public boolean isSafe( Case anyCase){
		if(!this.isRondin){
			for (Car car : this.cars){
				if (car.occupyCase(anyCase))
					return false;
			}
			return true;
		}else{
			for (Car car : this.cars){
				if (!car.occupyCase(anyCase))
					return false;
			}
			return true;
		}
	}



	/*
	 * Fourni : mayAddCar(), getFirstCase() et getBeforeFirstCase() 
	 */

	/**
	 * Ajoute une voiture au debut de la voie avec probabilite egale e la
	 * densite, si la premiere case de la voie est vide
	 */
	private void mayAddCar() {
		if (isSafe(getFirstCase()) && isSafe(getBeforeFirstCase())) {
			if (game.randomGen.nextDouble() < density) {
				cars.add(new Car(game, getBeforeFirstCase(), this.leftToRight,this.isRondin));
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
