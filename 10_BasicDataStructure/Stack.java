package basicdatastructure;

public class Stack<T>{
    public Object[] oa;
    private int top = -1;

    public Stack(){
        this(10);
    }

    public Stack(int i){
        oa = new Object[i];
    }

    public Stack(T[] ta){
        oa = ta;
        top = ta.length-1;
    }

    public boolean isEmpty(){
        return top==-1;
    }

    public void push(T e){
        if(top==oa.length-1)
            throw new RuntimeException("Stack.push(): Overflow");
        top++;
        oa[top]=e;
    }

    @SuppressWarnings("unchecked")
    public T pop(){
        if(top==-1)
            throw new RuntimeException("Stack.pop(): Underflow");
        top--;
        return (T)(oa[top+1]);
    }
}