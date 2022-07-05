package test;
import lineartimesorting.*;
import selection.*;
import quicksort.*;
import basicdatastructure.*;

public class Test{
    public static void main(String[] args){
        Queue<Integer> qi = new Queue<>(intAtoIntA(generate(0,9,10)));
        int i = 10;
        while(i!=0){
            System.out.print(qi.dequeue()+", ");
            i--;
        }
        System.out.println("");
        while(i!=10){
            qi.enqueue(i);
            i++;
        }
        while(i!=0){
            System.out.print(qi.dequeue()+", ");
            i--;
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