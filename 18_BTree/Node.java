package btree;

import btree.*;

public class Node<T>{
    public boolean leaf;
    public int n;   //key number
    public SimpleGenericArray<T> key;
    public SimpleGenericArray<Node<T>> child;

    public Node(int t){
        leaf = false;
        key = new SimpleGenericArray<T>(2*t-1);
        child = new SimpleGenericArray<Node<T>>(2*t);
    }

    public boolean addKey(int i, T k){
        if(i<0||i>=key.length())
            throw new RuntimeException("Node.addKey(): Index Out Of Boundary");
        key.add(i, k);
        n++;
        return true;
    }

    public boolean addChild(int i, Node<T> c){
        if(i<0||i>=child.length())
            throw new RuntimeException("Node.addchild(): Index Out Of Boundary");
        child.add(i, c);
        return true;
    }

    public void removeKey(int i){
        key.remove(i);
        n--;
    }

    public void removeChild(int i){
        child.remove(i);
    }

    public void print(){
        System.out.print("("+n+":");
        for(int i=0; i<n; i++)
            System.out.print(key.at(i)+",");
        System.out.print(")");
    }
}