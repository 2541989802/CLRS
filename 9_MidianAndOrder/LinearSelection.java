package selection;

import quicksort.*;

public class LinearSelection<T extends Comparable<T>>{
    public T[] array;
    public LinearSelection(T[] array){
        this.array = array;
    }

    private T selection(Object[] arr, int in){
        System.out.print("\nhead: ");
        for(Object o: arr){
            System.out.print(o+", ");
        }
        System.out.println("");

        if(arr.length==1)
            return (T)(arr[0]);
        if(arr.length < 1)
            throw new RuntimeException("");
        int len = arr.length/5*5+((arr.length%5==0)?0:5);
        Object[] array = new Object[len];
        Object[] key = new Object[len/5];

        for(int i = 0; i < arr.length; i=i+5){
            Object t;
            for(int j = 0; j < 5; j++){
                for(int k = 0; k < 4&&k+i < arr.length-1; k++){
                    if(((T)arr[i+k]).compareTo((T)arr[i+k+1])>=0){
                        t = arr[i+k+1];
                        arr[i+k+1] = arr[i+k];
                        arr[i+k] = t;
                    }
                }
            }
        }

        //System.out.print("key: ");
        for(int i = 0; i < arr.length/5*5; i++){
            array[i]=arr[i];
            if(i%5==2)
                key[i/5]=arr[i]; 
            //System.out.print(key[i/5] + " : " + array[i] + " : " + arr[i] + ", ");
        }
        //System.out.println("");

        for(int i = arr.length/5*5; i < arr.length; i++){
            array[i+2-(arr.length%5)/2]=arr[i];
            if((i+2-(arr.length%5)/2)%5==2)
                key[i/5]=array[i+2-(arr.length%5)/2];
        }
        
        System.out.print("Mid: ");
        for(Object o: array){
            System.out.print(o+", ");
        }
        System.out.print("\nKey: ");
        for(Object o: key)
            System.out.print(o+", ");
        System.out.println("");
        
        Object mid = selection(key, (len/5)/2+1);
        /*
        if(mid==null){
            System.out.printf("len: %d, len/5: %d, mid: %s\n",len, len/5, (T)mid);
            for(Object o: key)
                System.out.print(o+", ");
            System.out.println("");
        }
        */
        int m = 0;
        for(int i = 0; i < len/5; i++){
            if(((T)mid).compareTo((T)(array[i*5+2]))>0){
                Object t;
                for(int j = 0; j < 5; j++){
                    t = array[i*5+j];
                    array[i*5+j]=array[m*5+j];
                    array[m*5+j]=t;
                }
                m++;
            }
        }
        int upper = 0;
        int lower = 0;
        m = len/5/2;
        for(int i = 0; i < len; i++){
            if((i%5>2||i/5>m)&&array[i]!=null){
                upper++;
            }
            if((i%5<2||i/5<m)&&array[i]!=null){
                lower++;
            }
        }
        Object res;
        System.out.printf("\nlower: %d, in: %d\n",lower, in);
        if(arr.length-lower < arr.length-in){
            if(lower != 0){
                key = new Object[lower];
                int index = 0;
                for(int i = 0; i < array.length; i++){
                    if((i%5<2||i/5<m)&&array[i]!=null){
                        key[index]=array[i];
                        index++;
                    }
                }
                res = selection(key,in);
            } else {
                res = mid;
            }
        }
        else {//if (arr.length-upper < in){
            if(upper != 0){
                key = new Object[upper];
                int index = 0;
                for(int i = 0; i < array.length; i++){
                    if((i%5>2||i/5>m)&&array[i]!=null){
                        key[index]=array[i];
                        index++;
                    }
                }
                System.out.print("\n3rd call: ");
                for(Object o: key){
                    System.out.print(o+", ");
                }
                System.out.println("");
                for(Object o: array){
                    System.out.print(o+", ");
                }
                res = selection(key,in-(arr.length-upper));
            } else {
                res = mid;
            }
        }
        return (T)res;
    }

    public T select(int i){
        return selection(array, i);
    }

    public T select(T[] arr, int i){
        return selection(arr, i);
    }
}