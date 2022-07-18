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

public class Test{
    public static void main(String[] args){
        OSIntervalTree<Integer> tree = new OSIntervalTree<Integer>();
        for(int i = 0; i < 10; i++){
            int t = (int)(Math.random()*100);
            System.out.print(t+", ");
            tree.insert(t);
        }
        System.out.println("\npart 1:");
        Integer t = tree.min();
        while(t!=null){
            System.out.print(t+", ");
            t = tree.successor(t);
        }
        System.out.println("\n"+tree.height());
        for(int i = 0; i < 10; i++){
            System.out.print(tree.osSelect(i+1)+", ");
        }
        /*System.out.println("\n"+tree.height());
        for(int i = 0; i < 900; i++){
            //tree.insert((int)(Math.random()*100));
            tree.delete(tree.min());
        }
        System.out.println("\n"+tree.height());
        for(int i = 0; i < 100; i++){
            System.out.print(tree.osSelect(i)+", ");
        }*/
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