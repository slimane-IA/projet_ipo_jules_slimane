package gameCommons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import frog.Frog;
import frog.IFrog;
import environment.Environment;
import graphicalElements.FroggerGraphic;

public class Main {

	public static void main(String[] args) {

		//Game settings
		int width = 26;
		int height = 20;
		int tempo = 100;
		int minSpeedInTimerLoops = 1;
		double defaultDensity = 0.1;
		
		//Creation of the graphical interface
		FroggerGraphic graphic = new FroggerGraphic(width, height);
		//Creation of the game
		Game game = new Game(graphic, width, height, minSpeedInTimerLoops, defaultDensity, tempo);
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


