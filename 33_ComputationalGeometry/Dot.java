package computationalgeometry;

public class Dot{
    public double x;
    public double y;

    public Dot(double x, double y){
        this.x = x;
        this.y = y;
    }

    public String toString(){
        return "("+x+","+y+")";
    }
}