package binarysearchtree;

public class BinarySearchTree<T extends Compareble<T>>{
    public class Node<T> implements Compareble<Node<T>>{
        public T data;
        public Node<T> parent;
        public Node<T> left;
        public Node<T> right;

        public Node(T data, Node<T> parent){
            this.data = data;
            this.parent = parent;
        }

        public int compareTo(Node<T> o){
            if(o==null)
                throw new RuntimeException("BinarSearchTree<T>.Node<T>.compareTo(): recieve a null object, shouldn't happen in code");
            if(data!=null)
                return data.compareTo(o.data);
            if(o.data!=null)
                return -1*o.data.compareTo(data);
            else
                return 0;
        }
    }

    public Node<T> root;

    public void treeWalk(Node<T> root){
        if(root!=null){
            treeWalk(root.left);
            System.out.print(root.data+", ");
            treeWalk(root.right);
        }
    }

    private Node<T> search1_h(Node<T> node, T key){
        Node<T> other = new Node<T>(key,null);
        if(node==null)
            return null;
        else if(node.compareTo(other)==0)
            return node;
        else if(node.compareTo(other)>0)
            return search(node.left, key);
        else
            return search(node.right, key);
    }

    private Node<T> search2_h(Node<T> node, T key){
        Node<T> other = new Node<T>(key, null);
        while(node!=null&&node.compareTo(other)!=0){
            if(node.compareTo(other)>0)
                node = node.left;
            else
                node = node.right;
        }
        return node;
    }

    public T search(T key){
        Node<T> res = search2_h(root, key);
        return res!=null?res.data:null;
    }

    private Node<T> min_h(Node<T> node){
        while(node!=null&&node.left!=null){
            node = node.left;
        }
        return node;
    }

    public T min(){
        Node<T> res = min_h(root);
        return res!=null?res.data:null;
    }

    private Node<T> max_h(Node<T> node){
        while(node!=null&&node.right!=null){
            node = node.right;
        }
        return node;
    }

    public T max(){
        Node<T> res = max_h(root);
        return res!=null?res.data:null;
    }

    private Node<T> successor_h(Node<T> node){
        if(node!=null){
            if(node.right!=null)
                return min_h(node.right);
            else {
                while(node.parent!=null && node.parent.right==node)
                    node = node.parent;
                return node.parent;
                }
        }
        return null;
    }

    public T successor(T key){
        Node<T> res = successor_h(search2_h(root, key));
        return res!=null?res.data:null;
    }

    public void insert(T key){
        Node<T> p = null;
        Node<T> x = root;
        Node<T> z = new Node<T>(key,null);
        while(x!=null){
            p = x;
            if(x.compareTo(z)>=0)
                x = x.left;
            else
                x = x.right;
        }
        z.parent = p;
        if(p==null)
            root = z;
        else(p.compareTo(z)>=0)
            p.left = z;
        else
            p.right = z;
    }

    private void transplant(Node<T> u, Node<T> v){
        if(u.parent==null)
            root = v;
        else if(u==u.parent.left)
            u.parent.left = v;
        else
            u.parent.right = v;
        if(v!=null)
            v.parent = u.parent;
    }
}