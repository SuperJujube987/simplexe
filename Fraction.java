public class Fraction implements Comparable {
    int numerateur,denominateur;

    public Fraction(int numerateur, int denominateur) {
        this.numerateur = numerateur;
        this.denominateur = denominateur;
    }

    public Fraction(int num){
        numerateur = num;
        denominateur = 1;
    }

    public Fraction(Fraction frac){
        this.numerateur = frac.numerateur;
        this.denominateur = frac.denominateur;
    }

    private int euclide(int num1, int num2){
        int reste = num2;
        int a = num1;
        int b;
        do {
            b=reste;
            reste = a%b;
            a=b;
        }while (reste != 0);
        return b;
    }

    private int pgcd(){
        return euclide(numerateur, denominateur);
    }

    public Fraction reduire(Fraction frac){
        int d = frac.pgcd();
        frac.numerateur /= d;
        frac.denominateur /= d;
        return frac;
    }

    private Fraction inverse(){
        return new Fraction(denominateur,numerateur);
    }

    private Fraction oppose(){
        return new Fraction(-numerateur,denominateur);
    }

    public Fraction addition(Fraction q){
        return reduire(new Fraction(this.numerateur*q.denominateur+this.denominateur*q.numerateur,this.denominateur*q.denominateur));
    }

    public Fraction soustraction(Fraction q){
        return reduire(addition(q.oppose()));
    }

    public Fraction multiplication(Fraction q){
        return reduire(new Fraction(this.numerateur*q.numerateur,this.denominateur*q.denominateur));
    }

    public Fraction division(Fraction q){
        return reduire(multiplication(q.inverse()));
    }

    public Fraction produit(int a){
        return reduire(multiplication(new Fraction(a)));
    }

    private boolean plusPetit(Fraction frac){
        return (this.numerateur * frac.denominateur) < (this.denominateur * frac.numerateur);
    }

    private boolean plusGrand(Fraction frac){
        return (this.numerateur * frac.denominateur) > (this.denominateur * frac.numerateur);
    }

    private boolean egal(Fraction frac){
        return (this.numerateur * frac.denominateur) == (this.denominateur * frac.numerateur);
    }

    @Override
    public String toString() {
        if (denominateur != 1)
            return numerateur + "/" + denominateur;
        return Integer.toString(numerateur);
    }

    @Override
    public int compareTo(Object o) {
        if (!(o instanceof Integer || o instanceof Fraction))
            throw new ClassCastException("Object not a type comparable to a Fraction");
        Fraction a;
        if (o instanceof Integer){
            a = new Fraction((Integer) o);
        } else {
            a = new Fraction((Fraction) o);
        }
        if (plusPetit(a))
            return -1;
        if (plusGrand(a))
            return 1;
        else
            return 0;
    }
}
