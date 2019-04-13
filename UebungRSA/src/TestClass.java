import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;
import java.util.function.IntFunction;

public class TestClass {

  public static void main(String[] args) {
    String text = "Wörter und so";
    System.out.println("Ermittlung Zahl 1");
    BigInteger p = RSA.getPrime();
    System.out.println(p);
    System.out.println("p is prime: " + p.isProbablePrime(100));
    System.out.println("Ermittlung Zahl 2");
    BigInteger q = RSA.getPrime();
    System.out.println(q);
    System.out.println("p is prime: " + q.isProbablePrime(100));
    System.out.println("Ermittlung e");
    BigInteger e = RSA.getE(p, q);
    System.out.println("E ermittelt");
    System.out.println("Start Verschlüsselung");
    BigInteger[] verschluesselt = RSA.encry(text, p, q, e);
  }
}