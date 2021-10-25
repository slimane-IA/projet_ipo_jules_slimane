class Position{

    private int x;
    private int y;

    Position(int x, int y){
        this.x=x;
        this.y=y;
    }

    public getX(){
        return this.x
    }

    public getY(){
        return this.y;
    }

    public setX(int x){
        this.x=x;
    }
    public setY(int y){
        this.y=y;
    }

    public String toString(){
        return "("+this.x","+this.y+")";
    }
}