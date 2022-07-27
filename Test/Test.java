package test;
import lineartimesorting.*;
import selection.*;
import quicksort.*;
import basicdatastructure.*;
import numbertheoretic.*;
import hashtable.*;
import binarysearchtree.*;
import redblacktree.*;
import augmentingdatastructure.*;
import dynamicprogramming.*;
import greedyalgorith.*;

public class Test{
    public static void main(String[] args){
        ActivitySelection as = new ActivitySelection();
        int[] s = {1,3,0,5,3,5, 6, 8, 6, 2,12};
        int[] f = {4,5,6,7,9,9,10,11,12,14,16};
        for(int i:as.select(s, f)){
            System.out.print(i+", ");
        }
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