import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Stopwords {
    private BufferedReader in;
    private StringTokenizer tokens;
    private HashMap<String, String> table = new HashMap<>();

    public HashMap<String, String> readStopwords() throws IOException {
        in = new BufferedReader(new FileReader("stopwords.txt"));
        String line = in.readLine();
        while (line != null) {
            tokens = new StringTokenizer(line);
            try {
                while (tokens.hasMoreTokens())
                    table.put(tokens.nextToken(), null);
            } catch (final IllegalArgumentException e) {
                System.out.print(e + "Error" + line);
            }
            line = in.readLine();
        }
        return table;
    }

    public void print(){
        for(String s: table.keySet()){
            System.out.println("Key:" + s + "   Value:" + table.get(s));
        }
        System.out.print(table.size());
    }
}