public class Main {
    public static void main(String[] args) {
        Matrice m = new Matrice(4,7);
        m.setLigne(0, new Fraction[]{new Fraction(1), new Fraction(0),
                new Fraction(0), new Fraction(3), new Fraction(2),
                new Fraction(0), new Fraction(60)});
        m.setLigne(1, new Fraction[]{new Fraction(0), new Fraction(1),
                new Fraction(0), new Fraction(-1), new Fraction(1),
                new Fraction(4), new Fraction(10)});
        m.setLigne(2, new Fraction[]{new Fraction(0), new Fraction(0),
                new Fraction(1), new Fraction(2), new Fraction(-2),
                new Fraction(5), new Fraction(50)});
        m.setLigne(3, new Fraction[]{new Fraction(0), new Fraction(0),
                new Fraction(0), new Fraction(-2), new Fraction(-3),
                new Fraction(-3), new Fraction(0)});

        System.out.println("Matrice initiale :");
        System.out.println(m);
        Matrice m1 = Matrice.simplexe(m, new int[]{0,1,2}, new int[]{4,2,3});
        System.out.println("Matrice après la méthode du simplexe :");
        System.out.println(m1);
    }
}
