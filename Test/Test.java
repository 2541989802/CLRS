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
import disjointset.*;

import java.util.*;

public class Test{
    public static void main(String[] args){
        DisjointSet<String> ds = new DisjointSet<>();
        Scanner scan = new Scanner(System.in);
        String input = "";
        while(!input.equals("!q")){
            System.out.print("Mode/!q: ");input = scan.nextLine();
            if(input.equals("make")){
                System.out.println(">>>MakeSet>>>");
                while(!input.equals("!q")){
                    System.out.print("Set/!q: "); input = scan.nextLine();
                    if(!input.equals("!q")){
                        ds.makeSet(input);
                    }
                }
                System.out.println("<<<MakeSet<<<");
                input = "";
            } else if(input.equals("union")) {
                System.out.println(">>>Union>>>");
                String b;
                while(!input.equals("!q")){
                    System.out.print("A/!q: "); input = scan.nextLine();
                    if(input.equals("!q"))
                        break;
                    System.out.print("B/!q: "); b = scan.nextLine();
                    if(b.equals("!q"))
                        break;
                    ds.union(input, b);
                }
                System.out.println("<<<Union<<<");
                input = "";
            } else if(input.equals("check")){
                System.out.println(">>>Check>>>");
                String b;
                while(!input.equals("!q")){
                    System.out.print("A/!q: "); input = scan.nextLine();
                    if(input.equals("!q"))
                        break;
                    System.out.print("B/!q: "); b = scan.nextLine();
                    if(b.equals("!q"))
                        break;
                    System.out.println("OUT:"+(ds.findSet(input)==ds.findSet(b)));
                }
                System.out.println("<<<Check<<<");
                input = "";
            }
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