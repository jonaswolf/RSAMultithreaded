public class SimpleMaths {

    public static void main(String[] args) {

        Mathe addieren = (int a, int b) -> a + b;
        Mathe mult = (int a, int b) -> a * b;

        System.out.println(addieren.funktion(5, 3));

    }

    interface Mathe {
        int funktion (int a, int b);
    }

}
