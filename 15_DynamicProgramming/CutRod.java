package dynamicprogramming;

public class CutRod{
    public double recursive(double[] q, int n){
        if(n==0)
            return 0;
        else if(n>q.length){
            double max=-1;
            double t;
            for(int i=0; i<q.length; i++){
                t = q[i]+recursive(q, n-i-1);
                if(t>max)
                    max=t;
            }
            return max;
        }
        else{
            double max=-1;
            double t;
            for(int i=0; i<n; i++){
                t = q[i]+recursive(q, n-i-1);
                if(t>max)
                    max=t;
            }
            return max;
        }
    }

    public double memoRecursive(double[] q, int n){
        if(n==0)
            return 0.0;
        double[] mem = new double[n];
        for(int i=0; i<mem.length; i++){
            mem[i] = -1;
        }
        mem[0]=q[0];
        memoRecursive_h(q, mem, n);
        return mem[n-1];
    }

    public void memoRecursive_h(double[] q, double[] mem, int n){
        if(n==0)
            return;
        else if(n>q.length){
            double max=-1;
            double t;
            for(int i=0; i<q.length-1; i++){
                if(mem[n-i-1]<0)
                    memoRecursive_h(q, mem, n-i-1);
                t = q[i]+mem[n-i-2];
                if(t>max)
                    max=t;
            }
            mem[n-1]=max;
        }
        else{
            double max=q[n-1];
            double t;
            for(int i=0; i<n-1; i++){
                if(mem[n-i-1]<0)
                    memoRecursive_h(q, mem, n-i-1);
                t = q[i]+mem[n-i-2];
                if(t>max)
                    max=t;
            }
            mem[n-1]=max;
        }
    }

    public double bottomup(double[] q, int n){
        if(n==0)
            return 0;
        double[] mem = new double[n];
        mem[0]=q[0];
        double t, max;
        for(int i = 1; i < n; i++){
            if(i>q.length-1)
                max = q[q.length-1];
            else
                max = q[i];
            for(int j = 0; j<q.length-1 && j<i; j++){
                t = q[j]+mem[i-j-1];
                if(t>max)
                    max = t;
            }
            mem[i] = max;
        }
        return mem[n-1];
    }

    public int[] extendsBottomup(double[] q, int n){
        double[] mem = new double[n];
        int[] cut = new int[n+1];
        cut[0]=0;
        double t, max;
        for(int i = 0; i < q.length; i++){
            mem[i] = q[i];
        }
        for(int i = 1; i < n; i++){
            max=mem[i];
            cut[i+1]=0;
            for(int j=0; j < q.length-1 && j<i; j++){
                t=q[j]+mem[i-j-1];
                if(t>max){
                    max = t;
                    cut[i+1] = j+1;
                }
            }
            mem[i] = max;
        }
        return cut;
    }
}