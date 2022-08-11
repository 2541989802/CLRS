package computationalgeometry;

import computationalgeometry.*;
import redblacktree.*;
import dynamictable.*;
import quicksort.QuickSort;

public class AnySegmentIntersection{
    public static double relativeX;
    public class Seg implements Comparable<Seg>{
        public Do p1;
        public Do p2;
        public redblacktree.Node<Seg> node;
        //public boolean print=false;
        public Seg(Segment s){
            p1 = new Do(s.p1, this, true);
            p2 = new Do(s.p2, this, false);
        }
        public int compareTo(Seg o){
            if(SegmentsIntersection.check(new Segment(p1.x,p1.y,p2.x,p2.y),new Segment(o.p1.x,o.p1.y,o.p2.x,o.p2.y)))
                return 0;
            double y = p1.y+(p2.y-p1.y)*(relativeX-p1.x)/(p2.x-p1.x);
            double oy = o.p1.y+(o.p2.y-o.p1.y)*(relativeX-o.p1.x)/(o.p2.x-o.p1.x);
            if(y==oy)
                return 0;
            return y>oy?1:-1;
        }

        public String toString(){
            return "{"+p1+","+p2+"}";
        }
    }

    public class Do implements Comparable<Do>{
        public boolean left;
        public double x;
        public double y;
        public Seg seg;
        public Do(Dot x, Seg s, boolean l){
            this.x = x.x;
            y = x.y;
            seg = s;
            left = l;
        }

        public String toString(){
            return "("+x+", "+y+"|"+left+")";
        }

        public int compareTo(Do d){
            if(x!=d.x)
                return (x-d.x)>0?1:-1;
            if(left!=d.left)
                return left?-1:1;
            if(y==d.y)
                return 0;
            return (y-d.y)>0?1:-1;
        }
    }

    public DynamicTable<Do> dots = new DynamicTable<Do>();

    public void addSegment(Segment seg){
        Seg s = new Seg(seg);
        dots.push(s.p1);
        dots.push(s.p2);
    }

    public boolean check(){
        Do[] ds = new Do[dots.num()];
        for(int i=0; i<dots.num(); i++)
            ds[i]=dots.at(i);
        QuickSort<Do> sdots = new QuickSort<Do>(ds);
        sdots.quickSort();
        //for(Do d: ds)
        //    System.out.print(d+", ");
        //System.out.println("");
        RedBlackTree<Seg> segs = new RedBlackTree<Seg>();
        for(int i=0; i<ds.length; i++){
            relativeX=ds[i].x;
            if(ds[i].left){
                ds[i].seg.node = segs.insert(ds[i].seg);
                binarysearchtree.Node<Seg> a, b;
                a = segs.predecessor_h(ds[i].seg.node);
                if(a!=null && ds[i].seg.compareTo(a.data)==0){
                    System.out.println("true form A1:"+a.data+","+ds[i].seg);
                    return true;
                }
                a = segs.successor_h(ds[i].seg.node);
                if(a!=null && ds[i].seg.compareTo(a.data)==0){
                    System.out.println("true form A2:"+a.data+","+ds[i].seg);
                    return true;
                }
            } else {
                binarysearchtree.Node<Seg> a, b;
                a = segs.predecessor_h(ds[i].seg.node);
                b = segs.successor_h(ds[i].seg.node);
                //if(a!=null)a.print = true;
                //System.out.println("Delete: "+ds[i].seg);
                if(a!=null&&b!=null&&a.data.compareTo(b.data)==0){
                    System.out.println("true form B:"+a.data+","+b.data);
                    return true;
                }
                segs.delete(ds[i].seg.node);
                //if(a!=null)a.print = false;
            }
        }
        return false;
    }
}

/*
        AnySegmentIntersection anySeg = new AnySegmentIntersection();
        Scanner scan = new Scanner(System.in);
        String input = "";
        while(!input.equals("!q")){
            System.out.print("Mode/!q: ");input = scan.nextLine();
            if(input.equals("add")){
                System.out.println(">>>Add Segment>>>");
                double p1x,p1y,p2x,p2y;
                while(!input.equals("!q")){
                    System.out.print("p1.x/!q: "); p1x = scan.nextDouble();scan.nextLine();
                    if(p1x<-100)
                        break;
                    System.out.print("p1.y/!q: "); p1y = scan.nextDouble();scan.nextLine();
                    if(p1y<-100)
                        break;
                    System.out.print("p2.x/!q: "); p2x = scan.nextDouble();scan.nextLine();
                    if(p2x<-100)
                        break;
                    System.out.print("p2.y/!q: "); p2y = scan.nextDouble();scan.nextLine();
                    if(p2y<-100)
                        break;
                    anySeg.addSegment(new Segment(p1x, p1y, p2x, p2y));
                }
                System.out.println("<<<Add Segment<<<");
                input = "";
            } else if(input.equals("check")) {
                System.out.println("OUT: "+anySeg.check());
            } else if(input.equals("new")){
                anySeg = new AnySegmentIntersection();
            }
        }
*/