package quicksort;
public class QuickSort<T extends Comparable<T>>{
    public T[] array;
    public boolean acesending = true;

    public QuickSort(){}
    public QuickSort(T[] array){
        this.array = array;
    }
    //inclusive
    public int partition(T[] array, int l, int r, boolean random){
        if(random){
            random_h(array, l, r);
        }
        int m = l;
        while(array[m] == null && m <= r){
            m++;
        }
        if(m > r){
            return -1;
        }
        T p = array[m];
        array[m] = array[l];
        array[l] = p;
        m = l;
        T t;
        if(acesending){
            for(int i = l+1; i <= r; i++){
                if(p.compareTo(array[i]) > 0){
                    m++;
                    t = array[i];
                    array[i] = array[m];
                    array[m] = t;
                }
            }
        } else {
            for(int i = l+1; i <= r; i++){
                if(p.compareTo(array[i]) < 0){
                    m++;
                    t = array[i];
                    array[i] = array[m];
                    array[m] = t;
                }
            }
        }
        array[l] = array[m];
        array[m] = p;
        return m;
    }

    private void random_h(T[] array, int l, int r){
        int len = r+1-l;
        T t;
        int index;
        for(int i = 0; i < len; i++){
            index = (int)(Math.random()*(len-i))+i;
            t = array[i+l];
            array[i+l] = array[index+l];
            array[index+l] = t;
        }
    }

    public void random(){
        random_h(array, 0, array.length-1);
        return;
    }

    public void quickSort_h(T[] array, int i, int j, boolean random){
        if(i >= j)
            return;
        int m = partition(array, i,j, random);
        quickSort_h(array, i,m-1, random);
        quickSort_h(array, m+1, j, random);
        return;
    }

    public void quickSort() {
        quickSort_h(array, 0, array.length-1, false);
        return;
    }

    public void quickSortRandom() {
        quickSort_h(array, 0, array.length-1, true);
        return;
    }

    public void print(){
        System.out.println("");
        for(T e : array){
            System.out.print(e + ", ");
        }
        System.out.println("");
    }
}