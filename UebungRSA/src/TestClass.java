import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Iterator;
import java.util.function.IntFunction;

public class TestClass {

  public static void main(String[] args) {
    String text = "Wörter und so";
    BigInteger e = new BigInteger("35");
    BigInteger p = new BigInteger("100000000019");
    BigInteger q = new BigInteger("191938579691");
    BigInteger[] verschluesselt =  RSA.encry(text,p,q,e);
    }



  }