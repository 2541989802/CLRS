package redblacktree;

import binarysearchtree.*;

public class RedBlackTree<T extends Comparable<T>> extends BinarySearchTree<T>{
    public class Node<T extends Comparable<T>> extends BinarySearchTree<T>.Node<T>{
        public boolean black = false;
        public Node(T key, Node<T> parent, boolean black){
            super(key, parent);
            this.black = black;
        }
    }

    public void insert(T data){
        Node<T> node = new Node<T>(data, null, false);
    }

    public void rightRotate(Node<T> root){
        if(root == null)
            return;
        if(root.left==null)
            throw new RuntimeException("RedBlackTree.rightRotate(): do not exist left child to right rotate");
        Node<T> x = root.left;
        Node<T> y = root.left.right;
        x.parent = root.parent;
        if(x.parent != null){
            if(root==root.parent.left)
                x.parent.left = x;
            else
                x.parent.right = x;
        }
        root.parent = x;
        root.left = y;
        if(y!=null)
            y.parent = root;
    }

    public boolean isBlack(T key){
        return ((Node<T>)search1_h(root, key)).black;
    }
}