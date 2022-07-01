package lineartimesorting;

public class RadixSort{
    public int[] array;
    public int[] key;
    
    public RadixSort(int[] array){
        this.array = array;
    }
    
    public void sort(){
        int d = 1;
        int b = 10;
        while(d != 0){
            d = 0;
            key = new int[array.length];
            for(int i = 0; i < key.length; i++){
                key[i] = (array[i] % b)*10/b;
                if(key[i] != 0)
                    d = 1;
            }
            b = b*10;
            int k = 0;
            int m = 0;
            for(int i = 0; i < key.length; i++){
                if(key[i] > k){
                    k = key[i];
                }
                if(key[i] < m){
                    m = key[i];
                }
            }
            int[] count = new int[k-m+1];
            for(int i = 0; i < key.length; i++){
                count[key[i]-m] = count[key[i]-m] + 1;
            }
            int[] res = new int[key.length];
            for(int i = 1; i < count.length; i++){
                count[i] = count[i] + count[i-1];
            }
            for(int i = key.length-1; i >=0; i--){
                res[count[key[i]-m]-1] = array[i];
                count[key[i]-m] = count[key[i]-m] - 1;
            }
            array = res;
        }
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