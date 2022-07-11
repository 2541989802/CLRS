package test;
import lineartimesorting.*;
import selection.*;
import quicksort.*;
import basicdatastructure.*;
import numbertheoretic.*;

public class Test{
    public static void main(String[] args){
        /*Euclid eu = new Euclid();
        int a, b, n;
        int[] res;
        a = (int)(Math.random()*1000); b = (int)(Math.random()*1000); n = (int)(Math.random()*100)+2; res = eu.modSolver(a, b, n);
        System.out.print(String.format("%d*x = %d mod %d: ", a, b, n));
        if(res==null) System.out.print("no solution, d: "+eu.gcd(a,n)); else for(int i : res) System.out.print(i+", "); System.out.println("");
        a = (int)(Math.random()*1000); b = (int)(Math.random()*1000); n = (int)(Math.random()*100)+2; res = eu.modSolver(a, b, n);
        System.out.print(String.format("%d*x = %d mod %d: ", a, b, n));
        if(res==null) System.out.print("no solution, d: "+eu.gcd(a,n)); else for(int i : res) System.out.print(i+", "); System.out.println("");
        a = (int)(Math.random()*1000); b = (int)(Math.random()*1000); n = (int)(Math.random()*100)+2; res = eu.modSolver(a, b, n);
        System.out.print(String.format("%d*x = %d mod %d: ", a, b, n));
        if(res==null) System.out.print("no solution, d: "+eu.gcd(a,n)); else for(int i : res) System.out.print(i+", "); System.out.println("");*/
        
        SimpleMessage locA = new SimpleMessage(new int[]{100});
        SimpleMessage locB = new SimpleMessage(new int[]{100});
        int[] data = new int[25];
        for(int i=0; i < data.length; i++)
            data[i] = (int)(Math.random()*100);
        locA.data=data;
        locA.send(locB);
        locA.print();
        locB.recieve();
        locB.print();
        locB.send(locA);
        locB.print();
        locA.recieve();
        locA.print();
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