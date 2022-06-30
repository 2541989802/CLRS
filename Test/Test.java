package test;
import heapsort.*;

public class Test{
    public static void main(String[] args){
        //MaxHeap<Double> heap = new MaxHeap<>(new Double[]{0.0,1.0,2.0,3.0,4.0,5.0,6.0,7.0,8.0,9.0,10.0,11.0});
        PriorityQueues<Integer> heap = new PriorityQueues<>(new Integer[]{3,56,76,4,5,6,7,9,0,0,0,1});
        //heap = new MaxHeap<>(new Integer[]{11,10,9,8,7,6,5,4,3,2,1});
        System.out.println(heap.parent(0)+" "+heap.parent(1));
        System.out.println(heap.parent(10)+" "+heap.parent(11));
        System.out.println(heap.left(0)+" "+heap.right(0));
        System.out.println(heap.left(5)+" "+heap.right(5));
        //heap.maxHeapify(0);
        for(Integer i : heap.heap){
            System.out.print(i + " ");
        }
        System.out.println("");
        heap.heapSort();
        for(Integer i : heap.heap){
            System.out.print(i + " ");
        }
        System.out.println("");
        heap.resetSize();
        heap.buildMaxHeap();
        for(Integer i : heap.heap){
            System.out.print(i + " ");
        }
        System.out.println("");        
        while(heap.size > 0){
            System.out.print(heap.extractMax() + " ");
        }
    }
}