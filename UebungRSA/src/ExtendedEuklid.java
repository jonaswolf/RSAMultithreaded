import java.util.function.Consumer;

public class ExtendedEuklid {

  private static int sphi;
  private static int seEEA;
  private static boolean eea;

  public ExtendedEuklid(int sphi, int seEEA, boolean eea) {
    this.sphi = sphi;
    this.seEEA = seEEA;
    this.eea = eea;
  }

  public static int feea(Consumer<ExtendedEuklid> m) {
    //ExtendedEuklid f = new ExtendedEuklid();
    int u = 0;
    int u2 = 1;
    int qe = sphi;
    int qe2 = seEEA;
    int r = 1;
    int r2;
    int cache;
    //EA
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
    if (eea) {
      return u2;
    } else {
      return qe;
    }
  }
}
