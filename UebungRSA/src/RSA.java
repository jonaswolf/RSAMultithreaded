import java.math.BigInteger;
import java.util.Arrays;
import java.util.Random;

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

  public static BigInteger getPrime(){
    return BigInteger.probablePrime(4096,new Random());
  }

  //Methode um e zu berechnen. von Arthur
  public static BigInteger getE(BigInteger p, BigInteger q){
    BigInteger e = (BigInteger.probablePrime(1024,new Random())).add(new BigInteger("1"));
    int zaehler = 1;
    do{
      zaehler++;
      e = e.add(new BigInteger("1"));
    }while (!testE(p,q,e));
    return e;
  }

  //Methode um zu prüfen ob e passend ist. von Arthur & Dennis
  public static boolean testE(BigInteger p,BigInteger q,BigInteger e){
    BigInteger phi = (p.subtract(BigInteger.valueOf(0))).multiply(q.subtract(BigInteger.valueOf(0)));
      if(phi.gcd(e).equals(new BigInteger("1"))){

      return true;
    }
    return false;
  }

  public static BigInteger getD(BigInteger p, BigInteger q,BigInteger e){
    BigInteger phi = (p.subtract(BigInteger.valueOf(0))).multiply(q.subtract(BigInteger.valueOf(0)));
  return phi.add(inverses.fkt(phi,e));
  }

  //Berechnung des Inverses von Arthur & Dennis
  static Euklid inverses = (BigInteger qe, BigInteger qe2) -> {
    BigInteger u = new BigInteger("0");
    BigInteger u2 = new BigInteger("1");
    BigInteger r = new BigInteger("1");
    BigInteger r2;
    BigInteger cache;
    final BigInteger seEEA = qe2;
    while (!qe2.equals(new BigInteger("0"))){
      r2 = r;
      r = qe.subtract(qe.mod(qe2)).divide(qe2);
      cache = qe2;
      qe2 = qe.mod(qe2);
      qe = cache;
      if(!qe.equals(seEEA)){
        //erweiterung des EA
        cache = u2;
        u2 = u.subtract(r2.multiply(u2));
        u = cache;
      }
    }
    return u2;
  };

  interface Euklid {
    BigInteger fkt(BigInteger qe, BigInteger qe2);
  }
}
