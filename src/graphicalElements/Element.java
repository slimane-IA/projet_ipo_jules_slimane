package graphicalElements;

import java.awt.*;
import java.util.ArrayList;

//import javax.swing.ImageIcon;

//import environment.Car;
import util.Case;
import util.ImageG;


public class Element extends Case {
    public final Color color;
    public ArrayList<ImageG> image;
    public int elementType = 0;
    public boolean hasBackGround = false;

    public Element(int absc, int ord, Color color) {
        super(absc, ord);
        this.color = color;
        this.image=null;
    }
    public Element(Case c, Color color) {
        super(c.absc, c.ord);
        this.color = color;
        this.image=null;
    }
    
    public Element(Case c, Color color, ArrayList<ImageG> image) {
        super(c.absc, c.ord);
        this.color = color;
        this.image = image;
    }

    public Element(int absc, int ord, Color color,ArrayList<ImageG> image) {
        super(absc, ord);
        this.color = color;
        this.image=image;
    }

    public Element(int absc, int ord, Color color,ArrayList<ImageG> image, int elementType) {
        super(absc, ord);
        this.color = color;
        this.image=image;
        this.elementType=elementType;
    }

 

    
}
