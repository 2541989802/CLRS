package dynamicprogramming;

public class OptimalBST{
    private class Res{
        double[][] res;
        int[][] root;
        public Res(double[][] res, int[][] root){
            this.res = res;
            this.root = root;
        }
    }

    private Res bst_h(double[] p, double[] q){
        if(p.length!=q.length-1)
            return new Res(null, null);
        double[][] expect = new double[q.length][q.length];
        double[][] weight = new double[q.length][q.length];
        int[][] root = new int[q.length][q.length];
        for(int i=0; i<q.length; i++){
            for(int j=0; j<q.length-i; j++){
                expect[j][j+i]=-1;
                weight[j][j+i]=-1;
                if(i==0){
                    expect[j][j]=q[j];
                    weight[j][j]=q[j];
                    root[j][j]=j;
                }
                if(weight[j][j+i]==-1)
                    weight[j][j+i]=weight[j][j+i-1]+weight[j+i][j+i]+p[j+i-1];
                double t;
                for(int k=0; k<i; k++){
                    t=expect[j][j+k]+expect[j+k+1][j+i]+weight[j][j+i];
                    if(expect[j][j+i]>t||expect[j][j+i]==-1){
                        expect[j][j+i]=t;
                        root[j][j+i]=j+k+1;
                    }
                }
            }
        }
        return new Res(expect, root);
    }

    public double[][] expect(double[] p, double[] q){
        return bst_h(p, q).res;
    }

    public int[][] root(double[] p, double[] q){
        return bst_h(p, q).root;
    }
}