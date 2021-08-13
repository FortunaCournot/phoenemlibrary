import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


/**
*/
public class text2ssml {

    static final int WORDCOUNT = 15;

    /**
     */
    private static void writePhoneWordMap(Map<String, List<String []>> phoneWordMap) {
       for (Map.Entry<String, List<String []>> entry : phoneWordMap.entrySet()) {
            String phone = entry.getKey();
            List<String []> dictEntryList = entry.getValue();
            System.out.print(phone);
            for (String [] dictEntry : dictEntryList) {
              System.out.print(" " + dictEntry[0]);
            }
            System.out.println("");
       }
    }

    /**
     */
    private static void processDictEntry(String [] token, Map<String, List<String []>> phoneWordMap) {
      String word = token[0];
      if (word.startsWith(";")) {
          return;
      }
      for (int i=1; i<token.length; i++) {
          List<String []> dictEntryList = phoneWordMap.get(token[i]);
          if (dictEntryList == null) {
              dictEntryList = new ArrayList();
              phoneWordMap.put(token[i], dictEntryList);
          }
          if (dictEntryList.size() < WORDCOUNT) {
	    dictEntryList.add(token);
          }
	  else {
            for (int j=0; j<5; j++) {
              if (token.length < 4) {
		continue;
              }

              if( dictEntryList.get(j)[1].equals(token[i]) )
              {
		continue;
              }

              if( dictEntryList.get(j)[dictEntryList.get(j).length-1].equals(token[i]) )
              {
		continue;
              }

              if (token.length < dictEntryList.get(j).length) {
                  dictEntryList.remove(j);
	          dictEntryList.add(token);
                  break;
              }
            }
          }
      }
    }

    /**
     */
    private static Map<String, List<String []>> createPhoneWordMapFromDictonary(String datName) {

        File file = new File(datName);

        if (!file.canRead() || !file.isFile()) {
	    System.err.println("access failed");
            return null;
        }

        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(datName));
            String zeile = null;
            Map<String, List<String []>> phoneWordMap = new HashMap<String, List<String []>>();
            while ((zeile = in.readLine()) != null) {
		String [] token = zeile.trim().toLowerCase().split("\\s+");
		if (token.length > 1) {
			processDictEntry(token, phoneWordMap);
		}
                // System.out.println("Gelesene Zeile: " + zeile);

            }
            return phoneWordMap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
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

    String dicPath = args[0];
    Map<String, List<String []>> phoneWordMap = createPhoneWordMapFromDictonary(dicPath);
    if (phoneWordMap==null) {
	System.exit(-1);
    }


    writePhoneWordMap(phoneWordMap);


  }
}
