package test;
import lineartimesorting.*;
import selection.*;
import quicksort.*;
import basicdatastructure.*;
import numbertheoretic.*;
import hashtable.*;

public class Test{
    public static void main(String[] args){
        int[] key = new int[1000];
        Integer[] data = new Integer[key.length];
        for(int i = 0; i < key.length; i++){
            key[i] = (int)(Math.random()*100)+(i==0?0:key[i-1]+1);
            data[i] = i*10;
        }
        PerfectHash<Integer> table = new PerfectHash<Integer>(data, key);
        for(int i = 0; i < key.length; i++){
            System.out.print(table.search(key[i])+", ");
        }
        System.out.println("\n"+table.size());
    }

    public static int[] generate(int min, int max, int len){
        int[] res = new int[len];
        for(int i = 0; i < len; i++)
            res[i] = (int)(Math.random()*(max+1-min))+min;
        return res;
    }

    public static void check(int[] A){
        for(int i = 1; i < A.length; i++){
            if(A[i-1]>A[i]){
                System.out. println(false);
                return;
            }
        }
        System.out.println(true);
        return;
    }

    public static Integer[] intAtoIntA(int[] a){
        Integer[] res = new Integer[a.length];
        for(int i = 0; i < a.length; i++)
            res[i] = a[i];
        return res;
    }
    public static String[] intAtoStringA(int[] a){
        String[] res = new String[a.length];
        for(int i = 0; i < a.length; i++)
            res[i] = String.format("%d",a[i]);
        return res;
    }
}