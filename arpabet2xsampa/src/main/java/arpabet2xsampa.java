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
	ARPABET2XSAMPA.put("B","b");
	ARPABET2XSAMPA.put("D","d");
	ARPABET2XSAMPA.put("JH","dZ");
	ARPABET2XSAMPA.put("DH","D");
	ARPABET2XSAMPA.put("F","f");
	ARPABET2XSAMPA.put("G","g");
	ARPABET2XSAMPA.put("H","h");
	ARPABET2XSAMPA.put("HH","h");
	ARPABET2XSAMPA.put("Y","j");
	ARPABET2XSAMPA.put("K","k");
	ARPABET2XSAMPA.put("L","l");
	ARPABET2XSAMPA.put("M","m");
	ARPABET2XSAMPA.put("N","n");
	ARPABET2XSAMPA.put("NX","N");
	ARPABET2XSAMPA.put("NG","N");
	ARPABET2XSAMPA.put("P","p");
	ARPABET2XSAMPA.put("R","r\\");
	ARPABET2XSAMPA.put("S","s");
	ARPABET2XSAMPA.put("SH","S");
	ARPABET2XSAMPA.put("T","t");
	ARPABET2XSAMPA.put("CH","tS");
	ARPABET2XSAMPA.put("TH","T");
	ARPABET2XSAMPA.put("V","V");
	ARPABET2XSAMPA.put("W","w");
	ARPABET2XSAMPA.put("Z","z");
	ARPABET2XSAMPA.put("ZH","Z");
	ARPABET2XSAMPA.put("AX","@");
	ARPABET2XSAMPA.put("AXR","@`");
	ARPABET2XSAMPA.put("AE","{");
	ARPABET2XSAMPA.put("AY","aI");
	ARPABET2XSAMPA.put("AW","aU");
	ARPABET2XSAMPA.put("AA","A");
	ARPABET2XSAMPA.put("EY","eI");
	ARPABET2XSAMPA.put("ER","3`");
	ARPABET2XSAMPA.put("EH","E");
	ARPABET2XSAMPA.put("IY","i");
	ARPABET2XSAMPA.put("IH","I");
	ARPABET2XSAMPA.put("OW","oU");
	ARPABET2XSAMPA.put("AO","O");
	ARPABET2XSAMPA.put("OY","OI");
	ARPABET2XSAMPA.put("UW","u");
	ARPABET2XSAMPA.put("UH","U");
	ARPABET2XSAMPA.put("AH","V");
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
          if (Character.isDigit(token[i].charAt(token[i].length()-1))) {
              token[i]=token[i].substring(0,token[i].length()-1);
          }
          newToken[i]=ARPABET2XSAMPA.get(token[i].toUpperCase());
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
                    for (int i=0; i<newToken.length; i++) {
                        System.out.print(newToken[i]);
                        System.out.print(" ");
                    }
                    System.out.println("");
		}

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
