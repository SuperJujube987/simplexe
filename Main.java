public class Main {
    public static void main(String[] args) {
        Matrice m = new Matrice(4,9);
        for (int i = 0; i < m.m; i++) {
            for (int j = 0; j < m.n; j++) {
                m.setValeur(i,j,Fraction.reduire(new Fraction(i+j,i*j+1)));
            }
        }
        System.out.println("Matrice originale :");
        Matrice.affichage(m);
    }
}
