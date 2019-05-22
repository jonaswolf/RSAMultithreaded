import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;
import java.util.function.IntFunction;

public class TestClass {

  public static void main(String[] args) {
    String text = "Wörter und so";
    System.out.println("Der Text lautet");
    System.out.println(text);

    System.out.println("Ermittlung Zahl 1 p");
    BigInteger p = RSA.getPrime();
    System.out.println(p);
    System.out.println("p is prime: " + p.isProbablePrime(100));

    System.out.println("Ermittlung Zahl 2 q");
    BigInteger q = RSA.getPrime();
    System.out.println(q);
    System.out.println("q is prime: " + q.isProbablePrime(100));

    System.out.println("Ermittlung e");
    BigInteger e = RSA.getE(p, q);
    System.out.println(e);
    System.out.println("E ermittelt");

    System.out.println("Ermittlung d");
    BigInteger d = RSA.getD(p, q, e);
    System.out.println(d);
    System.out.println("D ermittelt");

    System.out.println("Ermittlung n");
    BigInteger n = RSA.getN(p, q);
    System.out.println(n);
    System.out.println("N ermittelt");

    System.out.println("Der verschlüsselte Text lautet:");
    BigInteger[] verschluesselt = RSA.encry(text, n, e);
    String verschluesseltString = ("");
    for(int i = 0;i<verschluesselt.length;i++){
      verschluesseltString = verschluesseltString+(verschluesselt[i].toString())+" ";
    }
    System.out.println(verschluesseltString);

    System.out.println("Der entschlüsselte Text lautet:");
    BigInteger[] entschluesselt = RSA.encry(verschluesseltString, n, d);
    String entschluesseltString = ("");
    for(int i = 0;i<entschluesselt.length;i++){
      entschluesseltString = entschluesseltString+(entschluesselt[i].toString())+" ";
    }
    System.out.println(entschluesseltString);



  }
}