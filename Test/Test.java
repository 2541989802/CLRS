package test;
import lineartimesorting.*;
import selection.*;
import quicksort.*;
import heapsort.*;
import basicdatastructure.*;
import numbertheoretic.*;
import hashtable.*;
import binarysearchtree.*;
import redblacktree.*;
import augmentingdatastructure.*;
import dynamicprogramming.*;
import greedyalgorithm.*;

public class Test{
    public static void main(String[] args){
        int[] d={ 4, 2, 4, 3, 1, 4, 6};
        int[] w={70,60,50,40,30,20,10};
        UnitTaskScheduling uts = new UnitTaskScheduling();
        for(int i: d)
            System.out.print(i+", ");
        System.out.println("");
        for(int i: w)
            System.out.print(i+", ");
        System.out.println("");
        for(int i: uts.schedule(d,w))
            System.out.print(i+", ");
        System.out.println("");
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