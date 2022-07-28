package greedyalgorithm;

import quicksort.*;

public class UnitTaskScheduling{
    public class Task implements Comparable<Task>{
        public int d;
        public int w;
        public int id;

        public Task(int d, int w, int id){
            this.d = d;
            this.w = w;
            this.id = id;
        }

        public int compareTo(Task t){
            return w-t.w;
        }
    }
    public int[] schedule(int[] d, int[] w){
        if(d.length!=w.length)
            return null;
        Task[] in = new Task[d.length];
        Task[] res = new Task[d.length];
        for(int i=0; i<d.length; i++){
            if(d[i]<=0)
                throw new RuntimeException("UnitTaskScheduling.schedule(): this function only support positive deadline yet");
            in[i] = new Task(d[i],w[i],i+1);
        }
        QuickSort<Task> qs = new QuickSort<Task>(in);
        qs.acesending=false;
        qs.quickSortRandom();
        in = qs.array;
        for(int i=0; i<d.length; i++){
            for(int j=in[i].d-1; j>=0 && in[i]!=null; j--){
                if(j<d.length && res[j]==null){
                    res[j]=in[i];
                    in[i]=null;
                }
            }
        }
        int size=0;
        for(int i=0; i<d.length; i++){
            if(res[i]!=null){
                res[size]=res[i];
                size++;
            }
        }
        for(int i=0; i<d.length; i++){
            if(in[i]!=null){
                res[size]=in[i];
                size++;
            }
        }
        int[] ret = new int[d.length];
        for(int i=0; i<d.length; i++){
            ret[i]=res[i].id;
        }
        return ret;
    }
}