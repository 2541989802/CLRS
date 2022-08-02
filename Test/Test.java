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
        BTree<Integer> bt = new BTree<Integer>(2);
        int[] input = new int[100];//generate(0,100, 100);
        for(int i=0; i<input.length; i++){
            input[i] = (i*21)%100;
            System.out.print(input[i]+",");
        }
        System.out.println("");
        for(int i=0; i<input.length; i++){
            //System.out.println(i);
            bt.insertKey(input[(7*i)%input.length]);
        }
        bt.print();
        System.out.println("After Insertion");
        for(int i=0; i<input.length; i++){
            //System.out.println(i);
            System.out.print(bt.findKey(input[(3*i)%input.length])+", ");
            bt.deleteKey(input[(3*i)%input.length]);
            System.out.println(bt.findKey(input[(3*i)%input.length]));
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