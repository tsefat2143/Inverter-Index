import java.io.*;
import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) throws Exception {
        InverterIndex i1 = new InverterIndex();

        String serializeFileName = "Serialize.ser";
		if(args.length > 0)
		{
			serializeFileName = args[0];
		}

		InverterIndex inverterindex = new InverterIndex();
		FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        
		try
		{
			fileOutputStream = new FileOutputStream(serializeFileName);
			objectOutputStream = new ObjectOutputStream(fileOutputStream);
			//The object is being persisted here
			objectOutputStream.writeObject(inverterindex);
            objectOutputStream.close();
            String word = i1.concat(args);
            i1.search(word, "results.txt");
            i1.printAll("All.txt");
		}
		catch(IOException ioe)
		{
			//Close all I/O streams
			ioe.printStackTrace();
			//Handle the exception here
        }

        Scanner scan = new Scanner(System.in);
        System.out.println("Would you like to use Porter's Stemming: yes or no"); //gives you option to stem 
        String answer = scan.nextLine();
        scan.close();
        if(answer.toLowerCase() == "no"){
            System.exit(0);;
        }
        else if(answer.toLowerCase() == "yes"){
            WritetoFile input = new WritetoFile("Inputfile.txt");
            input.write(i1.concat(args));

            // https://tartarus.org/martin/PorterStemmer/java.txt
            // Porter's Algorithm
            char[] w = new char[501];
            Stemmer s = new Stemmer();
            for (int i = 0; i < args.length; i++)
            try
            {
               FileInputStream in = new FileInputStream("Inputfile.txt");
      
               try
               { while(true)
      
                 {  int ch = in.read();
                    if (Character.isLetter((char) ch))
                    {
                       int j = 0;
                       while(true)
                       {  ch = Character.toLowerCase((char) ch);
                          w[j] = (char) ch;
                          if (j < 500) j++;
                          ch = in.read();
                          if (!Character.isLetter((char) ch))
                          {
                             /* to test add(char ch) */
                             for (int c = 0; c < j; c++) s.add(w[c]);
      
                             /* or, to test add(char[] w, int j) */
                             /* s.add(w, j); */
      
                             s.stem();
                             {  String u;
      
                                /* and now, to test toString() : */
                                u = s.toString();
      
                                /* to test getResultBuffer(), getResultLength() : */
                                /* u = new String(s.getResultBuffer(), 0, s.getResultLength()); */
      
                                InverterIndex i2 = new InverterIndex();
                                i2.search(u, "results1.txt");
                                i2.printAll("All1.txt");
                             }
                             break;
                          }
                       }
                    }
                    if (ch < 0) break;
                    System.out.print((char)ch);
                 }
               }
               catch (IOException e)
               {  System.out.println("error reading " + args[i]);
                  break;
               }
               in.close();
            }
            catch (FileNotFoundException e)
            {  System.out.println("file " + args[i] + " not found");
               break;
            }
      
        }

        //Deserailize
        InverterIndex i2 = null;
		FileInputStream fileInputStream = null;
		ObjectInputStream objectInputStream = null;

		String serializedFileName = "Serialize.ser";
		if(args.length > 0)
		{
			serializedFileName = args[0];
		}

		try
		{
			fileInputStream = new FileInputStream(serializedFileName);
			objectInputStream = new ObjectInputStream(fileInputStream);
			i2 = (InverterIndex) objectInputStream.readObject();
			objectInputStream.close();

            String word2 = i2.concat(args);
            i2.search(word2, "results1.txt");
            i2.printAll("All1.txt");

		}
		catch(FileNotFoundException fnfe)
		{
			System.out.println("File not found: "+fnfe.getMessage());
			//Close all I/O streams
			//Handle the exception here
		}
		catch(IOException ioe)
		{
			ioe.printStackTrace();
			//Close all I/O streams
			//Handle the exception here
		}
		catch(ClassNotFoundException cnfe)
		{
			cnfe.printStackTrace();
			//Close all I/O streams
			//Handle the exception here
		}
    }
}
