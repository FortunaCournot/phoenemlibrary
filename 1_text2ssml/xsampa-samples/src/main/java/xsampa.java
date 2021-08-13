import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


/**
 * X-SAMPHA Phone Samples
*/
public class xsampa {

    static final Map<String,String> XSAMPA = new HashMap<String,String>();
    static final Map<String,String[]> WORDTOKEN = new HashMap<String,String[]>();

    /**
     */
    private static void buildMapping() {
	XSAMPA.put("bed","b");
	XSAMPA.put("dig","d");
	XSAMPA.put("jump","dZ");
	XSAMPA.put("then","D");
	XSAMPA.put("five","f");
	XSAMPA.put("game","g");
	XSAMPA.put("house","h");
	XSAMPA.put("yes","j");
	XSAMPA.put("cat","k");
	XSAMPA.put("lay","l");
	XSAMPA.put("battle","l=");
	XSAMPA.put("mouse","m");
	XSAMPA.put("anthem","i=");
	XSAMPA.put("nap","n");
	XSAMPA.put("thing","N");
	XSAMPA.put("pin","p");
	XSAMPA.put("red","r\\");
	XSAMPA.put("seem","s");
	XSAMPA.put("ship","S");
	XSAMPA.put("task","t");
	XSAMPA.put("chart","tS");
	XSAMPA.put("thin","T");
	XSAMPA.put("vest","F");
	XSAMPA.put("west","w");
	XSAMPA.put("zero","z");
	XSAMPA.put("vision","Z");
	XSAMPA.put("arena","@");
	XSAMPA.put("reader","@`");
	XSAMPA.put("trap","{");
	XSAMPA.put("price","aI");
	XSAMPA.put("mouth","aU");
	XSAMPA.put("father","A");
	XSAMPA.put("face","eI");
	XSAMPA.put("nurse","3`");
	XSAMPA.put("dress","E");
	XSAMPA.put("fleece","i");
	XSAMPA.put("kit","I");
	XSAMPA.put("goat","oU");
	XSAMPA.put("thought","O");
	XSAMPA.put("choice","OI");
	XSAMPA.put("goose","u");
	XSAMPA.put("foot","U");
	XSAMPA.put("strut","V");
    }

    private static void processDictEntry(String [] token) {
      String word = token[0];
      if (word.startsWith(";")) {
          return;
      }
      WORDTOKEN.put(word, token);
    }

    /**
     */
    private static void buildTokenMap(String dicPath) {
        File file = new File(dicPath);

        if (!file.canRead() || !file.isFile()) {
            System.err.println("access failed");
        }

        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(file));
            String zeile = null;
            while ((zeile = in.readLine()) != null) {
                String [] token = zeile.trim().split("\\s+");
                if (token.length > 1) {
                        processDictEntry(token);
                }
                // System.out.println("Gelesene Zeile: " + zeile);

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null)
                try {
                    in.close();
                } catch (IOException e) {
                }
        }
    }

    /**
     */
    private static void writeSentences() {
        for (String word : XSAMPA.keySet()) {
             String phone = XSAMPA.get(word);
             String [] token = WORDTOKEN.get(word);

	     System.out.println("<speak><phoneme alphabet=\"x-sampa\" ph=\""+phone+"\">"+phone+"</phoneme></speak>");

	     System.out.print("<speak><phoneme alphabet=\"x-sampa\" ph=\""+phone+"\">"+phone+"</phoneme>"+"<break />");
	     System.out.print(word);
             for (int i=1; i<token.length; i++) {
	         System.out.print("<break />"+"<phoneme alphabet=\"x-sampa\" ph=\""+token[i]+"\">"+token[i]+"</phoneme>");
             }
	     System.out.println("</speak>");
        }

    }

    /**
     */
  public static void main(String []args) {
    if (args.length!=1) {
        System.out.println("Usage: xsampa pathtoxsampadict");
        System.exit(-1);
    }

    String dicPath = args[0];

    buildMapping();
    buildTokenMap(dicPath);

    writeSentences();
  }
}
