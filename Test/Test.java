package test;
import lineartimesorting.*;
import selection.*;
import quicksort.*;
import basicdatastructure.*;
import numbertheoretic.*;

public class Test{
    public static void main(String[] args){
        int a = 437;
        int b = 278;
        Euclid e = new Euclid();
        Euclid.GCD res = e.extendGcd(a,b);
        System.out.println(String.format("%d = %d*%d %s %d*%d",res.d, res.x, a, res.y<0?"":"+", res.y, b));
        a = (int)(Math.random()*1000); b = (int)(Math.random()*1000); res = e.extendGcd(a,b);
        System.out.println(String.format("%d = %d*%d %s %d*%d",res.d, res.x, a, res.y<0?"":"+", res.y, b));
        a = (int)(Math.random()*1000); b = (int)(Math.random()*1000); res = e.extendGcd(a,b);
        System.out.println(String.format("%d = %d*%d %s %d*%d",res.d, res.x, a, res.y<0?"":"+", res.y, b));
        a = (int)(Math.random()*1000); b = (int)(Math.random()*1000); res = e.extendGcd(a,b);
        System.out.println(String.format("%d = %d*%d %s %d*%d",res.d, res.x, a, res.y<0?"":"+", res.y, b));
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