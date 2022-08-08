package disjointset;

import basicdatastructure.LinkList;

public class DisjointSet<T extends Comparable<T>>{
    public LinkList<Node<T>> list;

    public DisjointSet(){
        list = (LinkList<Node<T>>)new LinkList();
    }

    public Node<T> makeSet(T e){
        Node<T> ret = new Node<T>(e);
        list.insert(ret);
        return ret;
    }

    public Node<T> findSet(Node<T> x){
        if(x != x.parent)
            x.parent = findSet(x.parent);
        return x.parent;
    }

    public Node<T> findSet(T key){
        return findSet(list.search(new Node<T>(key)));
    }

    public void link(Node<T> x, Node<T> y){
        if(x.rank>y.rank)
            y.parent = x;
        else{
            x.parent = y;
            if(x.rank==y.rank)
                y.rank+=1;
        }
    }

    public void union(T x, T y){
        union(list.search(new Node<T>(x)),list.search(new Node<T>(y)));
    }

    public void union(Node<T> x, Node<T> y){
        if(x==null||y==null)
            return;
        x = findSet(x);
        y = findSet(y);
        if(x!=y)
            link(x, y);
    }
}
/*
        Scanner scan = new Scanner(System.in);
        String input = "";
        while(!input.equals("!q")){
            System.out.print("Mode/!q: ");input = scan.nextLine();
            if(input.equals("make")){
                System.out.println(">>>MakeSet>>>");
                while(!input.equals("!q")){
                    System.out.print("Set/!q: "); input = scan.nextLine();
                    if(!input.equals("!q")){
                        ds.makeSet(input);
                    }
                }
                System.out.println("<<<MakeSet<<<");
                input = "";
            } else if(input.equals("union")) {
                System.out.println(">>>Union>>>");
                String b;
                while(!input.equals("!q")){
                    System.out.print("A/!q: "); input = scan.nextLine();
                    if(input.equals("!q"))
                        break;
                    System.out.print("B/!q: "); b = scan.nextLine();
                    if(b.equals("!q"))
                        break;
                    ds.union(input, b);
                }
                System.out.println("<<<Union<<<");
                input = "";
            } else if(input.equals("check")){
                System.out.println(">>>Check>>>");
                String b;
                while(!input.equals("!q")){
                    System.out.print("A/!q: "); input = scan.nextLine();
                    if(input.equals("!q"))
                        break;
                    System.out.print("B/!q: "); b = scan.nextLine();
                    if(b.equals("!q"))
                        break;
                    System.out.println("OUT:"+(ds.findSet(input)==ds.findSet(b)));
                }
                System.out.println("<<<Check<<<");
                input = "";
            }
        }
*/