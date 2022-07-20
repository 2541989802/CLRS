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
            int t1 = (int)(100*Math.random()*29+17)%100;
            int t2 = (int)((50*Math.random()*17+13)%50)+t1;
            System.out.print("("+t1+":"+t2+"), ");
            tree.insert(t1, t2);
            if(!tree.check())
                throw new RuntimeException("TREE FAILURE");
        }
        tree.nodeMax();
        for(int i = 0; i < 10; i++){
            int j = (int)(150*Math.random());
            augmentingdatastructure.Node<Integer> n = tree.intervalSearch(j);
            if(n==null)
                System.out.println(j+":"+n+", ");
            else
                System.out.println(j+":("+n.data+":"+n.high+"), ");
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