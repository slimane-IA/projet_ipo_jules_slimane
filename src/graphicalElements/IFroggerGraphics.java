package graphicalElements;

import gameCommons.Game;
import gameCommons.IFrog;

import javax.swing.*;

public interface IFroggerGraphics {
	
	/**
	 * Ajoute l'element aux elements e afficher
	 * @param e
	 */
    public void add(Element e);
    public void addLane(Element e);

    //public void paintImage(Graphics g,String imageName,int x, int y,int width ,int height);
    
    /**
     * Enleve tous les elements actuellement affiches
     */
    public void clear();
    
    /***
     * Actualise l'affichage
     */
    public void repaint();
    
    /**
     * Lie la grenouille e l'environneemnt graphique
     * @param frog
     */
    public void setFrog(IFrog frog);
    
    /**
     * Lance un ecran de fin de partie
     * @param message le texte e afficher
     */
    public void endGameScreen(String s) ;

    /**
     * afficher un text en haut de l'ecran 
     * @param message le texte e afficher en top
     */
    public void addLabelTop(String s);

 


    	// timer methods
	// setText
	public void setTimerText(String text);
	// display
	public void displayTimer();
	// hide
	public void hideTimer();
    // geters, setters
    public JLabel getTimer();
	public void setTimer(JLabel timer);

    public void setGame(Game game);


  

	
}
