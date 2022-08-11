package computationalgeometry;

import computationalgeometry.*;

public class SegmentsIntersection{
    public static boolean check(Segment a, Segment b){
        double d1 = side(a.p1, a.p2, b.p1);
        double d2 = side(a.p1, a.p2, b.p2);
        double d3 = side(b.p1, b.p2, a.p1);
        double d4 = side(b.p1, b.p2, a.p2);
        if(d1*d2<0 && d3*d4<0)
            return true;
        if(d1==0&&on(a.p1, a.p2, b.p1))
            return true;
        if(d2==0&&on(a.p1, a.p2, b.p2))
            return true;
        if(d3==0&&on(b.p1, b.p2, a.p1))
            return true;
        if(d4==0&&on(b.p1, b.p2, a.p2))
            return true;
        return false;
    }

    //p1 vertex
    public static double side(Dot p1, Dot p2, Dot x){
        double vx = p2.x-p1.x;
        double vy = p2.y-p1.y;
        double ux = x.x-p1.x;
        double uy = x.y-p1.y;
        return vx*uy-ux*vy;
    }

    public static boolean on(Dot p1, Dot p2, Dot x){
        double xu = p1.x>p2.x?p1.x:p2.x;
        double yu = p1.y>p2.y?p1.y:p2.y;
        double xl = p1.x>p2.x?p2.x:p1.x;
        double yl = p1.y>p2.y?p2.y:p1.y;
        return xu>=x.x&&x.x>=xl&&yu>=x.y&&x.y>=xl;
    }
}