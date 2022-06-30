import java.io.File;
import java.util.Scanner;
import java.io.FileWriter;
public class set{
    public static void main(String[] args){
        try{
        File file = new File("src.txt");
        Scanner scan = new Scanner(file);
        String out = "";
        String ln = "";
        while(scan.hasNextLine()){
            ln = scan.nextLine();
            for(int i = 0; i < ln.length(); i++){
                if(ln.charAt(i)=='\\'){
                    i++;
                    ln = ln.substring(0,i) + '\\' + ln.substring(i,ln.length());
                }
            }
            out = out + ln + " \n";
        }
        FileWriter w= new FileWriter(file);
        w.write(out);
        w.close();
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}