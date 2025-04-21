public class Main {
    public static void main(String[] args) {
        Fraction f1 = new Fraction(2);
        Fraction f2 = new Fraction(2,3);
        Fraction f3 = new Fraction(2,4);
        Fraction f4 = new Fraction(0);
        Fraction f5 = new Fraction(0);
        Fraction f6 = new Fraction(0);
        try{
            f4 = Fraction.parse("");
        }catch (IllegalArgumentException e){
            System.err.println("erreur f4:" + e.getMessage());
        }
        try {
            f5 = Fraction.parse("2");
        }catch (IllegalArgumentException e){
            System.err.println("erreur f5:" + e.getMessage());
        }
        try {
            f6 = Fraction.parse("2/5");
        }catch (IllegalArgumentException e){
            System.err.println("erreur f6:" + e.getMessage());
        }
        System.out.println(f1);
        System.out.println(f2);
        System.out.println(f3);
        System.out.println(f4);
        System.out.println(f5);
        System.out.println(f6);

    }
}
