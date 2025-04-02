import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Fraction[] fracs = new Fraction[]{new Fraction(1,2),new Fraction(1,3),new Fraction(1,4),new Fraction(-1),new Fraction(-2,3)};
        Fraction[] fracs_ = fracs.clone();
        Arrays.sort(fracs_);
        for (int i = 0; i < fracs.length; i++) {
            System.out.printf("%8s\t%8s\n",fracs[i],fracs_[i]);
        }
    }
}
