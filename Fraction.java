public class Fraction implements Comparable {
    int numerateur,denominateur;

    public Fraction(int numerateur, int denominateur) {
        this.numerateur = numerateur;
        this.denominateur = denominateur;
        Fraction frac = Fraction.reduire(this);
        this.numerateur = frac.numerateur;
        this.denominateur = frac.denominateur;

    }

    public Fraction(int num){
        numerateur = num;
        denominateur = 1;
    }

    public Fraction(Fraction frac){
        this.numerateur = frac.numerateur;
        this.denominateur = frac.denominateur;
    }
    //TODO accepter les nombres à virgule
    public static Fraction parse(String str){
        if (str.equalsIgnoreCase(""))
            return new Fraction(0);
        String[] parts = str.split("/");
        if (parts.length == 1) {
            try{
                return new Fraction(Integer.parseInt(parts[0]));
            }catch (IllegalArgumentException e){
                throw new IllegalArgumentException("Le nombre doit être un nombre entier");
            }
        } else if (parts.length == 2) {
            try{
                return new Fraction(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
            }catch (IllegalArgumentException e){
                throw new IllegalArgumentException("Le numérateur et le dénominateur doivent être des nombres entiers");
            }
        } else {
            throw new IllegalArgumentException("La fraction doit être au format 'numerateur/denominateur'");
        }
    }

    private int euclide(int num1, int num2){
        int reste = num2;
        int a = num1;
        int b;
        do {
            b = reste;
            reste = a%b;
            a = b;
        }while (reste != 0);
        return b;
    }

    private int pgcd(){
        return euclide(numerateur, denominateur);
    }

    private static Fraction reduire(Fraction frac){
        int d = frac.pgcd();
        frac.numerateur /= d;
        frac.denominateur /= d;
        if (frac.denominateur < 0){
            frac.numerateur *= -1;
            frac.denominateur *= -1;
        }
        return frac;
    }

    public static Fraction inverse(Fraction f){
        return new Fraction(f.denominateur,f.numerateur);
    }

    public static Fraction oppose(Fraction f){
        return new Fraction(-f.numerateur,f.denominateur);
    }

    public Fraction addition(Fraction q){
        return new Fraction(this.numerateur*q.denominateur+this.denominateur*q.numerateur,this.denominateur*q.denominateur);
    }

    public Fraction soustraction(Fraction q){
        return addition(Fraction.oppose(q));
    }

    public Fraction multiplication(Fraction q){
        return new Fraction(this.numerateur*q.numerateur,this.denominateur*q.denominateur);
    }

    public Fraction division(Fraction q){
        return multiplication(Fraction.inverse(q));
    }

    public Fraction produit(int a){
        return multiplication(new Fraction(a));
    }

    private boolean plusPetit(Fraction frac){
        return (this.numerateur * frac.denominateur) < (this.denominateur * frac.numerateur);
    }

    private boolean plusGrand(Fraction frac){
        return (this.numerateur * frac.denominateur) > (this.denominateur * frac.numerateur);
    }

    @Override
    public String toString() {
        if (denominateur != 1)
            return numerateur + "/" + denominateur;
        return Integer.toString(numerateur);
    }

    @Override
    public int compareTo(Object o) {
        if (o == null)
            throw new NullPointerException();
        if (!(o instanceof Integer || o instanceof Fraction))
            throw new ClassCastException("Objet non comparable à une fraction");
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
        return 0;
    }
}
