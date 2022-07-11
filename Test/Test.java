package test;
import lineartimesorting.*;
import selection.*;
import quicksort.*;
import basicdatastructure.*;
import numbertheoretic.*;

public class Test{
    public static void main(String[] args){
        RhoFactor rh = new RhoFactor();
        Prime pr = new Prime();
        int d, p;
        System.out.println(pr.millerRabin(2));
        /*
        d = (int)(Math.random()*1000)+1000; while(pr.millerRabin(d)) d = (int)(Math.random()*1000)+1000;
        System.out.println("\n\nnumber: "+d);
        while(d!=1){
            p = rh.rho(d);
            while(p==-1)
                p = rh.rho(d);
            System.out.print(p + ", ");
            d = d/p;
        }*/
        d = 1183;
        System.out.println("\n\nnumber: "+d);
        while(d!=1){
            p = rh.rho(d);
            while(p==-1)
                p = rh.rho(d);
            System.out.print(p + ", ");
            d = d/p;
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