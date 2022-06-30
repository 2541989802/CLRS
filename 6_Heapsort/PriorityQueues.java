package heapsort;

public class PriorityQueues<T extends Comparable<T>> extends MaxHeap<T>{
    public PriorityQueues(T[] heap){
        super(heap);
        buildMaxHeap();
    }

    public T heapMax(){
        return heap[0];
    }

    public T extractMax(){
        T res = heap[0];
        if(size<=0)
            throw new RuntimeException("PriorityQueues.extractMax(): Heap Underflow");
        heap[0] = heap[size-1];
        size--;
        maxHeapify(0);
        return res;
    }

}