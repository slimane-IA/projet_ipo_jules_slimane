package graphicalElements;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.lang.Math;

import environment.Lane;
import gameCommons.Game;
import frog.IFrog;
import util.Direction;
import util.ImageG;



public class FroggerGraphic extends JPanel implements KeyListener {
	private ArrayList<Element> elementsToDisplay;
	private ArrayList<Element> elementsLaneToDisplay;
	private int pixelByCase = 16;
	private int width;
	private int height;
	private IFrog frog;
	private JFrame frame;
	private JLabel timer;
	protected Game game;
	
	private static HashMap<String, ImageG> elementsImages = new HashMap<>();




	public FroggerGraphic(int width, int height) {
		this.width = width;
		this.height = height;
		elementsToDisplay = new ArrayList<Element>();
		elementsLaneToDisplay = new ArrayList<Element>();
		this.game = null;

		setBackground(Color.GRAY);
		setPreferredSize(new Dimension(this.width * pixelByCase, this.height * pixelByCase));

		JFrame frame = new JFrame("Frogger");
		this.frame = frame;
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(this);
		frame.pack();
		frame.setVisible(true);
		frame.addKeyListener(this);

		// init timer: 
		JLabel label = new JLabel("");
		this.timer=label;
		label.setFont(new Font("Verdana", 1, 20));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setVerticalAlignment(SwingConstants.TOP);
		label.setSize(this.getSize());
	}

	// getters:
	public int getPixelByCase() {
		return this.pixelByCase;
	}


	// setters:
	public void setGame(Game game) {
		this.game = game;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		if(this.game.getEnvironment() == null) {return;} //On first iteration

		ImageG roadImage = new ImageG("road.jpg");
		ImageG riverImage = new ImageG("water.jpg");
		ImageG emptyImage = new ImageG("");
		
		ArrayList<Integer> laneTypes = new ArrayList<Integer>();
		for(Lane l : this.game.getEnvironment().getLanes()) {
			laneTypes.add(l.getLaneType());
		}
		
		//Get the coordinates right
		int size = laneTypes.size();
		int linesBelow = this.game.getEnvironment().getLinesBelow();
		int lowestLine = this.game.getEnvironment().getLowestLine();
		int frogPos = this.game.getFrog().getPosition().ord;
		int lowLine = Math.min( frogPos-linesBelow, lowestLine );
		
		//Draw the lanes background
		for (int i=linesBelow-1; i<size; i++) {
			int j = height-(i+lowestLine-frogPos+1);

			ImageG backImg;
			switch(laneTypes.get(i)) {
				case 1: backImg = roadImage; break;
				case 2: backImg = riverImage; break;
				default: backImg = emptyImage; break;
			}

			g.drawImage(backImg.image, 0, j*pixelByCase, width*pixelByCase, pixelByCase, null);
		}

		//Draw the cars
		for (Element e : elementsToDisplay) {
			if(e.props != null) {
				for (int i=0; i<e.props.size();i++) {
					String iStr = e.props.get(i);
				
					//Add the image to the static list if not already included
					if( !elementsImages.containsKey(iStr)) {
						elementsImages.put(iStr, new ImageG(iStr+".png"));
					}

					ImageG curImage = elementsImages.get(iStr);
					g.drawImage(curImage.image, (pixelByCase * (e.absc+i)), pixelByCase * (height - (e.ord - lowLine + 1 - linesBelow)), pixelByCase, pixelByCase, null );
				}
			}
		}
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			frog.move(Direction.up); break;
		case KeyEvent.VK_DOWN:
			frog.move(Direction.down); break;
		case KeyEvent.VK_LEFT:
			frog.move(Direction.left); break;
		case KeyEvent.VK_RIGHT:
			frog.move(Direction.right); break;
		/*case KeyEvent.VK_ENTER:
			frog.pressedEnter(); break;*/
		}
	}

	public void clear() {
		this.elementsToDisplay.clear();
	}

	public void add(Element e) {
		this.elementsToDisplay.add(e);
	}

	public void addLane(Element e) {
		this.elementsLaneToDisplay.add(e);
	}

	public void setFrog(IFrog frog) {
		this.frog = frog;
	}

	

	public void endGameScreen(String s) {
		frame.remove(this);
		JLabel label = new JLabel(s);
		label.setFont(new Font("Verdana", 1, 20));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setSize(this.getSize());
		frame.getContentPane().add(label);
		frame.getContentPane().add(this.timer);
		frame.repaint();

	}

	
	
	public void addLabelTop(String s) {
		JLabel label = new JLabel(s);
		label.setFont(new Font("Verdana", 1, 20));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setVerticalAlignment(SwingConstants.TOP);
		label.setSize(this.getSize());
		this.add(label);
		this.timer=label;
	}

	// timer methods
	// setText
	public void setTimerText(String text) {
		this.timer.setText(text);
	}
	// display
	public void displayTimer() {
		this.add(this.timer);
	}
	// hide
	public void hideTimer() {
		this.remove(this.timer);
	}

	public JLabel getTimer() {
		return this.timer;
	}
	public void setTimer(JLabel timer) {
		this.timer=timer;
	}

	 



}
