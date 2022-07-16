package test;
import lineartimesorting.*;
import selection.*;
import quicksort.*;
import basicdatastructure.*;
import numbertheoretic.*;
import hashtable.*;
import binarysearchtree.*;
import redblacktree.*;

public class Test{
    public static void main(String[] args){
        RedBlackTree<Integer> tree = new RedBlackTree<Integer>();
        tree.insert(1);
        System.out.print(tree.isBlack(1));
        System.out.print(tree.isBlack(2));
        /*
        for(int i = 0; i < 100; i++){
            tree.insert((int)(Math.random()*100));
        }
        Integer t = tree.min();
        while(t!=null){
            System.out.print(t+", ");
            t = tree.successor(t);
        }
        */
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