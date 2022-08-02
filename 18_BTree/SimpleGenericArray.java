package btree;

public class SimpleGenericArray<T>{
    public Object[] array;

    public SimpleGenericArray(int i){
        array = new Object[i];
    }

    @SuppressWarnings("unchecked")
    public T at(int i){
        return (T)(array[i]);
    }

    public void set(int i, T e){
        array[i]=e;
    }

    public int length(){
        return array.length;
    }

    public void add(int i, T e){
        if(i<0||i>=array.length)
            throw new RuntimeException("SimpleGenericArray.add(): Index Out Of Boundary");
        for(int j=array.length-1; j>i; j--){
            array[j]=array[j-1];
        }
        array[i]=e;
    }

    public void remove(int i){
        if(i<0||i>=array.length)
            throw new RuntimeException("SimpleGenericArray.add(): Index Out Of Boundary");
        for(int j=i; j<array.length-1; j++){
            array[j]=array[j+1];
        }
        array[array.length-1]=null;
    }
}