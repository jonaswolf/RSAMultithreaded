import java.math.BigInteger;
import java.util.Arrays;

public class RSA {
  public static BigInteger[] encry(String word, BigInteger p,BigInteger q, BigInteger e){
    //int e = 35;
    //p*p=n>200000000
    //BigInteger p = new BigInteger("100000000019");
    //BigInteger q = new BigInteger("191938579691");
    BigInteger n = p.multiply(q);
    System.out.println(n);
    int threadAnz = (int)word.length()/6;
    String[] words = new String[threadAnz+1];
    int teilLaenge = (word.length()-(word.length()%threadAnz))/threadAnz;
    BigInteger[] wordsBigInt = new BigInteger[threadAnz+1];
    wordsBigInt[threadAnz] = BigInteger.valueOf(0);
    //int[] wordsInt = new int[threadAnz+1];
    //long[] wordsLong = new long[threadAnz+1];
    String cacheWord = "";
    for(int i = 0;i<threadAnz;i++){
      for(int o = 0;o<teilLaenge;o++){
        int buchstabe = ((int)word.charAt((teilLaenge*i)+o))+100;
        cacheWord = cacheWord+buchstabe;
      }
      wordsBigInt[i] = new BigInteger(cacheWord);
      //words[i] = cacheWord;
      cacheWord = "";
    }
    for(int i = 0;i<word.length()%threadAnz;i++){
      int buchstabe = ((int)word.charAt((teilLaenge*threadAnz)+i))+100;
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
            .map(x -> x.modPow(e,n))
            .toArray(BigInteger[]::new);
    System.out.println("-------wordInt Inhalt------");
    for(int i = 0;i<wordsBigInt.length;i++){
      System.out.println(wordsBigInt[i]);
    }
    return wordsBigInt;
  }
}
