package computationalgeometry;

import computationalgeometry.*;

public class Segment{
    public Dot p1;
    public Dot p2;

    public Segment(double x1, double y1, double x2, double y2){
        if(x1<x2){
            p1 = new Dot(x1, y1);
            p2 = new Dot(x2, y2);
        } else {
            p2 = new Dot(x1, y1);
            p1 = new Dot(x2, y2);
        }
    }
}