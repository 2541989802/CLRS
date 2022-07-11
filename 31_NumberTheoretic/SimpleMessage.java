package numbertheoretic;

public class SimpleMessage{
    private SimpleRSA rsa;
    public int[] data;
    public int[] m;

    public SimpleMessage(int[] data){
        this.data = data;
        int max = 0;
        for(int i = 0; i < data.length; i++){
            if(data[i]>max)
                max = data[i];
            if(data[i]<0)
                throw new RuntimeException("new SimpleMessage(): data cannot be negative");
        }
        int i;
        for(i = 0; i*i<max;){
            i++;
        }
        rsa = new SimpleRSA((int)(Math.random()*(i-1)/2)+i-1, (int)(Math.random()*(i-2)+1));
    }

    public void send(SimpleMessage rec){
        SimpleRSA tarRsa = rec.rsa;
        m = new int[data.length];
        for(int i = 0; i < m.length; i++){
            if(m[i]>rsa.n)
                throw new RuntimeException("SimpleMessage.send(): m["+i+"] is too large to send to target RSA");
            m[i] = tarRsa.encrypt(data[i]);
        }
        rec.copy(m);
    }

    public void recieve(){
        data = new int[m.length];
        for(int i = 0; i < m.length; i++){
            data[i] = rsa.decrypt(m[i]);
        }
    }

    public void copy(int[] m){
        this.m = new int[m.length];
        for(int i=0; i<m.length; i++){
            this.m[i] = m[i];
        }
    }

    public void print(){
        System.out.print("\npublic key: ("+rsa.e+", "+rsa.n+")");
        System.out.print("\ndata: ");
        for(int i : data)
            System.out.print(i+", ");
        System.out.print("\nmessage: ");
        for(int i : m)
            System.out.print(i+", ");
        System.out.println("");
    }
}