import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Iterator;
import java.util.function.IntFunction;

public class TestClass {

  public static void main(String[] args) {
    int e = 35;
    //p*p=n>200000000
    BigInteger p = new BigInteger("100000000019");
    BigInteger q = new BigInteger("191938579691");
    BigInteger n = p.multiply(q);
    System.out.println(n);

    String textWTE = "Hier ist zu test Zwecken ein etwas längerer Text";
    String word = textWTE;
    int threadAnz = (int)word.length()/6;
    String[] words = new String[threadAnz+1];
    int teilLaenge = (textWTE.length()-(textWTE.length()%threadAnz))/threadAnz;
    BigInteger[] wordsBigInt = new BigInteger[threadAnz+1];
    wordsBigInt[threadAnz] = BigInteger.valueOf(0);
    //int[] wordsInt = new int[threadAnz+1];
    //long[] wordsLong = new long[threadAnz+1];
    String cacheWord = "";
    for(int i = 0;i<threadAnz;i++){
      for(int o = 0;o<teilLaenge;o++){
        int buchstabe = ((int)textWTE.charAt((teilLaenge*i)+o))+100;
        cacheWord = cacheWord+buchstabe;
      }
      wordsBigInt[i] = new BigInteger(cacheWord);
      //words[i] = cacheWord;
      cacheWord = "";
    }
    for(int i = 0;i<textWTE.length()%threadAnz;i++){
      int buchstabe = ((int)textWTE.charAt((teilLaenge*threadAnz)+i))+100;
      cacheWord = cacheWord+buchstabe;
      wordsBigInt[i] = new BigInteger(cacheWord);
    }

    words[threadAnz]= cacheWord;
    System.out.println("------wordInt Inhalt--------");
  for(int i = 0;i<wordsBigInt.length;i++){
    System.out.println(wordsBigInt[i]);
  }

  System.out.println("---------WordInt Stream----");

     wordsBigInt =
         (BigInteger[]) Arrays
         .stream(wordsBigInt)
         .parallel()
         //.map(x -> (x^e)%n)
         .map(x -> x.modPow(BigInteger.valueOf(e),n))
         .toArray(BigInteger[]::new);
    System.out.println("-------wordInt Inhalt------");
    for(int i = 0;i<wordsBigInt.length;i++){
      System.out.println(wordsBigInt[i]);
    }

/** anderer Ansatz
 * MyThread[] myThreads = new MyThread[threadAnz];
 *     Thread[] threads = new Thread[threadAnz];
 *     for(int i = 0;i<threadAnz;i++){
 *       myThreads[i] = new MyThread();
 *       threads[i] = new Thread(myThreads[i]);
 *       threads[i].start();
 */


    }

  }