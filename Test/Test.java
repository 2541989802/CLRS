package test;
import lineartimesorting.*;

public class Test{
    public static void main(String[] args){
        BucketSort array = new BucketSort(new int[]{1,-3,4,2,-5,6,7,8,-1,5,35,-8,12,4,0});
        array.print();
        array.sort();
        array.print();
        
        array = new BucketSort(generate(-100, 100, 1000));
        array.print();
        array.sort();
        array.print();
        chechk(array.array);
    }
    public static int[] generate(int min, int max, int len){
        int[] res = new int[len];
        for(int i = 0; i < len; i++)
            res[i] = (int)(Math.random()*(max+1-min))+min;
        return res;
    }
    public static void chechk(int[] A){
        for(int i = 1; i < A.length; i++){
            if(A[i-1]>A[i]){
                System.out. println(false);
                return;
            }
        }
        System.out.println(true);
        return;
    }
}