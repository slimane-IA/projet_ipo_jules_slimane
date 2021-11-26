package gameCommons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import frog.Frog;
//import givenEnvironment.GivenEnvironment;
import environment.Environment;
import graphicalElements.FroggerGraphic;
import graphicalElements.IFroggerGraphics;

public class Main {

	public static void main(String[] args) {

		//Caractéristiques du jeu
		int width = 26;
		int height = 20;
		int tempo = 100;
		int minSpeedInTimerLoops = 1;
		double defaultDensity = 0.3;
		
		//Creation of the graphical interface
		IFroggerGraphics graphic = new FroggerGraphic(width, height);
		//Creation of the game
		Game game = new Game(graphic, width, height, minSpeedInTimerLoops, defaultDensity);
		graphic.setGame(game);
		//Creation and linking of the frog
		IFrog frog = new Frog(game);
		game.setFrog(frog);
		graphic.setFrog(frog);
		//Creation and linking of the environment
		Environment env = new Environment(game);
		game.setEnvironment(env);
		
		//Main loop: update the environment every tempo milliseconds
		Timer timer = new Timer(tempo, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				game.update();
				graphic.repaint();
			}
		});
		timer.setInitialDelay(0);
		timer.start();


		
	}
}


