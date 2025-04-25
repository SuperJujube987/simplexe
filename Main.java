public class Main {
    public static void main(String[] args) {
        Fraction f1 = new Fraction(2);
        System.out.println("f1:"+f1);
        Fraction f2 = new Fraction(2,3);
        System.out.println("f2:"+f2);
        Fraction f3 = new Fraction(2,4);
        System.out.println("f3:"+f3);
        Fraction f4 = new Fraction(0);
        Fraction f5 = new Fraction(0);
        Fraction f6 = new Fraction(0);
        try{
            f4 = Fraction.parse("");
        }catch (IllegalArgumentException e){
            System.out.println("\terreur f4:" + e.getMessage());
        }finally {
            System.out.println("f4:"+f4);
        }
        try {
            f5 = Fraction.parse("2");
        }catch (IllegalArgumentException e){
            System.out.println("\terreur f5:" + e.getMessage());
        }finally {
            System.out.println("f5:"+f5);
        }
        try {
            f6 = Fraction.parse("2/5");
        }catch (IllegalArgumentException e){
            System.out.println("\terreur f6:" + e.getMessage());
        }finally {
            System.out.println("f6:"+f6);
        }
    }
}
