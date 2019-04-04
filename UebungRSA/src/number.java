public class number {

  private int x;

  public number(int x) {
    this.x = x;
  }

  public int getX() {
    return x;
  }

  public String getSize() {
    String i = Integer.toString(x);
    int b = i.length();
    return i;
  }
}


