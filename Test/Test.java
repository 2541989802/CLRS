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
import vanemdeboas.*;

public class Test{
    public static void main(String[] args){
        VanEmdeBoas veb = new VanEmdeBoas((int)Math.pow(2,15));
        int[] input = generate(0,veb.u-1,1000);
        int min = veb.u-1;
        int max = 0;
        for(int i=0; i<input.length; i++){
            if(min>input[i])
                min=input[i];
            if(max<input[i])
                max=input[i];
            //System.out.print("("+input[i]+":"+veb.member(input[i])+"), ");
        }
        System.out.println("Min: "+min+"\tMax: "+max+"\tlen: "+input.length);
        for(int i=0; i<input.length; i++)
            veb.insert(input[i]);
        if(!veb.check()){
            for(int i=0; i<veb.u; i++){
                if(veb.member(i))
                    System.out.print(i+", ");
            }
            throw new RuntimeException("\n");
        }

        System.out.println("\nAfter Insert");
        for(int i=0; i<input.length; i++){
            System.out.print("("+input[i]+":"+veb.member(input[i])+"), ");
        }
        System.out.println("\nBefore Delete 1");
        int next = 0;
        if(veb.member(next))
            System.out.print(next+", ");
        while(next!=-1){
            next = veb.successor(next);
            if(next!=-1)
                System.out.print(next+", ");
        }
        System.out.println("\nBefore Delete 2");
        next = veb.u-1;
        if(veb.member(next))
            System.out.print(next+", ");
        while(next!=-1){
            next = veb.predecessor(next);
            if(next!=-1)
                System.out.print(next+", ");
            if(!veb.member(next))
                throw new RuntimeException("");
        }
        for(int i=0; i<input.length; i++){
            veb.delete(input[i]);
        }
        System.out.println("\nAfter Delete");
        if(!veb.check()){
            System.out.println("");
            for(int i=0; i<veb.u; i++){
                if(veb.member(i))
                    System.out.print(i+", ");
            }
            throw new RuntimeException("\n");
        }
        for(int i=0; i<input.length; i++){
            if(veb.member(input[i]))
                System.out.print("("+input[i]+":"+veb.member(input[i])+"), ");
        }
        System.out.println("\nAfter Delete 2");
        next = 0;
        if(veb.member(next))
            System.out.print(next+", ");
        while(next!=-1){
            next = veb.successor(next);
            if(next!=-1)
                System.out.print(next+", ");
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