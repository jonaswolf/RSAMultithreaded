import java.math.BigInteger;
import java.util.Arrays;

public class RSA {
  /**
   * encry Methode von Dennis und Arthur
   * @param word
   * Die Sequenz die Verschlüsselt werden soll
   * @param p
   * Eine möglichst zufällige Primzahl im BigInteger Format
   * @param q
   * Eine möglichst zufällige Primzahl im BigInteger Format
   * @param e
   * Muss eine Zahl sein bei der ggT(e,phi)== 1 ist.
   * @return
   * Ein Array an verschlüsselten Teilsequenzen im BigInteger Format
   */
  public static BigInteger[] encry(String word, BigInteger p,BigInteger q, BigInteger e){
    //Berechnung von n
    BigInteger n = p.multiply(q);
    //Bestimmung der relativen Größe der Zeichen Gruppe.
    int threadAnz = 4;
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
