package test;
import lineartimesorting.*;

public class Test{
    public static void main(String[] args){
        RadixSort array = new RadixSort(new int[]{1,-3,4,2,-5,6,7,8,-1,5,35,-8,12,4,0});
        array.print();
        array.sort();
        array.print();
        
        array = new RadixSort(generate(0, 5, 20));
        array.print();
        array.sort();
        array.print();
    }
    public static int[] generate(int min, int max, int len){
        int[] res = new int[len];
        for(int i = 0; i < len; i++)
            res[i] = (int)(Math.random()*(max+1-min))+min;
        return res;
    }
}