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

public class Test{
    public static void main(String[] args){
        double[] p={0.15, 0.1, 0.05, 0.1, 0.2};
        double[] q={0.05, 0.1, 0.05, 0.05, 0.05, 0.1};
        OptimalBST bst = new OptimalBST();
        int[][] root = bst.root(p,q);
        double[][] expect = bst.expect(p,q);
        for(int i=0; i<root.length; i++){
            for(int j=0; j<root[0].length; j++)
                System.out.print(root[i][j]+", ");
            System.out.println("");
        }
        System.out.println("");
        for(int i=0; i<expect.length; i++){
            for(int j=0; j<expect[0].length; j++)
                System.out.print(String.format("%.2f, ",expect[i][j]));
            System.out.println("");
        }
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