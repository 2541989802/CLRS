package test;
import lineartimesorting.*;

public class Test{
    public static void main(String[] args){
        CountingSort array = new CountingSort(new int[]{1,-3,4,2,-5,6,7,8,-1,5,35,-8,12,4,0});
        array.print();
        array.sort();
        array.print();

        array.random();
        array.print();
        array.sort();
        array.print();
        
        array.random();
        array.print();
        array.sort();
        array.print();
    }
}