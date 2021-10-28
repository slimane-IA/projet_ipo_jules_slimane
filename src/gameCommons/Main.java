package gameCommons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import frog.Frog;
import givenEnvironment.GivenEnvironment;
import graphicalElements.FroggerGraphic;
import graphicalElements.IFroggerGraphics;
import util.Case;

public class Main {

	public static void main(String[] args) {

		//Game characteristics
		int width = 26;
		int height = 20;
		int tempo = 100;
		int minSpeedInTimerLoops = 3;
		double defaultDensity = 0.2;
		
		//Creation of the graphical interface
		IFroggerGraphics graphic = new FroggerGraphic(width, height);

		//Creation of the game
		Game game = new Game(graphic, width, height, minSpeedInTimerLoops, defaultDensity);

		//Creation and linking of the frog
		IFrog frog = new Frog(game, new Case(Math.round(width/2), 0));
		game.setFrog(frog);
		graphic.setFrog(frog);

		//Creation and linking of the environment
		IEnvironment env = new GivenEnvironment(game);
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
