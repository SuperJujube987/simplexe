import java.util.ArrayList;

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

    public Fraction[] getLigne(int i) {
        return valeurs[i];
    }

    public Fraction[] getColonne(int j) {
        Fraction[] colonne = new Fraction[m];
        for (int i = 0; i < m; i++) {
            colonne[i] = valeurs[i][j];
        }
        return colonne;
    }

    public void setValeur(int i, int j, Fraction val) {
        valeurs[i][j] = val;
    }

    public void setLigne(int i, Fraction[] ligne) {
        if (ligne.length != n) {
            throw new IllegalArgumentException("La longueur de la ligne doit être égale au nombre de colonnes de la matrice.");
        }
        System.arraycopy(ligne, 0, valeurs[i], 0, n);
    }

    public void setColonne(int j, Fraction[] colonne) {
        if (colonne.length != m) {
            throw new IllegalArgumentException("La longueur de la colonne doit être égale au nombre de lignes de la matrice.");
        }
        for (int i = 0; i < m; i++) {
            valeurs[i][j] = colonne[i];
        }
    }

    public int getNumRows() {
        return m;
    }

    public int getNumCols() {
        return n;
    }

    public static Matrice matriceLigne(Fraction[] ligne) {
        Matrice matrice = new Matrice(1, ligne.length);
        matrice.setLigne(0, ligne);
        return matrice;
    }

    public static Matrice matriceColonne(Fraction[] colonne) {
        Matrice matrice = new Matrice(colonne.length, 1);
        matrice.setColonne(0, colonne);
        return matrice;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        int len = maxLen(this)+2;
        String format = "%" + len + "s";
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(String.format(format, getValeur(i, j)));
            }
            sb.append("\n");
        }

        return sb.toString();
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

    public static Matrice sousMatrice(Matrice m, int[] lignes, int[] colonnes) {
        Matrice sousMatrice = new Matrice(lignes.length, colonnes.length);
        for (int i = 0; i < lignes.length; i++) {
            for (int j = 0; j < colonnes.length; j++) {
                sousMatrice.setValeur(i, j, m.getValeur(lignes[i], colonnes[j]));
            }
        }
        return sousMatrice;
    }

    public static Matrice identite(int n) {
        Matrice identite = new Matrice(n, n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    identite.setValeur(i, j, new Fraction(1));
                } else {
                    identite.setValeur(i, j, new Fraction(0));
                }
            }
        }
        return identite;
    }

    public static Fraction det(Matrice m){
        if (m.m != m.n) {
            throw new IllegalArgumentException("La matrice doit être carrée pour le calcul du déterminant.");
        }
        if (m.m == 1) {
            return m.getValeur(0, 0);
        }
        if (m.m == 2) {
            return m.getValeur(0, 0).multiplication(m.getValeur(1, 1)).soustraction(m.getValeur(0, 1).multiplication(m.getValeur(1, 0)));
        }
        Fraction det = new Fraction(0);
        for (int j = 0; j < m.n; j++) {
            Matrice sousMatrice = new Matrice(m.m - 1, m.n - 1);
            for (int i = 1; i < m.m; i++) {
                for (int k = 0; k < m.n; k++) {
                    if (k < j) {
                        sousMatrice.setValeur(i - 1, k, m.getValeur(i, k));
                    } else if (k > j) {
                        sousMatrice.setValeur(i - 1, k - 1, m.getValeur(i, k));
                    }
                }
            }
            det = det.addition(m.getValeur(0, j).multiplication(det(sousMatrice)).multiplication((j % 2 == 0) ? new Fraction(1) : new Fraction(-1)));
        }
        return det;
    }

    public static Matrice inverse(Matrice m) {
        if (m.m != m.n) {
            throw new IllegalArgumentException("La matrice doit être carrée pour le calcul de l'inverse.");
        }
        Fraction det = det(m);
        if (det.equals(new Fraction(0))) {
            throw new IllegalArgumentException("La matrice est singulière et n'a pas d'inverse.");
        }
        Matrice adjoint = new Matrice(m.m, m.n);
        for (int i = 0; i < m.m; i++) {
            for (int j = 0; j < m.n; j++) {
                Matrice sousMatrice = new Matrice(m.m - 1, m.n - 1);
                for (int k = 0; k < m.m; k++) {
                    for (int l = 0; l < m.n; l++) {
                        if (k != i && l != j) {
                            sousMatrice.setValeur(k < i ? k : k - 1, l < j ? l : l - 1, m.getValeur(k, l));
                        }
                    }
                }
                adjoint.setValeur(j, i, det(sousMatrice).multiplication((i + j) % 2 == 0 ? new Fraction(1) : new Fraction(-1)));
            }
        }
        return produitScalaire(adjoint, Fraction.inverse(det));
    }

    public static Matrice concat_h(Matrice m1, Matrice m2) {
        if (m1.m != m2.m) {
            throw new IllegalArgumentException("Les matrices doivent avoir le même nombre de lignes pour la concaténation.");
        }
        Matrice resultat = new Matrice(m1.m, m1.n + m2.n);
        for (int i = 0; i < m1.m; i++) {
            for (int j = 0; j < m1.n; j++) {
                resultat.setValeur(i, j, m1.getValeur(i, j));
            }
            for (int j = 0; j < m2.n; j++) {
                resultat.setValeur(i, j + m1.n, m2.getValeur(i, j));
            }
        }
        return resultat;
    }

    public static Matrice concat_v(Matrice m1, Matrice m2) {
        if (m1.n != m2.n) {
            throw new IllegalArgumentException("Les matrices doivent avoir le même nombre de colonnes pour la concaténation.");
        }
        Matrice resultat = new Matrice(m1.m + m2.m, m1.n);
        for (int i = 0; i < m1.m; i++) {
            for (int j = 0; j < m1.n; j++) {
                resultat.setValeur(i, j, m1.getValeur(i, j));
            }
        }
        for (int i = 0; i < m2.m; i++) {
            for (int j = 0; j < m2.n; j++) {
                resultat.setValeur(i + m1.m, j, m2.getValeur(i, j));
            }
        }
        return resultat;
    }

    private static int member(int[] tableau, int val) {
        for (int i = 0; i < tableau.length; i++) {
            if (tableau[i] == val) {
                return i;
            }
        }
        return -1;
    }

    public static Matrice simplexe(Matrice mat, int[] initial, int[] base) {
        Matrice resultat = new Matrice(mat);

        int m,n;
        m = mat.getNumRows()-1;
        n = mat.getNumCols()-1;
        ArrayList<Integer> hors_base_ = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (member(initial,i)==-1){
                hors_base_.add(i);
            }
        }
        int[] hors_base = hors_base_.stream().mapToInt(i -> i).toArray();
        int[] lignes = new int[m];
        for (int i = 0; i < m; i++) {
            lignes[i]=i;
        }
        Matrice B = Matrice.sousMatrice(mat, lignes, base);
        Matrice N = Matrice.sousMatrice(mat, lignes, hors_base);
        Matrice cB = Matrice.sousMatrice(mat, new int[]{m}, base);
        Matrice cN = Matrice.sousMatrice(mat, new int[]{m}, hors_base);
        Matrice b = Matrice.sousMatrice(mat, lignes, new int[]{n});
        Matrice Z = Matrice.produitScalaire(Matrice.sousMatrice(mat,
                new int[]{m}, new int[]{n}), new Fraction(-1));

        Matrice B_inv = Matrice.inverse(B);
        Matrice B_invN = Matrice.multiplication(B_inv, N);
        Matrice moinsPi = Matrice.produitScalaire(Matrice.multiplication(cB,B_inv), new Fraction(-1));
        Matrice cN_bar = Matrice.addition(cN, Matrice.multiplication(moinsPi, N));
        Matrice b_bar = Matrice.multiplication(B_inv, b);
        Matrice moinsZ_bar = Matrice.soustraction(Matrice.produitScalaire(Z, new Fraction(-1)),
                Matrice.multiplication(cB, b_bar));

        for (int i = 0; i < n; i++) {
            int pos = member(initial,i);
            if (pos != -1) {
                resultat.setColonne(i, Matrice.concat_v(Matrice.matriceColonne(B_inv.getColonne(i)),
                        Matrice.matriceColonne(moinsPi.getColonne(i))).getColonne(0));
            } else{
                pos = member(hors_base,i);
                resultat.setColonne(i, Matrice.concat_v(Matrice.matriceColonne(B_invN.getColonne(pos)),
                        Matrice.matriceColonne(cN_bar.getColonne(pos))).getColonne(0));
            }
        }
        resultat.setColonne(n, Matrice.concat_v(b_bar,
                Matrice.matriceColonne(moinsZ_bar.getColonne(0))).getColonne(0));

        return resultat;
    }
}
