package maxflow;

public class RelabelToFront{
    public class Node{
        public String name;
        public EdgeList edges;
        public EdgeList curEdge;
        public Node next;
        public int h;
        public int e;

        public Node(String str){
            name=str;
            h=0;
            next=null;
            edges = null;
        }

        public void addEdge(Edge e){
            edges = new EdgeList(e, edges);
            curEdge = edges;
        }

        public int residualC(){
            if(curEdge==null)
                return -1;
            if(this==curEdge.e.from)
                return curEdge.e.c-curEdge.e.f;
            else
                return curEdge.e.f;
        }

        public void pushFlow(int i){
            if(curEdge==null)
                return;
            e-=i;
            if(this==curEdge.e.from){
                curEdge.e.f+=i;
                curEdge.e.to.e+=i;
            } else {
                curEdge.e.f-=i;
                curEdge.e.from.e+=i;
            }
        }

        public void relabel(){
            curEdge = edges;
            while(curEdge!=null){
                if(residualC()>0){
                    if(curEdge.e.from==this){
                        if(h>curEdge.e.to.h)
                            h=curEdge.e.to.h;
                    } else {
                        if(h>curEdge.e.from.h)
                            h=curEdge.e.from.h;
                    }
                }
                curEdge=curEdge.next;
            }
            h+=1;
        }

        public void disCharge(){
            while(e>0){
                if(curEdge==null){
                    relabel();
                    curEdge=edges;
                }
                else if(residualC()>0 && ((curEdge.e.from==this&&curEdge.e.to.h+1==h)||(curEdge.e.to==this&&curEdge.e.from.h+1==h))){
                    if(residualC()>e)
                        pushFlow(e);
                    else
                        pushFlow(residualC());
                    curEdge=curEdge.next;
                }else{
                    curEdge=curEdge.next;
                }
            }
        }
    }

    public class Edge{
        public Node from;
        public Node to;
        public int c;
        public int f;

        public Edge(Node u, Node v, int ca){
            from = u;
            to = v;
            c = ca;
            f = 0;
        }
    }

    public class EdgeList{
        public Edge e;
        public EdgeList next;

        public EdgeList(Edge ed, EdgeList n){
            e = ed;
            next = n;
        }
    }

    public Node nodes=null;
    public Node current=null;
    public int num=0;

    public void addNode(String str){
        Node node = new Node(str);
        node.next = nodes;
        nodes = node;
        num++;
    }

    public void addEdge(String a, String b, int c){
        Node cur = nodes;
        Node u = null;
        Node v = null;
        while(cur!=null){
            if(cur.name.equals(a))
                u = cur;
            if(cur.name.equals(b))
                v = cur;
            cur=cur.next;
        }
        if(u==null||v==null||u==v)
            return;
        Edge e = new Edge(u, v, c);
        u.addEdge(e);
        v.addEdge(e);
    }

    public void init(String s){
        Node cur = nodes;
        while(cur!=null){
            if(cur.name.equals(s)){
                cur.h=num;
                break;
            }
            cur=cur.next;
        }
        if(cur==null)
            throw new RuntimeException("Source Node invalid");
        cur.curEdge = cur.edges;
        while(cur.curEdge!=null){
            cur.pushFlow(cur.residualC());
            cur.curEdge = cur.curEdge.next;
        }
    }

    public void calculate(String s, String t){
        Node pre = null;
        Node cur = nodes;
        init(s);
        while(cur!=null){
            int oldh=cur.h;
            if(!cur.name.equals(s)&&!cur.name.equals(t)&&cur.e>0){
                cur.disCharge();
            }
            if(oldh<cur.h){
                if(pre!=null){
                    pre.next=cur.next;
                    cur.next=nodes;
                    nodes = cur;
                }
            }
            pre=cur;
            cur=cur.next;
        }
    }

    public int check(String a, String b){
        Node cur = nodes;
        while(cur!=null&&!cur.name.equals(a)){
            cur=cur.next;
        }
        if(cur==null)
            return -1;
        EdgeList curE = cur.edges;
        while(curE!=null&&!curE.e.to.name.equals(b)){
            curE=curE.next;
        }
        if(curE==null)
            return -1;
        return curE.e.f;
    }
}
/*
        RelabelToFront mf = new RelabelToFront();
        Scanner scan = new Scanner(System.in);
        String input = "";
        while(!input.equals("!q")){
            System.out.print("Mode/!q: ");input = scan.nextLine();
            if(input.equals("node")){
                System.out.println(">>>Add Node>>>");
                while(!input.equals("!q")){
                    System.out.print("Add Node/!q: "); input = scan.nextLine();
                    if(!input.equals("!q")){
                        mf.addNode(input);
                    }
                }
                System.out.println("<<<Add Node<<<");
                input = "";
            } else if(input.equals("edge")) {
                System.out.println(">>>Add Edge>>>");
                String b;
                int c;
                while(!input.equals("!q")){
                    System.out.print("From/!q: "); input = scan.nextLine();
                    if(input.equals("!q"))
                        break;
                    System.out.print("To/!q: "); b = scan.nextLine();
                    if(b.equals("!q"))
                        break;
                    System.out.print("Capcity/!q: "); c = scan.nextInt(); scan.nextLine();
                    if(c<0)
                        continue;
                    mf.addEdge(input, b, c);
                }
                System.out.println("<<<Add Edge<<<");
                input = "";
            } else if(input.equals("check")){
                System.out.println(">>>Check>>>");
                String b;
                while(!input.equals("!q")){
                    System.out.print("From/!q: "); input = scan.nextLine();
                    if(input.equals("!q"))
                        break;
                    System.out.print("To/!q: "); b = scan.nextLine();
                    if(b.equals("!q"))
                        break;
                    System.out.println("Flow:"+(mf.check(input, b)));
                }
                System.out.println("<<<Check<<<");
                input = "";
            } else if(input.equals("run")){
                String b;
                System.out.print("S/!q: "); input = scan.nextLine();
                if(input.equals("!q"))
                    continue;
                System.out.print("T/!q: "); b = scan.nextLine();
                if(b.equals("!q"))
                    continue;
                mf.calculate(input, b);
            } else if(input.equals("new")){
                mf = new RelabelToFront();
            }
*/