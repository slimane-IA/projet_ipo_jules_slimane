package util;

public class Case {
	public final int absc;
	public final int ord;
    public boolean empty;
	public boolean isLastLine;/* if the case belongs to the last line ( the winning line)*/ 
	
	public Case(int absc, int ord) {
		super();
		this.absc = absc;
		this.ord = ord;
		this.empty =true;
		this.isLastLine=false;
	}

	public Case getNextCase(boolean ltr) {
		if(ltr) {return new Case(this.absc+1, this.ord);}
		else {return new Case(this.absc-1, this.ord);}
	}

	public String toString(){
		return "("+this.absc+","+this.ord+")";
	}

}
