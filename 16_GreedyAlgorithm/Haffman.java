package greedyalgorithm;

import heapsort.*;
import basicdatastructure.*;

public class Haffman{
    public class Node implements Comparable<Node>{
        public Node left;
        public Node right;
        public String name;
        public double p;
        public Node(String n, double p){
            name = n;
            this.p = p;
        }

        public int compareTo(Node n){
            return p>n.p?1:(p==n.p?0:-1);
        }
    }

    public Node lastroot;
    public int lastsize;

    public Node build(double[] p, String[] name){
        if(p.length!=name.length)
            return null;
        Node[] ns = new Node[p.length];
        lastsize = ns.length;
        for(int i=0; i<ns.length; i++){
            ns[i]=new Node(name[i],p[i]);
        }
        PriorityQueues<Node> ps = new PriorityQueues<Node>(ns);
        ps.maxheap=false;
        ps.buildMaxHeap();
        Node t;
        while(ps.size>1){
            t = new Node(null, 0);
            t.left = ps.extractMax();
            t.right = ps.extractMax();
            t.p = t.left.p + t.right.p;
            ps.insertKey(t);
        }
        lastroot = ps.extractMax();
        return lastroot;
    }

    public void print(){
        if(lastroot==null)
            return;
        Queue<Node> a = new Queue<Node>(lastsize/2+1);
        Queue<Node> b = new Queue<Node>(lastsize/2+1);
        boolean isA = true;
        a.enqueue(lastroot);
        Queue<Node> cur;
        Queue<Node> next;
        Node t;
        System.out.println("============");
        while(!(a.isEmpty()&&b.isEmpty())){
            cur = isA?a:b;
            next = !isA?a:b;
            while(!cur.isEmpty()){
                t = cur.dequeue();
                if(t.name!=null)
                    System.out.print(String.format("(%s:%.2f), ", t.name, t.p));
                if(t.left!=null)
                    next.enqueue(t.left);
                if(t.right!=null)
                    next.enqueue(t.right);
            }
            System.out.println("");
            isA=!isA;
        }
        System.out.println("============");
    }
}