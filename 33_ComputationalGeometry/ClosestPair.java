package computationalgeometry;

import dynamictable.DynamicTable;
import quicksort.QuickSort;

public class ClosestPair{
    public class DoX implements Comparable<DoX>{
        public Dot d;

        public DoX(Dot d){
            this.d=d;
        }

        public int compareTo(DoX o){
            double res = d.x-o.d.x;
            if(res==0){
                res = d.y-o.d.y;
                if(res==0)
                    return 0;
                return res>0?1:-1;
            }
            return res>0?1:-1;
        }

        public double dis(DoX o){
            return Math.pow((d.x-o.d.x)*(d.x-o.d.x)+(d.y-o.d.y)*(d.y-o.d.y),0.5);
        }
    }

    public class DoY implements Comparable<DoY>{
        public Dot d;

        public DoY(Dot d){
            this.d=d;
        }

        public int compareTo(DoY o){
            double res = d.y-o.d.y;
            if(res==0){
                res = d.x-o.d.x;
                if(res==0)
                    return 0;
                return res>0?1:-1;
            }
            return res>0?1:-1;
        }

        public double dis(DoY o){
            return Math.pow((d.x-o.d.x)*(d.x-o.d.x)+(d.y-o.d.y)*(d.y-o.d.y),0.5);
        }
    }

    public DynamicTable<Dot> ds = new DynamicTable<>();
    public DoX[] dsx;
    public DoY[] dsy;
    public double mdis;

    public void addDot(Dot d){
        ds.push(d);
    }

    public double check(){
        dsx = new DoX[ds.num()];
        dsy = new DoY[ds.num()];
        for(int i=0; i<ds.num(); i++){
            dsx[i] = new DoX(ds.at(i));
            dsy[i] = new DoY(ds.at(i));
        }
        QuickSort<DoX> qsx = new QuickSort<DoX>(dsx);
        QuickSort<DoY> qsy = new QuickSort<DoY>(dsy);
        qsx.quickSort();
        qsy.quickSort();
        double mdx = dsx[dsx.length-1].d.x-dsx[0].d.x;
        double mdy = dsy[dsy.length-1].d.y-dsy[0].d.y;
        mdis = Math.pow(mdx*mdx+mdy*mdy, 0.5);
        //for(int i=0; i<dsy.length; i++){
        //    System.out.print(dsy[i].d+", ");
        //}
        //System.out.println("");
        return check_h(0, ds.num()-1);
    }

    public double check_h(int l, int r){
        if(r-l<3){
            double min = mdis;
            for(int i=l; i<=r; i++){
                for(int j=i+1; j<=r; j++){
                    double t = dsx[i].dis(dsx[j]);
                    if(t<min)
                        min = t;
                }
            }
            return min;
        } else {
            int m = (l+r)/2;
            DoX mid = dsx[m];
            DoY[] left = new DoY[m-l+1];
            int li=0;
            DoY[] right = new DoY[r-m];
            int ri=0;
            for(int i=l; i<=r; i++){
                if(mid.compareTo(new DoX(dsy[i].d))<=0){
                    if(li<left.length){
                        left[li] = dsy[i];
                        li++;
                    } else {
                        right[ri] = dsy[i];
                        ri++;
                    }
                } else {
                    if(ri<right.length){
                        right[ri] = dsy[i];
                        ri++;
                    } else {
                        left[li] = dsy[i];
                        li++;
                    }
                }
            }
            for(int i=0; i<left.length; i++){
                dsy[l+i] = left[i];
            }
            for(int i=0; i<right.length; i++){
                dsy[l+left.length+i] = right[i];
            }
            double min = check_h(l,m);
            double min2 = check_h(m+1,r);
            //System.out.println(String.format("%d|%d|%d,min:%.2f,min2:%.2f,mid-x:%.2f",l,m,r,min,min2,mid.d.x));
            if(min2<min)
                min=min2;
            li=l; ri=m+1;
            DoY[] temp = new DoY[r-l+1];
            int count = 0;
            for(int i=0; i<temp.length;i++){
                if(li<=m && ri<=r){
                    if(dsy[li].compareTo(dsy[ri])<=0){
                        temp[i] = dsy[li];
                        li++;
                    } else {
                        temp[i] = dsy[ri];
                        ri++;
                    }
                } else if(li<=m){
                    temp[i] = dsy[li];
                    li++;
                } else if(ri<=r){
                    temp[i] = dsy[ri];
                    ri++;
                }
                //if(temp[i].d.x==5&&temp[i].d.y==4)
                //    System.out.println("exist:"+temp[i].d+","+mid.d);
                //if(temp[i].d.x==6&&temp[i].d.y==4)
                //    System.out.println("exist:"+temp[i].d+","+mid.d);
                if(Math.abs(temp[i].d.x-mid.d.x)<=min)
                    count++;
            }
            DoY[] temp2 = new DoY[count];
            int i2=0;
            for(int i=0; i<temp.length; i++){
                dsy[l+i]=temp[i];
                if(Math.abs(temp[i].d.x-mid.d.x)<=min){
                    temp2[i2]=temp[i];
                    i2++;
                }
            }
            for(int i=0; i<temp2.length; i++){
                for(int j=1; j<8 && j+i<temp2.length;j++){
                    double t = temp2[i].dis(temp2[i+j]);
                    if(t<min)
                        min = t;
                }
            }
            return min;
        }
    }
}

/*
        ClosestPair cp = new ClosestPair();
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
                    cp.addDot(new Dot(p1x, p1y));
                }
                System.out.println("<<<Add Point<<<");
                input = "";
            } else if(input.equals("check")) {
                System.out.println("OUT: "+cp.check());
            } else if(input.equals("new")){
                cp = new ClosestPair();
            }
        }
*/