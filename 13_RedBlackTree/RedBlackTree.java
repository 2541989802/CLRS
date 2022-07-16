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
        super.insert(node);
        insertFixup(node);
    }

    public void insertFixup(Node<T> node){
        while(!node.black && node.parent!=null && !((Node<T>)(node.parent)).black){
            if(node.parent==node.parent.parent.left){
                if(node.parent.right!=null && !((Node<T>)(node.parent.right)).black){
                    node = (Node<T>)(node.parent);
                    ((Node<T>)(node.left)).black = true;
                    ((Node<T>)(node.right)).black = true;
                    node.black = false;
                } else {
                    if(node==node.parent.right)
                        leftRotate((Node<T>)(node.parent));
                    else
                        node = (Node<T>)(node.parent);
                    rightRotate((Node<T>)(node.parent));
                    node.black = true;
                    ((Node<T>)(node.right)).black = false;
                }
            } else {
                if(node.parent.left!=null && !((Node<T>)(node.parent.left)).black){
                    node = (Node<T>)(node.parent);
                    ((Node<T>)(node.left)).black = true;
                    ((Node<T>)(node.right)).black = true;
                    node.black = false;
                } else {
                    if(node==node.parent.left)
                        rightRotate((Node<T>)(node.parent));
                    else
                        node = (Node<T>)(node.parent);
                    leftRotate((Node<T>)(node.parent));
                    node.black = true;
                    ((Node<T>)(node.left)).black = false;
                }
            }
        }
        if(node.parent==null)
            root=node;
        if(root!=null)
            ((Node<T>)root).black = true;
    }

    public void delete(Node<T> u){
        Node<T> db = null;
        if(u==null)
            return;
        else if(u.left==null){
            transplant(u.right, u);
            db = u.right==null?(Node<T>)(u.right):null;
        }
        else if(u.right==null){
            transplant(u.left, u);
            db = u.left!=null?(Node<T>)(u.left):null;
        } else {
            Node<T> y = (Node<T>)min_h(u.right);
            db = y.right!=null?(Node<T>)(y.right):null;
            if(y.parent!=u){
                transplant(y, y.right);
                y.right = u.right;
                y.right.parent = y;
            }
            transplant(u, y);
            y.left = u.left;
            y.left.parent = y;
            boolean b = y.black;
            y.black = u.black;
            u.black = b;
        }
        if(u.black&&db!=null)
            deleteFixup(db);
    }

    public void deleteFixup(Node<T> db){
        Node<T> x, y, z, w;
        while(db!=null && db.parent!=null && db.black){
            if(db==db.parent.left){
                x = (Node<T>)db.parent;
                y = (Node<T>)x.right;
                z = (Node<T>)y.left;
                w = (Node<T>)y.right;
                if(!y.black){
                    y.black = true;
                    x.black = false;
                    leftRotate((Node<T>)(db.parent));
                } else {
                    if(z.black&&w.black){
                        y.black = false;
                        db = x;
                    } else {
                        if(w.black){
                            z.black = true;
                            y.black = false;
                            rightRotate(y);
                        } else {
                            y.black = x.black;
                            x.black = true;
                            w.black = true;
                            leftRotate(x);
                            db = (Node<T>)root;
                        }
                    }
                }
            } else {
                x = (Node<T>)db.parent;
                y = (Node<T>)x.left;
                z = (Node<T>)y.right;
                w = (Node<T>)y.left;
                if(!y.black){
                    y.black = true;
                    x.black = false;
                    rightRotate((Node<T>)(db.parent));
                } else {
                    if(z.black&&w.black){
                        y.black = false;
                        db = x;
                    } else {
                        if(w.black){
                            z.black = true;
                            y.black = false;
                            leftRotate(y);
                        } else {
                            y.black = x.black;
                            x.black = true;
                            w.black = true;
                            rightRotate(x);
                            db = (Node<T>)root;
                        }
                    }
                }
            }
        }
    }

    public void leftRotate(Node<T> root){
        if(root == null)
            return;
        if(root.right==null)
            throw new RuntimeException("RedBlackTree.rightRotate(): do not exist left child to left rotate");
        Node<T> x = (Node<T>)root.right;
        Node<T> y = (Node<T>)root.right.left;
        x.parent = root.parent;
        if(x.parent != null){
            if(root==root.parent.left)
                x.parent.left = x;
            else
                x.parent.right = x;
        } else {
            this.root = x;
        }
        root.parent = x;
        root.right = y;
        if(y!=null)
            y.parent = root;
    }

    public void rightRotate(Node<T> root){
        if(root == null)
            return;
        if(root.left==null)
            throw new RuntimeException("RedBlackTree.rightRotate(): do not exist left child to right rotate");
        Node<T> x = (Node<T>)root.left;
        Node<T> y = (Node<T>)root.left.right;
        x.parent = root.parent;
        if(x.parent != null){
            if(root==root.parent.left)
                x.parent.left = x;
            else
                x.parent.right = x;
        } else {
            this.root = x;
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