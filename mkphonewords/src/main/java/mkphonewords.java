import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class mkphonewords {
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
                System.out.println("Gelesene Zeile: " + zeile);

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




  }
}
