import java.io.FileWriter;
import java.io.IOException;

public class WritetoFile {
    FileWriter writer;

    public WritetoFile(String s){
        try {
            writer = new FileWriter(s);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println("does not open");
        }
    }

    public void write(String s) {
        try {
            writer.write(s);
        } catch (Exception e) {
            System.out.println("does not write to file");
        }
    }

    public void close() {
        try {
            writer.close();
        } catch (IOException e) {
            System.out.println("does not close");
        }
    }
}