package augmentingdatastructure;

import redblacktree.*;

public class OSIntervalTree<T extends Comparable<T>> extends RedBlackTree<T>{
    public class Node<T extends Comparable<T>> extends RedBlackTree<T>.Node<T>{
        int size;
        T high;
        public Node(T low, T high, Node<T> parent, boolean black){
            super(low, parent, black);
            this.high = high;
            if(high!=null&&high.compareTo(low)<0)
                this.high = low;
            else if(low!=null&&low.compareTo(high)>0)
                this.high = low;
            this.size=0;
        }
    }

    public void insert(T data){
        insert(new Node<T>(data, data, null, false));
    }

    public void insert(T low, T high){
        insert(new Node<T>(low, high, null, false));
    }

    public void insert(Node<T> node){
        super.insert(node);
        while(node.parent!=null){
            node=(Node<T>)(node.parent);
            node.size++;
        }
    }

    public void leftRotate(Node<T> root){
        Node<T> old = (Node<T>)(root.right);
        root.size = (root.left!=null?((Node<T>)(root.left)).size:0)+(root.right!=null?((Node<T>)(root.right)).size:0);
        super.leftRotate(root);
        if(old!=null){
            old.size = root.size;
            root.size = (root.left!=null?((Node<T>)(root.left)).size:0)+(root.right!=null?((Node<T>)(root.right)).size:0);
        }
    }

    public void rightRotate(Node<T> root){
        Node<T> old = (Node<T>)(root.left);
        root.size = (root.left!=null?((Node<T>)(root.left)).size:0)+(root.right!=null?((Node<T>)(root.right)).size:0);
        super.rightRotate(root);
        if(old!=null){
            old.size = root.size;
            root.size = (root.left!=null?((Node<T>)(root.left)).size:0)+(root.right!=null?((Node<T>)(root.right)).size:0);
        }
    }
}