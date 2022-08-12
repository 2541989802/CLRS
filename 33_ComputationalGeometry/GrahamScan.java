package computationalgeometry;

import dynamictable.DynamicTable;
import basicdatastructure.Stack;
import quicksort.QuickSort;

public class GrahamScan{

    public static Dot corner;
    public class Do implements Comparable<Do>{
        public Dot d;
        
        public Do(Dot d){
            this.d = d;
        }

        public int compareTo(Do o){
            if(corner.x==d.x&&corner.y==d.y)
                return -1;
            if(corner.x==o.d.x&&corner.y==o.d.y)
                return 1;
            double res = SegmentsIntersection.side(corner, d, o.d);
            if(res==0){
                if(d.x-corner.x==o.d.x-corner.x)
                    return 0;
                else if(Math.abs(d.x-corner.x)>Math.abs(o.d.x-corner.x))
                    return -1;
                else
                    return 1;
            }
            return res>0?-1:1;
        }
    }
    
    public DynamicTable<Do> ds = new DynamicTable<>();

    public void addDot(Dot d){
        ds.push(new Do(d));
    }

    public Dot[] check(){
        if(ds.num()<=2)
            return null;
        Do[] dos = new Do[ds.num()];
        for(int i=0; i<dos.length; i++)
            dos[i] = ds.at(i);
        corner = dos[0].d;
        for(int i=0; i<dos.length; i++){
            if(dos[i].d.y<corner.y)
                corner = dos[i].d;
            else if(dos[i].d.y==corner.y && dos[i].d.x<corner.x)
                corner = dos[i].d;
        }
        QuickSort<Do> sdos = new QuickSort<Do>(dos);
        sdos.quickSort();
        Stack<Do> res = new Stack<Do>(dos.length);
        int i=0;
        res.push(dos[i]); i++;
        while(i<dos.length && dos[i].d.x==res.at(0).d.x && dos[i].d.y==res.at(0).d.y)
            i++;
        if(i==dos.length)
            return null;
        res.push(dos[i]); i++;
        while(i<dos.length && SegmentsIntersection.side(res.at(1).d,res.at(0).d,dos[i].d)==0)
            i++;
        if(i==dos.length)
            return null;
        res.push(dos[i]); i++;
        for(; i<dos.length; i++){
            while(SegmentsIntersection.side(res.at(1).d,res.at(0).d,dos[i].d)<=0)
                res.pop();
            res.push(dos[i]);
        }
        Dot[] ret = new Dot[res.num()];
        for(int j=0; j<ret.length; j++)
            ret[ret.length-1-j] = res.at(j).d;
        return ret;
    }
}

/*
        GrahamScan grah = new GrahamScan();
        Scanner scan = new Scanner(System.in);
        String input = "";
        while(!input.equals("!q")){
            System.out.print("Mode/!q: ");input = scan.nextLine();
            if(input.equals("add")){
                System.out.println(">>>Add Point>>>");
                double p1x,p1y;
                while(!input.equals("!q")){
                    System.out.print("p1.x/!q: "); p1x = scan.nextDouble();scan.nextLine();
                    if(p1x<-100)
                        break;
                    System.out.print("p1.y/!q: "); p1y = scan.nextDouble();scan.nextLine();
                    if(p1y<-100)
                        break;
                    grah.addDot(new Dot(p1x, p1y));
                }
                System.out.println("<<<Add Point<<<");
                input = "";
            } else if(input.equals("check")) {
                Dot[] ds = grah.check();
                System.out.print("OUT: ");
                if(ds==null)
                    continue;
                for(Dot d: ds)
                    System.out.print(d+", ");
                System.out.println("");
            } else if(input.equals("new")){
                grah = new GrahamScan();
            }
        }
*/