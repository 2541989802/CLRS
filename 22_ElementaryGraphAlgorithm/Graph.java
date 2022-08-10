package graphalgorithm;

import basicdatastructure.LinkList;
//import java.util.Iterator;

public class Graph{

    public class Node implements Comparable<Node>{
        public String name;
        public LinkList<Edge> edges;

        public Node(String str){
            name = str;
            edges = new LinkList<Edge>();
        }

        public int compareTo(Node n){
            return name.compareTo(n.name);
        }

        public void addEdge(Edge e){
            edges.insert(e);
        }
    }

    public class Edge implements Comparable<Edge>{
        public String name;
        public double weight;

        public Edge(String str, double db){
            name = str;
            weight = db;
        }

        public int compareTo(Edge e){
            return name.compareTo(e.name);
        }
    }

    public LinkList<Node> nodes;

    public Graph(){
        nodes = new LinkList<Node>();
    }

    public void addNode(String str){
        nodes.insert(new Node(str));
    }

    public void addEdge(String a, String b, double db){
        nodes.search(new Node(a)).addEdge(new Edge(b, db));
    }

    public boolean hasEdge(String a, String b){
        Node n = nodes.search(new Node(a));
        if(n!=null && n.edges.search(new Edge(b, 0))!=null)
            return true;
        return false;
    }

    public double[][] toMatrix(){
        double[][] res = new double[nodes.getSize()][nodes.getSize()];
        int i = 0;
        for(Node n: nodes){
            int j = 0;
            for(Edge e: n.edges){
                j=0;
                for(Node n2: nodes){
                    if(e.name.equals(n2.name))
                        break;
                    j++;
                }
                if(j<nodes.getSize());
                    res[i][j]=e.weight;
            }
            i++;
        }
        return res;
    }
}