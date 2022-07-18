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
            this.size=1;
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
        root.size = (root.left!=null?((Node<T>)(root.left)).size:0)+(root.right!=null?((Node<T>)(root.right)).size:0)+1;
        super.leftRotate(root);
        if(old!=null){
            old.size = root.size;
            root.size = (root.left!=null?((Node<T>)(root.left)).size:0)+(root.right!=null?((Node<T>)(root.right)).size:0);
        }
    }

    public void rightRotate(Node<T> root){
        Node<T> old = (Node<T>)(root.left);
        root.size = (root.left!=null?((Node<T>)(root.left)).size:0)+(root.right!=null?((Node<T>)(root.right)).size:0)+1;
        super.rightRotate(root);
        if(old!=null){
            old.size = root.size;
            root.size = (root.left!=null?((Node<T>)(root.left)).size:0)+(root.right!=null?((Node<T>)(root.right)).size:0);
        }
    }

    public void delete(Node<T> u){
        Node<T> node = (Node<T>)(super.delete(u));
        while(node.parent!=null){
            node=(Node<T>)(node.parent);
            node.size = (root.left!=null?((Node<T>)(root.left)).size:0)+(root.right!=null?((Node<T>)(root.right)).size:0)+1;
        }
    }

    public Node<T> osSelect1(Node<T> node, int i){
        if(node == null)
            return null;
        int r = (node.left!=null?((Node<T>)(node.left)).size:0)+1;
        if(i == r)
            return node;
        else if(i < r)
            return osSelect1((Node<T>)(node.left), i);
        else
            return osSelect1((Node<T>)(node.right), i-r);
    }

    public Node<T> osSelect2(Node<T> node, int i){
        int r = (node.left!=null?((Node<T>)(node.left)).size:0)+1;;
        while(node!=null&&r!=i){
            if(i < r){
                node = (Node<T>)(node.left);
                r -= (node.right!=null?((Node<T>)(node.right)).size:0)+1;
            }
            else{
                node = (Node<T>)(node.right);
                r += (node.left!=null?((Node<T>)(node.left)).size:0)+1;
            }
        }
        return node;
    }

    public T osSelect(int i){
        return osSelect2((Node<T>)root, i).data;
    }

    public int osRank(Node<T> node){
        int r = (node.left!=null?((Node<T>)(node.left)).size:0)+1;
        while(node!=root){
            if(node==node.parent.right)
                r += ((Node<T>)(node.parent)).size+1;
            node = (Node<T>)(node.parent);
        }
        return r;
    }
}