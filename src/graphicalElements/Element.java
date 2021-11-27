package graphicalElements;

import java.util.ArrayList;

import util.Case;



public class Element extends Case {
    //public final Color color;
    //public ArrayList<ImageG> image;
    public ArrayList<String> props;
    //public int elementType = 0;
    public boolean hasBackground = false;

    /* public Element(int absc, int ord, Color color) {
        super(absc, ord);
        this.color = color;
        this.image = null;
    } */
    
    /* public Element(Case c, Color color) {
        super(c.absc, c.ord);
        this.color = color;
        this.image = null;
    } */

    public Element(int absc, int ord, ArrayList<String> props) {
        super(absc, ord);
        this.props = props;
    }

    public Element(int absc, int ord, String prop) {
        super(absc, ord);
        this.props = new ArrayList<String>();
        this.props.add(prop);
    }

    /* public Element(int absc, int ord, Color color, ArrayList<ImageG> image) {
        super(absc, ord);
        this.color = color;
        this.image = image;
    } */

    /* public Element(int absc, int ord, Color color, int elementType) {
        super(absc, ord);
        this.color = color;
        this.image = null;
        this.elementType = elementType;
    } */

    /* public Element(int absc, int ord, Color color, ArrayList<ImageG> image, int elementType) {
        super(absc, ord);
        this.color = color;
        this.image = image;
        this.elementType = elementType;
    } */

    @Override
	public String toString(){
		return "<Element on ("+this.absc+", "+this.ord+")>";
	}
}
