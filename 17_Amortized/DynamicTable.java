package dynamictable;

public class DynamicTable<T>{
    public Object[] table;
    private int size=0;
    private int num=0;

    @SuppressWarnings("unchecked")
    public void push(T e){
        if(size==0){
            //System.out.println("resize:"+1);
            table = new Object[1];
            size = 1;
        }
        else if(size==num){
            //System.out.println("resize:"+(size*2));
            Object[] temp = new Object[2*size];
            for(int i=0; i<num; i++){
                temp[i] = table[i];
            }
            table = temp;
            size *= 2;
        }
        table[num]=e;
        num++;
    }

    @SuppressWarnings("unchecked")
    public T pop(){
        if(num==0)
            throw new RuntimeException("underflow");
        num--;
        Object res = table[num];
        if(num==size/4){
            //System.out.println("resize:"+size/4);
            if(num==0)
                table = null;
            else{
                Object[] temp = (T[])new Object[size/4];
                for(int i=0; i<num; i++){
                    temp[i] = table[i];
                }
                table = temp;
            }
            size /= 4;
        }
        return (T)res;
    }

    @SuppressWarnings("unchecked")
    public T at(int i){
        if(i>=0&&i<num)
            return (T)(table[i]);
        throw new RuntimeException("DynamicTable.get(): Index Out Of Boundray");
    }

    public void set(int i, Object e){
        if(i>=0&&i<num){
            table[i] = e;
            return;
        }
        throw new RuntimeException("DynamicTable.get(): Index Out Of Boundray");
    }

    public int size(){
        return size;
    }

    public int num(){
        return num;
    }
}