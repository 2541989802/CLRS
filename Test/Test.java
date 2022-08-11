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
import maxflow.*;
import stringmatching.*;
import computationalgeometry.*;

import java.util.*;

public class Test{
    public static void main(String[] args){
        AnySegmentIntersection anySeg = new AnySegmentIntersection();
        Scanner scan = new Scanner(System.in);
        String input = "";
        while(!input.equals("!q")){
            System.out.print("Mode/!q: ");input = scan.nextLine();
            if(input.equals("add")){
                System.out.println(">>>Add Segment>>>");
                double p1x,p1y,p2x,p2y;
                while(!input.equals("!q")){
                    System.out.print("p1.x/!q: "); p1x = scan.nextDouble();scan.nextLine();
                    if(p1x<-100)
                        break;
                    System.out.print("p1.y/!q: "); p1y = scan.nextDouble();scan.nextLine();
                    if(p1y<-100)
                        break;
                    System.out.print("p2.x/!q: "); p2x = scan.nextDouble();scan.nextLine();
                    if(p2x<-100)
                        break;
                    System.out.print("p2.y/!q: "); p2y = scan.nextDouble();scan.nextLine();
                    if(p2y<-100)
                        break;
                    anySeg.addSegment(new Segment(p1x, p1y, p2x, p2y));
                }
                System.out.println("<<<Add Segment<<<");
                input = "";
            } else if(input.equals("check")) {
                System.out.println("OUT: "+anySeg.check());
            } else if(input.equals("new")){
                anySeg = new AnySegmentIntersection();
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
/*
        RelabelToFront mf = new RelabelToFront();
        Scanner scan = new Scanner(System.in);
        String input = "";
        while(!input.equals("!q")){
            System.out.print("Mode/!q: ");input = scan.nextLine();
            if(input.equals("node")){
                System.out.println(">>>Add Node>>>");
                while(!input.equals("!q")){
                    System.out.print("Add Node/!q: "); input = scan.nextLine();
                    if(!input.equals("!q")){
                        mf.addNode(input);
                    }
                }
                System.out.println("<<<Add Node<<<");
                input = "";
            } else if(input.equals("edge")) {
                System.out.println(">>>Add Edge>>>");
                String b;
                int c;
                while(!input.equals("!q")){
                    System.out.print("From/!q: "); input = scan.nextLine();
                    if(input.equals("!q"))
                        break;
                    System.out.print("To/!q: "); b = scan.nextLine();
                    if(b.equals("!q"))
                        break;
                    System.out.print("Capcity/!q: "); c = scan.nextInt(); scan.nextLine();
                    if(c<0)
                        continue;
                    mf.addEdge(input, b, c);
                }
                System.out.println("<<<Add Edge<<<");
                input = "";
            } else if(input.equals("check")){
                System.out.println(">>>Check>>>");
                String b;
                while(!input.equals("!q")){
                    System.out.print("From/!q: "); input = scan.nextLine();
                    if(input.equals("!q"))
                        break;
                    System.out.print("To/!q: "); b = scan.nextLine();
                    if(b.equals("!q"))
                        break;
                    System.out.println("Flow:"+(mf.check(input, b)));
                }
                System.out.println("<<<Check<<<");
                input = "";
            } else if(input.equals("run")){
                String b;
                System.out.print("S/!q: "); input = scan.nextLine();
                if(input.equals("!q"))
                    continue;
                System.out.print("T/!q: "); b = scan.nextLine();
                if(b.equals("!q"))
                    continue;
                mf.calculate(input, b);
            } else if(input.equals("new")){
                mf = new RelabelToFront();
            }
*/