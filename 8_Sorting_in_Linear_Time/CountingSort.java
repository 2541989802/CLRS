package lineartimesorting;
public class CountingSort{
    public int[] array;
    
    public CountingSort(int[] array){
        this.array = array;
    }

    public void sort(){
        int k = 0;
        int m = 0;
        for(int i = 0; i < array.length; i++){
            if(array[i] > k){
                k = array[i];
            }
            if(array[i] < m){
                m = array[i];
            }
        }
        int[] count = new int[k-m+1];
        for(int i = 0; i < array.length; i++){
            count[array[i]-m] = count[array[i]-m] + 1;
        }
        int[] res = new int[array.length];
        for(int i = 1; i < count.length; i++){
            count[i] = count[i] + count[i-1];
        }
        for(int i = array.length-1; i >=0; i--){
            res[count[array[i]-m]-1] = array[i];
            count[array[i]-m] = count[array[i]-m] - 1;
        }
        array = res;
    }

    public void random(){
        int len = array.length;
        int t;
        int index;
        for(int i = 0; i < len; i++){
            index = (int)(Math.random()*(len-i))+i;
            t = array[i];
            array[i] = array[index];
            array[index] = t;
        }
    }
    public void print(){
        System.out.println("");
        for(int i = 0; i < array.length; i++){
            System.out.print(array[i] + ", ");
        }
        System.out.println("");
    }
}