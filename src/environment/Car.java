package environment;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.*;

import javax.swing.DefaultBoundedRangeModel;

import java.awt.image.BufferedImage;
import util.Case;
import util.Direction;
import util.ImageG;
import gameCommons.Game;
import graphicalElements.Element;
import java.awt.Image;
import util.ImageG;


public class Car {
	protected Game game;
	protected Case leftPosition;
	protected boolean leftToRight;
	protected int length;
	protected Color color = Color.pink;//new Color(186, 140, 99); // by defautl to rondin color
	protected boolean isRondin;
	protected boolean frogOnIt=false;


	public ArrayList<ImageG> image ;



	public Car (Game game, Case leftPosition, boolean leftToRight, boolean isRondin){
		this.game = game;
		this.leftPosition = leftPosition;
		this.leftToRight = leftToRight;
		this.length= this.game.randomGen.nextInt(3);
		this.isRondin=isRondin;
		this.image = new ArrayList<ImageG>();
		setImage();
	}

	//getters:
	public  Case getLeftPosition(){
		return this.leftPosition;
	}

	public  boolean getLeftToRight(){
		return this.leftToRight;
	}

	public  int getLength(){
		return this.length;
	}

	public  boolean getFrogOnIt(){
		return this.frogOnIt;
	}

	// setters: 
	public void setImage() {
		 if(!this.isRondin){
			for ( int i=0; i<this.length;i++){
				if (leftToRight){
					this.image.add(new ImageG("c"+this.length+"l"+i+".png"));
					
				}else{
					this.image.add(new ImageG("c"+this.length+"r"+i+".png"));
				}
			}
		}else{
			for ( int i=0; i<this.length;i++){
				this.image.add(new ImageG("rondin.png"));			
			}
		}
	}


	
	// move qui comme move pour frog mais acev directtion left or right 
	public void move(){

		//check if car on it:
		if(this.isRondin && this.occupyCase(this.game.getFrogCase()) ){
			this.frogOnIt=true;
		}else{
			this.frogOnIt=false;
		}
		
		if(leftToRight){
			this.leftPosition = new Case(this.leftPosition.absc+1,this.leftPosition.ord);
		}else{
			this.leftPosition = new Case(this.leftPosition.absc-1,this.leftPosition.ord);
		}
		
		

		// mise-a-jour graphic
		this.addToGraphics();
	}

	// pause car
	public void pauseCar(){
		this.addToGraphics();
	}

	// prend une case en paramettre renvois 
	// true si la voiture occupe cette case 
	// false sinon
	public boolean occupyCase(Case anyCase){
		// for a car to occupy a case, it has to be in the same line (.ord) AND it has to occupy either by its first case or the othes case according to it's length 
		if (anyCase.ord==this.leftPosition.ord && anyCase.absc >= this.leftPosition.absc && anyCase.absc<(this.leftPosition.absc+this.length))
			return true;
		return false;
	}

	
	/* Fourni : addToGraphics() permettant d'ajouter un element graphique correspondant a la voiture*/
	private void addToGraphics() {
		for (int i = 0; i < length; i++) {
			game.getGraphic().add(new Element(leftPosition.absc + i, leftPosition.ord, this.color,this.image));
		}
		game.getGraphic().add(new Element(leftPosition.absc + length, leftPosition.ord, Color.GRAY));
	}


}
