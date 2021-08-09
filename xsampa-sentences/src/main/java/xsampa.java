import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


/**
*/
public class xsampa {

    static final Map<String,String> XSAMPA = new HashMap<String,String>();

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


    /**
     */
    private static void writeSentences() {
        for (String word : XSAMPA.keySet()) {
             String phone = XSAMPA.get(word);
	     System.out.print("<speak><phoneme alphabet=\"x-sampa\" ph=\""+phone+"\">"+phone+"</phoneme>"+"<break />");
	     System.out.println("<phoneme alphabet=\"x-sampa\" ph=\""+phone+phone+phone+"\">"+phone+"</phoneme>"+"<break />"+word+"</speak>");
        }

    }

    /**
     */
  public static void main(String []args) {
    if (args.length!=0) {
	System.out.println("Usage: xsampa");
	System.exit(-1);
    }

    buildMapping();

    writeSentences();
  }
}
