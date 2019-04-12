import java.math.BigInteger;
import java.util.Arrays;

public class RSA {
  //encry Methode von Dennis und Arthur
  public static BigInteger[] encry(String word, BigInteger p,BigInteger q, BigInteger e){
    //Berechnung von n
    BigInteger n = p.multiply(q);
    //Bestimmung der Größe der Zeichen Gruppe (Vorläufig. Warte auf Randomizer Methode und Zusammenführung einiger Teilaufgaben)
    int threadAnz = word.length()/6;
    //Vorbereitung eines BigInt Arrays
    String[] words = new String[threadAnz+1];
    int teilLaenge = (word.length()-(word.length()%threadAnz))/threadAnz;
    BigInteger[] wordsBigInt = new BigInteger[threadAnz+1];
    wordsBigInt[threadAnz] = BigInteger.valueOf(0);
    String cacheWord = "";
    for(int i = 0;i<threadAnz;i++){
      for(int o = 0;o<teilLaenge;o++){
        int buchstabe = ((int)word.charAt((teilLaenge*i)+o))+100;
        cacheWord = cacheWord+buchstabe;
      }
      wordsBigInt[i] = new BigInteger(cacheWord);
      cacheWord = "";
    }
    for(int i = 0;i<word.length()%threadAnz;i++){
      int buchstabe = ((int)word.charAt((teilLaenge*threadAnz)+i))+100;
      cacheWord = cacheWord+buchstabe;
      wordsBigInt[i] = new BigInteger(cacheWord);
    }
    //Funktionale Verschlüsselung der einzelnen Array Elemente
    wordsBigInt =
         Arrays
            .stream(wordsBigInt)
            .parallel()
            .map(x -> x.modPow(e,n))
            .toArray(BigInteger[]::new);
    return wordsBigInt;
  }
}
