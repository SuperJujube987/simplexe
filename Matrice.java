public class Matrice {
    Fraction[][] valeurs;
    int m, n;

    public Matrice(int m, int n) {
        this.m = m;
        this.n = n;
        valeurs = new Fraction[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                valeurs[i][j] = new Fraction(0);
            }
        }
    }

    public Matrice(Matrice matrice) {
        this.m = matrice.m;
        this.n = matrice.n;
        this.valeurs = new Fraction[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                this.valeurs[i][j] = new Fraction(matrice.getValeur(i, j));
            }
        }
    }

    public Fraction getValeur(int i, int j) {
        return valeurs[i][j];
    }

    public void setValeur(int i, int j, Fraction val) {
        valeurs[i][j] = val;
    }

    private static int maxLen(Matrice m){
        int curMax = 0;
        for (Fraction[] fracs:m.valeurs){
            for (Fraction frac:fracs) {
                int len = String.valueOf(frac.numerateur).length() + String.valueOf(frac.denominateur).length() + 1;
                if (len > curMax) {
                    curMax = len;
                }
            }
        }
        return curMax;
    }

    public static void affichage(Matrice m) {
        int len = maxLen(m)+2;
        String format = "%" + len + "s";
        for (int i = 0; i < m.m; i++) {
            for (int j = 0; j < m.n; j++) {
                System.out.printf(format,m.getValeur(i, j));
            }
            System.out.println();
        }
    }

    public static Matrice transpose(Matrice m) {
        Matrice transposee = new Matrice(m.n, m.m);
        for (int i = 0; i < m.m; i++) {
            for (int j = 0; j < m.n; j++) {
                transposee.setValeur(j, i, m.getValeur(i, j));
            }
        }
        return transposee;
    }

    public static Matrice addition(Matrice m1, Matrice m2) {
        if (m1.m != m2.m || m1.n != m2.n) {
            throw new IllegalArgumentException("Les matrices doivent avoir la même taille pour l'addition.");
        }
        Matrice resultat = new Matrice(m1.m, m1.n);
        for (int i = 0; i < m1.m; i++) {
            for (int j = 0; j < m1.n; j++) {
                resultat.setValeur(i, j, m1.getValeur(i, j).addition(m2.getValeur(i, j)));
            }
        }
        return resultat;
    }

    public static Matrice soustraction(Matrice m1, Matrice m2) {
        if (m1.m != m2.m || m1.n != m2.n) {
            throw new IllegalArgumentException("Les matrices doivent avoir la même taille pour la soustraction.");
        }
        Matrice resultat = new Matrice(m1.m, m1.n);
        for (int i = 0; i < m1.m; i++) {
            for (int j = 0; j < m1.n; j++) {
                resultat.setValeur(i, j, m1.getValeur(i, j).soustraction(m2.getValeur(i, j)));
            }
        }
        return resultat;
    }

    public static Matrice multiplication(Matrice m1, Matrice m2) {
        if (m1.n != m2.m) {
            throw new IllegalArgumentException("Le nombre de colonnes de la première matrice doit être égal au nombre de lignes de la deuxième matrice.");
        }
        Matrice resultat = new Matrice(m1.m, m2.n);
        for (int i = 0; i < m1.m; i++) {
            for (int j = 0; j < m2.n; j++) {
                Fraction somme = new Fraction(0);
                for (int k = 0; k < m1.n; k++) {
                    somme = somme.addition(m1.getValeur(i, k).multiplication(m2.getValeur(k, j)));
                }
                resultat.setValeur(i, j, somme);
            }
        }
        return resultat;
    }

    public static Matrice produitScalaire(Matrice m, Fraction scalaire) {
        Matrice resultat = new Matrice(m.m, m.n);
        for (int i = 0; i < m.m; i++) {
            for (int j = 0; j < m.n; j++) {
                resultat.setValeur(i, j, m.getValeur(i, j).multiplication(scalaire));
            }
        }
        return resultat;
    }

    public static Matrice simplexe(Matrice m, int[] base_initiale, int[] base_finale) {
        Matrice resultat = new Matrice(m);

        return resultat;
    }
}
