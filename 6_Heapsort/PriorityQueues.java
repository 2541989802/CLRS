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

    public void increaseKey(int i, T key){
        if(i < 0 || i >= size){
            throw new IndexOutOfBoundsException("PriorityQueues.increaseKey()");
        }
        if(key == null && heap[i] == null){
            return;
        }
        if(maxheap &&(heap[i] != null && heap[i].compareTo(key) > 0) || (key != null && key.compareTo(heap[i]) < 0)){
            throw new RuntimeException("PriorityQueues.increaseKey(): new key is smaller than old key when maxheap is true");
        }
        if(!maxheap &&(heap[i] != null && heap[i].compareTo(key) < 0) || (key != null && key.compareTo(heap[i]) > 0)){
            throw new RuntimeException("PriorityQueues.increaseKey(): new key is greater than old key when maxheap is false");
        }
        heap[i] = key;
        T t = heap[i];
        while(i > 0 && heap[i].compareTo(heap[parent(i)]) > 0){
            heap[i] = heap[parent(i)];
            i = parent(i);
            heap[i] = t;
        }
    }

    public void insertKey(T key){
        if(size < heap.length){
            heap[size] = key;
            size++;
            increaseKey(size-1, key);
        }
    }

    public void simplePrint(){
        int l = 0;
        int r = 0;
        int t = 1;
        while(t < size){
            t = t*2 + 1;
        }
        int t2 = t;
        System.out.println("");
        for(int i = 0; i < t2/2; i++)
            System.out.print("========");
        System.out.println("");
        for(int i = 0; i < size; i++){
            if(l == i){
                t = t/2;
                l = left(l);
            }
            for(int j = 0; j < (t+1)/2; j++){
                System.out.print("\t");
            }
            System.out.print(heap[i] + "\t");
            if(r == i){
                System.out.println("");
                r = right(r);
            } else {
                for(int j = 0; j < (t+1)/2-1; j++){
                    System.out.print("\t");
                }
            }
        }
        System.out.println("");
        for(int i = 0; i < t2/2; i++)
            System.out.print("========");
        System.out.println("");

    }

}