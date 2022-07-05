package selection;

import quicksort.*;

public class LinearSelection<T extends Comparable<T>>{
    public T[] array;
    public LinearSelection(T[] array){
        this.array = array;
    }

    @SuppressWarnings("unchecked")
    private T selection(Object[] arr, int in){
        if(arr.length==1)
            return (T)(arr[0]);
        int len = arr.length/5+((arr.length%5==0)?0:1);
        Object[] key = new Object[len];

        for(int i = 0; i < arr.length; i=i+5){
            Object t;
            for(int j = 0; j < 5; j++){
                for(int k = 0; k < 4 && k+i < arr.length-1; k++){
                    if(((T)arr[i+k]).compareTo((T)arr[i+k+1])>=0){
                        t = arr[i+k+1];
                        arr[i+k+1] = arr[i+k];
                        arr[i+k] = t;
                    }
                }
            }
        }
        for(int i = 0; i < arr.length/5; i++){
            key[i]=arr[i*5];
        }
        if(arr.length%5 != 0){
            key[len-1]=arr[arr.length-(arr.length%5-1)/2-1];
        }
        Object mid = selection(key, len/2);
        int m = 0;
        int mp = 0;
        Object t;
        int upper = 0;
        int lower = 0;
        for(int i = 0; i < arr.length; i++){
            if(((T)mid).compareTo((T)(arr[i]))>=0){
                t = arr[i];
                arr[i]=arr[m];
                arr[m]=t;
                if(((T)mid).compareTo((T)(arr[m]))==0){
                    mp = m;
                }
                m++;
                lower++;
            } else {
                upper++;
            }
        }
        t = arr[mp];
        arr[mp]=arr[m-1];
        arr[m-1]=t;
        m = mp;
        if(lower == in){
            return (T)mid;
        }
        if(lower < in){
            key = new Object[upper];
            int index = 0;
            for(int i = lower; i < arr.length; i++){
                key[index]=arr[i];
                index++;
            }
            return selection(key,in-lower);
        } else {
            key = new Object[lower-1];
            int index = 0;
            for(int i = 0; i < lower-1; i++){
                key[index]=arr[i];
                index++;
            }
            return selection(key,in);
        }
    }

    public T select(int i){
        return selection(array, i);
    }

    public T select(T[] arr, int i){
        return selection(arr, i);
    }
}