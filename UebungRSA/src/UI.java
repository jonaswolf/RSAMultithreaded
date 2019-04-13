import java.math.BigInteger;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;
public class UI extends JFrame {

    private String word;
    private static RSA rsa;
    private static final String GENERATE_COMMAND = "generate";
    private static final String ENCRYPT_COMMAND = "encrypt";
    private static final String DECRYPT_COMMAND = "decrypt";

    public UI (){
        //initialise RSA
        rsa = new RSA();
        BigInteger p = RSA.getPrime();
        BigInteger q = RSA.getPrime();
        BigInteger e = RSA.getE(p,q);
        BigInteger d = RSA.getD(p,q,e);
        // initialize Panels
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel centerPanel = new JPanel(new FlowLayout());

        ((FlowLayout)centerPanel.getLayout()).setHgap(20);
        ((FlowLayout)centerPanel.getLayout()).setVgap(20);


        JPanel keyGeneratorPanel = new JPanel(new GridLayout(0, 1));
        JPanel encrypt  = new JPanel(new GridLayout(0, 1));

        ((GridLayout)keyGeneratorPanel.getLayout()).setVgap(15);
        ((GridLayout)encrypt.getLayout()).setVgap(15);

        JPanel keys = new JPanel(new GridLayout(0,4));
        JPanel generate = new JPanel(new FlowLayout(FlowLayout.CENTER));

        ((GridLayout)keys.getLayout()).setHgap(15);
        ((GridLayout)keys.getLayout()).setVgap(10);

        JPanel inputAndOutput = new JPanel(new GridLayout(0,1));
        JPanel decryptAndEncryptButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        ((GridLayout)inputAndOutput.getLayout()).setVgap(10);
        ((GridLayout)inputAndOutput.getLayout()).setHgap(15);

        //create & assign elements for keys area
        keys.add(new JLabel("Private Key: "));
        keys.add(new JTextField("GENERATED PRIVATE KEY"));
        keys.add(new JTextField(" n: " + n));
        keys.add(new JTextField(" d: " + d));

        keys.add(new JLabel("Public Key: "));
        keys.add(new JTextField("GENERATED PUBLIC KEY"));
        keys.add(new JTextField(" n: " + n));
        keys.add(new JTextField(" e: " + e));

        //create & assign elements for generate area
        JButton generateButton = new JButton("GENERATE");
        generate.add(generateButton);

        //create & assign elements for inputAndOutput area
        JTextField inputText = new JTextField("INSERT TEXT");
        JLabel encryptedWord = new JTextField("ENCRYPTED");
        JLabel decryptedWord = new JTextField("DECRYPTED");

        inputAndOutput.add(inputText);
        inputAndOutput.add(new JLabel("Ecrypted word"));
        inputAndOutput.add(encryptedWord);
        inputAndOutput.add(new JLabel("Decrypted word"));
        inputAndOutput.add(decryptedWord);

        //create & assign elements for decryptAndEnctyptButton
        JButton encryptButton = new JButton("ENCRYPT");
        JButton decryptButton = new JButton("DECRYPT");

        decryptAndEncryptButtonPanel.add(encryptButton);
        decryptAndEncryptButtonPanel.add(decryptButton);

        // create & assign Borders
        Border etchedBorder = BorderFactory.createEtchedBorder();
        Border connectionBorder = BorderFactory.createTitledBorder(etchedBorder, "Key Generator");
        Border fileBorder = BorderFactory.createTitledBorder(etchedBorder, "Verschlüsselung");
        Border centerBorder = BorderFactory.createBevelBorder(BevelBorder.LOWERED);

        keyGeneratorPanel.setBorder(connectionBorder);
        encrypt.setBorder(fileBorder);
        centerPanel.setBorder(centerBorder);

        //set Button Functions
        encryptButton.setActionCommand(ENCRYPT_COMMAND);
        decryptButton.setActionCommand(DECRYPT_COMMAND);
        generateButton.setActionCommand(GENERATE_COMMAND);


        ActionListener buttonlistener = a -> {
            if (a.getActionCommand().equals(ENCRYPT_COMMAND)) {
                System.out.println("ENCRYPT");
                word = inputText.getText();
                inputText.setText(" ");
                encryptedWord.setText("THIS WORD HAS BEEN ENCRYPTED");
                System.out.println(word);
                decryptedWord.setText("DECRYPT");

            } else if (a.getActionCommand().equals(DECRYPT_COMMAND)) {
                System.out.println("DECRYPT");
                decryptedWord.setText("THIS WORD HAS BEEN DECRYPTED");
                encryptedWord.setText("ENCRYPT");

            }else if (a.getActionCommand().equals(GENERATE_COMMAND)){
                System.out.println("KEYS GENERATED");
            }
        };

        encryptButton.addActionListener(buttonlistener);
        decryptButton.addActionListener(buttonlistener);
        generateButton.addActionListener(buttonlistener);

        // combine Panels
        keyGeneratorPanel.add(keys);
        keyGeneratorPanel.add(generate);

        encrypt.add(inputAndOutput);
        encrypt.add(decryptAndEncryptButtonPanel);

        centerPanel.add(keyGeneratorPanel);
        centerPanel.add(encrypt);

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        this.setContentPane(mainPanel);

        // set JFrame behavior
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new UI();
    }
}
