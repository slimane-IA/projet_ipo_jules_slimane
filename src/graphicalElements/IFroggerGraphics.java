package graphicalElements;

import gameCommons.IFrog;

public interface IFroggerGraphics {
	
	/**
	 * Ajoute l'element aux elements e afficher
	 * @param e
	 */
    public void add(Element e);
    
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
    public void endGameScreen(String message);
}
