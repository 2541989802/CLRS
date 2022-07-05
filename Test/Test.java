package test;
import lineartimesorting.*;
import selection.*;
import quicksort.*;
import java.util.ArrayList;

public class Test{
    public static void main(String[] args){
        /*
        BucketSort array = new BucketSort(new int[]{1,-3,4,2,-5,6,7,8,-1,5,35,-8,12,4,0});
        array.print();
        array.sort();
        array.print();
        array.random();
        ExpectLinearSelection<Integer> selector = new ExpectLinearSelection<>(intAtoIntA(array.array));
        for(int i = 0; i < selector.array.length; i++){
            System.out.print(selector.select(i+1)+ ", ");
        }
        
        array = new BucketSort(generate(-100, 100, 1000));
        array.print();
        array.sort();
        array.print();
        chechk(array.array);
        array.random();
        selector = new ExpectLinearSelection<>(intAtoIntA(array.array));
        for(int i = 0; i < selector.array.length; i++){
            System.out.print(selector.select(i+1) + ", ");
        }*/
        LinearSelection<Integer> sel = new LinearSelection<Integer>(intAtoIntA(generate(-1000,1000,1000)));
        int[] array = new int[sel.array.length];
        for(int i = 0; i < sel.array.length; i++){
            array[i]=sel.select(i+1);
            System.out.print(array[i]+", ");
        }
        System.out.println("");
        check(array);
        /*QuickSort<Integer> quick = new QuickSort<Integer>(sel.array);
        quick.quickSort();
        quick.print();*/

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