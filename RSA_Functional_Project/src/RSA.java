import java.util.Scanner;
import java.util.stream.IntStream;

public class RSA {

    //Scanner initialisieren
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

      // Generieren der Primzahlen

        System.out.println("Bitte geben Sie die erste Primzahl ein.");
        int prime = sc.nextInt();
        while (!isPrime(prime)){
            System.out.println(prime + " ist keine Primzahl.");
            prime = sc.nextInt();
        }
        int qrime = sc.nextInt();
        while (!isPrime(qrime)){
            System.out.println(qrime + " ist keine Primzahl.");
            qrime = sc.nextInt();
        }
        System.out.println(isPrime(prime));

        // Phi berechnen
        Berechnungen phi = (p, q) -> (p-1) * (q-1);
        int phiInt = phi.funktion(prime, qrime);

        // N berechnen
        Berechnungen n = (p, q) -> p * q;
        int nInt = n.funktion(prime, qrime);

        // E-Eingabe testen
        System.out.println("Bitte geben Sie ein passendes E ein.");
        int e = sc.nextInt();
        while (!testE(phiInt,e)){
            System.out.println("ggT(phi," + e + ") != 1");
            e = sc.nextInt();
        }

    }

    // Ist die eingegebene Zahl eine Primzahl?
    public static boolean isPrime(int prime) {
        return prime > 1 &&
                IntStream.range(2, prime)
                .noneMatch(index -> prime % index == 0);
    }

    // Haben E und Phi den ggT = 1?
    public static boolean testE(int phi, int e){
        if(ggt.fkt(phi,e)==1){
            return true;
        }
        return false;
    }

    // Interface zur Berechnung von Phi und N
    interface Berechnungen {
        int funktion (int p, int q);
    }

    // Interface zur Berechnung des ggT und des Inversen
    interface Euklid {
        int fkt(int qe, int qe2);
    }

    // ggT berechnen
    static Euklid ggt = (qe, qe2) -> {
        int u = 0;
        int u2 = 1;
        int r = 1;
        int r2;
        int cache;
        while (qe2 != 0) {
            r2 = r;
            r = (qe - (qe % qe2)) / qe2;
            cache = qe2;
            qe2 = qe % qe2;
            qe = cache;
        }
        return qe;
    };


    // Inverses berechnen
    Euklid inverses = (qe, qe2) -> {
        int u = 0;
        int u2 = 1;
        int r = 1;
        int r2;
        int cache;
        final int seEEA = qe2;
        while (qe2 != 0) {
            r2 = r;
            r = (qe - (qe % qe2)) / qe2;
            cache = qe2;
            qe2 = qe % qe2;
            qe = cache;
            if (qe != seEEA) {
                //erweiterung des EA
                cache = u2;
                u2 = u - r2 * u2;
                u = cache;
            }
        }
        return u2;
    };

}
