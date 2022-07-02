package lineartimesorting;

import java.util.ArrayList;

public class BucketSort{
    private Data[] key;
    public int[] array;
    private class Data{
        public double key;
        public int index;
        public Data(double key, int index){
            this.key = key;
            this.index = index;
        }
    }

    public BucketSort(int[] array){
        this.array = array;
    }

    private int setup(){
        int m = 0;
        int s = 0;
        for(int i = 0; i < array.length; i++){
            if(m < array[i])
                m = array[i];
            if(s > array[i])
                s = array[i];
        }
        m = m + 1 -s;
        key = new Data[array.length];
        for(int i = 0; i < key.length; i++){
            key[i] = new Data((array[i]-s)/(double)m,i);
        }
        return m;
    }

    @SuppressWarnings("unchecked")
    public void sort(){
        int m = setup();
        ArrayList<Data>[] bucket = (ArrayList<Data>[])new ArrayList[m];
        {
            int index;
            for(int i = 0; i < key.length; i++){
                index = (int)(key[i].key*m);
                if(bucket[index] == null)
                    bucket[index] = new ArrayList<Data>();
                bucket[index].add(key[i]);
            }
        }
        for(int i = 0; i < bucket.length; i++){
            if(bucket[i] != null){
                bucket[i].sort((a,b)->{return (a.key < b.key)?-1:((a.key == b.key)?0:1);});
            }
        }
        int index=0;
        int[] res = new int[array.length];
        for(int i = 0; i < bucket.length; i++){
            if(bucket[i] != null){
                for(int j=0; j < bucket[i].size(); j++){
                    res[index] = array[bucket[i].get(j).index];
                    index++;
                }
            }
        }
        array = res;
    }
    
    public void random(){
        int len = array.length;
        int t;
        int index;
        for(int i = 0; i < len; i++){
            index = (int)(Math.random()*(len-i))+i;
            t = array[i];
            array[i] = array[index];
            array[index] = t;
        }
    }
    public void print(){
        System.out.println("");
        for(int i = 0; i < array.length; i++){
            System.out.print(array[i] + ", ");
        }
        System.out.println("");
    }
}