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

public class Test{
    public static void main(String[] args){
        BPlusTree<Integer, Integer> bt = new BPlusTree<Integer, Integer>((int)(Math.random()*20+2));
        int[] input = generate(0,100, (int)(Math.random()*1000+100));
        System.out.println("");
        for(int i=0; i<input.length; i++){
            bt.insertKey(input[i], input[i]*10);
        }
        bt.print();
        
        for(int i=0; i<input.length; i++){
            if(!bt.findData(input[i]).equals(input[i]*10)){
                System.out.println("ERROR with KEY: "+input[i]+", Data:"+i);
            }
        }
        System.out.println("After Insertion");
        for(int i=0; i<input.length; i++){
            if(bt.findData(input[i])!=input[i]*10){
                System.out.println("ERROR with KEY: "+input[i]);
            }
            bt.deleteKey(input[i]);
        }
        bt.print();
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