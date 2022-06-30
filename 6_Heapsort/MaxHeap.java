package heapsort;

public class MaxHeap<T extends Comparable<T>> extends ArrayHeap<T>{
    public int size = 0;
    public boolean maxheap = false;

    public MaxHeap(T[] heap){
        /*super((T[])(new Oject[heap.length]));
        for(T t : heap){
            this.heap
        }*/
        super(heap);
        size = heap.length;
    }

    public void maxHeapify(int i){
        int l = left(i);
        int r = right(i);
        int max = i;
        if(l < size && l >= 0 && heap[l].compareTo(heap[max]) > 0){
            max = l;
        }
        if(r < size && r >= 0 && heap[r].compareTo(heap[max]) > 0){
            max = r;
        }
        if(max != i){
            T t = heap[i];
            heap[i] = heap[max];
            heap[max] = t;
            maxHeapify(max);
        }
    }

    public void buildMaxHeap(){
        for(int i = heap.length/2; i >= 0; i--){
            maxHeapify(i);
        }
    }

    public void heapSort(){
        T t;
        buildMaxHeap();
        for(int i = heap.length-1; i >= 0; i--){
            t = heap[0];
            heap[0] = heap[i];
            heap[i] = t;
            size--;
            maxHeapify(0);
        }
    }

    public void resetSize(){
        size = heap.length;
    }
}