import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


/**
*/
public class arpabet2xsampa {

    static final int WORDCOUNT = 15;
    static final Map<String,String> ARPABET2XSAMPA = new HashMap<String,String>();

    /**
     */
    private static void buildMapping() {
    }

    /**
     */
    private static String [] processDictEntry(String [] token) {
      String [] newToken = new String[token.length];

      String word = token[0];
      if (word.startsWith(";")) {
          return token;
      }
      newToken[0]=word;

      for (int i=1; i<token.length; i++) {
          newToken[i]=ARPABET2XSAMPA.get(token[i]);
          if (newToken[i]==null)
          {
	     System.err.println("token at word "+word+" unmapped: "+token[i]);
             System.exit(-1);
          }
      }
      return newToken;
    }

    /**
     */
    private static void convertDict(String datName) {

        File file = new File(datName);

        if (!file.canRead() || !file.isFile()) {
	    System.err.println("access failed");
            return;
        }

        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(datName));
            String zeile = null;
            while ((zeile = in.readLine()) != null) {
		String [] token = zeile.trim().toLowerCase().split("\\s+");
		if (token.length > 1) {
			String [] newToken = processDictEntry(token);
		}
                // System.out.println("Gelesene Zeile: " + zeile);

            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
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
  public static void main(String []args) {
    if (args.length!=1) {
	System.out.println("Usage: mkphonewords pathtodict");
	System.exit(-1);
    }

    buildMapping();

    String dicPath = args[0];
    convertDict(dicPath);
  }
}
