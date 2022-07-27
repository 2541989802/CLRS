package greedyalgorith;

import basicdatastructure.*;
import quicksort.*;

public class ActivitySelection{
    private class Period implements Comparable<Period>{
        int s;
        int f;
        public Period(int s, int f){
            this.s = s;
            this.f = f;
        }

        public int compareTo(Period p){
            return this.f-p.f;
        }
    }
    public int[] select(int[] s, int[] f){
        if(s==null||f==null||s.length==0||f.length==0||s.length!=f.length)
            return null;
        Period[] ps = new Period[s.length];
        for(int i=0; i<s.length; i++){
            ps[i]=new Period(s[i], f[i]);
        }
        QuickSort<Period> qs = new QuickSort<>(ps);
        qs.quickSortRandom();
        ps = qs.array;
        Queue<Integer> que = new Queue<>(s.length);
        int j = 0;
        que.enqueue(j);
        for(int i=0; i<ps.length; i++){
            if(ps[j].f<=ps[i].s){
                que.enqueue(i);
                j=i;
            }
        }
        int[] res = new int[que.size()];
        for(int i=0; i<res.length; i++){
            res[i] = que.dequeue();
        }
        return res;
    }
}