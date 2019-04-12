import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
// p q 17 23
// e 35
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.lang.String;
import javax.swing.*;
import java.util.ArrayList;
import java.math.BigDecimal;


public class GUI extends JFrame {

  private ArrayList<number> cryWord;
  private JLabel labelPrime1, labelPrime2, labelTestPrime1, labelTestPrime2, labelE, labelEFits;
  private JTextField textPrime1, textPrime2, textE, textWTE, textEncryResult, textDecryResult;
  private JButton buttonTestPrime, buttonEncry, buttonDecry, buttonTestE;
  int p = 0;
  int q = 0;
  boolean bothPrime = false;
  int phi = 0;
  int eRSA = 0;
  int n = 0;
  int dRSA = 0;
  String encryResult = "";

  public GUI() {
    cryWord = new ArrayList<number>();
    this.setTitle("RSA mit ugly User Interface");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(1920, 1040);
    try {
      this.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("atosBackground.png")))));
    } catch (IOException e) {
    }
    this.setIconImage(new ImageIcon(("atos.png")).getImage());
    this.iniComp();
    this.structureComp();
    this.testPrime();
    this.testE();
    this.encry();
    this.decry();
  }

  public void iniComp() {
    this.labelPrime1 = new JLabel("Prime 1:");
    this.labelPrime2 = new JLabel("Prime 2");
    this.labelTestPrime1 = new JLabel("");
    this.labelTestPrime2 = new JLabel("");
    this.labelE = new JLabel("e");
    this.labelEFits = new JLabel("");
    this.textEncryResult = new JTextField("");
    this.textDecryResult = new JTextField("");
    this.textPrime1 = new JTextField("");
    this.textPrime2 = new JTextField("");
    this.textE = new JTextField("");
    this.textWTE = new JTextField("");
    this.buttonTestPrime = new JButton("Test prime");
    this.buttonDecry = new JButton("Decrypt text");
    this.buttonEncry = new JButton("Encrypt text");
    this.buttonTestE = new JButton("Test e");
  }

  public void structureComp() {
    this.getContentPane().setLayout(new GridBagLayout());
    this.getContentPane().setBackground(Color.blue);
    GridBagConstraints c = new GridBagConstraints();
    c.fill = GridBagConstraints.HORIZONTAL;
    c.insets = new Insets(1, 1, 1, 1);
    c.gridx = 0;
    c.gridy = 0;
    this.getContentPane().add(this.labelPrime1, c);
    labelPrime1.setForeground(Color.blue);
    c.gridx = 1;
    this.getContentPane().add(this.textPrime1, c);
    textPrime1.setBackground(Color.blue);
    textPrime1.setForeground(Color.white);
    c.gridx = 2;
    this.getContentPane().add(this.labelTestPrime1, c);
    this.labelTestPrime1.setForeground(Color.blue);
    c.gridy = 1;
    c.gridx = 0;
    this.getContentPane().add(this.labelPrime2, c);
    this.labelPrime2.setForeground(Color.blue);
    c.gridx = 1;
    this.getContentPane().add(this.textPrime2, c);
    this.textPrime2.setBackground(Color.blue);
    this.textPrime2.setForeground(Color.white);
    c.gridx = 2;
    this.getContentPane().add(this.labelTestPrime2, c);
    this.labelTestPrime2.setForeground(Color.blue);
    c.gridy = 2;
    c.gridx = 1;
    this.getContentPane().add(this.buttonTestPrime, c);
    this.buttonTestPrime.setBackground(Color.blue);
    this.buttonTestPrime.setForeground(Color.white);


  }

  public void structureComp2() {
    this.getContentPane().setLayout(new GridBagLayout());
    this.getContentPane().setBackground(Color.blue);
    GridBagConstraints c = new GridBagConstraints();
    c.fill = GridBagConstraints.HORIZONTAL;
    c.insets = new Insets(1, 1, 1, 1);
    c.gridx = 0;
    c.gridy = 0;
    this.getContentPane().add(this.labelPrime1, c);
    labelPrime1.setForeground(Color.blue);
    c.gridx = 1;
    this.getContentPane().add(this.textPrime1, c);
    textPrime1.setBackground(Color.blue);
    textPrime1.setForeground(Color.white);
    c.gridx = 2;
    this.getContentPane().add(this.labelTestPrime1, c);
    this.labelTestPrime1.setForeground(Color.blue);
    c.gridy = 1;
    c.gridx = 0;
    this.getContentPane().add(this.labelPrime2, c);
    this.labelPrime2.setForeground(Color.blue);
    c.gridx = 1;
    this.getContentPane().add(this.textPrime2, c);
    this.textPrime2.setBackground(Color.blue);
    this.textPrime2.setForeground(Color.white);
    c.gridx = 2;
    this.getContentPane().add(this.labelTestPrime2, c);
    this.labelTestPrime2.setForeground(Color.blue);
    c.gridy = 2;
    c.gridx = 1;
    this.getContentPane().add(this.buttonTestPrime, c);
    this.buttonTestPrime.setBackground(Color.blue);
    this.buttonTestPrime.setForeground(Color.white);
    c.gridy = 3;
    c.gridx = 0;
    this.getContentPane().add(this.labelE, c);
    this.labelE.setForeground(Color.blue);
    c.gridx = 1;
    this.getContentPane().add(this.textE, c);
    this.textE.setBackground(Color.blue);
    this.textE.setForeground(Color.white);
    c.gridx = 2;
    this.getContentPane().add(this.labelEFits, c);
    this.labelEFits.setForeground(Color.blue);
    c.gridy = 4;
    c.gridx = 1;
    this.getContentPane().add(this.buttonTestE, c);
    this.buttonTestE.setBackground(Color.blue);
    this.buttonTestE.setForeground(Color.white);
    c.gridy = 5;
    c.gridx = 0;

  }

  public void structureComp3() {
    this.getContentPane().setLayout(new GridBagLayout());
    this.getContentPane().setBackground(Color.blue);
    GridBagConstraints c = new GridBagConstraints();
    c.fill = GridBagConstraints.HORIZONTAL;
    c.insets = new Insets(1, 1, 1, 1);
    c.gridx = 0;
    c.gridy = 0;
    this.getContentPane().add(this.labelPrime1, c);
    labelPrime1.setForeground(Color.blue);
    c.gridx = 1;
    this.getContentPane().add(this.textPrime1, c);
    textPrime1.setBackground(Color.blue);
    textPrime1.setForeground(Color.white);
    c.gridx = 2;
    this.getContentPane().add(this.labelTestPrime1, c);
    this.labelTestPrime1.setForeground(Color.blue);
    c.gridy = 1;
    c.gridx = 0;
    this.getContentPane().add(this.labelPrime2, c);
    this.labelPrime2.setForeground(Color.blue);
    c.gridx = 1;
    this.getContentPane().add(this.textPrime2, c);
    this.textPrime2.setBackground(Color.blue);
    this.textPrime2.setForeground(Color.white);
    c.gridx = 2;
    this.getContentPane().add(this.labelTestPrime2, c);
    this.labelTestPrime2.setForeground(Color.blue);
    c.gridy = 2;
    c.gridx = 1;
    this.getContentPane().add(this.buttonTestPrime, c);
    this.buttonTestPrime.setBackground(Color.blue);
    this.buttonTestPrime.setForeground(Color.white);
    c.gridy = 3;
    c.gridx = 0;
    this.getContentPane().add(this.labelE, c);
    this.labelE.setForeground(Color.blue);
    c.gridx = 1;
    this.getContentPane().add(this.textE, c);
    this.textE.setBackground(Color.blue);
    this.textE.setForeground(Color.white);
    c.gridx = 2;
    this.getContentPane().add(this.labelEFits, c);
    this.labelEFits.setForeground(Color.blue);
    c.gridy = 4;
    c.gridx = 1;
    this.getContentPane().add(this.buttonTestE, c);
    this.buttonTestE.setBackground(Color.blue);
    this.buttonTestE.setForeground(Color.white);
    c.gridy = 5;
    c.gridx = 0;
    this.getContentPane().add(this.textWTE, c);
    this.textWTE.setBackground(Color.blue);
    this.textWTE.setForeground(Color.white);
    c.gridx = 2;
    this.getContentPane().add(this.textEncryResult, c);
    this.textEncryResult.setForeground(Color.white);
    this.textEncryResult.setBackground(Color.blue);
    c.gridy = 6;
    c.gridx = 0;
    this.getContentPane().add(this.buttonEncry, c);
    this.buttonEncry.setBackground(Color.blue);
    this.buttonEncry.setForeground(Color.white);
    c.gridy = 7;
    this.getContentPane().add(this.buttonDecry, c);
    this.buttonDecry.setBackground(Color.blue);
    this.buttonDecry.setForeground(Color.white);
    c.gridx = 2;
    this.getContentPane().add(this.textDecryResult, c);
    this.textDecryResult.setForeground(Color.white);
    this.textDecryResult.setBackground(Color.blue);
  }

  public void testPrime() {
    this.buttonTestPrime.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        p = Integer.parseInt(textPrime1.getText());
        if (testPrime(p)) {
          labelTestPrime1.setText("Primzahl " + p + " eingeloggt");
        } else {
          labelTestPrime1.setText(p + " ist keine Primzahl");
          p = 0;
          bothPrime = false;
        }
        q = Integer.parseInt(textPrime2.getText());
        if (testPrime(q)) {
          labelTestPrime2.setText("Primzahl " + q + " eingeloggt");
          if (testPrime(p)) {
            bothPrime = true;
          }
        } else {
          labelTestPrime2.setText(q + " ist keine Primzahl");
          q = 0;
          bothPrime = false;
        }
        if (bothPrime) {
          structureComp2();
          phi = (p - 1) * (q - 1);
          n = p * q;
          labelTestPrime1.setText("φ = (" + p + "-1)*(" + q + "-1) = " + phi);
          labelTestPrime2.setText("n = " + p + "*" + q + " = " + n);
        }
      }
    });

  }

  public boolean testPrime(int pri) {
    for (int i = 2; i < pri; i++) {
      if (pri % i == 0) {
        return false;
      }
    }
    return true;
  }

  public void testE() {
    this.buttonTestE.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        eRSA = Integer.parseInt(textE.getText());
        int a = extendedEuklid(phi, false, eRSA);
        if (a == 1) {
          dRSA = extendedEuklid(phi, true, eRSA) + phi;
          labelEFits.setText(
              "ggT(" + phi + "," + eRSA + " = " + a + ") also Public Key(" + eRSA + "," + n
                  + ") und Private Key(" + dRSA + "," + n + ")");
          structureComp3();

        } else {
          System.out.println(e + " doesn't fit!");
          labelEFits.setText("ggT(" + phi + "," + eRSA + ") = " + a + "  != 1");
        }
      }
    });
  }

  public void encry2(){
    this.buttonEncry.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String word = textWTE.getText();
        String[] words = new String[5];

      }
    });
  }

  public void encry() {
    this.buttonEncry.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        BigDecimal nBig = new BigDecimal(n);
        double wordZ;
        double cache;
        String word = textWTE.getText();
        String wordInZahl = "";
        for (int i = 0; i < word.length(); i++) {
          int charAsInt = (int) word.charAt(i);
          wordInZahl = wordInZahl + charAsInt;
        }

        for (int i = 0; i < word.length(); i++) {
          wordZ = word.charAt(i);
          BigDecimal wordZBig = new BigDecimal(wordZ);
          cache = wordZ;
          BigDecimal cacheBig = new BigDecimal(cache);
          for (int o = 2; o <= eRSA; o++) {
            wordZBig = wordZBig.multiply(cacheBig);
          }
          wordZBig = wordZBig.remainder(nBig);
          int wordZInt = wordZBig.intValueExact();
          number newNumber = new number(wordZInt);
          cryWord.add(newNumber);
        }

        for (int m = 0; m < cryWord.size(); m++) {
          encryResult = encryResult + cryWord.get(m).getX();
        }
        textEncryResult.setText(wordInZahl + "^" + eRSA + " MODULO " + n + " = " + encryResult);
      }
    });
  }

  public void decry() {
    this.buttonDecry.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        int decry;
        double nDouble = n;
        String result = "";
        String resultNumber = "";
        BigDecimal nBigDouble = new BigDecimal(nDouble);
        double wordEdouble;
        for (int i = 0; i < cryWord.size(); i++) {
          wordEdouble = cryWord.get(i).getX();
          BigDecimal wordEBigdouble = new BigDecimal((wordEdouble));
          BigDecimal cache = wordEBigdouble;
          for (int o = 2; o <= dRSA; o++) {
            wordEBigdouble = wordEBigdouble.multiply(cache);
          }
          BigDecimal r = wordEBigdouble.remainder(nBigDouble);
          decry = r.intValueExact();
          resultNumber = resultNumber + decry;
          result = result + (char) decry;
        }
        textDecryResult.setText(
            encryResult + "^" + dRSA + " MODULO " + n + " = " + resultNumber + " = "
                + result);
        cryWord.clear();
      }
    });
  }

  public int extendedEuklid2(int phi, boolean eea, int eEEA) {
    int u = 0;
    int u2 = 1;
    int qe = phi;
    int qe2 = eEEA;
    int r = 1;
    int r2;
    int cache;
    //EA
    while (qe2 != 0) {
      r2 = r;
      r = (qe - (qe % qe2)) / qe2;
      cache = qe2;
      qe2 = qe % qe2;
      qe = cache;
      if (qe != eEEA) {
        //erweiterung des EA
        cache = u2;
        u2 = u - r2 * u2;
        u = cache;
      }
    }
    if (eea) {
      return u2;
    } else {
      return qe;
    }
  }


   public int extendedEuklid(int phi,boolean eea, int eEEA){
     ExtendedEuklid klasse = new ExtendedEuklid(phi,eEEA,eea);
     int i = ExtendedEuklid.feea(eeb -> {

          });
          return i;
      }

}
