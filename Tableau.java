import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Tableau extends JFrame {
//region GRAPHIC_COMPONENTS
    private JPanel mainPanel;
    private JTextField textField31;
    private JTextField textField21;
    private JTextField textField11;
    private JTextField textField12;
    private JTextField textField13;
    private JTextField textField14;
    private JTextField textField22;
    private JTextField textField23;
    private JTextField textField24;
    private JTextField textField32;
    private JTextField textField33;
    private JTextField textField34;
    private JButton effectuerButton;
    private JTextField textField1;
    private JTextField textField2;
//endregion

    Matrice mat = new Matrice(3,4);
    int[] base_init = new int[2];
    int[] nouvelle_base = new int[2];

    public Tableau() {

        effectuerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mat.setValeur(0,0,Fraction.parse(textField11.getText()));
                mat.setValeur(0,1,Fraction.parse(textField12.getText()));
                mat.setValeur(0,2,Fraction.parse(textField13.getText()));
                mat.setValeur(0,3,Fraction.parse(textField14.getText()));
                mat.setValeur(1,0,Fraction.parse(textField21.getText()));
                mat.setValeur(1,1,Fraction.parse(textField22.getText()));
                mat.setValeur(1,2,Fraction.parse(textField23.getText()));
                mat.setValeur(1,3,Fraction.parse(textField24.getText()));
                mat.setValeur(2,0,Fraction.parse(textField31.getText()));
                mat.setValeur(2,1,Fraction.parse(textField32.getText()));
                mat.setValeur(2,2,Fraction.parse(textField33.getText()));
                mat.setValeur(2,3,Fraction.parse(textField34.getText()));

                String[] str = textField2.getText().split(",");
                base_init[0] = Integer.parseInt(str[0]);
                base_init[1] = Integer.parseInt(str[1]);
                str = textField1.getText().split(",");
                nouvelle_base[0] = Integer.parseInt(str[0]);
                nouvelle_base[1] = Integer.parseInt(str[1]);

                System.out.println(Matrice.simplexe(mat, base_init, nouvelle_base));
            }
        });

        setTitle("Exécuteur de pivot du simplexe");
        setContentPane(mainPanel);
        setResizable(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);
    }
}
