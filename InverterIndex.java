import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InverterIndex implements Serializable{
    private BufferedReader in;
    private StringTokenizer tokens;
    private static HashMap<Integer, String> docs;
    private static HashMap<String, HashSet<Integer>> map;
    int docnum = 0;
    String[] args;

    public InverterIndex(){
        docs = new HashMap<>();
        map = new HashMap<>();
    }

    public void readHtml(String filename) throws Exception {
        in = new BufferedReader(new FileReader(filename));
        docs.put(docnum, filename);
        String line = in.readLine();
        while (line != null) {
            tokens = new StringTokenizer(line, "\\W+/^([^0-9]*)-_\"^$#@=:!?{}.%&,(){};'<>");//remove some values
            try {
                while (tokens.hasMoreTokens()) { 
                    Pattern pattern = Pattern.compile("<li(.*?)>(.*?)</li>");
                    Matcher match = pattern.matcher(tokens.nextToken());

                    Pattern pattern2 = Pattern.compile("<p(.*?)>(.*?)</p>");
                    Matcher match2 = pattern2.matcher(tokens.nextToken());

                    Pattern pattern3 = Pattern.compile("<title(.*?)>(.*?)</title>");
                    Matcher match3 = pattern3.matcher(tokens.nextToken());

                    Pattern pattern4 = Pattern.compile("<td(.*?)>(.*?)</td>");
                    Matcher match4 = pattern4.matcher(tokens.nextToken());

                    Pattern pattern5 = Pattern.compile("<ol(.*?)>(.*?)</ol>");
                    Matcher match5 = pattern5.matcher(tokens.nextToken());

                    Pattern pattern6 = Pattern.compile("<li(.*?)>(.*?)</li>");
                    Matcher match6 = pattern6.matcher(tokens.nextToken());

                    if (match.find() || match2.find() || match3.find() || match4.find() || match5.find() || match6.find())  {
                        tokens.nextToken().replace(match.group(1), ""); // removes script tags
                    }
                    if (!map.containsKey(tokens.nextToken())) {
                        map.put(tokens.nextToken(), new HashSet<Integer>());
                    }
                    map.get(tokens.nextToken()).add(docnum);
                }
            } catch (Exception e) {
            }
            line = in.readLine();
        }
        docnum++;
    }

    public void printAll(String file){
        WritetoFile all = new WritetoFile(file);
        for(String i:map.keySet()){
            all.write("Word:" + i + "\n");
            all.write("  Frequency:" + map.get(i).size() + "\n");
            HashSet<Integer> value = map.get(i);        
            for(Integer x: value){
                all.write("    " + docs.get(x) + "\n");
            }   
        }
        all.close();
    }
    //not saving the stopwords in map
    public HashMap<String, HashSet<Integer>> uselessStopwords(Stopwords stop, HashMap<String, HashSet<Integer>> map) {
        try {
            HashMap<String,String> temp = stop.readStopwords();
            for(String s: temp.keySet())
                {
                    if(map.containsKey(s))
                    map.remove(s);
                }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
        }

        public String concat(String[] args){
            String wordabs = "";
            for(String search:args){
                wordabs+=search;
            }
            return wordabs;
        }

    public void search(String args, String file) throws Exception {
        InverterIndex p = new InverterIndex();
        File path = new File("Article/");     
        File [] files = path.listFiles();
    //    Stemmer st1 = new Stemmer();
    //    char[] w = new char[501];


        for(int i = 0; i < files.length; i++){
            if(files[i].isFile())
            p.readHtml(files[i].toString()); //read file
        }
        Stopwords stop = new Stopwords();
        p.uselessStopwords(stop, map);

        WritetoFile write1 = new WritetoFile(file);
      //  if (args.length > 0) { 
            write1.write("The command line arguments are:\n");
           // String s = p.concat(args);
                write1.write("Word:" + args + "\n");
                 if(map.containsKey(args)){   
                    write1.write("Frequency:" + map.get(args).size() + "\n");
                    HashSet<Integer> value = map.get(args);        
                    for(Integer x: value){    //looks for the values in the hashset to find the articles where the term is used
                        write1.write(docs.get(x) + "\n");
                    }                    
                 }
                 
                 else{ 
                    write1.write("not in index\n" );
                 }
                
    
   /*     else{
            write1.write("No command line arguments found."); 
        }*/
        write1.close();
    }
}