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
import dynamictable.*;
import btree.*;
import fibonacciheap.*;

public class Test{
    public static void main(String[] args){
        FibonacciHeap<Integer> fh = new FibonacciHeap<Integer>();
        int[] ia = generate(0,100,10000);
        for(int i: ia){
            fh.insert(i);
            //System.out.print(i+", ");
        }
        System.out.println("");
        int i,j;
        i=fh.extractMin();
        while(fh.num>0){
            j=fh.extractMin();
            if(i>j)
                System.out.print(""+false);
            i=j;
            //System.out.print(fh.extractMin()+", ");
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