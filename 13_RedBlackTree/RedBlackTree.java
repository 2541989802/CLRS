package redblacktree;

import binarysearchtree.BinarySearchTree;
import redblacktree.Node;

public class RedBlackTree<T extends Comparable<T>> extends BinarySearchTree<T>{

    public int height(Node<T> root){
        if(root==null)
            return 0;
        int l = height((Node<T>)(root.left));
        int r = height((Node<T>)(root.right));
        return 1+(l>r?l:r);
    }

    public int height(){
        return height((Node<T>)root);
    }

    @Override
    public Node<T> insert(T data){
        Node<T> ret = new Node<T>(data, null, false);
        insert(ret);
        return ret;
    }

    public void insert(Node<T> node){
        super.insert(node);
        insertFixup(node);
    }

    public void insertFixup(Node<T> node){
        while(!node.black && node.parent!=null && !((Node<T>)(node.parent)).black){
            if(node.parent==node.parent.parent.left){
                if(node.parent.parent.right!=null && !((Node<T>)(node.parent.parent.right)).black){
                    node = (Node<T>)(node.parent.parent);
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
                if(node.parent.parent.left!=null && !((Node<T>)(node.parent.parent.left)).black){
                    node = (Node<T>)(node.parent.parent);
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

    public void delete(T key){
        delete((Node<T>)search2_h(root, key));
    }

    public Node<T> delete(Node<T> n){
        Node<T> u = (Node<T>)n;
        Node<T> db = null;
        Node<T> dbp = (Node<T>)(u.parent);
        if(u==null)
            return null;
        else if(u.left==null){
            db = u.right!=null?(Node<T>)(u.right):null;
            transplant(u, u.right);
        }
        else if(u.right==null){
            db = u.left!=null?(Node<T>)(u.left):null;
            transplant(u, u.left);
        } else {
            Node<T> y = (Node<T>)min_h(u.right);
            db = y.right!=null?(Node<T>)(y.right):null;
            dbp = y;
            if(y.parent!=u){
                dbp = (Node<T>)(y.parent);
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
        else if(u.black&&db==null&&dbp!=null){
            db = new Node<T>(null,dbp,true);
            if(dbp.left==null)
                dbp.left = db;
            else if(dbp.right==null)
                dbp.right = db;
            deleteFixup(db);
            if(dbp.left==db)
                dbp.left = null;
            else if(dbp.right==db)
                dbp.right = null;
        } else{
            db=dbp;
        }
        size--;
        return db;
    }

    public void deleteFixup(Node<T> db){
        Node<T> x, y, z, w;
        //Node<T> up = new Node<T>(null, null, false);
        while(db!=null && db.parent!=null && db.black){
            if(db==db.parent.left){
                x = (Node<T>)db.parent;
                y = (Node<T>)x.right;
                z = (Node<T>)y.left;
                w = (Node<T>)y.right;
                if(!y.black){
                    y.black = true;
                    x.black = false;
                    leftRotate(x);
                } else {
                    if((z==null||z.black)&&(w==null||w.black)){
                        y.black = false;
                        db = x;
                        //up = x;
                    } else {
                        if(w==null||w.black){
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
                    rightRotate(x);
                } else {
                    if((z==null||z.black)&&(w==null||w.black)){
                        y.black = false;
                        db = x;
                        //up = x;
                    } else {
                        if((w==null||w.black)){
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
        if(db!=null)
            db.black = true;
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
        x.left = root;
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
        x.right = root;
        if(y!=null)
            y.parent = root;
    }

    public T successor(T key){
        binarysearchtree.Node<T> node = search2_h(root, key);
        while(successor_h(node)!=null&&node.compareTo(successor_h(node))==0){
            node = successor_h(node);
        }
        node = successor_h(node);
        return node!=null?node.data:null;
    }

    public void blackRed_h(Node<T> node, int i){
        if(node==null)
            return;
        blackRed_h((Node<T>)(node.left),i+1);
        System.out.print("("+node.data+":"+node.black+":"+i+"), ");
        blackRed_h((Node<T>)(node.right),i+1);
    }

    public void blackRed(){
        System.out.println("");
        blackRed_h((Node<T>)root,1);
        System.out.println("");
    }

    public boolean check(){
        if(this.check_h((Node<T>)root, 0)==-1){
            return false;
        }
        return true;
    }

    public int check_h(Node<T> root, int i){
        if(root==null)
            return 0;
        int l = check_h((Node<T>)(root.left),i);
        int r = check_h((Node<T>)(root.right),i);
        if(l==-1||r==-1||l!=r)
            return -1;
        else if(root.black){
            l+=1;
        }
        return l;
    }
}